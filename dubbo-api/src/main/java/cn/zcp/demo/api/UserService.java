package cn.zcp.demo.api;

import cn.zcp.demo.bean.User;

import java.util.List;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-04-28 17:34
 * @describe dubbo-demo 用户处理服务接口
 */
public interface UserService {

    /**
     * 获取所有用户信息
     * @return
     */
    List<User> getUsers();

    /**
     * 根据用户id查找用户
     * @param id 用户id
     * @return
     */
    User getUserById(int id);

    /**
     * 根据用户id删除用户
     * @param id 用户id
     */
    void deleteUser(int id);
}
