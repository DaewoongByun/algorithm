const fs = require("fs");

const [line] = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// const line = "7 218";
const [D, K] = line.split(" ").map((_) => +_);

const 계수 = [
  [1, 0],
  [0, 1],
];
for (let day = 2; day < D; day++) {
  const cur = [
    계수[day - 2][0] + 계수[day - 1][0],
    계수[day - 2][1] + 계수[day - 1][1],
  ];
  계수.push(cur);
}

const result = getResult(계수.pop());
console.log(result.join("\n"));

function getResult(total) {
  for (let A = 1; A < K; A++) {
    for (let B = A; B < K; B++) {
      if (A * total[0] + B * total[1] === K) return [A, B];
    }
  }
  return [0, 0];
}

/*
    첫째날 : A
    둘째날 : B
    셋째날 : A + B
    넷째날 : A + 2B
    다섯째날 : 2A + 3B
    여섯째날 : 3A + 5B
*/
