package nl.bijdorpstudio.core.data;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaginationTest
{
    @Test
    public void ShouldReturnCorrectNextPage()
        throws Exception
    {
        Pagination pagination = new Pagination( 1, 123 );

        assertThat( pagination.getNextPage() ).isEqualTo( 2 );
    }

    @Test
    public void ShouldReportLastPage_WhenItIs()
        throws Exception
    {
        Pagination pagination = new Pagination( 1, 2 );

        assertThat( pagination.isLastPage() ).isTrue();
    }

    @Test
    public void ShouldReportNotLastPage_WhenThereAreMore()
        throws Exception
    {
        Pagination pagination = new Pagination( 1, 34 );

        assertThat( pagination.isLastPage() ).isFalse();
    }
}