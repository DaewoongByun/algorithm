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
// let [first, ...inputs] = fs
//   .readFileSync("/dev/stdin")
//   .toString()
//   .trim()
//   .split("\n");

let [first, ...inputs] = `5 4
3 1
3 2
4 3
5 3`
  .trim()
  .split("\n");

const [N, M] = first.split(" ").map((n) => +n);
const graph = Array.from(new Array(N + 1), () => []);

inputs.forEach((line) => {
  const [A, B] = line.split(" ").map((n) => +n);
  graph[B].push(A);
});

let max = 0;
let maxArr = [];
const visit = Array.from(new Array(N + 1), () => false);

for (let start = 1; start <= N; start++) {
  if (visit[start]) continue;
  const count = getCount(start);
  if (max < count) {
    max = count;
    maxArr = [start];
  } else if (max === count) {
    maxArr.push(start);
  }
}

function getCount(start) {
  const q = new Queue();
  const v = Array.from(new Array(N + 1), () => false);
  q.push(start);
  let count = 1;
  v[start] = true;
  visit[start] = true;

  while (q.length > 0) {
    const cur = q.pop();
    graph[cur].forEach((to) => {
      if (!v[to]) {
        v[to] = true;
        visit[to] = true;
        q.push(to);
        count++;
      }
    });
  }
  return count;
}
maxArr.sort((a, b) => a - b);
console.log(maxArr.join(" "));
