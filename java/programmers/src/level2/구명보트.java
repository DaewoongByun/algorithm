package level2;

public class 구명보트 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 40, 40, 40, 40 }, 50));
	}

	public static int solution(int[] people, int limit) {
		int answer = 0;

		int[] weights = new int[241];
		for (int weight : people) {
			weights[weight]++;
		}

		int remainPeopleCount = people.length;

		while (remainPeopleCount > 0) {
			int boatRemainWeight = limit;
			int maxWeight = findMaxWeight(weights, boatRemainWeight);
			weights[maxWeight]--;
			remainPeopleCount--;
			boatRemainWeight -= maxWeight;

			int nextWeight = findMaxWeight(weights, boatRemainWeight);
			if (nextWeight != -1) {
				weights[nextWeight]--;
				remainPeopleCount--;
			}
			answer++;
		}

		return answer;
	}

	public static int findMaxWeight(int[] weights, int boatRemainWeight) {
		int result = -1;
		for (int weight = boatRemainWeight; weight >= 40; weight--) {
			if (weights[weight] > 0)
				return weight;
		}
		return result;
	}

}
