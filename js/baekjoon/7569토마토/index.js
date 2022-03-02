const fs = require("fs");

const [first, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [first, ...inputs] = `5 3 1
// 0 -1 0 0 0
// -1 -1 0 1 1
// 0 0 0 1 1`.split("\n");

const [M, N, H] = first.split(" ").map((n) => +n);

const map = Array.from(new Array(H), () =>
  Array.from(new Array(N), () => Array.from(new Array(M), () => 0))
);

let queue = [];

for (let h = 0; h < H; h++) {
  for (let r = 0; r < N; r++) {
    const line = inputs[h * N + r];
    map[h][r] = line.split(" ").map((n, c) => {
      if (+n === 1) {
        queue.push([h, r, c]);
      }
      return +n;
    });
  }
}

const dr = [-1, 0, 1, 0, 0, 0];
const dc = [0, 1, 0, -1, 0, 0];
const dh = [0, 0, 0, 0, -1, 1];

let result = 0;

while (queue.length) {
  const len = queue.length;
  const tmp = [...queue];
  queue = [];
  for (let i = 0; i < len; i++) {
    const [h, r, c] = tmp.pop();
    for (let d = 0; d < 6; d++) {
      const nh = h + dh[d];
      const nr = r + dr[d];
      const nc = c + dc[d];
      if (
        nh >= 0 &&
        nh < H &&
        nr >= 0 &&
        nr < N &&
        nc >= 0 &&
        nc < M &&
        map[nh][nr][nc] === 0
      ) {
        map[nh][nr][nc] = 1;
        queue.push([nh, nr, nc]);
      }
    }
  }
  result++;
}

function isEnd() {
  for (let h = 0; h < H; h++) {
    for (let r = 0; r < N; r++) {
      for (let c = 0; c < M; c++) {
        if (map[h][r][c] === 0) return false;
      }
    }
  }
  return true;
}

console.log(isEnd() ? result - 1 : -1);
