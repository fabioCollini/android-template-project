package nl.bijdorpstudio.funda.network;

import nl.bijdorpstudio.funda.core.network.Response;
import nl.bijdorpstudio.funda.network.data.JsonResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import rx.Observable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class NetworkImplTest
{
    private static final String API_KEY = "testdsdfsdfs";

    private NetworkImpl network;

    @Mock
    private FundaService fundaServiceMock;

    @Mock
    private JsonResponse jsonResponseMock;

    @Mock
    private Response coreResponseMock;

    @Before
    public void setUp()
        throws Exception
    {
        initMocks( this );

        network = new NetworkImpl( fundaServiceMock, API_KEY );
        when( fundaServiceMock.queryData( anyString(), anyString(), anyString(), anyInt(), anyInt() ) ).thenReturn(
            Observable.just( jsonResponseMock ) );
    }

    @Test
    public void ShouldPassApiKeyToService()
        throws Exception
    {
        network.queryData( "test", "ok", 1, 2 );

        verify( fundaServiceMock ).queryData( eq( API_KEY ), anyString(), anyString(), anyInt(), anyInt() );
    }

    @Test
    public void ShouldPassQueryParametersToService()
        throws Exception
    {
        final String queryType = "test";
        final String queryString = "ok";
        final int pageIndex = 1;
        final int pageSize = 2;

        network.queryData( queryType, queryString, pageIndex, pageSize );

        verify( fundaServiceMock ).queryData( anyString(), eq( queryType ), eq( queryString ), eq( pageIndex ),
                                              eq( pageSize ) );
    }

    @Test
    public void ShouldTransformJsonResponseToCoreResponse()
        throws Exception
    {
        when( jsonResponseMock.toCoreResponse() ).thenReturn( coreResponseMock );

        final Response response = network.queryData( "", "", 1, 1 ).toBlocking().first();

        assertThat( response ).isSameAs( coreResponseMock );
    }
}