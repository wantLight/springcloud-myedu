package com.wsq.edu.algorithm.array;

import java.util.Arrays;

/**
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 *
 * @author xyzzg
 * @version 1.0
 * @date 2019-12-31 11:00
 */
public class IntersectArray {


    public int[] intersect(int[] nums1, int[] nums2) {
        return Arrays.stream(nums1).filter(x ->  Arrays.stream(nums2).allMatch(x2 -> x==x2)).toArray();
    }
}
