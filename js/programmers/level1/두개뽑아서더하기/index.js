function solution(numbers) {
  const set = new Set();

  for (let i1 = 0; i1 < numbers.length - 1; i1++) {
    for (let i2 = i1 + 1; i2 < numbers.length; i2++) {
      set.add(numbers[i1] + numbers[i2]);
    }
  }

  return [...set].sort((n1, n2) => n1 - n2);
}
