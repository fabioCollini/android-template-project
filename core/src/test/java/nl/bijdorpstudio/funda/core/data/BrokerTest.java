package nl.bijdorpstudio.funda.core.data;

import nl.bijdorpstudio.funda.core.network.Pagination;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BrokerTest
{

    private static final long ID = 132334234234234L;

    private Broker broker;

    @Before
    public void setUp()
    {
        broker = new Broker( ID, "broker" );
    }

    @Test
    public void ShouldReturnBrokerName_WhenToStringCalled()
    {
        assertThat( broker.toString() ).isEqualTo( "broker" );
    }

    @Test
    public void ShouldReturnCorrectHashBasedOnId_WhenHashAsked()
        throws Exception
    {
        assertThat( broker.hashCode() ).isEqualTo( (int) ( ID ^ ( ID >>> 32 ) ) );
    }

    @Test
    public void ShouldReturnFalse_WhenIdDoNotMatch()
        throws Exception
    {
        Broker broker1 = new Broker( 23, "test" );

        assertThat( broker.equals( broker1 ) ).isFalse();
    }

    @Test
    public void ShouldReturnTrue_WhenIdMatch()
        throws Exception
    {
        Broker broker1 = new Broker( ID, "test" );

        assertThat( broker.equals( broker1 ) ).isTrue();
    }

    @Test
    public void ShouldReturnTrue_WhenSameObject()
        throws Exception
    {
        //noinspection EqualsWithItself
        assertThat( broker.equals( broker ) ).isTrue();
    }

    @Test
    public void ShouldReturnFalse_WhenNull()
        throws Exception
    {
        //noinspection ObjectEqualsNull
        assertThat( broker.equals( null ) ).isFalse();
    }

    @Test
    public void ShouldReturnFalse_WhenNotBroker()
        throws Exception
    {
        //noinspection EqualsBetweenInconvertibleTypes
        assertThat( broker.equals( new Pagination( 2, 3 ) ) ).isFalse();
    }
}