package com.tyy;

import java.util.Arrays;

public class test {
	
    public static int[] twoSum(int[] nums, int target) {
        int original[] = nums.clone(); 
        Arrays.sort(nums);
        int lp = 0;
        int rp = nums.length-1;
        int tmp = -1;
        while(lp!=rp){
            if(nums[lp]+nums[rp]<target){
                lp++;
            }
            else if(nums[lp]+nums[rp]>target){
                rp--;
            }
            else{
                tmp = nums[lp];
                break;
            }
        }
        int p1=-1;
        int p2=-1;
        for(int i = 0;i<original.length;i++){
            if(original[i]==tmp){
                p1 = i;
                break;
            }
        }
        for(int i = 0;i<original.length;i++){
            if(original[i]==target - tmp&&i!=p1){
                p2 = i;
                break;
            }
        }
        int[] result = {p1,p2};
        return result;
    }
	
	public static void main (String args[]) {
		int[] nums = {3,3};
		int target = 6;
		System.out.println(twoSum(nums,target));
	}
}
