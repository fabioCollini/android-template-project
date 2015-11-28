package nl.bijdorpstudio.funda.app;

import android.app.Application;
import nl.bijdorpstudio.funda.app.inject.AppComponent;
import nl.bijdorpstudio.funda.app.inject.DaggerAppComponent;
import nl.bijdorpstudio.funda.network.inject.NetworkModule;

public class FundaApp
    extends Application
{

    public static final String APP_INJECTION = "APP_INJECTION";

    private AppComponent component;

    @Override
    public void onCreate()
    {
        super.onCreate();

        initInjection();
    }

    private void initInjection()
    {
        if ( component == null )
        {
            final NetworkModule networkModule =
                new NetworkModule( getString( R.string.funda_url ), getString( R.string.funda_key ) );

            component = DaggerAppComponent.builder().networkModule( networkModule ).build();
        }
    }

    @Override
    public Object getSystemService( String name )
    {
        if ( APP_INJECTION.equals( name ) )
        {
            return component;
        }

        return super.getSystemService( name );
    }
}
