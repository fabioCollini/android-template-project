package nl.bijdorpstudio.network;

import nl.bijdorpstudio.network.data.JsonResponse;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface FundaService
{
    @GET( "/feeds/Aanbod.svc/json/{key}" )
    Observable<JsonResponse> queryData( @Path( "key" ) String key, @Query( "type" ) String type,
                                       @Query( "zo" ) String search, @Query( "page" ) int page,
                                       @Query( "pagesize" ) int pageSize );
}
