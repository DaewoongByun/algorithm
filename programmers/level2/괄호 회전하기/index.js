function solution(s) {
  let count = 0;
  let i = 0;
  while (i < s.length) {
    if (check(s, i)) {
      count++;
      i += 2;
    } else {
      i++;
    }
  }
  return count;
}

function check(s, i) {
  const arr = rotate(s, i);
  const stack = [];
  const pair = {
    "}": "{",
    "]": "[",
    ")": "(",
  };
  for (const ch of arr) {
    if (ch === "[" || ch === "(" || ch === "{") {
      stack.push(ch);
    } else {
      if (stack.length === 0) return false;
      if (stack.pop() !== pair[ch]) {
        return false;
      }
    }
  }
  if (stack.length === 0) return true;
  else return false;
}

function rotate(s, i) {
  if (i === 0) {
    return s.split("");
  } else {
    const arr = s.split("");
    return arr.slice(i).concat(arr.slice(0, i));
  }
}
