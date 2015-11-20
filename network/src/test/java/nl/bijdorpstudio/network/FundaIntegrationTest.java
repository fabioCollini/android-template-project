package nl.bijdorpstudio.network;

import android.util.Pair;
import nl.bijdorpstudio.core.data.Broker;
import nl.bijdorpstudio.network.data.JsonResponse;
import nl.bijdorpstudio.network.inject.DaggerFundaIntegrationComponent;
import nl.bijdorpstudio.network.inject.FundaIntegrationComponent;
import nl.bijdorpstudio.network.inject.NetworkModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FundaIntegrationTest
{

    private static final String SERVICE_KEY = "005e7c1d6f6c4f9bacac16760286e3cd";

    private FundaService fundaService;

    private Funda funda;

    @Before
    public void setUp()
        throws Exception
    {
        FundaIntegrationComponent component = DaggerFundaIntegrationComponent.builder().networkModule(
            new NetworkModule( "http://partnerapi.funda.nl", SERVICE_KEY ) ).build();

        fundaService = component.getFundaService();
        funda = component.getFunda();
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

    @Test
    public void ShouldFetchAndReturnTopTen()
        throws Exception
    {
        final List<Pair<Broker, Integer>> result = funda.topBrokers( "/Amstelveen", 10 ).toBlocking().first();

        assertThat( result ).hasSize( 10 );
        assertThat( result.get( 0 ).first ).isNotNull();
        assertThat( result.get( 0 ).second ).isGreaterThan( result.get( 1 ).second );
    }
}
