package com.wsq.edu.algorithm.array;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
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
        return Arrays.stream(nums1).filter(x -> Arrays.stream(nums2).allMatch(x2 -> x == x2)).toArray();
    }


    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
//        List<Integer> list = Lists.newArrayList();
//        list.add(1);

        int length = digits.length;
        //判断第一种情况
        if (length == 0) {
            return null;
        }
        int temp = 0;
        for (int i = length - 1; i >= 0; i--) {
            temp = digits[i] + 1;
            if (temp >= 10) {
                digits[i] = 0;
                if (i == 0) {
                    int[] newdig = new int[length + 1];
                    System.arraycopy(digits, 0, newdig, 1, Math.min(length, length + 1));
                    newdig[0] = 1;
                    return newdig;
                    //Arrays.copyOf();
                }
            } else {
                digits[i] = temp;
                break;
            }
        }
        return digits;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 必须在原数组上操作，不能拷贝额外的数组。
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {

        int flag = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) {
                flag++;
                continue;
            }
            if (flag != 0) {
                nums[i - flag] = nums[i];
            }
        }
        for (int i = 0; i < flag; i++) {
            nums[length - 1 - i] = 0;
        }
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

        int a1 = 0;
        int a2 = 0;
        my:
        for (int i = 0; i < nums.length; i++) {
            a1 = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] + nums[i] == target) {
                    a2 = j;
                    break my;
                }
            }

        }


        int[] answer = new int[]{a1, a2};
        return answer;
    }


    /**
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     *
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     *
     *
     * 一个有效的数独（部分已被填充）不一定是可解的。
     * 只需要根据以上规则，验证已经填入的数字是否有效即可。
     * 给定数独序列只包含数字 1-9 和字符 '.' 。
     * 给定数独永远是 9x9 形式的。
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.'){
                    //判断行
                    char temp = board[i][j];

                    for (int k = i+1; k < board.length; k++) {
                        if (board[k][j] == temp){
                            System.out.println(k+","+j+","+temp);
                            return false;
                        }
                    }
                    //判断列
                    for (int k = j+1; k < board[i].length; k++) {
                        if (board[i][k] == temp){
                            System.out.println(i+","+k+","+temp+"!!");
                            return false;
                        }
                    }
                    //判断九宫格 fix bug

                    int rowNum = i % 3;
                    int cellNum = j % 3;
                    for (int k = 0; k < 3 - rowNum ; k++) {
                        for (int l = 0; l < 3 - cellNum ; l++) {
                            int s = j+l;
                            if (k==0 && l==0){
                                continue;
                            }

                            if (i+k < 9 && s <9 ){
                                if (board[i+k][s] == temp){
                                    System.out.println(i+","+j+",");
                                    System.out.println(i+k+","+s+","+temp+"!!!");
                                    return false;
                                }
                            }

                        }
                    }

                }

            }
        }

        return true;
    }

    public static void main(String[] args) {

        //twoSum(new int[]{-3, 4, 3, 0}, 0);
        char[][] chars = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(isValidSudoku(chars));
    }
}
