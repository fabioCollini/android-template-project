package nl.bijdorpstudio.network.data;

import com.google.gson.annotations.SerializedName;
import nl.bijdorpstudio.core.House;

import java.util.List;

public class JsonResponse
{
    @SerializedName( "Objects" )
    public List<House> houses;
}
