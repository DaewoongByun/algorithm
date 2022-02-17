const fs = require("fs");

const [firstLine, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [firstLine, ...inputs] = `7
// 0110100
// 0110101
// 1110101
// 0000111
// 0100000
// 0111110
// 0111000`.split("\n");

const N = +firstLine;
const map = inputs.map((line) => line.split("").map((num) => -num));

const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];

let townNum = 1;
let townCount = 0;
let count = 0;
let result = [];

for (let r = 0; r < N; r++) {
  for (let c = 0; c < N; c++) {
    if (map[r][c] === -1) {
      count = 0;
      setTownNum(r, c);
      townCount++;
      result.push(count);
      townNum++;
    }
  }
}

function setTownNum(r, c) {
  map[r][c] = townNum;
  count++;
  for (let d = 0; d < 4; d++) {
    const nr = r + dr[d];
    const nc = c + dc[d];
    if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] === -1)
      setTownNum(nr, nc);
  }
}

console.log(townCount);
console.log(result.sort((a, b) => a - b).join("\n"));
