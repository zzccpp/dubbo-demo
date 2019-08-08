package cn.zcp.demo.protocol;

import cn.zcp.demo.dto.RpcRequest;

import java.io.*;
import java.net.Socket;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-08 15:06
 * @describe dubbo-demo <描述>
 */
public class SocketProtocol {

    //服务地址      IP:PORT
    private String discovery;

    public SocketProtocol(String discovery) {
        this.discovery = discovery;
    }

    /**
     * 向服务器发送报文
     * @param rpcRequest
     * @return
     */
    public Object send(RpcRequest rpcRequest) {
        String[] strs = discovery.split(":");
        String ip = strs[0];
        int port = Integer.parseInt(strs[1]);
        Socket socket = null;
        try {
            socket = new Socket(ip,port);
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(rpcRequest);

            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Object obj = ois.readObject();
            is.close();
            oos.close();
            os.close();
            ois.close();
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("发起远程服务请求异常!",e);
        } finally {
            try {
                if(null!=socket)socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
