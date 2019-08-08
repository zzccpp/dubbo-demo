package cn.zcp.demo.service;

import cn.zcp.demo.balance.LoadBalance;
import cn.zcp.demo.balance.RandomLoadBanalce;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;
import java.util.Objects;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-08 14:26
 * @describe dubbo-demo
 *
 * 服务发现
 */
public class ZKServiceDiscovery implements IServiceDiscovery {

    private String ZOOKEEPER_HOST="182.61.44.56:2181,182.61.44.56:2182,182.61.44.56:2183";
    private int SESSION_TIMEOUT=30*1000;
    private int CONNECT_TIMEOUT=20*1000;

    private CuratorFramework cf;

    private List<String> childNodes;

    public ZKServiceDiscovery(){
        //失败,尝试3次,间隔1秒
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        this.cf = CuratorFrameworkFactory.newClient(ZOOKEEPER_HOST, SESSION_TIMEOUT, CONNECT_TIMEOUT, retryPolicy);
        cf.start();
        System.out.println("zk start!");
    }

    @Override
    public String discovery(String serverName) {
        String nodePath = "/my-rpc/provider/"+serverName;
        try {
            childNodes = cf.getChildren().forPath(nodePath);
            Objects.requireNonNull(childNodes,"未发现服务!");
            //动态监听
            if(null!=childNodes){watcher(nodePath);}
            //如果有多个的话，随机获取一个服务
            LoadBalance loadBalance  = new RandomLoadBanalce();
            return loadBalance.selectHost(childNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 动态监听
     * @param nodePath
     */
    private void watcher(final String nodePath) throws Exception{

        PathChildrenCache childrenCache = new PathChildrenCache(cf,nodePath,false);

        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                childNodes = cf.getChildren().forPath(nodePath);
            }
        });
        childrenCache.start();
    }
}
