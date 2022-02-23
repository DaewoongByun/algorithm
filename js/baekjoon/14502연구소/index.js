const fs = require("fs");
const [first, ...input] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [first, ...input] = `8 8
// 2 0 0 0 0 0 0 2
// 2 0 0 0 0 0 0 2
// 2 0 0 0 0 0 0 2
// 2 0 0 0 0 0 0 2
// 2 0 0 0 0 0 0 2
// 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0`.split("\n");

const [N, M] = first.split(" ").map((n) => +n);
const empty = [];
const virus = [];

const map = input.map((line, r) =>
  line.split(" ").map((n, c) => {
    if (+n === 0) empty.push([r, c]);
    else if (+n === 2) virus.push([r, c]);
    return +n;
  })
);
const sel = [[], [], []];
let count = 0;
function combi(start, index, sel) {
  if (sel.length === index) {
    count = Math.max(count, getCount(sel));
    return;
  }
  for (let i = start; i < empty.length; i++) {
    sel[index] = empty[i];
    combi(i + 1, index + 1, sel);
  }
}

function getCount(sel) {
  const copyMap = JSON.parse(JSON.stringify(map));
  sel.forEach(([r, c]) => {
    copyMap[r][c] = 1;
  });
  virus.forEach(([r, c]) => {
    spread(copyMap, r, c);
  });
  return count0(copyMap);
}

const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];
function spread(map, r, c) {
  map[r][c] = 2;
  for (let d = 0; d < 4; d++) {
    const nr = r + dr[d];
    const nc = c + dc[d];
    if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] === 0) {
      spread(map, nr, nc);
    }
  }
}

function count0(map) {
  let count = 0;
  for (let r = 0; r < N; r++) {
    for (let c = 0; c < M; c++) {
      if (map[r][c] === 0) count++;
    }
  }
  return count;
}

combi(0, 0, sel);
console.log(count);
