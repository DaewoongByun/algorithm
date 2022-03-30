const fs = require("fs");
const [first, ...inputs] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// const [first, ...inputs] = `2
// 3
// 911
// 97625999
// 91125426
// 5
// 113
// 12340
// 123440
// 12345
// 98346`.split("\n");

const T = +first;
let index = 0;
for (let t = 0; t < T; t++) {
  const N = +inputs[index];
  const numbers = inputs.slice(index + 1, index + N + 1);
  index += 1 + N;

  console.log(getResult(numbers));
}

function getResult(numbers) {
  numbers.sort();
  for (let i = 0; i < numbers.length - 1; i++) {
    const str1 = numbers[i];
    const str2 = numbers[i + 1];
    if (str1.length === str2.length) continue;
    if (str2.search(str1) === 0) return "NO";
  }
  return "YES";
}
