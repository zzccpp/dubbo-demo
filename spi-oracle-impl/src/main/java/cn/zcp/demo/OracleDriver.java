package cn.zcp.demo;

/**
 * @author zhongchunping
 * @version 1.0
 * @Time 2019-08-07 10:33
 * @describe dubbo-demo <描述>
 */
public class OracleDriver implements ConnDriver{
    public void loadDriver() {
        System.out.println("Oracle..ConnDriver");
    }
}
