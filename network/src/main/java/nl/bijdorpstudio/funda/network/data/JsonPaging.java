package nl.bijdorpstudio.funda.network.data;

import com.google.gson.annotations.SerializedName;
import nl.bijdorpstudio.funda.core.network.Pagination;

public class JsonPaging
{
    @SerializedName( "AantalPaginas" )
    private int numberOfPages;

    @SerializedName( "HuidigePagina" )
    private int currentPage;

    public Pagination toCorePagination()
    {
        return new Pagination( currentPage, numberOfPages );
    }

    public int getNumberOfPages()
    {
        return numberOfPages;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }
}
