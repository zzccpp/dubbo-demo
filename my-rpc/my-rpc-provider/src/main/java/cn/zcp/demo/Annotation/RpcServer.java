package cn.zcp.demo.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-07 18:28
 * @describe dubbo-demo
 *
 * 定义RPC调用的服务引用名称
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcServer {

    Class<?> value();
}
