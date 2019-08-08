package cn.zcp.demo.service.impl;

import cn.zcp.demo.Annotation.RpcServer;
import cn.zcp.demo.bean.User;
import cn.zcp.demo.service.HelloService;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-07 15:52
 * @describe dubbo-demo <描述>
 */
@RpcServer(value = HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {

        return "Hi,"+name;
    }

    @Override
    public User getUser(Integer age) {
        User user = new User();
        user.setUserName("张三"+age);
        user.setAge(age);
        return user;
    }
}
