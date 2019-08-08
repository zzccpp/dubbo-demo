package cn.zcp.demo.service;

import cn.zcp.demo.bean.User;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-07 15:49
 * @describe dubbo-demo
 *
 * 定义一个接口
 */
public interface HelloService {

    String sayHello(String name);

    /**
     * 定义获取实体封装对象
     * @param age
     * @return
     */
    User getUser(Integer age);

}
