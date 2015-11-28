package nl.bijdorpstudio.funda.network.data;

import com.google.gson.GsonBuilder;
import nl.bijdorpstudio.funda.core.network.Response;
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

        assertThat( response.getNumberOfPropositions() ).isEqualTo( totalObjects );

        final Response coreResponse = response.toCoreResponse();
        assertThat( coreResponse.getPagination() ).isNotNull();
        assertThat( coreResponse.getPropositions() ).hasSize( 0 );
    }
}