package com.wsq.edu.algorithm.array;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.HashSet;
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
     *
     *
     * 出现的次数的题目，我们一般用hash表来做。（划重点）
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        int[][] rowCount = new int[10][9];
        int[][] columnCount = new int[10][9];
        int[][] subBoardCount = new int[10][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int num = board[i][j] - '0';

                if (board[i][j] != '.' &&
                        (++rowCount[num][i] == 2  //表示第 i 行数字 num 出现的次数
                                || ++columnCount[num][j] == 2  //表示第 j 列数字 num 出现的次数
                                || ++subBoardCount[num][i / 3 * 3 + j / 3] == 2)  //表示小9宫格 num 出现的次数
                ) {
                    return false;
                }
            }
        }

//        for(int x=0; x<subBoardCount.length; x++) {
//            for(int y=0; y<subBoardCount[x].length; y++) {
//                System.out.print(subBoardCount[x][y]+" ");
//            }
//            System.out.println();
//        }

        return true;
    }


    public boolean isValidSudoku2(char[][] board) {
        //最外层循环，每次循环并非只是处理第i行，而是处理第i行、第i列以及第i个3x3的九宫格
        for(int i = 0; i < 9; i++){
            HashSet<Character> line = new HashSet<>();
            HashSet<Character> col = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for(int j = 0; j < 9; j++){
                if('.' != board[i][j] && !line.add(board[i][j]))
                    return false;
                if('.' != board[j][i] && !col.add(board[j][i]))
                    return false;
                int m = i/3*3+j/3;
                int n = i%3*3+j%3;
                if('.' != board[m][n] && !cube.add(board[m][n]))
                    return false;
            }
        }
        return true;
    }

    /**
     * 给定一个 n × n 的二维矩阵表示一个图像。
     *
     * 将图像顺时针旋转 90 度。
     *
     * 说明：
     *
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        int col = matrix.length-1;
        for(int j=0; j<matrix.length/2; j++,col--){
            int r = col;//记录最后
            int c = 0;//记录开始
            for(int i=j;i<col; i++){
                //这里只需要循环互换3次。即可把4个数字旋转成功
                swap(matrix,i,j,r,i);
                swap(matrix,r,i,r-c,r);
                swap(matrix,r-c,r,j,r-c);
                c++;
            }
        }
    }
    private void swap(int[][]matrix,int i1,int j1,int i2,int j2){
        int temp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = temp;
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
