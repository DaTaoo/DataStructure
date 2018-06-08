package com.ImoocProjects.Queue;
import com.ImoocProjects.Array.MyArray;

public class ArrayQueue<E> implements Queue<E> {

    private MyArray<E> array;

    public ArrayQueue() {
        array = new MyArray<>();
    }

    public ArrayQueue(int capacity) {
        array = new MyArray<>(capacity);
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
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
        StringBuilder builder = new StringBuilder("Queue: Front-> [");
        for (int i = 0; i < array.getSize(); i++) {
            builder.append(array.get(i));
            if (i != array.getSize() - 1) {
                builder.append(", ");
            }
        }
        builder.append("] <- Tail");

        return builder.toString();
    }
}
