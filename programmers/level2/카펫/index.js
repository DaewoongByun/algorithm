function solution(brown, yellow) {
  const total = brown + yellow;
  let width, height;
  for (height = 3; height <= total / 3; height++) {
    if (total % height !== 0) continue;
    width = total / height;
    const curBrown = width * 2 + height * 2 - 4;
    if (curBrown === brown) {
      return [width, height];
    }
  }
}
