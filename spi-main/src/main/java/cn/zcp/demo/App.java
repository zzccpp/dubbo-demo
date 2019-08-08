package cn.zcp.demo;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){

        //源码分析:创建一个ServiceLoader对象及interator内部类，
        ServiceLoader<ConnDriver> service = ServiceLoader.load(ConnDriver.class);
        //返回一个包装的interator内部类,其中包含首先先去缓存LinkedHashMap<String,S>中拿对象

        Iterator<ConnDriver> serviceIterator = service.iterator();
        //执行包装Interator内部类，先去查看是否有缓存，没有的则去MATE-INF/services配置文件中继续去拿配置并且通过反射创建对象,
        //并放入缓存中
        while(serviceIterator.hasNext()){
            serviceIterator.next().loadDriver();
        }
        //总结: SPI的缺点，可能会获取到多个ServiceImpl
        /**
         * 虽然ServiceLoader也算是使用的延迟加载，但是基本只能通过遍历全部获取，也就是接口的实现类全部加载并实例化一遍。
         * 如果你并不想用某些实现类，它也被加载并实例化了，这就造成了浪费。获取某个实现类的方式不够灵活，只能通过Iterator形式获取，
         * 不能根据某个参数来获取对应的实现类。
         * 多个并发多线程使用ServiceLoader类的实例是不安全的。
         *
         * 作者：caison
         * 链接：https://www.jianshu.com/p/46b42f7f593c
         * 来源：简书
         * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
         */
    }
}
