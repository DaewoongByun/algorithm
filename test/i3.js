function solution(width, height, diagonals) {
  const divideNum = 10000019;
  // graph는 node(r,c)에 연결된 node들
  const graph = Array.from(new Array(height + 1), () =>
    Array.from(new Array(width + 1), () => [])
  );
  for (let r = 0; r <= height; r++) {
    for (let c = 0; c <= width; c++) {
      if (r > 0) graph[r][c].push([r - 1, c]);
      if (c > 0) graph[r][c].push([r, c - 1]);
    }
  }
  diagonals.forEach(([x, y]) => {
    graph[y - 1][x].push([y, x - 1, true]);
    graph[y][x - 1].push([y - 1, x, true]);
  });
  ////////////////////

  // counts는 node(r,c) 까지 가는 방법 수 [대각선 x, 대각선 o]
  const counts = Array.from(new Array(height + 1), () =>
    Array.from(new Array(width + 1), () => [0, 0])
  );
  counts[0][0] = [1, 0];

  getCounts(height + 1, width + 1, graph, counts, divideNum);

  return counts[height][width][1];
}

function getCounts(R, C, graph, counts, divideNum) {
  // 대각선 이용 x 작업
  for (let k = 0; k < Math.min(R, C); k++) {
    // 가로
    for (let c = k; c < C; c++) {
      const r = k;
      if (r === 0 && c === 0) continue;
      let count = 0;
      graph[r][c].forEach(([nr, nc, isDiagonal]) => {
        if (!isDiagonal) {
          count += counts[nr][nc][0] % divideNum;
        }
      });
      counts[r][c][0] = count % divideNum;
    }
    // 세로
    for (let r = k; r < R; r++) {
      const c = k;
      if (r === 0 && c === 0) continue;
      let count = 0;
      graph[r][c].forEach(([nr, nc, isDiagonal]) => {
        if (!isDiagonal) {
          count += counts[nr][nc][0] % divideNum;
        }
      });
      counts[r][c][0] = count % divideNum;
    }
  }

  // 대각선 이용 o 작업
  for (let k = 0; k < Math.min(R, C); k++) {
    // 가로
    for (let c = k; c < C; c++) {
      const r = k;
      if (r === 0 && c === 0) continue;
      let count = 0;
      graph[r][c].forEach(([nr, nc, isDiagonal]) => {
        if (isDiagonal) {
          count += counts[nr][nc][0] % divideNum;
        } else {
          count += counts[nr][nc][1] % divideNum;
        }
      });
      counts[r][c][1] = count % divideNum;
    }
    // 세로
    for (let r = k; r < R; r++) {
      const c = k;
      if (r === 0 && c === 0) continue;
      let count = 0;
      graph[r][c].forEach(([nr, nc, isDiagonal]) => {
        if (isDiagonal) {
          count += counts[nr][nc][0] % divideNum;
        } else {
          count += counts[nr][nc][1] % divideNum;
        }
      });
      counts[r][c][1] = count % divideNum;
    }
  }
}

// console.log(solution(51, 37, [[17, 19]]));
