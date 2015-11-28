package nl.bijdorpstudio.funda.network.inject;

import nl.bijdorpstudio.funda.network.FundaService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NetworkModuleTest
{

    private static final String SERVICE_KEY = "fjffh";

    private NetworkModule module;

    @Before
    public void setUp()
        throws Exception
    {
        module = new NetworkModule( "http:/google.com", SERVICE_KEY );
    }

    @Test
    public void ShouldReturnProvidedServiceKey()
        throws Exception
    {
        assertThat( module.getServiceKey() ).isEqualTo( SERVICE_KEY );
    }

    @Test
    public void ShouldConstructFundaService()
        throws Exception
    {
        final FundaService fundaService = module.provideUserService();

        assertThat( fundaService ).isNotNull();
    }
}