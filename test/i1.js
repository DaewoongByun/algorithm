function solution(money, costs) {
  const minCosts = [...costs];
  for (let i = 1; i < costs.length; i++) {
    if (i % 2 === 0) {
      minCosts[i] = Math.min(costs[i - 1] * 2, costs[i]);
    } else {
      minCosts[i] = Math.min(costs[i - 1] * 5, costs[i]);
    }
  }

  let result = 0;
  for (let i = minCosts.length - 1; i >= 0; i--) {
    const coin = indexToCost(i);
    const count = parseInt(money / coin);
    const cost = minCosts[i];

    money -= count * coin;
    result += cost * count;
  }
  return result;
}

function indexToCost(i) {
  const result = {
    0: 1,
    1: 5,
    2: 10,
    3: 50,
    4: 100,
    5: 500,
  };
  return result[i];
}
