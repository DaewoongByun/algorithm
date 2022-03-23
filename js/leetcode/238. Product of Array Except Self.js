/**
 * @param {number[]} nums
 * @return {number[]}
 */
var productExceptSelf = function (nums) {
  const p = nums.reduce((prev, cur) => prev * cur, 1);
  return nums.map((num, i) => {
    if (num === 0) {
      return getProduct(nums, i);
    } else return p / num;
  });
};

function getProduct(nums, idx) {
  return nums.reduce((prev, cur, i) => (i === idx ? prev : prev * cur), 1);
}
