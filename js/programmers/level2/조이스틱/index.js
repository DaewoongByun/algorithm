function solution(name) {
  let answer = 0;
  let str = "A".repeat(name.length);
  let i = 0;
  answer += moveCount(name[0]);
  str = str.replaceAt(0, name[0]);
  while (str !== name) {
    let [nextIndex, k] = findNextIndex(name, str, i);
    answer += moveCount(name[nextIndex]);
    answer += k;
    str = str.replaceAt(nextIndex, name[nextIndex]);
    i = nextIndex;
  }

  return answer;
}

function findNextIndex(name, str, i) {
  for (let k = 1; k < name.length; k++) {
    let leftIndex = i - k >= 0 ? i - k : name.length + i - k;
    let rightIndex = i + k < name.length ? i + k : i + k - name.length;
    if (name[rightIndex] !== str[rightIndex]) return [rightIndex, k];
    if (name[leftIndex] !== str[leftIndex]) return [leftIndex, k];
  }
  return [0, 0];
}

function moveCount(ch) {
  const startNum = "A".charCodeAt(0);
  const chNum = ch.charCodeAt(0);
  const endNum = "Z".charCodeAt(0);
  return Math.min(chNum - startNum, endNum - chNum + 1);
}

String.prototype.replaceAt = function (index, ch) {
  const arr = this.split("");
  arr[index] = ch;
  return arr.join("");
};

console.log(solution("ABAAAAAAAAABB"));
