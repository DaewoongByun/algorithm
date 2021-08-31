function solution(n) {
  var answer = [];
  for (let i = 1; i <= n; i++) {
    answer.push(Array.from(new Array(i), () => 0));
  }
  let curDir = 0;
  let cur = [0, 0];
  let d = [
    [1, 0],
    [0, 1],
    [-1, -1],
  ];

  for (let curNum = 1; curNum <= (n * (n + 1)) / 2; curNum++) {
    answer[cur[0]][cur[1]] = curNum;
    let nr = cur[0] + d[curDir][0];
    let nc = cur[1] + d[curDir][1];
    if (
      nr < 0 ||
      nr >= n ||
      nc < 0 ||
      nc >= answer[nr].length ||
      answer[nr][nc] > 0
    ) {
      curDir = (curDir + 1) % 3;
      nr = cur[0] + d[curDir][0];
      nc = cur[1] + d[curDir][1];
    }
    cur = [nr, nc];
  }
  const result = [];
  for (const arr of answer) {
    result.push(...arr);
  }
  return result;
}

solution(4);
