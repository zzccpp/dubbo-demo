package cn.zcp.demo.api.impl;

import cn.zcp.demo.api.UserService;
import cn.zcp.demo.bean.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-04-28 17:43
 * @describe dubbo-demo 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    static List<User> users = new ArrayList<>();
    static{
        users.add(new User(1,"zhangsan",23));
        users.add(new User(2,"lisi",21));
        users.add(new User(3,"wangwu",25));
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUserById(int id) {
        if(id<0||id>3) return null;
        return users.get(id);
    }

    @Override
    public void deleteUser(int id) {
        if(id<0||id>3){
            System.out.println("未查询到相关用户!");
            return;
        }
        System.out.println("删除用户成功!");
    }
}
