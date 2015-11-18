package nl.bijdorpstudio.network.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import nl.bijdorpstudio.core.data.Broker;
import nl.bijdorpstudio.core.data.House;
import nl.bijdorpstudio.core.data.Proposition;

public class JsonProposition
{
    @SerializedName( "AantalKamers" )
    private int numberOfRooms;

    @SerializedName( "GlobalId" )
    private long houseId;

    @SerializedName( "Adres" )
    private String address;

    @SerializedName( "Postcode" )
    private String postcode;

    @SerializedName( "Woonplaats" )
    private String city;

    @SerializedName( "MakelaarNaam" )
    private String brokerName;

    @SerializedName( "MakelaarId" )
    private long brokerId;

    public Proposition toCoreProposition()
    {
        return new Proposition( new House( houseId, address, postcode, city, numberOfRooms ),
                                new Broker( brokerId, brokerName ) );
    }

    public long getBrokerId()
    {
        return brokerId;
    }

    @Nullable
    public String getBrokerName()
    {
        return brokerName;
    }

    public long getHouseId()
    {
        return houseId;
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

    public int getNumberOfRooms()
    {
        return numberOfRooms;
    }
}
