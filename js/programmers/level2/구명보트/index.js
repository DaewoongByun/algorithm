function solution(people, limit) {
  let answer = 0;
  let weights = Array.from(new Array(241), () => 0);

  for (let weight of people) {
    weights[weight]++;
  }

  let remainPeopleCount = people.length;

  while (remainPeopleCount > 0) {
    let boatRemainWeight = limit;
    let maxWeight = findMaxWeight(weights, boatRemainWeight);
    weights[maxWeight]--;
    remainPeopleCount--;
    boatRemainWeight -= maxWeight;

    let nextWeight = findMaxWeight(weights, boatRemainWeight);
    if (nextWeight !== -1) {
      weights[nextWeight]--;
      remainPeopleCount--;
    }
    answer++;
  }

  return answer;
}

function findMaxWeight(weights, boatRemainWeight) {
  let result = -1;
  for (let weight = boatRemainWeight; weight >= 40; weight--) {
    if (weights[weight] > 0) {
      return weight;
    }
  }
  return result;
}
