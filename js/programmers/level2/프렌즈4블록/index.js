function solution(m, n, board) {
  let answer = 0;
  board = board.map((b) => b.split(""));
  while (true) {
    let removedBlockCount = remove(m, n, board);
    if (removedBlockCount === 0) break;
    answer += removedBlockCount;
  }

  return answer;
}

function remove(m, n, board) {
  let removedQueue = [];
  let v = Array.from(new Array(m), () => Array.from(new Array(n), () => false));
  for (let r = 0; r < m - 1; r++) {
    for (let c = 0; c < n - 1; c++) {
      if (board[r][c] === "-") continue;
      if (fourBlockCheck(r, c, board)) {
        if (!v[r][c]) {
          removedQueue.push([r, c]);
          v[r][c] = true;
        }
        if (!v[r + 1][c]) {
          removedQueue.push([r + 1, c]);
          v[r + 1][c] = true;
        }
        if (!v[r][c + 1]) {
          removedQueue.push([r, c + 1]);
          v[r][c + 1] = true;
        }
        if (!v[r + 1][c + 1]) {
          removedQueue.push([r + 1, c + 1]);
          v[r + 1][c + 1] = true;
        }
      }
    }
  }
  let removedBlockCount = removedQueue.length;
  if (removedBlockCount === 0) return 0;
  while (removedQueue.length > 0) {
    let cur = removedQueue.shift();
    board[cur[0]][cur[1]] = "-";
  }
  drop(m, n, board);
  return removedBlockCount;
}

function drop(m, n, board) {
  for (let c = 0; c < n; c++) {
    let queue = [];
    for (let r = m - 1; r >= 0; r--) {
      if (board[r][c] !== "-") {
        queue.push(board[r][c]);
        board[r][c] = "-";
      }
    }
    for (let r = m - 1; r >= 0; r--) {
      if (queue.length === 0) break;
      board[r][c] = queue.shift();
    }
  }
}

function fourBlockCheck(r, c, board) {
  for (let i = r; i <= r + 1; i++) {
    for (let j = c; j <= c + 1; j++) {
      if (board[i][j] !== board[r][c]) return false;
    }
  }
  return true;
}

console.log(solution(4, 5, ["CCBDE", "AAADE", "AAABF", "CCBBF"]));
