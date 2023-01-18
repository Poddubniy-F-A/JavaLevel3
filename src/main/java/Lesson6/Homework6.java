package Lesson6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Homework6 {
    @Test
    void testArrayTransmute() throws RuntimeException {
        assertArrayEquals(
                arrayTransmute(new int[] {0, 1, 2, 3, 4, 5, 6}),
                new int[] {5, 6});
        assertArrayEquals(
                arrayTransmute(new int[] {4, 4, 4, 5, 6, 4, 7}),
                new int[] {7});
        assertArrayEquals(
                arrayTransmute(new int[] {1, 2, 3, 4}),
                new int[] {});
        assertThrows(RuntimeException.class, () -> arrayTransmute(new int[] {1, 2, 3}));
    }

    public static int[] arrayTransmute(int[] array) {
        int lastIndex = array.length - 1;

        int index = lastIndex;
        while (array[index] != 4) {
            index--;
        }

        int newLength = lastIndex - index;
        int[] result = new int[newLength];
        System.arraycopy(array, index + 1, result, 0, newLength);
        return result;
    }

    @Test
    void testArrayCheck() {
        assertTrue(arrayCheck(new int[] {1, 4, 4, 4}));
        assertFalse(arrayCheck(new int[] {4, 4, 4, 4}));
        assertFalse(arrayCheck(new int[] {1, 4, 4, 0}));
    }

    public static boolean arrayCheck(int[] array) {
        boolean wasOne = false,
                wasFour = false;

        for (int i : array) {
            if (i == 1) {
                wasOne = true;
            } else if (i == 4) {
                wasFour = true;
            } else {
                return false;
            }
        }
        return wasOne & wasFour;
    }
}