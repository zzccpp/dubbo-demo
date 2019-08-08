package cn.zcp.demo.bean;

import java.io.Serializable;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-08 15:42
 * @describe dubbo-demo
 *
 * serialVersionUID自动生成生成方法:
 * setting中搜索serializable class without ..ID,打开,  在实体类中ctrl+1
 */
public class User implements Serializable {

    private static final long serialVersionUID = 2889623844214360425L;
    private String userName;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
