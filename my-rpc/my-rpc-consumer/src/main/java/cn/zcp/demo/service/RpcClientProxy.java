package cn.zcp.demo.service;

import cn.zcp.demo.dto.RpcRequest;
import cn.zcp.demo.protocol.SocketProtocol;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-08 14:30
 * @describe dubbo-demo <描述>
 */
public class RpcClientProxy implements InvocationHandler {


    private IServiceDiscovery serviceDiscovery;



    public RpcClientProxy(IServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMathedName(method.getName());
        rpcRequest.setParams(args);
        //开启一个socket客户端,取远程请求获取结果
        String discovery = serviceDiscovery.discovery(rpcRequest.getClassName());
        SocketProtocol socketProtocol = new SocketProtocol(discovery);
        return socketProtocol.send(rpcRequest);
    }

    /**
     * 获取服务代理对象
     * @param cls
     * @return
     */
    public Object getService(Class cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, this);
    }
}
