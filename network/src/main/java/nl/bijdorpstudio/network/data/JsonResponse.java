package nl.bijdorpstudio.network.data;

import com.google.gson.annotations.SerializedName;
import nl.bijdorpstudio.core.data.House;
import nl.bijdorpstudio.core.data.Pagination;
import rx.Observable;

import java.util.List;

public class JsonResponse
{
    @SerializedName( "Objects" )
    private List<JsonHouse> houses;

    @SerializedName( "Paging" )
    private JsonPaging pagination;

    @SerializedName( "TotaalAantalObjecten" )
    private int numberOfObjects;

    public Pagination getPagination()
    {
        return pagination.toCorePagination();
    }

    public List<House> getHouses()
    {
        return Observable.from( houses ).map( JsonHouse::toCoreHouse ).toList().toBlocking().first();
    }
}
