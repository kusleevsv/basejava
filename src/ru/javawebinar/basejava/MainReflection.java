package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuid");
        // TODO : invoke r.toString via reflection
        for(Method method : resume.getClass().getMethods()) {
            if(method.getName().equals("toString")) {
                try {
                    System.out.println("invoke r.toString via reflection " +  method.invoke(resume));
                } catch (InvocationTargetException e) {
                    System.out.println(e);
                }
            }
        }
        System.out.println(resume);
    }
}