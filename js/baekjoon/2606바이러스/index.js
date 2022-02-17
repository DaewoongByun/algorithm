const fs = require("fs");

const [first, second, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [first, second, ...inputs] = `7
// 6
// 1 2
// 2 3
// 1 5
// 5 2
// 5 6
// 4 7`.split("\n");

const N = +first;
const M = +second;

const graph = Array.from(new Array(N + 1), () => []);
inputs.forEach((input) => {
  const [from, to] = input.split(" ").map((num) => +num);
  graph[from].push(to);
  graph[to].push(from);
});

const v = Array.from(new Array(N + 1), () => false);

let count = 0;

function dfs(num) {
  count++;
  v[num] = true;

  graph[num].forEach((to) => {
    if (!v[to]) dfs(to);
  });
}
dfs(1);

console.log(count - 1);
