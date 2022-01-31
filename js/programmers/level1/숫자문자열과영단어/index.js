function solution(s) {
  let result = "";
  const map = {
    one: 1,
    two: 2,
    three: 3,
    four: 4,
    five: 5,
    six: 6,
    seven: 7,
    eight: 8,
    nine: 9,
    zero: 0,
  };

  let arr = s.split("");
  let temp = "";
  while (arr.length) {
    temp += arr.shift();
    if (isNumber(temp)) {
      result += temp;
      temp = "";
      continue;
    }

    if (map[temp] !== undefined) {
      result += map[temp];
      temp = "";
    }
  }
  return +result;
}

function isNumber(s) {
  if (+s >= 0 && +s <= 9) return true;
  return false;
}

console.log(solution("seven7zero0seven"));
