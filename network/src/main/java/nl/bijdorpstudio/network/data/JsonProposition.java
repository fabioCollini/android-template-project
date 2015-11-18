package nl.bijdorpstudio.network.data;

import com.google.gson.annotations.SerializedName;
import nl.bijdorpstudio.core.data.Broker;
import nl.bijdorpstudio.core.data.House;
import nl.bijdorpstudio.core.data.Proposition;

public class JsonProposition
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

    public Proposition toCoreProposition()
    {
        return new Proposition( new House( id, address, postcode, city, numberOfRooms ),
                                new Broker( brokerId, brokerName ) );
    }
}
