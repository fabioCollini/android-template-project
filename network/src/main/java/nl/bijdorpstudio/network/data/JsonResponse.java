package nl.bijdorpstudio.network.data;

import com.google.gson.annotations.SerializedName;
import nl.bijdorpstudio.core.data.Pagination;
import nl.bijdorpstudio.core.data.Proposition;
import rx.Observable;

import java.util.List;

public class JsonResponse
{
    @SerializedName( "Objects" )
    private List<JsonProposition> houses;

    @SerializedName( "Paging" )
    private JsonPaging pagination;

    @SerializedName( "TotaalAantalObjecten" )
    private int numberOfObjects;

    public Pagination getPagination()
    {
        return pagination.toCorePagination();
    }

    public List<Proposition> getPropositions()
    {
        return Observable.from( houses ).map( JsonProposition::toCoreProposition ).toList().toBlocking().first();
    }
}
