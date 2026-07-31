package Array;

class Solution {

    public int removeDuplicates(int[] nums) {
        int prev = 0;

        int i = 0;
        int j = 0;
        int n = nums.length;

        while(j < n){

            if(nums[i] == nums[j]){
                j++;
            }
            else if(nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];

                j++;
            }
        }
        return i + 1;

    }

    public void swap(int[] nums, int i, int j){

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

public class RemoveDUplicateFromSortedArray {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = {0,0,1,1,1,2,2,3,3,4};

        solution.removeDuplicates(nums);
    }


}
