function solution(a, b, m) {
  let answer = Infinity;
  const n = a.length + 1;

  const treeA = Array.from(new Array(n + 1), () =>
    Array.from(new Array(n + 1), () => false)
  );
  const treeB = Array.from(new Array(n + 1), () =>
    Array.from(new Array(n + 1), () => false)
  );
  a.forEach(([from, to]) => {
    treeA[from][to] = true;
    treeA[to][from] = true;
  });
  b.forEach(([from, to]) => {
    treeB[from][to] = true;
    treeB[to][from] = true;
  });

  //   const queue = new Queue();
  //   queue.push(copy(treeB));
  const v = new Set();
  //   v.add(JSON.stringify(treeB));

  //   while (queue.size() > 0) {
  //     const curTree = queue.pop();
  //     // 2번 해보기

  //     // 1번 해보기
  //   }

  const tempTreeB = copyTree(treeB);
  getResult(tempTreeB, 0, 0);

  function getResult(tree, mCount, num1Count) {
    v.add(JSON.stringify(tree));
    if (isSame(tree, treeA)) {
      answer = Math.min(num1Count, answer);
      return;
    }
    // 2번 해보기
    if (mCount < m) {
      for (let from = 1; from <= n; from++) {
        for (let to = 1; to <= n; to++) {
          if (from === to) continue;
          const tempTree = copyTree(tree);
          swap(tempTree, from, to);
          if (!v.has(JSON.stringify(tempTree)))
            getResult(tempTree, mCount + 1, num1Count);
        }
      }
    }

    // 1번 해보기
    for (let from = 1; from <= n; from++) {
      for (let to = 1; to <= n; to++) {
        if (from === to) continue;
        if (tree[from][to]) {
          const tempTree = copyTree(tree);
          tempTree[from][to] = false;
          tempTree[to][from] = false;

          for (let newFrom = 1; newFrom <= n; newFrom++) {
            for (let newTo = 1; newTo <= n; newTo++) {
              if (newFrom === newTo) continue;
              if (!tempTree[newFrom][newTo]) {
                tempTree[newFrom][newTo] = true;
                tempTree[newTo][newFrom] = true;
                if (!v.has(JSON.stringify(tempTree)))
                  getResult(tempTree, mCount, num1Count + 1);
                tempTree[newFrom][newTo] = false;
                tempTree[newTo][newFrom] = false;
              }
            }
          }
        }
      }
    }
  }
  function swap(tree, i1, i2) {
    //   const temp = tree[i1];
    //   tree[i1] = tree[i2];
    //   tree[i2] = temp;
    for (let i = 1; i <= n; i++) {
      tree[i][i1] = !tree[i][i1];
      tree[i][i2] = !tree[i][i2];
    }
  }
  return answer;
}

function copyTree(tree) {
  return tree.map((arr) => [...arr]);
}

function isSame(tree1, tree2) {
  return JSON.stringify(tree1) === JSON.stringify(tree2);
}

function swap(tree, i1, i2) {
  //   const temp = tree[i1];
  //   tree[i1] = tree[i2];
  //   tree[i2] = temp;
  for (let i = 1; i <= n; i++) {
    tree[i][i1] = !tree[i][i1];
    tree[i][i2] = !tree[i][i2];
  }
}

const result = solution(
  [
    [1, 2],
    [3, 1],
    [2, 4],
    [3, 5],
  ],
  [
    [2, 1],
    [4, 1],
    [2, 5],
    [3, 2],
  ],
  1
);
console.log(result);

class Queue {
  constructor() {
    this.queue = [];
    this.firstIndex = 0;
  }

  push(node) {
    this.queue.push(node);
  }

  pop() {
    return this.queue[this.firstIndex++];
  }

  size() {
    return this.queue.length - this.firstIndex;
  }
}
