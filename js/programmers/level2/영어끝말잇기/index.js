function solution(n, words) {
  let answer = [0, 0];
  let turn = 0;
  let set = new Set();

  for (let i = 0; i < words.length; i++) {
    let word = words[i];

    if (
      set.has(word) ||
      (i > 0 && words[i - 1][words[i - 1].length - 1] !== word[0])
    ) {
      answer[0] = turn + 1;
      answer[1] = parseInt(i / n) + 1;
      break;
    }
    turn = (turn + 1) % n;
    set.add(word);
  }

  return answer;
}

const a = solution(5, [
  "hello",
  "observe",
  "effect",
  "take",
  "either",
  "recognize",
  "encourage",
  "e",
  "establish",
  "hang",
  "gather",
  "refer",
  "reference",
  "estimate",
  "executive",
]);
console.log(a);
