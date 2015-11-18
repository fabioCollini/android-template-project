package nl.bijdorpstudio.core.data;

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
}
