package nl.bijdorpstudio.funda.network.data;

import com.google.gson.GsonBuilder;
import nl.bijdorpstudio.funda.core.data.Broker;
import nl.bijdorpstudio.funda.core.data.House;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonPropositionTest
{
    @Test
    public void ShouldDeserializeFromJson()
        throws Exception
    {
        long brokerId = 3;
        String brokerName = "Gans";

        final int houseId = 1;
        final int numberOfRooms = 5;
        String address = "Here";
        String postcode = "code";
        String city = "Ams";

        JsonProposition proposition =
            constructJsonProposition( houseId, numberOfRooms, address, postcode, city, brokerId, brokerName );

        assertThat( proposition.getBrokerId() ).isEqualTo( brokerId );
        assertThat( proposition.getBrokerName() ).isEqualTo( brokerName );
        assertThat( proposition.getHouseId() ).isEqualTo( houseId );
        assertThat( proposition.getNumberOfRooms() ).isEqualTo( numberOfRooms );
        assertThat( proposition.getAddress() ).isEqualTo( address );
        assertThat( proposition.getPostcode() ).isEqualTo( postcode );
        assertThat( proposition.getCity() ).isEqualTo( city );
    }

    private JsonProposition constructJsonProposition( long id, int numberOfRooms, String address, String postcode,
                                                      String city, long brokerId, String brokerName )
    {
        final String json = String.format(
            "{\"AantalKamers\":%d, \"Adres\":\"%s\", \"GlobalId\":%d, \"Postcode\":\"%s\", \"Woonplaats\":\"%s\", "
                + "\"MakelaarNaam\":\"%s\", \"MakelaarId\":%d}", numberOfRooms, address, id, postcode, city, brokerName,
            brokerId );
        return new GsonBuilder().create().fromJson( json, JsonProposition.class );
    }

    @Test
    public void ShouldConstructCoreHouse_WhenAsked()
        throws Exception
    {
        long brokerId = 3;
        String brokerName = "Peter";

        final int houseId = 43;
        final int numberOfRooms = 2;
        String address = "Adres";
        String postcode = "code";
        String city = "City";

        JsonProposition proposition =
            constructJsonProposition( houseId, numberOfRooms, address, postcode, city, brokerId, brokerName );
        House coreHouse = proposition.toCoreProposition().getHouse();

        assertThat( coreHouse.getId() ).isEqualTo( houseId );
        assertThat( coreHouse.getNumberOfRooms() ).isEqualTo( numberOfRooms );
        assertThat( coreHouse.getAddress() ).isEqualTo( address );
        assertThat( coreHouse.getPostcode() ).isEqualTo( postcode );
        assertThat( coreHouse.getCity() ).isEqualTo( city );
    }

    @Test
    public void ShouldConstructCoreBroker_WhenAsked()
        throws Exception
    {
        long brokerId = 3;
        String brokerName = "Peter";

        final int houseId = 43;
        final int numberOfRooms = 2;
        String address = "Adres";
        String postcode = "code";
        String city = "City";

        JsonProposition proposition =
            constructJsonProposition( houseId, numberOfRooms, address, postcode, city, brokerId, brokerName );
        Broker coreBroker = proposition.toCoreProposition().getBroker();

        assertThat( coreBroker.getId() ).isEqualTo( brokerId );
        assertThat( coreBroker.getName() ).isEqualTo( brokerName );
    }

    @Test
    public void ShouldReturnUnknown_WhenBrokerNameIsNull()
        throws Exception
    {
        final String json =
            "{\"AantalKamers\":2, \"Adres\":\"\", \"GlobalId\":2, \"Postcode\":\"\", \"Woonplaats\":\"\", "
                + "\"MakelaarNaam\":null, \"MakelaarId\":4}";
        final JsonProposition proposition = new GsonBuilder().create().fromJson( json, JsonProposition.class );

        assertThat( proposition.getBrokerName() ).isEqualTo( "Unknown" );
    }
}