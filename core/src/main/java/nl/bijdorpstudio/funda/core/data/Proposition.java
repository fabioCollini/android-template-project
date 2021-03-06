package nl.bijdorpstudio.funda.core.data;

import android.support.annotation.NonNull;

public class Proposition
{
    @NonNull
    private final House house;

    @NonNull
    private final Broker broker;

    public Proposition( @NonNull final House house, @NonNull final Broker broker )
    {
        this.house = house;
        this.broker = broker;
    }

    @NonNull
    public House getHouse()
    {
        return house;
    }

    @NonNull
    public Broker getBroker()
    {
        return broker;
    }
}
