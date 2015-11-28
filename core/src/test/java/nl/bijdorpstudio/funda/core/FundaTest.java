package nl.bijdorpstudio.funda.core;

import android.util.Pair;
import nl.bijdorpstudio.funda.core.data.Broker;
import nl.bijdorpstudio.funda.core.data.House;
import nl.bijdorpstudio.funda.core.data.Proposition;
import nl.bijdorpstudio.funda.core.network.Network;
import nl.bijdorpstudio.funda.core.network.Pagination;
import nl.bijdorpstudio.funda.core.network.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith( RobolectricGradleTestRunner.class )
@Config( constants = BuildConfig.class, sdk = 21 )
public class FundaTest
{
    private static final String SERVICE_KEY = "005e7c1d6f6c4f9bacac16760286e3cd";

    @Mock
    private Network networkMock;

    private Funda funda;

    @Before
    public void setUp()
        throws Exception
    {
        initMocks( this );

        funda = new Funda( networkMock, SERVICE_KEY );
    }

    @Test
    public void ShouldFetchAndReturnTopTen()
        throws Exception
    {
        Observable<Response> response = constructResponseWithTwoPropositions();
        when( networkMock.queryData( anyString(), anyString(), anyString(), anyInt(), anyInt() ) ).thenReturn(
            response );

        final List<Pair<Broker, Integer>> result = funda.topBrokers( "/Amstelveen", 10 ).toBlocking().first();

        assertThat( result ).hasSize( 2 );
        assertThat( result.get( 0 ).first ).isNotNull();
        assertThat( result.get( 0 ).second ).isGreaterThan( result.get( 1 ).second );
    }

    private Observable<Response> constructResponseWithTwoPropositions()
    {
        final Broker broker1 = new Broker( 1, "Broker1" );
        final Broker broker2 = new Broker( 2, "Broker2" );
        final House house1 = new House( 1, "Test1", "Test1", "Test1", 3 );
        final House house2 = new House( 2, "Test2", "Test2", "Test2", 3 );
        final House house3 = new House( 3, "Test3", "Test3", "Test3", 3 );
        final Proposition proposition1 = new Proposition( house1, broker1 );
        final Proposition proposition2 = new Proposition( house2, broker1 );
        final Proposition proposition3 = new Proposition( house3, broker2 );
        return Observable.just(
            new Response( Arrays.asList( proposition1, proposition2, proposition3 ), new Pagination( 1, 4 ) ) );
    }
}
