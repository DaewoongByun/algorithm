function swap(map, r1, c1, r2, c2) {
  let temp = map[r1][c1];
  map[r1][c1] = map[r2][c2];
  map[r2][c2] = temp;
}
function getMax(map, r1, c1, r2, c2) {
  const dr = [-1, 1, 0, 0];
  const dc = [0, 0, 1, -1];

  function getLineMax(map, r, c, dir) {
    let count = 1;
    let nr = r + dr[dir];
    let nc = c + dc[dir];

    while (nr >= 0 && nr < map.length && nc >= 0 && nc < map.length && map[r][c] === map[nr][nc]) {
      count++;
      nr += dr[dir];
      nc += dc[dir];
    }
    return count;
  }

  let max = 0;
  max = Math.max(getLineMax(map, r1, c1, 0) + getLineMax(map, r1, c1, 1) - 1, max);
  max = Math.max(getLineMax(map, r1, c1, 2) + getLineMax(map, r1, c1, 3) - 1, max);
  max = Math.max(getLineMax(map, r2, c2, 0) + getLineMax(map, r2, c2, 1) - 1, max);
  max = Math.max(getLineMax(map, r2, c2, 2) + getLineMax(map, r2, c2, 3) - 1, max);
  return max;
}

let fs = require("fs");
let [firstLine, ...input] = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// let str = `5
// YCPZY
// CYZZP
// CCPPP
// YCYZC
// CPPZZ`;
// let [firstLine, ...input] = str.trim().split("\n");

const N = +firstLine;
const map = input.map((line) => line.split(""));

let max = 0;

L: for (let r = 0; r < N; r++) {
  for (let c = 0; c < N; c++) {
    if (r + 1 < N) {
      swap(map, r, c, r + 1, c);
      max = Math.max(max, getMax(map, r, c, r + 1, c));
      swap(map, r, c, r + 1, c);
    }
    if (c + 1 < N) {
      swap(map, r, c, r, c + 1);
      max = Math.max(max, getMax(map, r, c, r, c + 1));
      swap(map, r, c, r, c + 1);
    }
    if (max === N) break L;
  }
}

console.log(max);
