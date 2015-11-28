package nl.bijdorpstudio.funda.network.data;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import nl.bijdorpstudio.funda.core.data.Proposition;
import nl.bijdorpstudio.funda.core.network.Response;
import rx.Observable;

import java.util.List;

public class JsonResponse
{
    @SerializedName( "Objects" )
    private List<JsonProposition> propositions;

    @SerializedName( "Paging" )
    private JsonPaging pagination;

    @SerializedName( "TotaalAantalObjecten" )
    private int numberOfObjects;

    private List<Proposition> getPropositions()
    {
        return Observable.from( propositions ).map( JsonProposition::toCoreProposition ).toList().toBlocking().first();
    }

    public int getNumberOfPropositions()
    {
        return numberOfObjects;
    }

    @NonNull
    public Response toCoreResponse()
    {
        return new Response( getPropositions(), pagination.toCorePagination() );
    }
}
