class Country {
  constructor(num, g, s, c) {
    this.num = num;
    this.g = g;
    this.s = s;
    this.c = c;
  }
}

let fs = require("fs");
let [firstLine, ...input] = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// let inputString = `4 1
// 1 1 2 0
// 2 0 1 0
// 3 0 1 0
// 4 0 0 1`;
// let [firstLine, ...input] = inputString.trim().split("\n");
let [N, K] = firstLine.split(" ").map((n) => +n);

let countries = input.map((line) => {
  let [num, g, s, c] = line.split(" ").map((n) => +n);
  return new Country(num, g, s, c);
});

countries.sort((c1, c2) => {
  if (c1.g !== c2.g) return c2.g - c1.g;
  if (c1.s !== c2.s) return c2.s - c1.s;
  if (c1.c === c2.c) {
    if (c1.num === K) return -1;
    if (c2.num === K) return 1;
  }
  return c2.c - c1.c;
});

const rank = countries.findIndex((country) => country.num === K) + 1;
console.log(rank);
