package nl.bijdorpstudio.funda.network;

import nl.bijdorpstudio.funda.network.data.JsonResponse;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface FundaService
{

    String KEY_PATH_PARAM = "key";
    String FEED_PATH = "/feeds/Aanbod.svc/json/{" + KEY_PATH_PARAM + "}";
    String TYPE_QUERY_PARAM = "type";
    String SEARCH_QUERY_PARAM = "zo";
    String PAGE_QUERY_PARAM = "page";
    String PAGESIZE_QUERY_PARAM = "pagesize";

    @GET( FEED_PATH )
    Observable<JsonResponse> queryData( @Path( KEY_PATH_PARAM ) String key, @Query( TYPE_QUERY_PARAM ) String type,
                                        @Query( SEARCH_QUERY_PARAM ) String search, @Query( PAGE_QUERY_PARAM ) int page,
                                        @Query( PAGESIZE_QUERY_PARAM ) int pageSize );
}
