/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
  let minPrice = prices[0];
  let result = 0;
  for (let i = 1; i < prices.length; i++) {
    const price = prices[i];
    result = Math.max(result, price - minPrice);
    minPrice = Math.min(minPrice, price);
  }
  return result;
};
