package cn.zcp.demo.service;

import java.util.List;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-08 14:15
 * @describe dubbo-demo 服务发现接口
 */
public interface IServiceDiscovery {


    /**
     * 获取一个服务
     * @param serverName
     * @return
     */
    String discovery(String serverName);


}
