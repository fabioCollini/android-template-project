package nl.bijdorpstudio.network.util;


import nl.bijdorpstudio.core.data.Broker;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValueComparatorTest
{
    private ValueComparator<Broker, Integer> comparator;

    private Map<Broker, Integer> originalMap;

    @Before
    public void setUp()
        throws Exception
    {
        originalMap = new HashMap<>();
        comparator = new ValueComparator<>( originalMap );
    }

    @Test
    public void ShouldReturnPositive_WhenFirstValueIsBigger()
        throws Exception
    {
        final Broker broker1 = new Broker( 23, "test1" );
        final Broker broker2 = new Broker( 4, "test2" );
        originalMap.put( broker1, 24 );
        originalMap.put( broker2, 2 );

        assertThat( comparator.compare( broker1, broker2 ) ).isGreaterThan( 0 );
    }

    @Test
    public void ShouldReturnNegative_WhenFirstValueIsSmaller()
        throws Exception
    {
        final Broker broker1 = new Broker( 1, "test1" );
        final Broker broker2 = new Broker( 4, "test2" );
        originalMap.put( broker1, 2 );
        originalMap.put( broker2, 45 );

        assertThat( comparator.compare( broker1, broker2 ) ).isLessThan( 0 );
    }

    @Test
    public void ShouldReturnZero_WhenValuesAreEqual()
        throws Exception
    {
        final Broker broker1 = new Broker( 1, "test1" );
        final Broker broker2 = new Broker( 4, "test2" );
        originalMap.put( broker1, 4 );
        originalMap.put( broker2, 4 );

        assertThat( comparator.compare( broker1, broker2 ) ).isZero();
    }
}