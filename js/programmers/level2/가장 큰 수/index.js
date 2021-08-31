function solution(numbers) {
  numbers = numbers.map((number) => String(number));
  numbers.sort((n1, n2) => compare(n1, n2));
  if (numbers[0] === "0") return "0";
  return numbers.join("");
}

function compare(n1, n2) {
  let sum1 = Number(n1 + n2);
  let sum2 = Number(n2 + n1);
  if (sum1 >= sum2) {
    return -1;
  } else {
    return 1;
  }
}
