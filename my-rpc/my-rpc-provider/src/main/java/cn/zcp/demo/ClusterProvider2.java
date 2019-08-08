package cn.zcp.demo;

import cn.zcp.demo.protocol.SocketProtocol;
import cn.zcp.demo.register.zk.ZookeeperRegister;
import cn.zcp.demo.service.impl.HelloServiceImpl;
import cn.zcp.demo.service.impl.HelloServiceImpl1;

/**
 * Hello world!
 *
 */
public class ClusterProvider2 {
    public static void main( String[] args ) throws Exception{

        //1、连接注册中心
        ZookeeperRegister zk = new ZookeeperRegister();
        //2、发布服务节点,并启动本地服务
        SocketProtocol socketProtocol = new SocketProtocol(zk,"127.0.0.1:9992");
        //3、绑定服务实现
        socketProtocol.bind(new HelloServiceImpl1());
        //4、发布服务
        socketProtocol.pulish();
    }
}