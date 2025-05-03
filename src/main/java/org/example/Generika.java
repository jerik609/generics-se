package org.example;


// https://stackoverflow.com/questions/211143/java-enum-definition

// https://en.wikipedia.org/wiki/Curiously_recurring_template_pattern

// https://madbean.com/2004/mb2004-3/

import java.util.ArrayList;
import java.util.List;

public class Generika<T> {

    List<T> dataList = new ArrayList<>();

    public void putItems(List<? extends T> items) {
        dataList.addAll(items);
    }

    public List<? super T> getItems() {
        return dataList;
    }

    private final T data;

    Generika(T input) {
        this.data = input;
    }

    T getData() {
        return data;
    }


}
