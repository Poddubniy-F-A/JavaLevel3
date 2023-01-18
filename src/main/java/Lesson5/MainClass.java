package Lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static volatile boolean wasWin = false;

    public static void main(String[] args) {
        CountDownLatch prepare = new CountDownLatch(CARS_COUNT);

        Race race = new Race(new Road(60), new Tunnel(80, CARS_COUNT), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        for (int i = 0; i < CARS_COUNT; i++) {
            int localI = i;
            new Thread(() -> {
                cars[localI] = new Car(race, 20 + (int) (Math.random() * 10));
                prepare.countDown();
            }).start();
        }
        try {
            prepare.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CountDownLatch finishing = new CountDownLatch(CARS_COUNT);
        Semaphore semaphore = new Semaphore(1);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        for (int i = 0; i < CARS_COUNT; i++) {
            int localI = i;
            new Thread(() -> {
                cars[localI].run();
                finishing.countDown();

                try {
                    semaphore.acquire();
                    if (!wasWin) {
                        System.out.println(cars[localI].getName() + " - WIN");
                        wasWin = true;
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            finishing.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}