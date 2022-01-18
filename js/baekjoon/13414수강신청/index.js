let fs = require("fs");
let [firstLine, ...input] = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// let str = `3 8
// 20103324
// 20133221
// 20133221
// 20093778
// 20140101
// 01234567
// 20093778
// 20103325`;
// let [firstLine, ...input] = str.trim().split("\n");

let [K, L] = firstLine.split(" ").map((n) => +n);

const set = new Set();

input.forEach((id) => {
  if (set.has(id)) set.delete(id);
  set.add(id);
});

const result = [];
for (const id of set) {
  result.push(id);
  if (--K === 0) break;
}
console.log(result.join("\n"));
