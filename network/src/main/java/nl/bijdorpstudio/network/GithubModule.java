package nl.bijdorpstudio.network;

import dagger.Module;
import retrofit.Retrofit;

import javax.inject.Singleton;

@Module
public class GithubModule
{
    private final String baseUrl;

    public GithubModule( String url )
    {
        this.baseUrl = url;
    }

    @Singleton
    public UserService provideUserService()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl( baseUrl ).build();

        return retrofit.create( UserService.class );
    }
}
