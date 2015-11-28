package nl.bijdorpstudio.funda.core.data;

import android.support.annotation.NonNull;

public class House
{
    private final long id;

    @NonNull
    private final String address;

    @NonNull
    private final String postcode;

    @NonNull
    private final String city;

    private final int numberOfRooms;

    public House( final long id, @NonNull final String address, @NonNull final String postcode,
                  @NonNull final String city, final int numberOfRooms )
    {
        this.id = id;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.numberOfRooms = numberOfRooms;
    }

    public long getId()
    {
        return id;
    }

    public int getNumberOfRooms()
    {
        return numberOfRooms;
    }

    @NonNull
    public String getAddress()
    {
        return address;
    }

    @NonNull
    public String getPostcode()
    {
        return postcode;
    }

    @NonNull
    public String getCity()
    {
        return city;
    }
}
