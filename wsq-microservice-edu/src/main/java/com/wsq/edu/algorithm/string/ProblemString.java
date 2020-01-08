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
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。
     * 如果不存在，则返回 -1。
     *"cdabcaabb"
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
//        char[] chars = s.toCharArray();
//        LinkedHashMap<Character,Integer> map = new LinkedHashMap<>();
//        for (int i = 0; i < chars.length; i++) {
//            if (map.containsKey(chars[i])){
//                map.put(chars[i],-1);
//            } else {
//                map.put(chars[i],i);
//            }
//        }
//        System.out.println(map.size());
//        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
//            if(entry.getValue() != -1){
//                return entry.getValue();
//            }
//        }
//        return -1;


        int res = -1;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            //得到字符串首字符出现位置
            int index = s.indexOf(ch);
            ///得到字符串末字符出现位置 相等代表出现位置一样
            if (index != -1 && index == s.lastIndexOf(ch)) {
                res = (res == -1 || res > index) ? index : res;
            }
        }
        return res;
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        //借助一个数组 实现对char的解析
        int[] map = new int[128];
        char[] sArray = s.toCharArray();
        for(char ch:sArray){
            map[ch]++;
        }
        char[] tArray = t.toCharArray();
        for(char ch:tArray){
            map[ch]--;
        }
        for(int n:map){
            if (n != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 给定一个字符串，验证它是否是回文串（正读和反读是一样的）
     * ，只考虑字母和数字字符，可以忽略字母的大小写。
     * "race a car" raca ecar
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        char[] charArray = s.toLowerCase().toCharArray();
        int j = charArray.length-1;
        for (int i = 0; i < charArray.length; i++,j--) {
            if ( !(charArray[i] >= 'a' && charArray[i] <= 'z') &&  !(charArray[i] >= '0' && charArray[i] <= '9') ){
                j++;
            } else if ( !(charArray[j] >= 'a' && charArray[j] <= 'z') &&  !(charArray[j] >= '0' && charArray[j] <= '9') ){

                i--;
            } else if (charArray[i] != charArray[j]) {
                return false;
            }
            if (i >= j){
                break;
            }
        }
        return true;

//        char[] cs = s.toCharArray();
//        int cnt = 0, j = 0;
//        for (int i = 0; i < cs.length; i++) {
//            if (('0' <= cs[i] && cs[i] <= '9') || ('a' <= cs[i] && cs[i] <= 'z')) {
//                cs[cnt++] = cs[i];
//            } else if ('A' <= cs[i] && cs[i] <= 'Z') {
//                cs[cnt++] = (char) (cs[i] - 'A' + 'a');
//            }
//        }
//        cnt--;
//        while (j < cnt) {if (cs[j++] != cs[cnt--]) {return false;}}
//        return true;
    }


    /**
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     *
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     *
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     *
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     *
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     *
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     *
     * 说明：
     *
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        //判断不为空
        if (str == null) {
            return 0;
        }
        //移除前面多余空格
        str = str.trim();

        if (str.length() == 0) {
            return 0;
        }
        char c = str.charAt(0);
        //判断首字母必须是0-9 或 + -
        if ((c > '9' || c < '0') && c != '+' && c != '-') {
            return 0;
        }

        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        //将第一个字母加入buffer，他可能是+ - 或 0-9
        sb.append(chars[0]);

        for (int i = 1; i < chars.length; i++) {
            //遇到第一个不是 0 - 9 得数 则退出循环
            if (chars[i] < '0' || chars[i] > '9') {
                break;
            }
            sb.append(chars[i]);
        }

        //如果只有一个+号 或者只有一个-号则说明不含数字，返回0
        if (sb.length() == 1 && (sb.charAt(0) == '+' || sb.charAt(0) == '-')) {
            return 0;
        }
        //尝试转换成数字，如果失败了，则说明超过Integer 得取值范围，则返回最大值或最小值
        try {
            return Integer.valueOf(sb.toString());
        } catch (Exception e) {
            return sb.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

    }


    public int myAtoi2(String str) {
        if(str.isEmpty()) return 0;
        char[] mychar=str.toCharArray();
        long ans=0;
        int i=0,sign=1,n=str.length();
        while(i<n&&mychar[i]==' ') {
            i++;
        }
        if(i < n &&mychar[i]=='+') {
            i++;
        }
        else if(i < n &&mychar[i]=='-') {
            i++;
            sign =-1;
        }
        //重点：只管是数字的时候，其余取0
        while(i<n&&(mychar[i]>='0'&&mychar[i]<='9')) {
            if(ans!=(int)ans) {
                return (sign==1)?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            ans=ans*10+mychar[i++]-'0';
        }

        if(ans!=(int)ans) {
            return (sign==1)?Integer.MAX_VALUE:Integer.MIN_VALUE;
        }

        return (int)(ans*sign);

    }


    /**
     * 实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if("".equals(needle)  || needle==null)return 0;
        return haystack.indexOf(needle);
    }


    /**
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 1 被读作  "one 1"  ("一个一") , 即 11。
     * 11 被读作 "two 1s" ("两个一"）, 即 21。
     * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
     *
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
     *
     * 注意：整数序列中的每一项将表示为一个字符串。
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        //basic【Conclude String】
        String[] resNums = new String[n];
        resNums[0] = "1";
        for(int i = 1;i < n;i++){
            resNums[i] = convertString(resNums[i - 1]);
        }
        return resNums[n - 1];
    }

    public static String convertString(String str){
        int count = 1;
        char preChar = str.charAt(0);
        StringBuffer resStrBuf = new StringBuffer();
        for(int i = 1;i < str.length();i++){
            char curC = str.charAt(i);
            if(curC == preChar){
                count++;
            }else{
                resStrBuf.append(count);
                resStrBuf.append(preChar);
                count = 1;
                preChar = curC;
            }
        }
        resStrBuf.append(count);
        resStrBuf.append(preChar);
        return resStrBuf.toString();
    }


    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0){
            return "";
        }
        String ggzfc=strs[0];
        for(int a=0;a<strs.length;a++){
            while(strs[a].indexOf(ggzfc) !=0){
                ggzfc=ggzfc.substring(0,ggzfc.length()-1);
                if(ggzfc.length()==0){
                    return "";
                }
            }
        }
        return ggzfc;
    }

    public static void main(String[] args) {
        System.out.println('w' <0 );
        System.out.println('w' >9 );
        System.out.println('w' != '+');
        System.out.println( 'w' != '-');
    }
}
