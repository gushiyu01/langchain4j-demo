package org.gsy.langchaindemo;

/**
 * &#064;program:  langchain4j-demo
 * @description: 快速排序
 * @author: GSY
 * @create: 2025-08-01 11:12
 **/
public class QuickSort {

    /**
     * 使用快排郑许排列
     * @param arr 数组
     */
    public void quickOrder(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++){
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {9, 8, 7, 6, 9, 8, 6, 2, 1, 5, 3, 2, 4};
        quickSort.quickOrder(arr);
        for (int j : arr) {
            System.out.println(j);
        }
    }
}
