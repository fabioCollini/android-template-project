package nl.bijdorpstudio.funda.core.network;

import android.support.annotation.NonNull;
import rx.Observable;

public interface Network
{
    @NonNull
    Observable<Response> queryData( @NonNull String queryType, @NonNull String queryString, int pageIndex,
                                    int pageSize );
}
