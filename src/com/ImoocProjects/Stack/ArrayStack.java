package com.ImoocProjects.Stack;

import com.ImoocProjects.Array.MyArray;

public class ArrayStack<E> implements Stack<E> {

    private MyArray<E> array;

    public ArrayStack() {
       array = new MyArray<>();
    }

    public ArrayStack(int capacity) {
        array = new MyArray<>(capacity);
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Stack [");
        for (int i = 0; i < array.getSize(); i++) {
            builder.append(array.get(i));
            if (i != array.getSize() - 1) {
                builder.append(", ");
            }
        }
        builder.append("] <- TOP");

        return builder.toString();
    }
}
