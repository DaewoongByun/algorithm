const fs = require("fs");

const [first, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [first, ...inputs] = `2 2
// 1 -1
// -1 1`.split("\n");

const [M, N] = first.split(" ").map((num) => +num);
let queue = [];
let tomatoCount = 0;
const map = inputs.map((line, r) =>
  line.split(" ").map((num, c) => {
    if (+num === 1) queue.push([r, c]);
    else if (+num === 0) tomatoCount++;
    return +num;
  })
);

let result = 0;

if (tomatoCount === 0) {
  console.log(0);
} else {
  while (queue.length > 0) {
    const dr = [-1, 0, 1, 0];
    const dc = [0, 1, 0, -1];
    //   const v = Array.from(new Array(N), () =>
    //     Array.from(new Array(M), () => false)
    //   );
    const temp = [...queue];
    const T = temp.length;
    queue = [];
    for (let t = 0; t < T; t++) {
      const cur = temp.pop();
      for (let d = 0; d < 4; d++) {
        const nr = cur[0] + dr[d];
        const nc = cur[1] + dc[d];
        if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] === 0) {
          map[nr][nc] = 1;
          queue.push([nr, nc]);
          tomatoCount--;
        }
      }
    }
    result++;
  }
  if (tomatoCount === 0) console.log(result - 1);
  else console.log(-1);
}
