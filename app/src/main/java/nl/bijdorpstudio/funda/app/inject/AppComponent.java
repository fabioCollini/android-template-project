package nl.bijdorpstudio.funda.app.inject;

import javax.inject.Singleton;

import dagger.Component;
import nl.bijdorpstudio.funda.app.ui.MainActivity;
import nl.bijdorpstudio.funda.network.inject.NetworkModule;

@Singleton
@Component( modules = NetworkModule.class )
public interface AppComponent
{

    void inject( MainActivity mainActivity );
}
