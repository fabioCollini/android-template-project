package nl.bijdorpstudio.funda.network;

import android.support.annotation.NonNull;
import nl.bijdorpstudio.funda.core.network.Network;
import nl.bijdorpstudio.funda.core.network.Response;
import nl.bijdorpstudio.funda.network.data.JsonResponse;
import rx.Observable;

/**
 * (C) Koninklijke Philips N.V., 2015.
 * All rights reserved.
 */
public class NetworkImpl
    implements Network
{
    @NonNull
    private final FundaService fundaService;

    @NonNull
    private final String apiKey;

    public NetworkImpl( @NonNull FundaService fundaService, @NonNull final String apiKey )
    {
        this.fundaService = fundaService;
        this.apiKey = apiKey;
    }

    @NonNull
    @Override
    public Observable<Response> queryData( @NonNull final String queryType, @NonNull final String queryString,
                                           final int pageIndex, final int pageSize )
    {
        return fundaService.queryData( apiKey, queryType, queryString, pageIndex, pageSize ).map(
            JsonResponse::toCoreResponse );
    }
}
