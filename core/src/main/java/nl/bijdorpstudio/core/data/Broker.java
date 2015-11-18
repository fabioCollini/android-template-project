package nl.bijdorpstudio.core.data;

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
}