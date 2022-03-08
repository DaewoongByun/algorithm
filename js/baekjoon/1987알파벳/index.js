const fs = require("fs");

const [first, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [first, ...inputs] = `5 5
// IEFCJ
// FHFKC
// FFALF
// HFGCF
// HMCHH`.split("\n");

const [R, C] = first.split(" ").map((n) => +n);
const map = inputs.map((line) => line.split(""));

const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];
let result = 1;

let v = {};
v[map[0][0]] = true;

function dfs(r, c, count) {
  const cur = map[r][c];

  let blocked = true;
  for (let d = 0; d < 4; d++) {
    const nr = r + dr[d];
    const nc = c + dc[d];
    if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
      const next = map[nr][nc];
      if (!v[next] && next !== cur) {
        v[next] = true;
        dfs(nr, nc, count + 1);
        v[next] = false;
        blocked = false;
      }
    }
  }
  if (blocked) result = Math.max(result, count);
}

dfs(0, 0, 1);
console.log(result);
