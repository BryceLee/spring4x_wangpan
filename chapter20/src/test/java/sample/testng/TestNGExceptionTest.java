package sample.testng;

import static org.testng.Assert.assertNotNull;

import com.smart.domain.User;

public class TestNGExceptionTest {
    private User user;

    @BeforeMethod
    public void init() {
        user = null;
    }

    @Test(enabled = true, expectedExceptions = NullPointerException.class)
    public void testUser() {
        assertNotNull(user.getUserName());
    }
}
