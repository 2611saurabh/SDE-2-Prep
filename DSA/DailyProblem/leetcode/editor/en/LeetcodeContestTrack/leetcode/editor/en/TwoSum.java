package DailyProblem.leetcode.editor.en;//You are given an array of integers nums and an integer target, return indices
//of the two numbers such that they add up to target.
//
// You may assume that each input would have exactly one solution, and you may
//not use the same element twice.
//
// You can return the answer in any order.
//
//
// Example 1:
//
//
//Input: nums = [2,7,11,15], target = 9
//Output: [0,1]
//Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
//
//
// Example 2:
//
//
//Input: nums = [3,2,4], target = 6
//Output: [1,2]
//
//
// Example 3:
//
//
//Input: nums = [3,3], target = 6
//Output: [0,1]
//
//
//
// Constraints:
//
//
// 2 <= nums.length <= 10⁴
// -10⁹ <= nums[i] <= 10⁹
// -10⁹ <= target <= 10⁹
// Only one valid answer exists.
//
//
//
//Follow-up: Can you come up with an algorithm that is less than
//O(n²)
// time complexity?
//
// Related Topics Array Hash Table 👍 69587 👎 2593


import java.util.Arrays;
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int[] ans = new int[2];

        for(int i = 0; i < nums.length; i++){

            map.put(target - nums[i], i);

            if(map.containsKey(nums[i])){
                ans[0] = i;
                ans[1] = map.get(nums[i]);
            }

        }

        return ans;


    }
}

public class TwoSum{

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        Solution1 solution = new Solution1();

        int[] result = solution.twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/*
Problem:
Two Sum

Pattern:
HashMap / Complement Lookup

My Approach:
Store complement first, then check current number.

What Went Wrong:
I inserted before checking.

Why:
The current element became available to match with itself.

Correct Pattern:
Check complement first → insert current number.

Trigger:
"Find a previous element that combines with current element."

Mistake to Avoid:
Never let current element enter the lookup structure
before checking when two different elements are required.
 */
