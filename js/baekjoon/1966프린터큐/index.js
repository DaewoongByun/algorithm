const fs = require("fs");
const [firstLine, ...input] = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// const [firstLine, ...input] = `3
// 1 0
// 5
// 4 2
// 1 2 3 4
// 6 0
// 1 1 9 1 1 1`
//   .trim()
//   .split("\n");

const T = +firstLine;
const result = [];

for (let i = 0; i < T; i++) {
  const first = input.shift();
  const second = input.shift();

  const [N, M] = first.split(" ").map((n) => +n);
  const importances = second.split(" ").map((n, i) => ({ index: i, importance: +n }));

  let max = Math.max(...importances.map((i) => i.importance));
  let turn = 1;
  while (importances.length) {
    const importance = importances.shift();
    if (importance.importance === max) {
      if (importance.index === M) {
        result.push(turn);
        break;
      }
      turn++;
      max = Math.max(...importances.map((i) => i.importance));
    } else {
      importances.push(importance);
    }
  }
}

console.log(result.join("\n"));
