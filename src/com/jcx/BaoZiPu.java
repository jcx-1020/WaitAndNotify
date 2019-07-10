package com.jcx;

/**
 * 包子铺类（生产者）
 */
public class BaoZiPu extends Thread {
    //创建包子成员变量
    private BaoZi bz;

    //有参构造为包子赋值
    public BaoZiPu(BaoZi bz) {
        this.bz = bz;
    }

    //设置线程任务：生产包子
    @Override
    public void run() {
        //定义一个变量
        int count = 0;
        //让包子铺一直生产包子
        while (true) {
            //同步保证只有一个线程执行
            synchronized (bz) {
                //判断包子状态
                if (bz.flag) {
                    //包子铺等待
                    try {
                        bz.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //被唤醒后继续执行，生产包子
                //判断生产包子种类
                if (count % 2 == 0) {
                    //生产薄皮三鲜馅包子
                    bz.pi = "薄皮";
                    bz.xian = "三鲜馅";
                } else {
                    //生产冰皮牛肉馅包子
                    bz.pi = "冰皮";
                    bz.xian = "牛肉馅";
                }
                count++;
                System.out.println("包子铺正在生产：" + bz.pi + bz.xian + "包子");
                //生产包子耗费5秒
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //包子铺生产好包子
                //修改包子状态为有
                bz.flag = true;
                //唤醒吃货线程，让其吃包子
                bz.notify();
                System.out.println("包子铺已经生产好了：" + bz.pi + bz.xian + "包子，吃货可以开吃！！！");
            }
        }
    }
}
