package dynamicProgramming;

import java.util.*;

class Solution309{

    int[][] t = new int[5001][2];
    public int maxProfit(int[] prices){

        int n = prices.length;
        int buy = 1;
        int day = 0;

        for(int[] row : t) Arrays.fill(row, -1);

        return solve(prices, day, n, buy);
    }

    public int solve(int[] prices, int day, int n, int buy){

        if(day >= n) return 0;

        int profit = 0;

        if(t[day][buy] != -1){
            return t[day][buy];
        }

        if(buy == 1){
            //today we buy stock then unable to buy again next day so we passsing
            //buy as false and profit amount is buy price of today stock - sell price
            // in future
            int buy_stock = solve(prices, day + 1, n, 0) - prices[day];

            //not to buy
            //if we not buying today then we going to buy on next day so marking
            //buy is true
            int notToBuy = solve(prices, day + 1, n, 1);

            profit = Math.max(buy_stock, notToBuy);
        }
        else{

            //sell stock
            //today we sell stock so today price of stock is added and then we do day + 2
            // because we cant buy againg on next day if you already sell today cooldown period
            int sellStock = prices[day] + solve(prices, day + 2, n, 1);

            // today we unable to sell stock so we going to sell stock on next day that's
            //why we restricting to buy stock
            int notSellStock = solve(prices, day + 1, n, 0);

            profit = Math.max(sellStock, notSellStock);

        }

        return t[day][buy]=profit;
    }
}
public class BuySellStockWithCooldown {

    public static void main(String[] args) {
        Solution309 obj = new Solution309();

        int[] prices = {1,2,3,0,2};

        int profit = obj.maxProfit(prices);
        System.out.println(profit);
    }

}
