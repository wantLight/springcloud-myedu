package com.wsq.edu.algorithm.array;

import java.util.Arrays;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * @author xyzzg
 * @version 1.0
 * @date 2019-12-30 19:42
 */
public class ContainsDupArray {

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1 ; i < nums.length ; i++) {
            if (nums[i - 1] == nums[i])
                return true;
        }
        return false;
    }

}
