package com.jcx;

/**
 * 吃货类（消费者）
 */
public class ChiHuo extends Thread {
    //创建包子成员变量
    private BaoZi bz;

    //有参构造为包子赋值
    public ChiHuo(BaoZi bz) {
        this.bz = bz;
    }

    //设置线程任务：吃包子
    @Override
    public void run() {
        //让吃货一直吃包子
        while (true) {
            //同步保证只有一个线程执行
            synchronized (bz) {
                //判断包子状态
                if (!bz.flag) {
                    //吃货等待
                    try {
                        bz.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //被唤醒后继续执行，吃包子
                System.out.println("吃货正在吃：" + bz.pi + bz.xian + "包子");
                //吃货5秒吃完包子
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //吃完包子
                //修改包子状态为没有
                bz.flag = false;
                //唤醒包子铺线程，让其生产包子
                bz.notify();
                System.out.println("吃货把：" + bz.pi + bz.xian + "包子吃完了，包子铺可以开始生产！！！");
                System.out.println("=============================================");
            }
        }
    }
}
