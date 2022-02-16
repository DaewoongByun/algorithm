const fs = require("fs");
const [firstLine, ...input] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [firstLine, ...input] = `4 5 1
// 1 2
// 1 3
// 1 4
// 2 4
// 3 4`
//   .trim()
//   .split("\n");

const [N, M, V] = firstLine.split(" ").map((num) => +num);
const graph = [[]];
for (let i = 1; i <= N; i++) {
  graph[i] = [];
}

input.forEach((line) => {
  const [from, to] = line.split(" ").map((num) => +num);
  graph[from].push(to);
  graph[to].push(from);
});

graph.forEach((arr) => {
  arr.sort((a, b) => a - b);
});
(function dfs() {
  const stack = [];
  stack.push(V);
  const result = [];
  const v = Array.from(new Array(N + 1), () => false);
  while (stack.length > 0) {
    const cur = stack.pop();
    if (!v[cur]) {
      result.push(cur);
      v[cur] = true;
      // add to stack
      const toArr = graph[cur];
      toArr.reverse().forEach((to) => {
        if (!v[to]) stack.push(to);
      });
    }
  }
  console.log(result.join(" "));
})();

(function bfs() {
  const queue = [];
  queue.push(V);
  const result = [];
  const v = Array.from(new Array(N + 1), () => false);
  v[V] = true;
  while (queue.length > 0) {
    const cur = queue.shift();
    result.push(cur);

    const toArr = graph[cur];
    toArr.reverse().forEach((to) => {
      if (!v[to]) {
        queue.push(to);
        v[to] = true;
      }
    });
  }
  console.log(result.join(" "));
})();
