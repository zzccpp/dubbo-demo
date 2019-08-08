package cn.zcp.demo.balance;

import java.util.List;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-08 14:43
 * @describe dubbo-demo
 *
 * 负载均衡接口
 */
public interface LoadBalance {


    String selectHost(List<String> list);

}
