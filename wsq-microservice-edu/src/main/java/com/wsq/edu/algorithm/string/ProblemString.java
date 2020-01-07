package com.wsq.edu.algorithm.string;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2020-1-6 16:59
 */
public class ProblemString {

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int len = s.length%2 != 0 ? s.length/2 +1 : s.length/2;
        int mylen = s.length-1;
        char temp;
        for (int i = 0; i < len ; i++,mylen = mylen-2) {
            temp = s[i];
            s[i] = s[i+mylen];
            s[i+mylen] = temp;
        }
    }


    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * @param x
     * @return
     */
    public int reverse(int x) {

        long num = 0;
        while (x != 0) {
            //这里每次取该数对10进行取余 得到的该数每次再*10就是翻转后的值
            num = num * 10 + x % 10;
            x /= 10;
        }
        if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE){
            return 0;
        }

        return (int)num;
    }

    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {

        return -1;
    }
}
