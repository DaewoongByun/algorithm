function solution(k, dungeons) {
  var answer = -1;

  function permu(selected, v, idx) {
    if (idx === selected.length) {
      answer = Math.max(answer, getCountDungeons(k, selected));
      return;
    }
    for (let i = 0; i < selected.length; i++) {
      if (v[i]) continue;
      v[i] = true;
      selected[idx] = dungeons[i];
      permu(selected, v, idx + 1);
      v[i] = false;
    }
  }

  permu(
    Array.from(dungeons, (d) => []),
    Array.from(dungeons, (d) => false),
    0
  );
  return answer;
}

function getCountDungeons(k, dungeons) {
  let count = 0;
  for (const dungeon of dungeons) {
    if (k >= dungeon[0] && k >= dungeon[1]) {
      count++;
      k -= dungeon[1];
    }
  }
  return count;
}
const result = solution(80, [
  [80, 20],
  [50, 40],
  [30, 10],
]);
console.log(result);
