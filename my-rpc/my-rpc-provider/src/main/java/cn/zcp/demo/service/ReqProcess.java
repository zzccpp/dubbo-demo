package cn.zcp.demo.service;

import cn.zcp.demo.dto.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-07 18:54
 * @describe dubbo-demo <描述>
 */
public class ReqProcess implements Runnable {

    private Socket socket;
    private HashMap<String, Object> serverObjMap;
    public ReqProcess(Socket socket, HashMap<String, Object> serverObjMap) {
        this.socket = socket;
        this.serverObjMap = serverObjMap;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest)ois.readObject();
            System.out.println(rpcRequest);
            //通过反射调用方法
            String className = rpcRequest.getClassName();
            Object[] params = rpcRequest.getParams();
            Class [] paramsCls = new Class[params.length];
            for (int i = 0;i<params.length;i++) {
                paramsCls[i] = params[i].getClass();
            }
            Object o = serverObjMap.get(className);

            Method method = Class.forName(className).getMethod(rpcRequest.getMathedName(), paramsCls);

            Object result = method.invoke(o, params);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(result);

            ois.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("处理远程调用逻辑异常!");
        }
    }
}
