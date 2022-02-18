const fs = require("fs");

const [N, K] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ")
  .map((num) => +num);

// const [N, K] = `5 17`.split(" ").map((num) => +num);

const v = {};

const queue = [[N, 0]];
v[N] = true;

const result = (function find() {
  if (N === K) {
    return 0;
  }
  while (queue.length > 0) {
    const [cur, time] = queue.shift();
    const k = [1, -1, cur];
    for (let i = 0; i < 3; i++) {
      const next = cur + k[i];
      if (next === K) return time + 1;
      if (next >= 0 && next <= 100000 && !v[next]) {
        v[next] = true;
        queue.push([next, time + 1]);
      }
    }
  }
})();

console.log(result);
