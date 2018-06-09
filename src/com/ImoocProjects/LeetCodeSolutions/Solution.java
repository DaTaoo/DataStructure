package com.ImoocProjects.LeetCodeSolutions;

import com.ImoocProjects.Stack.ArrayStack;

import java.util.*;

public class Solution {

    /**
     * Definition for singly-linked list.
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * Definition for a binary tree Node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //recursive version
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);

        root.right = left;
        root.left = right;

        return root;
    }

    //Iterative version
    public static void inverTreeIteratively(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null) {
            return;
        }

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.remove();

            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public static int[][] flipAndInvertImage(int[][] A) {
        int cols, rows;
        rows = cols = A.length;
        for (int i = 0; i < rows; i++) {
            int[] copy = new int[cols];
            for (int j = 0; j < cols; j++) {
                copy[j] = (A[i][cols - j - 1] == 1 ? 0 : 1);
            }
            A[i] = copy;
        }
        return A;
    }

    private static boolean isParenthesesValid(String s) {
        ArrayStack<Character> arrayStack = new ArrayStack<>();

        if (s == null || s.equals("")) {
            return false;
        }

        char[] ca = s.toCharArray();

        int i = 0;

        while (i < ca.length) {
            char nextChar = ca[i];
            if (nextChar == '(' || nextChar == '[' || nextChar == '{') {
                arrayStack.push(nextChar);
            }

            if (arrayStack.isEmpty()) {
                return false;
            }

            if (nextChar == ')' && arrayStack.pop() != '(') {
                return false;
            }

            if (nextChar == ']' && arrayStack.pop() != '[') {
                return false;
            }

            if (nextChar == '}' && arrayStack.pop() != '{') {
                return false;
            }

            i++;
        }

        return arrayStack.isEmpty();
    }

    /**
     * Find all the elements that appear twice in this array,
     * without extra space and in O(n) runtime.
     *
     * @param nums an array of integers, 1 ≤ a[i] ≤ n (n = size of array),
     *             some elements appear twice and others appear once.
     */
    public static List<Integer> findDuplicates(int[] nums) {
        /*思路: 以<元素数值>-1为下标取数组内元素，
          如果有重复数值的元素那么就会访问同一个元素两次
          第一次访问时把它变成负数，如果第二次访问时发现是负数那么说明这是重复元素
        */
        List<Integer> list = new ArrayList<>();
        int index;
        for (int i = 0; i < nums.length; i++) {
            index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            } else {
                list.add(index + 1);
            }
        }
        return list;
    }


    public boolean hasCycle(ListNode head) {
        return false;
    }


    /**
     * Your BSTIterator will be called like this:
     * BSTIterator i = new BSTIterator(root);
     * while (i.hasNext()) v[f()] = i.next();
     */
    public class BSTIterator {

        /*
          有两个思路：
            1.使用栈
            2.对BST进行中序遍历后得到一个排好序的队列
         */

        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();

            pushLeftNodes(root);
        }

        private void pushLeftNodes(TreeNode root) {
            if (root == null) {
                return;
            }
            stack.push(root);
            pushLeftNodes(root.left);
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode node = stack.pop();

            if (node.right != null) {
                pushLeftNodes(node.right);
            }

            return node.val;
        }
    }

}
