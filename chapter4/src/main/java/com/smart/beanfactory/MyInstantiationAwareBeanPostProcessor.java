package com.smart.beanfactory;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    /**
     * Apply this BeanPostProcessor before the target bean gets instantiated.
     * �ڹ��캯��֮ǰ����
     */
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("MyInstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation beanClass: " + beanClass);
        }
        return null;
    }

    /**
     * Perform operations after the bean has been instantiated, via a constructor or factory method,
     * but before Spring property population (from explicit properties or autowiring) occurs.
     * �ڹ��캯��֮�����
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("MyInstantiationAwareBeanPostProcessor.postProcessAfterInstantiation bean: " + bean);
        }
        return true;
    }

    /**
     * Post-process the given property values before the factory applies them to the given bean.
     * ����������֮ǰ����
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean,
            String beanName) throws BeansException {

        if ("car".equals(beanName)) {
            System.out.println("MyInstantiationAwareBeanPostProcessor.postProcessPropertyValues PropertyValues: " + pvs);
        }
        return pvs;
    }

    /**
     * Apply this BeanPostProcessor to the given new bean instance
     * before any bean initialization callbacks (like InitializingBean's afterPropertiesSet or a custom init-method).
     * The bean will already be populated with property values.
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("MyInstantiationAwareBeanPostProcessor.postProcessBeforeInitialization bean: " + bean);
        }
        return bean;
    }

    /**
     * Apply this BeanPostProcessor to the given new bean instance
     * after any bean initialization callbacks (like InitializingBean's afterPropertiesSet or a custom init-method).
     * The bean will already be populated with property values.
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("car".equals(beanName)) {
            System.out.println("MyInstantiationAwareBeanPostProcessor.postProcessAfterInitialization bean: " + bean);
        }
        return bean;
    }
}
