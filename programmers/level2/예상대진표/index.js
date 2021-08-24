function solution(n, a, b) {
  return find(a, b, 1);
}

function find(a, b, round) {
  if (nextNumber(a) === nextNumber(b)) {
    return round;
  } else {
    return find(nextNumber(a), nextNumber(b), round + 1);
  }
}

function nextNumber(n) {
  if (n % 2 === 0) {
    return parseInt(n / 2);
  } else {
    return parseInt((n + 1) / 2);
  }
}
