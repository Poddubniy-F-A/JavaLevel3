package Lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Homework1 {
    private static final int APPLES1 = 6;
    private static final int APPLES2 = 5;
    private static final int ORANGES = 4;

    public static void main(String[] args) {
        Double[] doubleArray = {1.0, 2.0, 3.0};
        System.out.println("До swapElements:\n" + Arrays.toString(doubleArray));
        swapElements(doubleArray, 1, 2);
        System.out.println("После swapElements:\n" + convert(doubleArray));

        Box<Apple> boxApple1 = new Box<>(new ArrayList<>());
        for (int i = 0; i < APPLES1; i++) {
            boxApple1.addFruit(new Apple());
        }
        Box<Apple> boxApple2 = new Box<>(new ArrayList<>());
        for (int i = 0; i < APPLES2; i++) {
            boxApple2.addFruit(new Apple());
        }
        Box<Orange> boxOrange = new Box<>(new ArrayList<>());
        for (int i = 0; i < ORANGES; i++) {
            boxOrange.addFruit(new Orange());
        }

        System.out.printf("До moveFruits:\n%f %f %f\n",
                boxApple1.getWeight(),
                boxApple2.getWeight(),
                boxOrange.getWeight()
        );

        System.out.println("Первая корзина весит столько же, сколько третья:\n" +
                boxApple1.compare(boxOrange));

        boxApple1.moveFruits(boxApple2);
        System.out.printf("После moveFruits:\n%f %f %f\n",
                boxApple1.getWeight(),
                boxApple2.getWeight(),
                boxOrange.getWeight()
        );
    }

    public static <A> void swapElements(A[] array, int index1, int index2) {
        A changer = array[index1];
        array[index1] = array[index2];
        array[index2] = changer;
    }

    private static <B> ArrayList<B> convert(B[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}