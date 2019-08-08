package cn.zcp.demo.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-08 11:41
 * @describe dubbo-demo
 *
 * 远程传输序列化对象
 */
public class RpcRequest implements Serializable {

    private String className;
    private String mathedName;
    private Object[] params;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMathedName() {
        return mathedName;
    }

    public void setMathedName(String mathedName) {
        this.mathedName = mathedName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", mathedName='" + mathedName + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
