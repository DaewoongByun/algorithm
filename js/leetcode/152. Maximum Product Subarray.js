/**
 * @param {number[]} nums
 * @return {number}
 */
var maxProduct = function (nums) {
  let max = -Infinity;
  for (let i = 0; i < nums.length; i++) {
    let cur = nums[i];
    max = Math.max(max, cur);
    for (let j = i + 1; j < nums.length; j++) {
      cur *= nums[j];
      max = Math.max(max, cur);
    }
  }
  return max;
};
