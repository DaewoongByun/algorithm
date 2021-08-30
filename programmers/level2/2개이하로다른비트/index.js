function solution(numbers) {
  var answer = [];

  for (let num of numbers) {
    answer.push(f(num));
  }

  return answer;
}

function f(num) {
  let numBin = num.toString(2);
  if (numBin[numBin.length - 1] === "0") return num + 1;
  else {
    let count1 = countLast1(numBin);
    if (count1 === 1) {
      return num + 1;
    } else {
      return num + 1 + parseInt("1".repeat(count1 - 1), 2);
    }
  }
}

function countLast1(numBin) {
  let count = 0;
  for (let i = numBin.length - 1; i >= 0; i--) {
    if (numBin[i] === "1") count++;
    else break;
  }
  return count;
}
