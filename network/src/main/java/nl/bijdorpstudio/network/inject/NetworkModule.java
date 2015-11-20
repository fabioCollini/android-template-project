package nl.bijdorpstudio.network.inject;

import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import nl.bijdorpstudio.network.FundaService;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

import javax.inject.Singleton;

@Module
public class NetworkModule
{
    @NonNull
    private final String baseUrl;

    @NonNull
    private final String serviceKey;

    public NetworkModule( @NonNull final String url, @NonNull final String serviceKey )
    {
        this.baseUrl = url;
        this.serviceKey = serviceKey;
    }

    @Singleton
    @Provides
    @NonNull
    public FundaService provideUserService()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl( baseUrl ).addConverterFactory(
            GsonConverterFactory.create() ).addCallAdapterFactory( RxJavaCallAdapterFactory.create() ).build();

        return retrofit.create( FundaService.class );
    }

    @Provides
    @NonNull
    public String getServiceKey()
    {
        return serviceKey;
    }
}