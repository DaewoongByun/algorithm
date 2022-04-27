const fs = require("fs");
let [str, findStr] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// let [str, findStr] = `aaaaaaa
// aa`
//   .trim()
//   .split("\n");

let result = 0;
let i = 0;
while (i < str.length) {
  const subStr = str.slice(i, i + findStr.length);
  if (subStr === findStr) {
    result++;
    i += findStr.length;
  } else {
    i++;
  }
}

console.log(result);
