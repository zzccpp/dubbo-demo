package cn.zcp.demo.register.zk;

import cn.zcp.demo.service.HelloService;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Objects;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-07 16:06
 * @describe dubbo-demo
 *
 * zookeeper注册中心
 */
public class ZookeeperRegister {

    private String ZOOKEEPER_HOST="182.61.44.56:2181,182.61.44.56:2182,182.61.44.56:2183";
    private int SESSION_TIMEOUT=10*1000;
    private int CONNECT_TIMEOUT=5*1000;

    private CuratorFramework cf;

    public ZookeeperRegister(){
        //失败,尝试3次,间隔1秒
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3,1000);
        this.cf = CuratorFrameworkFactory.newClient(ZOOKEEPER_HOST, SESSION_TIMEOUT, CONNECT_TIMEOUT, retryPolicy);
        cf.start();
    }

    /**
     * 发布服务,至my-rpc/provider节点下
     * @param cls
     */
    public void register(Class cls,String address) throws Exception {

        String path = "/my-rpc/provider";
        //获取全类名
        String name = cls.getName();
        Objects.requireNonNull(cf,"注册中心未连接上!");

        Stat stat = cf.checkExists().forPath(path);
        System.out.println(stat);
        if(null==stat){
            cf.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path,"0".getBytes("utf-8"));
        }
        cf.create().withMode(CreateMode.EPHEMERAL).forPath(path+"/"+address,"0".getBytes("utf-8"));
    }
}
