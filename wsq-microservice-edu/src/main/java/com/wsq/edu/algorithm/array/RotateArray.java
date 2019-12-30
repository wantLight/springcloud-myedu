package com.wsq.edu.algorithm.array;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * @author xyzzg
 * @version 1.0
 * @date 2019-12-30 17:20
 */
public class RotateArray {

    /**
     * 每次右移动时，下一个移动的就是 被移动对象
     * @param nums
     * @param k
     * @return
     */
    public static int[] rotate(int[] nums, int k) {
        int length = nums.length;
        if ( k > length){
            k = k % length;
        }

        //count为计数变量，记录移动成功的次数，temp1和temp2是辅助存储变量，为交换元素值之
        int temp1, temp2, index, count = 0;
        //移动的轮数最多k次，当然计数变量count=len的时候会跳出循环
        for (int i = 0; i <= k; i++) {
            if(count >= length){
                break;//对计数变量的控制，当所有位置全部移动完了就可以结束了
            }
            temp1 = nums[i];
            index = i;
            while ( (index + k)%length != i ){
                temp2 = nums[(index + k)%length];
                nums[(index + k)%length] = temp1;
                index = (index + k)%length;

                temp1 = temp2;
                count++;
            }
            nums[i] = temp1;
            count++;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,4,5,6,7};
        int[] ints = rotate(prices, 3);
        Arrays.stream(ints).forEach(System.out::println);
    }
}
