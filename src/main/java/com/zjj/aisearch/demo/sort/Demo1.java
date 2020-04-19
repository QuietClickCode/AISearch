package com.zjj.aisearch.demo.sort;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2020-03-14 17:42:38
 **/

/**
 * https://blog.csdn.net/huosanghuakai1995/article/details/75090370/
 */
public class Demo1 {
    public static int arr[] = {3, 0, 2, 5, 8, 1, 9, 4};

    public static void main(String[] args) {
   //     bubbleSort(arr);
        selectionSort(arr);
        for (int a : arr) {
            System.out.println(a);
        }
    }

    //冒泡排序
    public static void bubbleSort(int array[]) {

        int t = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }

    //选择排序
    public static int[] selectionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            // vim 删除到前一个单词
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;

        }
        return array;
    }


}
