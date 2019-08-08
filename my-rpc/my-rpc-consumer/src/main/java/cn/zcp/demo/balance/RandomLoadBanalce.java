package cn.zcp.demo.balance;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-08 14:44
 * @describe dubbo-demo <描述>
 */
public class RandomLoadBanalce implements LoadBalance {
    @Override
    public String selectHost(List<String> list) {

        Objects.requireNonNull(list,"服务地址为空!");
        return list.get(new Random().nextInt(list.size()));
    }
}
