package nl.bijdorpstudio.funda.network;

import nl.bijdorpstudio.funda.core.network.Response;
import nl.bijdorpstudio.funda.network.data.JsonResponse;
import nl.bijdorpstudio.funda.network.inject.DaggerFundaIntegrationComponent;
import nl.bijdorpstudio.funda.network.inject.FundaIntegrationComponent;
import nl.bijdorpstudio.funda.network.inject.NetworkModule;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FundaNetworkIntegrationTest
{
    private static final String SERVICE_KEY = "005e7c1d6f6c4f9bacac16760286e3cd";

    private FundaService fundaService;

    @Before
    public void setUp()
        throws Exception
    {
        FundaIntegrationComponent component = DaggerFundaIntegrationComponent.builder().networkModule(
            new NetworkModule( "http://partnerapi.funda.nl", SERVICE_KEY ) ).build();

        fundaService = component.getFundaService();
    }

    @Test
    public void ShouldContactSoundCloud_WhenUserQueried()
        throws Exception
    {
        JsonResponse response =
            fundaService.queryData( SERVICE_KEY, "koop", "/Amstelveen", 0, 20 ).toBlocking().first();

        assertThat( response ).isNotNull();
        final Response coreResponse = response.toCoreResponse();
        assertThat( coreResponse.getPagination() ).isNotNull();
        assertThat( coreResponse.getPropositions().size() ).isNotZero();
    }
}
