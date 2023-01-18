package Lesson7;

import Lesson7.Annotations.AfterSuite;
import Lesson7.Annotations.BeforeSuite;
import Lesson7.Annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Homework7 {
    public static void main(String[] args) throws MyException, InvocationTargetException, IllegalAccessException {
        Class<Tests> testsClass = Tests.class;
        Tests newTests = new Tests();

        Method[] methods = testsClass.getDeclaredMethods();
        ArrayList<Method> methodsBefore = new ArrayList<>();
        ArrayList<Method> methodsAfter = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (methodsBefore.isEmpty()) {
                    methodsBefore.add(method);
                } else {
                    throw new MyException("BeforeSuite");
                }
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                if (methodsAfter.isEmpty()) {
                    methodsAfter.add(method);
                } else {
                    throw new MyException("AfterSuite");
                }
            }
        }

        methodsBefore.get(0).invoke(newTests);
        for (int i = 0; i < Tests.MAX_PRIORITY; i++) {
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    if (method.getAnnotation(Test.class).priority() == i) {
                        method.invoke(newTests);
                    }
                }
            }
        }
        methodsAfter.get(0).invoke(newTests);
    }
}