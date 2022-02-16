const fs = require("fs");
const [firstLine, ...input] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [firstLine, ...input] = `7 7
// 1011111
// 1110001
// 1000001
// 1000001
// 1000001
// 1000001
// 1111111`
//   .trim()
//   .split("\n");

const [N, M] = firstLine.split(" ").map((num) => +num);
const map = input.map((line) => line.split("").map((num) => +num));

(function bfs() {
  const queue = [];
  const v = Array.from(new Array(N), () =>
    Array.from(new Array(M), () => false)
  );
  const dr = [-1, 0, 1, 0];
  const dc = [0, 1, 0, -1];

  v[0][0] = true;
  queue.push([0, 0, 1]);

  while (queue.length > 0) {
    const cur = queue.shift();
    for (let d = 0; d < 4; d++) {
      const nr = cur[0] + dr[d];
      const nc = cur[1] + dc[d];
      if (nr === N - 1 && nc === M - 1) {
        console.log(cur[2] + 1);
        return;
      }
      if (
        nr >= 0 &&
        nr < N &&
        nc >= 0 &&
        nc < M &&
        map[nr][nc] === 1 &&
        !v[nr][nc]
      ) {
        v[nr][nc] = true;
        queue.push([nr, nc, cur[2] + 1]);
      }
    }
  }
})();
