package org.gsy.langchaindemo;

/**
 * @program: langchain4j-demo
 * @description: 冒泡排序
 * @author: GSY
 * @create: 2025-08-01 13:58
 **/
public class BubbleSort {


    public void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            //遍历到最后一个元素
            for (int j = 0; j < arr.length-1-i; j++) {
                //交换位置
                if (arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
        int[] arr = {9, 8, 7, 6, 9, 8, 6, 2, 1, 5, 3, 2, 4};
        sort.bubbleSort(arr);
        for (int j : arr) {
            System.out.println(j);
        }
    }
}
