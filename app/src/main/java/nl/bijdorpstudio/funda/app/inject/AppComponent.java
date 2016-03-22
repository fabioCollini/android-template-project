package nl.bijdorpstudio.funda.app.inject;

import dagger.Component;
import nl.bijdorpstudio.funda.network.inject.NetworkModule;

import javax.inject.Singleton;

@Singleton
@Component( modules = NetworkModule.class )
public interface AppComponent
{
    ActivityComponent getActivityComponent();
}
