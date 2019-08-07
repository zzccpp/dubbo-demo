package cn.zcp.demo.protocol;

import cn.zcp.demo.service.impl.HelloServiceImpl;

import java.util.Objects;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-07 17:34
 * @describe dubbo-demo
 *
 * 定义通信协议
 */
public interface IProtocol {

    void pulish();

    void bind(Object... objs);
}
