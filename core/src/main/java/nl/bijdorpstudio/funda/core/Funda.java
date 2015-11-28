package nl.bijdorpstudio.funda.core;

import android.support.annotation.NonNull;
import android.util.Pair;
import nl.bijdorpstudio.funda.core.data.Broker;
import nl.bijdorpstudio.funda.core.network.Pagination;
import nl.bijdorpstudio.funda.core.data.Proposition;
import nl.bijdorpstudio.funda.core.network.Network;
import nl.bijdorpstudio.funda.core.network.Response;
import rx.Observable;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Funda
{
    @NonNull
    private final Network network;

    @NonNull
    private final String apiKey;

    @Inject
    public Funda( @NonNull final Network network, @NonNull final String apiKey )
    {
        this.network = network;
        this.apiKey = apiKey;
    }

    public Observable<List<Pair<Broker, Integer>>> topBrokers( @NonNull final String search, final int topNumber )
    {
        return Observable.create( subscriber -> {
            Pagination pagination = new Pagination( -1, Integer.MAX_VALUE );

            Map<Broker, Integer> brokers = new HashMap<>();

            do
            {
                final Response response =
                    network.queryData( apiKey, "koop", search, pagination.getNextPage(), 25 ).toBlocking().first();

                pagination = response.getPagination();

                updateBrokersInformation( brokers, response );

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

        Iterable<Broker> iterable = () -> new Iterator()
        {
            @Override
            public boolean hasNext()
            {
                return queue.size() > 0;
            }

            @Override
            public Broker next()
            {
                return queue.poll();
            }

            @Override
            public void remove()
            {

            }
        };

        return Observable.from( iterable ).map( broker -> new Pair<>( broker, brokers.get( broker ) ) ).take(
            topNumber ).toList().toBlocking().first();
    }

    private void updateBrokersInformation( Map<Broker, Integer> brokers, Response jsonResponse )
    {
        Observable.just( jsonResponse ).map( Response::getPropositions ).flatMapIterable(
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
