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
const [l1, l2, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [l1, l2, ...inputs] = `5 6
// 1
// 5 1 1
// 1 2 2
// 1 3 3
// 2 3 4
// 2 4 5
// 3 4 6`
//   .trim()
//   .split("\n");

const [V, E] = l1.split(" ").map((n) => +n);
const K = +l2;

const graph = Array.from(new Array(V + 1), () => []);
inputs.forEach((input) => {
  const [from, to, weight] = input.split(" ").map((n) => +n);
  graph[from].push([to, weight]);
});

const result = Array.from(new Array(V + 1), () => "INF");
let count = V;

const pq = new PriorityQueue((a, b) => a[1] - b[1]);
const v = Array.from(new Array(V + 1), () => false);
pq.push([K, 0]);

while (pq.size > 0) {
  const cur = pq.pop();
  if (v[cur[0]]) continue;
  result[cur[0]] = cur[1];
  v[cur[0]] = true;
  count--;
  if (count === 0) break;

  graph[cur[0]].forEach(([to, weight]) => {
    if (!v[to]) pq.push([to, cur[1] + weight]);
  });
}

console.log(result.slice(1).join("\n"));
