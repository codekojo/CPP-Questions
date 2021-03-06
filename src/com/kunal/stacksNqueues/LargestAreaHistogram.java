package com.kunal.stacksNqueues;


import java.util.Scanner;
import java.util.Stack;

public class LargestAreaHistogram {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        System.out.println(ans(arr));
    }

    private static int ans(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;

        stack.push(0);

        for (int i = 1; i < arr.length; i++) {
            // this works because at every i, in the first item of stack we
            // will have the smallest item till i
            while(!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                max = getMax(arr, stack, max, i);
            }
            stack.push(i);
        }
        int i = arr.length;
        while(!stack.isEmpty()) {
            max = getMax(arr, stack, max, i);
        }
        return max;
    }

    private static int getMax(int[] arr, Stack<Integer> stack, int max, int i) {
        int area;
        int popped = stack.pop();
        if(stack.isEmpty()) {
            area = arr[popped] * i;
        } else {
            area = arr[popped] * (i - stack.peek() - 1);
        }
        return Math.max(max, area);
    }
}
