package nl.bijdorpstudio.network;

import android.support.annotation.NonNull;
import android.util.Pair;
import nl.bijdorpstudio.core.data.Broker;
import nl.bijdorpstudio.core.data.Pagination;
import nl.bijdorpstudio.core.data.Proposition;
import nl.bijdorpstudio.network.data.JsonResponse;
import rx.Observable;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Funda
{
    @NonNull
    private final FundaService service;

    @NonNull
    private final String apiKey;

    @Inject
    public Funda( @NonNull final FundaService service, @NonNull final String apiKey )
    {
        this.service = service;
        this.apiKey = apiKey;
    }

    Observable<List<Pair<Broker, Integer>>> topBrokers( @NonNull final String search, final int topNumber )
    {
        return Observable.create( subscriber -> {
            Pagination pagination = new Pagination( -1, Integer.MAX_VALUE );

            Map<Broker, Integer> brokers = new HashMap<>();

            do
            {
                final JsonResponse jsonResponse =
                    service.queryData( apiKey, "koop", search, pagination.getNextPage(), 25 ).toBlocking().first();

                pagination = jsonResponse.getPagination();

                updateBrokersInformation( brokers, jsonResponse );

                if ( !subscriber.isUnsubscribed() )
                {
                    subscriber.onNext( getTopBrokers( brokers, topNumber ) );
                }
            }
            while ( !pagination.isLastPage() && !subscriber.isUnsubscribed() );

            if ( !subscriber.isUnsubscribed() )
            {
                subscriber.onCompleted();
            }
        } );
    }

    @NonNull
    private List<Pair<Broker, Integer>> getTopBrokers( @NonNull final Map<Broker, Integer> brokers,
                                                       final int topNumber )
    {
        PriorityQueue<Broker> queue =
            new PriorityQueue<>( brokers.size(), ( lhs, rhs ) -> brokers.get( rhs ).compareTo( brokers.get( lhs ) ) );

        Observable.from( brokers.keySet() ).forEach( queue::add );

        return Observable.from( queue ).map( broker -> new Pair<>( broker, brokers.get( broker ) ) ).take(
            topNumber ).toList().toBlocking().first();
    }

    private void updateBrokersInformation( Map<Broker, Integer> brokers, JsonResponse jsonResponse )
    {
        Observable.just( jsonResponse ).map( JsonResponse::getPropositions ).flatMapIterable(
            propositions -> propositions ).map( Proposition::getBroker ).forEach( broker -> {
            Integer number = brokers.get( broker );
            if ( number == null )
            {
                number = 0;
            }
            brokers.put( broker, number + 1 );
        } );
    }

}
