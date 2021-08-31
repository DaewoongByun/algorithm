function solution(clothes) {
  const map = new Map();
  for (const cloth of clothes) {
    const [name, kind] = cloth;
    if (!map.has(kind)) {
      map.set(kind, 1);
    } else {
      map.set(kind, map.get(kind) + 1);
    }
  }
  let result = 1;
  map.forEach((val) => (result *= val + 1));
  return result - 1;
}

solution([
  ["crowmask", "face"],
  ["bluesunglasses", "face"],
  ["smoky_makeup", "face"],
]);
