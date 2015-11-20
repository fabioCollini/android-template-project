package nl.bijdorpstudio.network.inject;

import dagger.Component;
import nl.bijdorpstudio.network.Funda;
import nl.bijdorpstudio.network.FundaService;

import javax.inject.Singleton;

@Component( modules = NetworkModule.class )
@Singleton
public interface FundaIntegrationComponent
{
    FundaService getFundaService();

    Funda getFunda();
}
