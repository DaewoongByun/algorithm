function solution(n, edges) {
  const graph = Array.from(new Array(n), () => []);
  edges.forEach(([from, to]) => {
    graph[from].push(to);
    graph[to].push(from);
  });

  const distance = Array.from(new Array(n), () =>
    Array.from(new Array(n), () => 0)
  );

  let count = 0;
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (i === j) continue;
      for (let k = 0; k < n; k++) {
        if (i === k || k === j) continue;
        const d1 = getDistance(i, j);
        const d2 = getDistance(j, k);
        const d3 = getDistance(i, k);
        if (d1 + d2 === d3) {
          count++;
        }
      }
    }
  }

  function getDistance(from, to) {
    if (distance[from][to]) return distance[from][to];
    let queue = [];
    const v = Array.from(new Array(n), () => false);
    v[from] = true;
    queue.push([from, 0]);

    while (true) {
      const len = queue.length;
      const temp = [...queue];
      queue = [];

      for (let i = 0; i < len; i++) {
        const cur = temp.pop();
        const tos = graph[cur[0]];
        for (let toIndex = 0; toIndex < tos.length; toIndex++) {
          const node = tos[toIndex];
          if (node === to) {
            distance[from][node] = cur[1] + 1;
            distance[node][from] = cur[1] + 1;
            return cur[1] + 1;
          }
          if (!v[node]) {
            queue.push([node, cur[1] + 1]);
            v[node] = true;
          }
        }
      }
    }
  }

  return count;
}

const result = solution(4, [
  [2, 3],
  [0, 1],
  [1, 2],
]);
console.log(result);
