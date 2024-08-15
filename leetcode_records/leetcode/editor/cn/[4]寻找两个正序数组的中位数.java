//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics 数组 二分查找 分治 👍 6452 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int s1_length = nums1.length;
        int s2_length = nums2.length;

        int totalCnt = s1_length + s2_length;
        int index = totalCnt / 2;

        int i = 0, j = 0;
        while (i < s1_length && j < s2_length) {
            if (i + j == index) {
                //两个数组长度之和为奇数
                if (totalCnt % 2 != 0) {

                    return                        
                } else {
                    // 两个数组长度之和为偶数
                }
            }

            if (nums1[i] < num2[j]) {
                i++;
            } else {
                j++;
            }
        }


    }
}
//leetcode submit region end(Prohibit modification and deletion)
