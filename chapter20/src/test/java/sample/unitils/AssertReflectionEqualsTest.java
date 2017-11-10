package sample.unitils;

import static org.unitils.reflectionassert.ReflectionAssert.assertLenientEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.util.Calendar;

import com.smart.domain.User;

public class AssertReflectionEqualsTest {

    @Test
    public void testLenientEquals() {
        Integer orderList1[] = new Integer[] {1, 2, 3};
        Integer orderList2[] = new Integer[] {3, 2, 1};


        //① 测试两个数组的值是否相等，忽略顺序
        //assertReflectionEquals(orderList1, orderList2,LENIENT_ORDER);
        assertLenientEquals(orderList1, orderList2);


        //② 测试两个对象的值是否相等，忽略时间值是否相等
        User user1 = new User("tom", "1234");
        Calendar cal1 = Calendar.getInstance();
        // user1.setLastVisit(cal1.getTime());
        User user2 = new User("tom", "1234");
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.DATE, 15);
        //user2.setLastVisit(cal2.getTime());
        //assertReflectionEquals(user1, user2, ReflectionComparatorMode.LENIENT_DATES);
        assertLenientEquals(user1, user2);

    }

    @Test
    public void testReflection() {
        User user1 = new User("tom", "1234");
        User user2 = new User("tom", "1234");
        assertReflectionEquals(user1, user2);
    }



}
