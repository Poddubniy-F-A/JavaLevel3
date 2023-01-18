package Lesson5;

public class Car implements Runnable {
    private static int CARS_COUNT = 0;

    private final Race race;
    private final int speed;
    private final String name;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;

        CARS_COUNT++;
        name = "Участник #" + CARS_COUNT;

        try {
            System.out.println(name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }
}