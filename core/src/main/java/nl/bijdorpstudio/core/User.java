package nl.bijdorpstudio.core;

import android.support.annotation.NonNull;

public class User
{
    @NonNull
    private final String username;

    public User( @NonNull String username )
    {
        this.username = username;
    }

    public String toString()
    {
        return username;
    }
}