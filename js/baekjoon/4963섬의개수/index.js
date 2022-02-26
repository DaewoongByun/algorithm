const fs = require("fs");
const [...inputs] = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// const [...inputs] = `1 1
// 0
// 2 2
// 0 1
// 1 0
// 3 2
// 1 1 1
// 1 1 1
// 5 4
// 1 0 1 0 0
// 1 0 0 0 0
// 1 0 1 0 1
// 1 0 0 1 0
// 5 4
// 1 1 1 0 1
// 1 0 1 0 1
// 1 0 1 0 1
// 1 0 1 1 1
// 5 5
// 1 0 1 0 1
// 0 0 0 0 0
// 1 0 1 0 1
// 0 0 0 0 0
// 1 0 1 0 1
// 0 0`.split("\n");

let index = 0;
const result = [];

while (true) {
  const [C, R] = inputs[index++].split(" ").map((n) => +n);
  if (R === 0 && C === 0) break;
  const map = inputs
    .slice(index, index + R)
    .map((line) => line.split(" ").map((n) => +n));

  result.push(getCountRand(R, C, map));
  index += R;
}

function getCountRand(R, C, map) {
  const dr = [-1, -1, -1, 0, 1, 1, 1, 0];
  const dc = [-1, 0, 1, 1, 1, 0, -1, -1];

  function countRand(map, r, c) {
    map[r][c] = 2;
    for (let d = 0; d < 8; d++) {
      const nr = r + dr[d];
      const nc = c + dc[d];
      if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] === 1) {
        countRand(map, nr, nc);
      }
    }
  }

  let count = 0;
  for (let r = 0; r < R; r++) {
    for (let c = 0; c < C; c++) {
      if (map[r][c] === 1) {
        countRand(map, r, c);
        count++;
      }
    }
  }
  return count;
}

console.log(result.join("\n"));
