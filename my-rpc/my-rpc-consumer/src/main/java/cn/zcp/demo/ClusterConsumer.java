package cn.zcp.demo;

import cn.zcp.demo.bean.User;
import cn.zcp.demo.service.HelloService;
import cn.zcp.demo.service.RpcClientProxy;
import cn.zcp.demo.service.ZKServiceDiscovery;

/**
 * Hello world!
 *
 */
public class ClusterConsumer {
    public static void main( String[] args ){

        //1、监控服务发现
        ZKServiceDiscovery serviceDiscovery = new ZKServiceDiscovery();
        //2、创建服务代理
        RpcClientProxy proxy = new RpcClientProxy(serviceDiscovery);
        //3、创建所需要的服务代理对象
        HelloService helloService = (HelloService)proxy.getService(HelloService.class);
        for(int i=0;i<10;i++) {
            String str = helloService.sayHello("my Rpc");
            System.out.println(str);
        }
    }
}
