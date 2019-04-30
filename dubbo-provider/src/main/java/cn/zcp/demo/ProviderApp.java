package cn.zcp.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class ProviderApp {
    public static void main( String[] args ) throws Exception{

        ApplicationContext cp = new ClassPathXmlApplicationContext("classpath:application.xml");

        Object userServiceImpl = cp.getBean("userServiceImpl");

        System.in.read();
    }
}
