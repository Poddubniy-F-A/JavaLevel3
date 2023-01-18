package Lesson7;

public class MyException extends Exception{
    public MyException (String type){
        super (type + " - этот метод не уникален");
    }
}