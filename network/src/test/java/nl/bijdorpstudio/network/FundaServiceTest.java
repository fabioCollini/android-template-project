package nl.bijdorpstudio.network;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FundaServiceTest
{
    @Test
    public void PathKeyIsCorrect()
        throws Exception
    {
        assertThat( FundaService.KEY_PATH_PARAM ).isEqualTo( "key" );
    }

    @Test
    public void FeedPathIsCorrect()
        throws Exception
    {
        assertThat( FundaService.FEED_PATH ).isEqualTo( "/feeds/Aanbod.svc/json/{key}" );
    }

    @Test
    public void TypeQueryParamIsCorrect()
        throws Exception
    {
        assertThat( FundaService.TYPE_QUERY_PARAM ).isEqualTo( "type" );
    }

    @Test
    public void SearchQueryParamIsCorrect()
        throws Exception
    {
        assertThat( FundaService.SEARCH_QUERY_PARAM ).isEqualTo( "zo" );
    }

    @Test
    public void PageQueryParamIsCorrect()
        throws Exception
    {
        assertThat( FundaService.PAGE_QUERY_PARAM ).isEqualTo( "page" );
    }

    @Test
    public void PageSizeQueryParamIsCorrect()
        throws Exception
    {
        assertThat( FundaService.PAGESIZE_QUERY_PARAM ).isEqualTo( "pagesize" );
    }
}