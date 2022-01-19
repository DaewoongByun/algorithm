function getNextLocation(map, r, c, d) {
  const dr = [-1, 0, 1, 0];
  const dc = [0, 1, 0, -1];

  for (let i = 0; i < 4; i++) {
    d = (d + 3) % 4;
    let [nr, nc] = [r + dr[d], c + dc[d]];
    if (map[nr][nc] === 0) {
      return [nr, nc, d];
    }
  }

  let back = (d + 2) % 4;
  let [nr, nc] = [r + dr[back], c + dc[back]];
  if (map[nr][nc] === 1) {
    return [0, 0, -1];
  } else {
    return [nr, nc, d];
  }
}

const fs = require("fs");
const [firstLine, secondLine, ...input] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [firstLine, secondLine, ...input] = `3 3
// 1 1 0
// 1 1 1
// 1 0 1
// 1 1 1`
//   .trim()
//   .split("\n");

const [N, M] = firstLine.split(" ").map((n) => +n);
let [r, c, d] = secondLine.split(" ").map((n) => +n);

let map = Array.from(input, (line) => line.split(" ").map((n) => +n));
let result = 0;

while (d >= 0) {
  if (map[r][c] === 0) {
    map[r][c] = 2;
    result++;
  }
  [r, c, d] = getNextLocation(map, r, c, d);
}
console.log(result);
