package nl.bijdorpstudio.funda.app.ui;

import nl.bijdorpstudio.funda.app.BuildConfig;
import nl.bijdorpstudio.funda.app.FundaApp;
import nl.bijdorpstudio.funda.app.inject.ActivityComponent;
import nl.bijdorpstudio.funda.app.inject.AppComponent;
import nl.bijdorpstudio.funda.core.Funda;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;
import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith( RobolectricGradleTestRunner.class )
@Config( constants = BuildConfig.class, sdk = 21 )
public class MainActivityTest
{
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private ActivityController<MainActivity> activityController;

    private MainActivity activity;

    @Mock
    private BrokersAdapter adapterMock;

    @Mock
    private Funda fundaMock;

    @Mock
    private ActivityComponent activityComponentMock;

    @Before
    public void setUp()
        throws Exception
    {
        //noinspection WrongConstant
        AppComponent appComponent =
            (AppComponent) RuntimeEnvironment.application.getSystemService( FundaApp.APP_INJECTION );
        when( appComponent.getActivityComponent() ).thenReturn( activityComponentMock );
        doAnswer( invocation -> {
            MainActivity activity1 = (MainActivity) invocation.getArguments()[0];
            activity1.funda = fundaMock;
            activity1.brokersAdapter = adapterMock;
            return null;
        } ).when( activityComponentMock ).inject( any( MainActivity.class ) );
        when( fundaMock.topBrokers( anyString(), anyInt() ) ).thenReturn( Observable.empty() );

        activityController = Robolectric.buildActivity( MainActivity.class );
        activity = activityController.create().get();
    }

    @Test
    public void ShouldAskFundaWithParameters_WhenCreated()
        throws Exception
    {

        verify( fundaMock ).topBrokers( "/Amstelveen", 10 );
    }
}