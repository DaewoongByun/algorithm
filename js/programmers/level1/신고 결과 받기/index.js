function solution(id_list, report, k) {
  const indexMap = {};
  id_list.forEach((id, i) => {
    indexMap[id] = i;
  });

  const sets = Array.from(id_list, () => new Set());

  report.forEach((re) => {
    const from = re.split(" ")[0];
    const to = re.split(" ")[1];
    const toIndex = indexMap[to];

    sets[toIndex].add(from);
  });
  const result = Array.from(id_list, () => 0);

  sets
    .filter((set) => set.size >= k)
    .forEach((set) => {
      [...set].forEach((id) => {
        result[indexMap[id]]++;
      });
    });

  return result;
}
