package com.ImoocProjects.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node root, E e) {
        if (root == null) {
            root = new Node(e);
            size++;
            return root;
        }

        if (e.compareTo(root.e) < 0) {
            root.left = add(root.left, e);
        } else if (e.compareTo(root.e) > 0) {
            root.right = add(root.right, e);
        }

        return root;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node root, E e) {
        if (root == null) {
            return false;
        }

        if (e.compareTo(root.e) == 0) {
            return true;
        } else if (e.compareTo(root.e) < 0) {
            return contains(root.left, e);
        } else {
            return contains(root.right, e);
        }
    }

    public void preOrderTraverse() {
        preOrderTraverse(root);
    }

    private void preOrderTraverse(Node root) {
        if (root == null) {
            return;
        }

        System.out.println(root.e);
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    public void preOrderTraverseNotRecursive() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public void inOrderTraverse() {
        inOrderTraverse(root);
    }

    private void inOrderTraverse(Node root) {
        if (root == null) {
            return;
        }

        inOrderTraverse(root.left);
        System.out.println(root.e);
        inOrderTraverse(root.right);
    }

    public void postOrderTraverse() {
        postOrderTraverse(root);
    }

    private void postOrderTraverse(Node root) {
        if (root == null) {
            return;
        }

        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.println(root.e);
    }

    public void levelOrderTraverse() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }

        return minimum(root).e;
    }

    private Node minimum(Node root) {
        if (root.left == null) {
            return root;
        }

        return minimum(root.left);
    }

    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }

        return maximum(root).e;
    }

    private Node maximum(Node root) {
        if (root.right == null) {
            return root;
        }

        return maximum(root.right);
    }

    public E removeMin() {
        E ret = minimum();

        root = removeMin(root);

        return ret;
    }

    private Node removeMin(Node root) {
        if (root.left == null) {
            Node rightNode = root.right;
            root.right = null;
            size--;
            return rightNode;
        }

        root.left = removeMin(root.left);
        return root;
    }

    public E removeMax() {
        E ret = maximum();

        root = removeMax(root);

        return ret;
    }

    private Node removeMax(Node root) {
        if (root.right == null) {
            Node leftNode = root.left;
            root.left = null;
            size--;
            return leftNode;
        }

        root.right = removeMax(root.right);
        return root;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node root, E e) {
        if (root == null) {
            return null;
        }

        if (e.compareTo(root.e) < 0) {
            root.left = remove(root.left, e);
            return root;
        } else if (e.compareTo(root.e) > 0) {
            root.right = remove(root.right, e);
            return root;
        } else {
            if (root.left == null) {
                Node rightNode = root.right;
                root.right = null;
                size--;
                return rightNode;
            } else if (root.right == null) {
                Node leftNode = root.left;
                root.left = null;
                size--;
                return leftNode;
            } else {
                Node successor = minimum(root.right);
                successor.right = removeMin(root.right);
                successor.left = root.left;
                root.right = root.left = null;
                return successor;
            }
        }
    }

    public void invertTree() {
        root = invertTree(root);
    }

    private Node invertTree(Node root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return root;
        }

        if (root.left == null) { // root.right != null
            root.left = invertTree(root.right);
            root.right = null;
            return root;
        }

        if (root.right == null) { // root.left != null
            root.right = invertTree(root.left);
            root.left = null;
            return root;
        }

        Node tempNode = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = tempNode;

        return root;
    }

    private class Node {
        E e;
        Node left, right;

        Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
}
