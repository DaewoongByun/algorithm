function solution(goods) {
  const answer = goods.map((good, i) => getIndexList(good, i));

  function getIndexList(good, goodIndex) {
    const length = good.length;
    const resultSet = new Set();

    for (let indexLength = 1; indexLength <= length; indexLength++) {
      for (let i = 0; i <= length - indexLength; i++) {
        const tempIndex = good.slice(i, i + indexLength);
        if (isIndex(tempIndex, goodIndex)) {
          resultSet.add(tempIndex);
        }
      }
      if (resultSet.size > 0) break;
    }
    const result = [...resultSet].sort().join(" ");
    return result ? result : "None";
  }

  function isIndex(tempIndex, goodIndex) {
    for (let i = 0; i < goods.length; i++) {
      if (i === goodIndex) continue;
      if (goods[i].includes(tempIndex)) return false;
    }
    return true;
  }

  return answer;
}
