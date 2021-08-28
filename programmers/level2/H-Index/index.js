function solution(citations) {
  citations.sort((a, b) => b - a);
  for (let h = citations[0]; h >= 0; h--) {
    // h번이상 인용된 논문의 수
    let count1 = citations.filter((n) => n >= h).length;
    if (count1 >= h) return h;
  }
}
