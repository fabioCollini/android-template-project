package nl.bijdorpstudio.funda.app.inject;

import dagger.Subcomponent;
import nl.bijdorpstudio.funda.app.ui.BrokersAdapter;
import nl.bijdorpstudio.network.Funda;

@Subcomponent
public interface ActivityComponent
{
    BrokersAdapter createBrokersAdapter();

    Funda getFundaService();
}
