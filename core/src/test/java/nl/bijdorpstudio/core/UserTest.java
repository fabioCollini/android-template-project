package nl.bijdorpstudio.core;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest
{
    private User user;

    @Before
    public void setUp()
    {
        user = new User( "user" );
    }

    @Test
    public void ShouldReturnUserName_WhenToStringCalled()
    {
        assertThat( user.toString() ).isEqualTo( "user" );
    }
}