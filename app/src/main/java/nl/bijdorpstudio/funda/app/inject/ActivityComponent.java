package nl.bijdorpstudio.funda.app.inject;

import dagger.Subcomponent;
import nl.bijdorpstudio.funda.app.ui.MainActivity;

@Subcomponent
public interface ActivityComponent
{
    void inject( MainActivity mainActivity );
}
