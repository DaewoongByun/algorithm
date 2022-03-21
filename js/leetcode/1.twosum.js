var twoSum = function (nums, target) {
  const map = new Map();
  nums.forEach((num, i) => {
    map.set(num, i);
  });
  for (let i = 0; i < nums.length; i++) {
    const num = nums[i];
    const rest = target - num;
    if (map.has(rest)) {
      if (i !== map.get(rest)) {
        return [i, map.get(rest)];
      }
    }
  }
};
