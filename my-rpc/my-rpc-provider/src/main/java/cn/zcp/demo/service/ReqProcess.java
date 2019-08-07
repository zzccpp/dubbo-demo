package cn.zcp.demo.service;

import java.net.Socket;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-07 18:54
 * @describe dubbo-demo <描述>
 */
public class ReqProcess implements Runnable {

    private Socket socket;

    public ReqProcess(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
