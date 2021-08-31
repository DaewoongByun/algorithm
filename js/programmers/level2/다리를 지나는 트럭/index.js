function solution(bridge_length, weight, truck_weights) {
  let t = 0;
  let onBridgeWeight = 0;
  let onBridge = Array.from(new Array(bridge_length), () => 0);

  while (onBridgeWeight !== 0 || truck_weights.length > 0) {
    onBridgeWeight -= onBridge.shift();
    const curTruck = nextTruck(truck_weights, weight, onBridgeWeight);
    onBridge.push(curTruck);
    onBridgeWeight += curTruck;
    t++;
  }

  return t;
}

function nextTruck(truck_weights, weight, onBridgeWeight) {
  if (truck_weights[0] + onBridgeWeight <= weight) {
    return truck_weights.shift();
  }

  return 0;
}

console.log(solution(2, 10, [7, 4, 5, 6]));
