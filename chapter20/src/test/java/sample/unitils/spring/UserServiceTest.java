package sample.unitils.spring;


import static org.testng.Assert.assertNotNull;

import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.smart.service.UserService;

public class UserServiceTest extends UnitilsTestNG {

    @SpringApplicationContext({"smart-service.xml", "smart-dao.xml"})
    private ApplicationContext applicationContext;

    @SpringBean("userService")
    private UserService userService;

    @Test
    public void testLoadSpringBean() {
        assertNotNull(applicationContext);
        assertNotNull(userService.findUserByUserName("tom"));
    }
}
