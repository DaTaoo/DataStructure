package com.ImoocProjects.TestCase;

import com.ImoocProjects.BinarySearchTree.BST;
import com.ImoocProjects.LinkedList.LinkedList;
import com.ImoocProjects.Queue.LinkedListQueue;
import com.ImoocProjects.Queue.LoopQueue;
import com.ImoocProjects.Queue.Queue;
import com.ImoocProjects.Stack.Stack;

import java.util.Random;

public class Main {

    private static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1.0e+9;
    }

    private static double testStack(Stack<Integer> s, int opCount) {
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            s.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            s.pop();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1.0e+9;
    }



    public static void main(String[] args) {
	// write your code here
        BST<Integer> tree = new BST<>();

        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            tree.add(random.nextInt(100));
        }

        tree.preOrderTraverse();

        System.out.println("----");

        tree.invertTree();

        tree.preOrderTraverse();
    }
}
