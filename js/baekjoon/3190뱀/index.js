const fs = require("fs");

let [N, K, ...input] = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
// let [N, K, ...input] = `10
// 4
// 1 2
// 1 3
// 1 4
// 1 5
// 4
// 8 D
// 10 D
// 11 D
// 13 L`
//   .trim()
//   .split("\n");

[N, K] = [+N, +K];
const map = Array.from(Array(N), () => Array.from(Array(N), () => 0));

for (let i = 0; i < K; i++) {
  [r, c] = input
    .shift()
    .split(" ")
    .map((n) => +n - 1);
  map[r][c] = 9;
}
const L = +input.shift();

const moves = input.map((line) => line.split(" ").map((n, i) => (i === 0 ? +n : n)));

const snake = [[0, 0]];
let d = 1;
map[0][0] = 1;
const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];
let time = 0;
while (true) {
  let [nr, nc] = [snake[0][0] + dr[d], snake[0][1] + dc[d]];
  if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] === 1) {
    console.log(time + 1);
    break;
  }
  snake.unshift([nr, nc]);
  if (map[nr][nc] === 0) {
    const tail = snake.pop();
    map[tail[0]][tail[1]] = 0;
  }
  map[nr][nc] = 1;
  time++;
  if (moves.length && moves[0][0] === time) {
    const [_, dir] = moves.shift();
    if (dir === "D") {
      d = (d + 1) % 4;
    } else {
      d = (d + 3) % 4;
    }
  }
}
