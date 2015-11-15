package nl.bijdorpstudio.core;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BrokerTest
{
    private Broker broker;

    @Before
    public void setUp()
    {
        broker = new Broker( "broker" );
    }

    @Test
    public void ShouldReturnBrokerName_WhenToStringCalled()
    {
        assertThat( broker.toString() ).isEqualTo( "broker" );
    }
}