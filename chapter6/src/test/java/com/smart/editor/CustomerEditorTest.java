package com.smart.editor;

import static org.testng.Assert.assertNotNull;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomerEditorTest {
	public ApplicationContext ctx = null;

	private static String[] CONFIG_FILES = { "com/smart/editor/beans.xml" };

	@BeforeClass
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext(CONFIG_FILES);
	}

	@Test
	public void testCustomerCarEditor(){
		Boss boss = (Boss) ctx.getBean("boss");
		assertNotNull(boss);
	    System.out.println(boss);
	} 
	
}
