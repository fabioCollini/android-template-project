package nl.bijdorpstudio.funda.core.data;

import android.support.annotation.NonNull;

public class Broker
{
    @NonNull
    private final String brokerName;

    private final long id;

    public Broker( final long id, @NonNull final String brokerName )
    {
        this.id = id;
        this.brokerName = brokerName;
    }

    public String toString()
    {
        return brokerName;
    }

    public long getId()
    {
        return id;
    }

    @NonNull
    public String getName()
    {
        return brokerName;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        Broker broker = (Broker) o;

        return id == broker.id;
    }

    @Override
    public int hashCode()
    {
        return (int) ( id ^ ( id >>> 32 ) );
    }
}