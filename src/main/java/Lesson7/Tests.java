package Lesson7;

import Lesson7.Annotations.AfterSuite;
import Lesson7.Annotations.BeforeSuite;
import Lesson7.Annotations.Test;

public class Tests {
    static int MAX_PRIORITY = 5;

    @BeforeSuite
    public void testBefore() {
        System.out.println("Test before all");
    }

    @Test(priority = 2)
    public void test1() {
        System.out.println("Test 1");
    }

    @Test(priority = 3)
    public void test2() {
        System.out.println("Test 2");
    }

    @Test(priority = 1)
    public void test3() {
        System.out.println("Test 3");
    }

    @AfterSuite
    public void testAfter() {
        System.out.println("Test after all");
    }

    /*@AfterSuite
    public void testAfterForException () {
        System.out.println("Test after all");
    }*/
}
