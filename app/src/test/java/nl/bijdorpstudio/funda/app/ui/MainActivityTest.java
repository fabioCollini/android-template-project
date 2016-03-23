package nl.bijdorpstudio.funda.app.ui;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import it.cosenonjaviste.daggermock.DaggerMockRule;
import nl.bijdorpstudio.funda.app.BuildConfig;
import nl.bijdorpstudio.funda.app.FundaApp;
import nl.bijdorpstudio.funda.app.inject.AppComponent;
import nl.bijdorpstudio.funda.core.Funda;
import nl.bijdorpstudio.funda.network.FundaService;
import nl.bijdorpstudio.funda.network.inject.NetworkModule;
import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith( RobolectricGradleTestRunner.class )
@Config( constants = BuildConfig.class, sdk = 21 )
public class MainActivityTest
{
    @Rule
    public DaggerMockRule<AppComponent> mockitoRule = new DaggerMockRule<>(AppComponent.class, new NetworkModule("", ""))
            .providesMock(FundaService.class)
            .set(c -> ((FundaApp) RuntimeEnvironment.application).setAppComponent(c));

    private ActivityController<MainActivity> activityController;

    private MainActivity activity;

    @Mock
    private Funda fundaMock;

    @Before
    public void setUp()
        throws Exception
    {
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