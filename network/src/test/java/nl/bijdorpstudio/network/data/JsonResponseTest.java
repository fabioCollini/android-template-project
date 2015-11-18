package nl.bijdorpstudio.network.data;

import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonResponseTest
{
    private JsonResponse constructJsonResponse( int numberOfRooms )
    {
        final String json =
            String.format( "{\"Objects\":[], \"Paging\":{}, \"TotaalAantalObjecten\":%d}", numberOfRooms );
        return new GsonBuilder().create().fromJson( json, JsonResponse.class );
    }

    @Test
    public void ShouldDeserializeFromJson()
        throws Exception
    {
        final int totalObjects = 1;

        JsonResponse response = constructJsonResponse( totalObjects );

        assertThat( response.getPagination() ).isNotNull();
        assertThat( response.getPropositions() ).hasSize( 0 );
        assertThat( response.getNumberOfPropositions() ).isEqualTo( totalObjects );
    }
}