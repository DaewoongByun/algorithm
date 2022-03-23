/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
  const sum = Array.from(nums, () => 0);
  for (let i = sum.length - 1; i >= 0; i--) {
    if (i === sum.length - 1) sum[i] = nums[i];
    else sum[i] = nums[i] + sum[i + 1];
  }

  let result = -Infinity;
  let minValue = 0;
  for (let i = sum.length - 1; i >= 0; i--) {
    result = Math.max(result, sum[i] - minValue);
    if (sum[i] < 0) minValue = Math.min(minValue, sum[i]);
  }
  console.log(result);
  return result;
};

maxSubArray([5, 4, -1, 7, 8]);
