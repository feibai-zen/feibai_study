package com.feibai.study.demos.multithread.createthread;

/**
 * 模拟龟兔赛跑 视频第199集
 * <p>
 * 这里思考下，两个人在做比赛，可以用两个线程分别模拟两个不同的人
 *
 * @author feibai
 */
public class TortoiseAndRabbit implements Runnable {

    private static String winner;//这里有个问题，winner是共享变量，如果线程1中修改之后，线程2不一定能够感知到修改，仍然会继续执行。可以尝试用volatile修饰

    //  private static AtomicBoolean a = new AtomicBoolean(false);//非阻塞同步
    @Override
    public void run() {
        try {
            for (int steps = 1; steps <= 100; steps++) {
                if (Thread.currentThread().getName() == "rabbit" && steps % 10 == 0) {
                    Thread.sleep(10000);
                }
                System.out.println(Thread.currentThread().getName() + "-->" + steps);
                boolean flag = gameOver(steps);
                // 判断游戏是否结束
                if (flag) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean gameOver(int steps) {
        if (winner != null) {
            return true;
        } else {
            if (steps == 100) {
                winner = Thread.currentThread().getName();
                System.out.println("winner==>" + winner);
            }
        }
        return false;
    }

}
