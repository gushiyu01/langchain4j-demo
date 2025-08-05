package org.gsy.langchaindemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @program: langchain4j-demo
 * @description: 单例模式示例
 * @author: GSY
 * @create: 2025-07-29 08:44
 **/
public class SingleTon {
    private static volatile SingleTon singleTon;

    private SingleTon(){}

    public static SingleTon getInstance(){
        if (singleTon == null){
            synchronized (SingleTon.class){
                if (singleTon == null){
                    singleTon = new SingleTon();
                }
            }
        }

        return singleTon;
    }

    public void print(){

        int[] arr = new int[5];
        int[] arr2 = {1, 2, 3, 4, 5};
        Integer[] numbers = new Integer[] { 1, 3, 2, 5, 4 };
        Integer[] numbers2 = { 1, 3, 2, 5, 4 };
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        list.forEach(System.out::println);
        list.add(2,22);
        list.forEach(System.out::println);
        list.remove(2);
        list.forEach(System.out::println);
        System.out.println(list.size());

        System.out.println(10 >> 1);
        System.out.println(8 >> 1);
        System.out.println(6 >> 1);
        System.out.println(6 << 1);
        System.out.println(6 << 2);
        System.out.println(6 << 3);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    public void erfen(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                System.out.println(mid);
                return;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

    }

    public int[] twoSum(int[] nums, int target){

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }
        }

        return new int[]{};
    }


}
