package cn.zcp.demo.protocol;

import cn.zcp.demo.Annotation.RpcServer;
import cn.zcp.demo.register.zk.ZookeeperRegister;
import cn.zcp.demo.service.ReqProcess;
import cn.zcp.demo.service.impl.HelloServiceImpl;
import org.apache.log4j.net.SocketServer;

import java.lang.annotation.Annotation;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-07 17:37
 * @describe dubbo-demo
 *
 * 使用Socket协议来完成远程调用
 */
public class SocketProtocol implements IProtocol{

    private ZookeeperRegister zk;
    private String address;
    //存放服务及对象的Map
    private HashMap<String,Object> serverObjMap = new HashMap<>();

    public SocketProtocol(ZookeeperRegister zk, String address) {
        this.zk = zk;
        this.address = address;
    }

    @Override
    public void pulish() {
        ServerSocket serverSocket=null;
        try {
            int port = Integer.parseInt(address.split(":")[1]);
            serverSocket = new ServerSocket(port);
            //注册服务
            for (String service:serverObjMap.keySet()) {
                zk.register(service,address);
            }
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new ReqProcess(socket,serverObjMap)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(null!=serverSocket){
                    serverSocket.close();
                }
                if(null!=serverSocket){
                    serverSocket.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void bind(Object... objs) {
        for(Object obj:objs){
            RpcServer rpcServer = obj.getClass().getAnnotation(RpcServer.class);
            Objects.requireNonNull(rpcServer,"未定义服务名称");
            String serverName = rpcServer.value().getName();
            serverObjMap.put(serverName,obj);
        }
    }
}
