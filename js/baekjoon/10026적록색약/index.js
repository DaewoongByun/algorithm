const fs = require("fs");

const [first, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [first, ...inputs] = `5
// RRRBB
// GGBBB
// BBBRR
// BBRRR
// RRRRR`.split("\n");

const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];
const N = +first;
const map = inputs.map((line) => line.split(""));
const v1 = Array.from(new Array(N), () =>
  Array.from(new Array(N), () => false)
);
const v2 = Array.from(new Array(N), () =>
  Array.from(new Array(N), () => false)
);
const result = [0, 0];
for (let r = 0; r < N; r++) {
  for (let c = 0; c < N; c++) {
    if (!v1[r][c]) {
      func1(r, c);
      result[0]++;
    }
    if (!v2[r][c]) {
      func2(r, c);
      result[1]++;
    }
  }
}

function func1(r, c) {
  v1[r][c] = true;
  for (let d = 0; d < 4; d++) {
    const nr = r + dr[d];
    const nc = c + dc[d];
    if (
      nr >= 0 &&
      nr < N &&
      nc >= 0 &&
      nc < N &&
      !v1[nr][nc] &&
      map[nr][nc] === map[r][c]
    ) {
      func1(nr, nc);
    }
  }
}

function func2(r, c) {
  v2[r][c] = true;
  for (let d = 0; d < 4; d++) {
    const nr = r + dr[d];
    const nc = c + dc[d];
    if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v2[nr][nc]) {
      if (map[r][c] === "B" && map[nr][nc] === "B") func2(nr, nc);
      if (
        (map[r][c] === "G" || map[r][c] === "R") &&
        (map[nr][nc] === "R" || map[nr][nc] === "G")
      ) {
        func2(nr, nc);
      }
    }
  }
}

console.log(result.join(" "));
