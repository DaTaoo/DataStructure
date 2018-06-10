package com.ImoocProjects.LeetCodeSolutions;

import com.ImoocProjects.Stack.ArrayStack;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        // write your code here

        int[] arr = {1,2,3,3,4,5,6,7,7,5,1};

        ListNode head = ListNode.Array2LinkedList(arr);

        head = reverseList(head);

        System.out.println(head);


    }

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }

        public static ListNode Array2LinkedList(int[] arr) {

            ListNode head = new ListNode(arr[0]);

            ListNode node = head;
            for (int i = 1; i < arr.length; i++) {
                node.next = new ListNode(arr[i]);
                node = node.next;
            }

            return head;
        }

        @Override
        public String toString() {
            ListNode node = this;
            StringBuilder builder = new StringBuilder().append(node.val).append("->");
            while (node.next != null) {
                node = node.next;
                builder.append(node.val).append("->");
            }
            builder.append("null");
            return builder.toString();
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

    /**
     * Given a linked list, determine if it has a cycle in it.
     *
     * @param head head of the linked list
     * @return true if it has a cycle in it.
     */
    public static boolean hasCycle(ListNode head) {
        /*
            方法：
                1.简单粗暴，直接用哈希表记录，但是会增加空间复杂度
                2.为链表设置两个索引，一个遍历步进为1个节点，一个为2个节点
         */

        if (head == null) {
            return false;
        }

        ListNode fastIter = head.next;
        ListNode slowIter = head;

        while (fastIter != slowIter) {
            if (fastIter == null || fastIter.next == null) {
                return false;
            }

            slowIter = slowIter.next;
            fastIter = fastIter.next.next;
        }

        return true;
    }


    /**
     * Merge two sorted linked lists and return it as a new list.
     *
     * @param l1 head of first linked list
     * @param l2 head of second linked list
     * @return head of the merged listNode
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else { //l1.val > l2.val
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        if (l1 == null)
            prev.next = l2;
        if (l2 == null)
            prev.next = l1;

        return dummy.next;
    }


    /**
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     * @param head ListNode
     * @return ListNode after delete all the duplicate nodes
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        if (head == null || head.next == null) {
            return head;
        }

        while (head.next != null) {
            if (head.val == head.next.val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }

        return dummy.next;
    }


    /**
     * Remove all elements from a linked list of integers that have value val recursively.
     * @param head linked list that contains that element.
     * @param val the value that need to be removed
     * @return linked list after removing val;
     */
    public static ListNode removeElementsRecursively(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        head.next = removeElementsRecursively(head.next, val);

        head = (head.val == val ? head.next : head);

        return head;
    }

    /**
     * Remove all elements from a linked list of integers that have value val.
     * @param head linked list that contains that element.
     * @param val the value that need to be removed
     * @return linked list after removing val;
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode curNode = dummyHead;

        while(curNode.next!=null){
            if(curNode.next.val==val){
                curNode.next = curNode.next.next;
            }else{
                curNode = curNode.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * Reverse a linked list recursively.
     * @param head linked list that need to be reverse.
     * @return head of the reversed linked list.
     */
    public static ListNode reverseListRecursively(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseListRecursively(head.next);

        head.next.next = head;

        head.next = null;

        return newHead;

    }

    /**
     * Reverse a linked list iteratively.
     * @param head linked list that need to be reverse.
     * @return a reversed linked list.
     */
    public static ListNode reverseList(ListNode head) {
        ListNode curNode = head;
        ListNode prevNode = null;

        while(curNode != null) {
            ListNode tempNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = tempNode;
        }

        return prevNode;
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
