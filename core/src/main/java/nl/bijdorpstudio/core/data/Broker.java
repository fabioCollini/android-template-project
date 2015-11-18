package nl.bijdorpstudio.core.data;

import android.support.annotation.NonNull;

public class Broker
{
    @NonNull
    private final String brokerName;

    public Broker( @NonNull String brokerName )
    {
        this.brokerName = brokerName;
    }

    public String toString()
    {
        return brokerName;
    }
}