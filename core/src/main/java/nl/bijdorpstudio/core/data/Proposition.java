package nl.bijdorpstudio.core.data;

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
}
