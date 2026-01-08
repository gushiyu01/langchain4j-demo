package org.gsy.langchaindemo;

import java.util.Arrays;

/**
 * &#064;program:  langchain4j-demo
 * @description: 快速排序
 * @author: GSY
 * @create: 2025-08-01 11:12
 **/
public class QuickSort {

    /**
     * 使用选择排序排列
     * 遍历数据 选择出最小元素放到最前面
     * @param arr 数组
     */
    public void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] > arr[i]) {
                    maxIndex = j;
                }
            }
            int temp = arr[maxIndex];
            arr[maxIndex] = arr[i];
            arr[i] = temp;
        }
        int[] i = new int[2];
    }

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

    public void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int target = arr[i];
            int j;
            for (j = i-1; j >= 0 && target < arr[j]; j--) {
                arr[j+1] = arr[j];
            }
            arr[j+1] = target;
        }
    }

    public void insertSortTwo(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int target = arr[i];
            int j = i-1;
            while(j>=0 && arr[j]>target){
                arr[j+1] = arr[j];
            }
            arr[j+1] = target;
        }
    }

    public static int partition(int[] array,int low,int high){
        int pivot = array[high];//取最后一个元素作为中心元素
        int pointer = low;//定义指向比中心元素大的指针，首先指向第一个元素
        // 遍历数组中的所有元素，将比中心元素大的放在右边，比中心元素小的放在左边
        for(int i = low;i<high;i++){
            if(array[i]<=pivot){
                // 将比中心元素小的元素和指针指向的元素交换位置
                // 如果第一个元素比中心元素小，这里就是自己和自己交换位置，指针和索引都向下一位移动
                // 如果元素比中心元素大，索引向下移动，指针指向这个较大的元素，直到找到比中心元素小的元素，并交换位置，指针向下移动
                int temp = array[i];
                array[i] = array[pointer];
                array[pointer] = temp;
                pointer++;
            }
            System.out.println(Arrays.toString(array));
        }
        // 将中心元素和指针指向的元素交换位置
        int temp = array[pointer];
        array[pointer] = array[high];
        array[high] = temp;
        return pointer;
    }

    public static void quickSort(int [] array, int low ,int high){
        if(low<high){
            // 获取划分子数组的位置
            int position = partition(array,low,high);
            // 左子数组递归调用
            quickSort(array,low,position-1);
            // 右子数组递归调用
            quickSort(array,position+1,high);
        }
    }

//    public static void main(String[] args) {
//        int[] array = {8,7,6,5,4,3,2};
//        quickSort(array,0,array.length-1);
//        System.out.println(Arrays.toString(array));
//    }

//    public static void main(String[] args) {
//        QuickSort quickSort = new QuickSort();
//        int[] arr = {9, 8, 7, 6, 9, 8, 6, 2, 1, 5, 3, 2, 4};
//        quickSort.selectionSort(arr);
//        for (int j : arr) {
//            System.out.println(j);
//        }
//    }

    /* 零钱兑换：贪心 */
    int coinChangeGreedy(int[] coins, int amt) {
        // 假设 coins 列表有序
        int i = coins.length - 1;
        int count = 0;
        // 循环进行贪心选择，直到无剩余金额
        while (amt > 0) {
            // 找到小于且最接近剩余金额的硬币
            while (i > 0 && coins[i] > amt) {
                i--;
            }
            // 选择 coins[i]
            amt -= coins[i];
            count++;
        }
        // 若未找到可行方案，则返回 -1
        return amt == 0 ? count : -1;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {1,2,3,4,5};
        int amt =7;
        System.out.println(quickSort.coinChangeGreedy(arr,amt));
    }
}
