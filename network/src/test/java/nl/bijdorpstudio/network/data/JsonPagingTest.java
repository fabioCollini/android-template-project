package nl.bijdorpstudio.network.data;


import com.google.gson.GsonBuilder;
import nl.bijdorpstudio.core.data.Pagination;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonPagingTest
{
    @Test
    public void ShouldDeserializeFromJson()
        throws Exception
    {
        final int numberOfPages = 5;
        final int currentPage = 1;

        JsonPaging paging = constructJsonPagination( numberOfPages, currentPage );

        assertThat( paging.getNumberOfPages() ).isEqualTo( numberOfPages );
        assertThat( paging.getCurrentPage() ).isEqualTo( currentPage );
    }

    private JsonPaging constructJsonPagination( int numberOfPages, int currentPage )
    {
        final String json =
            String.format( "{\"AantalPaginas\":%1d, \"HuidigePagina\":%2d}", numberOfPages, currentPage );
        return new GsonBuilder().create().fromJson( json, JsonPaging.class );
    }

    @Test
    public void ShouldConstructCorePagination_WhenAsked()
        throws Exception
    {
        final int numberOfPages = 45;
        final int currentPage = 10;

        JsonPaging paging = constructJsonPagination( numberOfPages, currentPage );
        Pagination corePagination = paging.toCorePagination();

        assertThat( corePagination.getNumberOfPages() ).isEqualTo( numberOfPages );
        assertThat( corePagination.getCurrentPage() ).isEqualTo( currentPage );
    }
}