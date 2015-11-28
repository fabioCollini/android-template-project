package nl.bijdorpstudio.funda.network.inject;

import dagger.Component;
import nl.bijdorpstudio.funda.network.FundaService;

import javax.inject.Singleton;

@Component( modules = NetworkModule.class )
@Singleton
public interface FundaIntegrationComponent
{
    FundaService getFundaService();
}
