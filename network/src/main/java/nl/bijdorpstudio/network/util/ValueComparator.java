package nl.bijdorpstudio.network.util;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator<K, V extends Comparable<V>>
    implements Comparator<K>
{
    private final Map<K, V> mapWithValues;

    public ValueComparator( Map<K, V> mapWithValues )
    {
        this.mapWithValues = mapWithValues;
    }

    @Override
    public int compare( K lhs, K rhs )
    {
        return mapWithValues.get( lhs ).compareTo( mapWithValues.get( rhs ) );
    }
}
