package org.example;

import java.util.List;

public class TypeErasure {

    public static class MyGeneric<T> {

        public T getElement(List<T> things) {
            return things.getFirst();
        }

    }

    public static class NonGeneric {

        public Object getElement(List things) {
            return things.getFirst();
        }

    }

    public <T> T extract(List<? extends T> readOnly, T data) {
        //readOnly.add(data);
        return readOnly.getFirst();
    }

    public <T> void insert(List<? super T> writeOnly, T data) {
        writeOnly.add(data);
        Object x = writeOnly.getFirst();
    }

    public static void main(String args[]) {

        {
            MyGeneric<String> myGen = new MyGeneric<String>();
            List<String> list = List.of("hello");
            String item = myGen.getElement(list);
            System.out.println(item);
        }

        {
            NonGeneric nonGen = new NonGeneric();
            List list = List.of("hello nongen");
            String item = (String)nonGen.getElement(list);
            System.out.println(item);
        }
    }
}