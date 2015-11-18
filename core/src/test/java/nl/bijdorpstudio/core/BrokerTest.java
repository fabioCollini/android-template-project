package nl.bijdorpstudio.core;

import nl.bijdorpstudio.core.data.Broker;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BrokerTest
{
    private Broker broker;

    @Before
    public void setUp()
    {
        broker = new Broker( 12, "broker" );
    }

    @Test
    public void ShouldReturnBrokerName_WhenToStringCalled()
    {
        assertThat( broker.toString() ).isEqualTo( "broker" );
    }
}