package nl.bijdorpstudio.network.data;

import com.google.gson.annotations.SerializedName;
import nl.bijdorpstudio.core.data.House;

public class JsonHouse
{
    @SerializedName( "AantalKamers" )
    private int numberOfRooms;

    @SerializedName( "GlobalId" )
    private long id;

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

    public House toCoreHouse()
    {
        return new House(id, address, postcode, city, numberOfRooms);
    }
}
