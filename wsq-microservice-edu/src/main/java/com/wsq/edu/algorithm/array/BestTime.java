package com.wsq.edu.algorithm.array;

/**
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * @author xyzzg
 * @version 1.0
 * @date 2019-12-30 16:37
 */
public class BestTime {

    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        //买入的条件一定是有波动的 即 低买高出
        //只在低点买入，高点卖出。所以设置 i, j两个指针，只累计nums[j]比nums[i]大的数据，其余的不加（加0）。

        int price = 0;
        int i = 0;
        int j = 1;

        while (j < prices.length){
            if ( prices[j] > prices[i] ){
                price += prices[j] - prices[i];
            }
            i++;
            j++;
        }
        //判断后面有没有大于它的
        return price;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }

}
