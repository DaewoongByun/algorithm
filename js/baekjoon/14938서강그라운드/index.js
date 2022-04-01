class PriorityQueue {
  #arr;
  #maxSize;
  #comp;
  size;

  constructor(comp) {
    this.#maxSize = 10;
    this.#arr = Array.from(new Array(this.#maxSize), () => undefined);
    this.#comp = comp;
    this.size = 0;

    if (!comp) {
      this.#comp = (a, b) => a - b;
    }
  }

  #swap(i1, i2) {
    const temp = this.#arr[i1];
    this.#arr[i1] = this.#arr[i2];
    this.#arr[i2] = temp;
  }

  #expandArr() {
    this.#arr = [
      ...this.#arr,
      ...Array.from(new Array(this.#maxSize), () => undefined),
    ];
    this.#maxSize *= 2;
  }

  #getMinIndex(left, right) {
    if (this.#arr[left] === undefined && this.#arr[right] === undefined)
      return undefined;
    else if (this.#arr[right] === undefined) {
      return left;
    } else {
      if (this.#comp(this.#arr[left], this.#arr[right]) <= 0) {
        return left;
      } else {
        return right;
      }
    }
  }

  push(item) {
    if (this.size === this.#maxSize - 1) this.#expandArr();
    this.#arr[++this.size] = item;
    let index = this.size;
    while (index > 1) {
      const parentIndex = parseInt(index / 2);
      const parent = this.#arr[parentIndex];
      if (this.#comp(item, parent) < 0) {
        this.#swap(index, parentIndex);
        index = parentIndex;
      } else break;
    }
  }

  pop() {
    if (this.size === 0) return undefined;
    const value = this.#arr[1];
    this.#arr[1] = this.#arr[this.size];
    this.#arr[this.size] = undefined;
    this.size--;

    let index = 1;
    while (true) {
      const minIndex = this.#getMinIndex(index * 2, index * 2 + 1);
      if (minIndex === undefined) break;
      if (this.#comp(this.#arr[index], this.#arr[minIndex]) > 0) {
        this.#swap(index, minIndex);
        index = minIndex;
      } else break;
    }

    return value;
  }
}

const fs = require("fs");
let [first, second, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// let [first, second, ...inputs] = `5 5 4
// 5 7 8 2 3
// 1 4 5
// 5 2 4
// 3 2 3
// 1 2 3`
//   .trim()
//   .split("\n");

const [N, M, R] = first.split(" ").map((_) => +_);
const graph = Array.from(new Array(N), () => []);
const itemCount = second.split(" ").map((_) => +_);

inputs.forEach((line) => {
  const [from, to, cost] = line.split(" ").map((_) => +_);
  graph[from - 1].push([to - 1, cost]);
  graph[to - 1].push([from - 1, cost]);
});

let answer = 0;

for (let start = 0; start < N; start++) {
  const result = getResult(start);
  answer = Math.max(answer, result);
}

function getResult(start) {
  let count = 0;
  const pq = new PriorityQueue((a, b) => a[1] - b[1]);
  const v = Array.from(new Array(N), () => false);
  pq.push([start, 0]);

  while (pq.size > 0) {
    const [cur, cost] = pq.pop();
    if (v[cur]) continue;
    if (cost > M) break;
    count += itemCount[cur];
    v[cur] = true;

    graph[cur].forEach(([to, toCost]) => {
      if (!v[to]) {
        pq.push([to, cost + toCost]);
      }
    });
  }

  return count;
}

console.log(answer);
