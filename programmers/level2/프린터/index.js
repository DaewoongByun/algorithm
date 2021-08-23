function solution(priorities, location) {
  let count = 1;
  while (priorities.length > 0) {
    if (priorities.length === 1) {
      return count;
    }
    if (canPrint(priorities)) {
      if (location === 0) {
        return count;
      }
      priorities.shift();
      location--;
      count++;
    } else {
      priorities.push(priorities.shift());
      if (location === 0) {
        location = priorities.length - 1;
      } else {
        location--;
      }
    }
  }
}

function canPrint(priorities) {
  const num = priorities[0];
  for (let i = 1; i < priorities.length; i++) {
    if (num < priorities[i]) {
      return false;
    }
  }
  return true;
}

console.log(solution([1, 1, 9, 1, 1, 1], 0));
