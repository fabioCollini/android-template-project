package nl.bijdorpstudio.network;

import nl.bijdorpstudio.network.data.JsonResponse;
import nl.bijdorpstudio.network.inject.DaggerFundaIntegrationComponent;
import nl.bijdorpstudio.network.inject.FundaIntegrationComponent;
import nl.bijdorpstudio.network.inject.NetworkModule;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FundaIntegrationTest
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
        assertThat( response.getPagination() ).isNotNull();
        assertThat( response.getPropositions().size() ).isNotZero();
    }

}
