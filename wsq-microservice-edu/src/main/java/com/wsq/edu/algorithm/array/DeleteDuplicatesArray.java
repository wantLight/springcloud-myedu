package com.wsq.edu.algorithm.array;

/**
 * 给定一个排序数组，
 * 你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * @author xyzzg
 * @version 1.0
 * @date 2019-12-30 16:03
 */
public class DeleteDuplicatesArray {

    public static int removeDuplicates(int[] nums) {
        //采用两个标记点 number 和 i ，number记录不重复元素的位置，i从number的下一个开始遍历数组
        int number = 0;
        int length = nums.length;
        for (int i = number+1; i < length; i++) {
            if ( nums[number] != nums[i] ){
                number++;
                nums[number] = nums[i];
            }
        }

        return number + 1;
    }


    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));

    }

}
