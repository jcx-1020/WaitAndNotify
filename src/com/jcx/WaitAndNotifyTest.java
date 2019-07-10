package com.jcx;

/**
 * 测试类
 */
public class WaitAndNotifyTest {
    public static void main(String[] args) {
        //创建包子对象
        BaoZi bz = new BaoZi();
        //创建包子铺线程并开启，生产包子
        new BaoZiPu(bz).start();
        //创建吃货线程并开启，吃包子
        new ChiHuo(bz).start();
    }
}
