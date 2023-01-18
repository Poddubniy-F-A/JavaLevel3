package Lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final int length;
    private final String description;

    private final Semaphore semaphore;

    public Tunnel(int length, int participantsNumber) {
        this.length = length;
        description = "Тоннель " + length + " метров";

        semaphore = new Semaphore(participantsNumber / 2);
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            semaphore.acquire();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(c.getName() + " закончил этап: " + description);
            semaphore.release();
        }
    }
}