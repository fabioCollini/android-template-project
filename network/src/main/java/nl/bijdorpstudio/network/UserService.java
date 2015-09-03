package nl.bijdorpstudio.network;

import nl.bijdorpstudio.core.User;
import retrofit.http.GET;
import retrofit.http.Path;

public interface UserService
{
    @GET( "/users/{username}" )
    User queryUser( @Path( "username" ) String username );
}
