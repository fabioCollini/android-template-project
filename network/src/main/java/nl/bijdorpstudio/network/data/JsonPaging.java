package nl.bijdorpstudio.network.data;

import com.google.gson.annotations.SerializedName;
import nl.bijdorpstudio.core.data.Pagination;

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
}
