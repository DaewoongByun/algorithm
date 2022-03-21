function solution(arr, processes) {
  const result = [];
  let resultTime = 0;

  const readWatingQueue = new Queue();
  const writeWatingQueue = new Queue();
  const workingQueue = new Queue();
  const workInTime = Array.from(new Array(1001), () => undefined);
  const workEndInTime = Array.from(new Array(10000), () => []);
  let curWorkingType = "";
  let remainWorkCount = processes.length;

  processes.forEach((process) => {
    const processArr = process.split(" ");
    const p = {};
    p.type = processArr[0];
    p.startTime = +processArr[1];
    p.workTime = +processArr[2];
    p.fromIndex = +processArr[3];
    p.toIndex = +processArr[4];
    p.writeNum = processArr[5];
    workInTime[p.startTime] = p;
  });

  let time = 1;

  while (true) {
    // 작업 등록
    let work = workInTime[time];
    if (work && work.type === "read") {
      readWatingQueue.push(work);
    } else if (work && work.type === "write") {
      writeWatingQueue.push(work);
    }

    // 작업 끝내기
    let endWorks = workEndInTime[time];
    endWorks.forEach((work) => {
      workingQueue.pop();
      remainWorkCount--;
    });
    if (workingQueue.size() === 0) {
      curWorkingType = "";
    }

    // 작업
    if (curWorkingType === "") {
      if (writeWatingQueue.size() > 0) {
        const work = writeWatingQueue.pop();
        write(work);
      } else {
        while (readWatingQueue.size() > 0) {
          const work = readWatingQueue.pop();
          read(work);
        }
      }
    } else if (curWorkingType === "read") {
      if (writeWatingQueue.size() === 0) {
        while (readWatingQueue.size() > 0) {
          const work = readWatingQueue.pop();
          read(work);
        }
      }
    }
    if (curWorkingType !== "") resultTime++;
    if (remainWorkCount === 0) break;
    time++;
  }

  return [...result, resultTime + ""];

  function write(work) {
    workingQueue.push(work);
    curWorkingType = "write";
    workEndInTime[work.workTime + time].push(work);
    for (let i = work.fromIndex; i <= work.toIndex; i++) {
      arr[i] = work.writeNum;
    }
  }

  function read(work) {
    workingQueue.push(work);
    curWorkingType = "read";
    workEndInTime[work.workTime + time].push(work);
    const readNum = arr.slice(work.fromIndex, work.toIndex + 1).join("");
    result.push(readNum);
  }
}

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
