package Lesson1;

import java.util.List;

public class Box<T extends Fruit> {
    private final double ACCURACY = 0.000001;

    private final List<T> fruits;

    public Box(List<T> fruits) {
        this.fruits = fruits;
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        float sumWeight = 0;
        for (T fruit : fruits) {
            sumWeight += fruit.getWeight();
        }
        return sumWeight;
    }

    public boolean compare(Box<?> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < ACCURACY;
    }

    public void moveFruits(Box<T> box) {
        for (T fruit : fruits) {
            box.addFruit(fruit);
        }
        this.fruits.clear();
    }
}