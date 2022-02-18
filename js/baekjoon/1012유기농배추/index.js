const fs = require("fs");

const [first, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [first, ...inputs] = `2
// 10 8 17
// 0 0
// 1 0
// 1 1
// 4 2
// 4 3
// 4 5
// 2 4
// 3 4
// 7 4
// 8 4
// 9 4
// 7 5
// 8 5
// 9 5
// 7 6
// 8 6
// 9 6
// 10 10 1
// 5 5`.split("\n");

const T = +first;
const result = [];
for (let t = 0; t < T; t++) {
  const [M, N, K] = inputs
    .shift()
    .split(" ")
    .map((num) => +num);
  const map = Array.from(new Array(N), () => Array.from(new Array(M), () => 0));
  for (let i = 0; i < K; i++) {
    const [c, r] = inputs
      .shift()
      .split(" ")
      .map((num) => +num);
    map[r][c] = 1;
  }

  const count = getCount(map, N, M);
  result.push(count);
}

function getCount(map, N, M) {
  const dr = [-1, 0, 1, 0];
  const dc = [0, 1, 0, -1];
  function jirung2(r, c) {
    map[r][c] = 2;
    for (let d = 0; d < 4; d++) {
      const nr = r + dr[d];
      const nc = c + dc[d];
      if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] === 1) {
        jirung2(nr, nc);
      }
    }
  }
  let count = 0;
  for (let r = 0; r < N; r++) {
    for (let c = 0; c < M; c++) {
      if (map[r][c] === 1) {
        jirung2(r, c);
        count++;
      }
    }
  }
  return count;
}

console.log(result.join("\n"));
