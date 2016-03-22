package nl.bijdorpstudio.funda.app;

import nl.bijdorpstudio.funda.app.inject.AppComponent;

import static org.mockito.Mockito.mock;

// This class will be used by Robolectric
@SuppressWarnings( "unused" )
public class TestFundaApp
    extends FundaApp
{
    private AppComponent appComponentMock = mock( AppComponent.class );

    @Override
    public void onCreate()
    {
        setAppComponent( appComponentMock );

        super.onCreate();
    }
}
