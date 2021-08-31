function solution(numbers) {
  const isPrime = Array.from(new Array(10 ** numbers.length), () => true);
  setPrime(isPrime);
  isPrime[0] = false;
  isPrime[1] = false;
  numbers = numbers.split("");
  const map = new Map();
  let count = 0;
  function select(numbers, selected, isSelected, index) {
    if (index === numbers.length) {
      if (selected.length === 0) return;
      const num = parseInt(selected.join(""));
      if (!map.has(num) && isPrime[num]) {
        count++;
        map.set(num, true);
      }
      return;
    }
    for (let i = 0; i < numbers.length; i++) {
      if (index === 0 && numbers[i] === "0") continue;
      if (
        !(selected.length === 0 && numbers[i] === "2") &&
        index === numbers.length - 1 &&
        parseInt(numbers[i]) % 2 === 0
      )
        continue;
      // 선택
      if (!isSelected[i]) {
        selected.push(numbers[i]);
        isSelected[i] = true;
        select(numbers, selected, isSelected, index + 1);
        isSelected[i] = false;
        selected.pop();
      }
      // 선택 안함
      select(numbers, selected, isSelected, index + 1);
    }
  }
  select(
    numbers,
    [],
    Array.from(new Array(numbers.length), () => false),
    0
  );
  return count;
}

function setPrime(isPrime) {
  for (let i = 2; i < isPrime.length; i++) {
    if (isPrime[i]) {
      for (let k = i + i; k < isPrime.length; k += i) {
        isPrime[k] = false;
      }
    }
  }
}

solution("2");
