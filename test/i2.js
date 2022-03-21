const dr = [-1, 0, 1, 0];
const dc = [0, 1, 0, -1];

function solution(n, clockwise) {
  const answer = Array.from(new Array(n), () =>
    Array.from(new Array(n), () => 0)
  );
  const points = clockwise
    ? [
        [0, 0, 1],
        [0, n - 1, 2],
        [n - 1, 0, 0],
        [n - 1, n - 1, 3],
      ]
    : [
        [0, 0, 2],
        [0, n - 1, 3],
        [n - 1, 0, 1],
        [n - 1, n - 1, 0],
      ];
  points.forEach(([r, c, d]) => {
    start(r, c, d);
  });

  return answer;

  function start(r, c, d) {
    let num = 1;
    let nr = r;
    let nc = c;
    let edgeCount = 1;
    while (true) {
      answer[nr][nc] = num;
      if (isEnd(nr, nc, n)) break;
      if (isEdgeEnd(nr, nc, n, edgeCount, d)) {
        d = clockwise ? (d + 1) % 4 : (d + 3) % 4;
        edgeCount++;
      }
      nr += dr[d];
      nc += dc[d];
      num++;
    }
  }
}

function isEnd(r, c, n) {
  if (endPoints(n).includes(r) && endPoints(n).includes(c)) return true;
  return false;
}

function isEdgeEnd(r, c, n, edgeCount, d) {
  if (d === 0) {
    return edgeCount >= r ? true : false;
  } else if (d === 1) {
    return c >= n - edgeCount - 1 ? true : false;
  } else if (d === 2) {
    return r >= n - edgeCount - 1 ? true : false;
  } else {
    return edgeCount >= c ? true : false;
  }
}

function endPoints(n) {
  const num = parseInt(n / 2);
  if (n % 2 === 0) {
    return [num, num - 1];
  } else {
    return [num];
  }
}
