function solution(sizes) {
  let max1 = 0;
  let max2 = 0;
  sizes.forEach((size) => {
    max1 = Math.max(max1, getMax(size));
    max2 = Math.max(max2, getMin(size));
  });
  return max1 * max2;
}

function getMax(size) {
  if (size[0] > size[1]) return size[0];
  return size[1];
}

function getMin(size) {
  if (size[0] < size[1]) return size[0];
  return size[1];
}
