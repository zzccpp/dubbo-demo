package cn.zcp.demo;

import cn.zcp.demo.api.UserService;
import cn.zcp.demo.bean.User;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class ConsumerApp {
    public static void main( String[] args ) throws Exception {

        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:application.xml");

        //获取远程代理服务对象
        UserService userService = (UserService) ac.getBean("userService");
        //获取所有users
        List<User> users = userService.getUsers();
        for (User user : users) {
            System.out.println(user);
        }
        //获取用户
        User user = userService.getUserById(2);
        System.out.println("获取id=2的用户:"+user);
        user = userService.getUserById(0);
        System.out.println("获取id=0的用户:"+user);
        //删除用户
        userService.deleteUser(2);

        System.in.read();
    }
}
