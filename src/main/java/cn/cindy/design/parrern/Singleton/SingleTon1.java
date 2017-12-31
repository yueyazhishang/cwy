package main.java.cn.cindy.design.parrern.Singleton;

/**
 * Created by Administrator on 2017/12/31.
 * 常见单利的几种形式
 */

/**
 * 同步锁+懒汉式
 * synchroized 关键字避免了多线程问题，但是会影响程序性能
 */
class SingleTon1 {
    /**声明示例对象*/
    private static SingleTon1 singleton;
    /**构造方法私有化*/
    private SingleTon1(){}
    /**提供静态获取实例方式*/
    public synchronized static SingleTon1 getInstance(){
        if(singleton == null){
            singleton = new SingleTon1();
        }
        return singleton;
    }
}
/**
 * 饿汉式,类加载时创建实例对象。缺点是如果初始化比较复杂，会导致程序初始化缓慢
 */
class SingleTon2{
    //类加载时初始化实例对象
    private static SingleTon2 instance = new SingleTon2();
    //构造方法私有化
    private SingleTon2(){}
    public static SingleTon2 getInstance (){
        return instance ;
    }
}
/***
 * 双重锁检查
 * 这是懒汉式形式的加强版，将synchroized关键字移动到了getInstance 方法里面，同时将SingleTon3对象加上volatile关键字，这种方式既可以
 * 必满多线程问题，又不会降低程序的性能，但是volatile关键词也有一些性能问题，不建议大量使用
 */
class SingleTon3{
    private static volatile SingleTon3 instance ;
    private SingleTon3(){ }
    public static SingleTon3 getInstance (){
        if(instance == null) {
            synchronized (SingleTon3.class) {
                if (instance == null)
                    instance = new SingleTon3();
            }
        }
        return  instance;
    }
}
/**
 * 静态内部类
 * 这里创建一个内部静态类，通过内部类的机制使得单利对象可以延迟加载，同时内部类相当于外不累的静态部分
 * 所以可以通过jvm来包装其线程安全。这种方式比较推荐
 */
class SingleTon4{
    private static class SingleTonHolder{
        private static SingleTon4 instance = new SingleTon4();
    }
    private SingleTon4(){}
    public static SingleTon4 getInstance (){
        return SingleTonHolder.instance;
    }
}
/**
 * 枚举形式的单利也是比较推荐的
 */
class Resource{
}
 enum SomeThing {
    INSTANCE;
    private Resource instance;
    SomeThing() {
        instance = new Resource();
    }
    public Resource getInstance() {
        return instance;
    }
}