package nl.bijdorpstudio.funda.core.network;

import android.support.annotation.NonNull;
import nl.bijdorpstudio.funda.core.data.Proposition;

import java.util.List;

public class Response
{
    @NonNull
    private final Pagination pagination;

    @NonNull
    private final List<Proposition> propositions;

    public Response( @NonNull final List<Proposition> propositions, @NonNull final Pagination pagination )
    {
        this.propositions = propositions;
        this.pagination = pagination;
    }

    @NonNull
    public Pagination getPagination()
    {
        return pagination;
    }

    @NonNull
    public List<Proposition> getPropositions()
    {
        return propositions;
    }
}
