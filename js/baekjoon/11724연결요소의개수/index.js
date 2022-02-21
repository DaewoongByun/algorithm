const fs = require("fs");

const [first, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [first, ...inputs] = `6 8
// 1 2
// 2 5
// 5 1
// 3 4
// 4 6
// 5 4
// 2 4
// 2 3`.split("\n");

const [N, M] = first.split(" ").map((n) => +n);

const graph = Array.from(new Array(N + 1), () => []);

inputs.forEach((line) => {
  const [from, to] = line.split(" ").map((n) => +n);
  graph[from].push(to);
  graph[to].push(from);
});

const v = Array.from(new Array(N + 1), () => false);
let result = 0;

for (let node = 1; node <= N; node++) {
  if (!v[node]) {
    connect(node);
    result++;
  }
}

function connect(node) {
  v[node] = true;
  graph[node].forEach((to) => {
    if (!v[to]) connect(to);
  });
}

console.log(result);
