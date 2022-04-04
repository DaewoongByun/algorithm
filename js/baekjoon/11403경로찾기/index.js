class Queue {
  #resetSize = 10000;
  #arr;
  #firstIndex;
  length;

  constructor() {
    this.#resetSize = 10000;
    this.#arr = [];
    this.#firstIndex = 0;
    this.length = 0;
  }

  push(item) {
    this.#arr.push(item);
    this.length++;
  }

  pop() {
    if (this.length === 0) return undefined;
    const value = this.#arr[this.#firstIndex++];
    if (this.#firstIndex === this.#resetSize) {
      this.#arr = this.#arr.slice(this.#resetSize);
      this.#firstIndex = 0;
    }
    this.length--;
    return value;
  }
}

const fs = require("fs");
let [first, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// let [first, ...inputs] = `3
// 0 1 0
// 0 0 1
// 1 0 0`
//   .trim()
//   .split("\n");

const N = +first;
const graph = Array.from(new Array(N), () => []);
inputs.forEach((line, r) => {
  line.split(" ").forEach((v, c) => {
    if (v === "1") {
      graph[r].push(c);
    }
  });
});
const result = Array.from(new Array(N), () =>
  Array.from(new Array(N), () => 0)
);

for (let start = 0; start < N; start++) {
  go(start);
}

function go(start) {
  const q = new Queue();
  const v = Array.from(new Array(N), () => false);
  q.push(start);

  while (q.length > 0) {
    const cur = q.pop();
    graph[cur].forEach((to) => {
      if (!v[to]) {
        v[to] = true;
        q.push(to);
        result[start][to] = 1;
      }
    });
  }
}
console.log(result.map((arr) => arr.join(" ")).join("\n"));
