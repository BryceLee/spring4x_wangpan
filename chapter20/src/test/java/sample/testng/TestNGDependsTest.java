
package sample.testng;

import static org.testng.Assert.assertNotNull;

import com.smart.domain.User;


public class TestNGDependsTest {

    @Test
    public void testMethod1() {}

    @Test
    public void testMethod2() {
        assertNotNull(new User());
    }

    @Test(dependsOnMethods = {"testMethod1", "testMethod2"}, alwaysRun = true)
    public void testMethod3() {

    }

}
