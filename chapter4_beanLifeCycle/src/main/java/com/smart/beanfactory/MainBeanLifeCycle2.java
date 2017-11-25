package com.smart.beanfactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.model.Car;

/**
 * @author CYH
 * @date 2017/11/13
 */
public class MainBeanLifeCycle2 {


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans2.xml");
        Car car = (Car) ctx.getBean("car");
        car.introduce();
    }

}
