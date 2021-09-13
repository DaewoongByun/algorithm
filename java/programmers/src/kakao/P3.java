package kakao;

import java.util.*;

public class P3 {
	static int minMinutes;
	static int minFee;
	static int unitMinutes;
	static int unitFee;

	static class Info {
		String id;
		List<String> in;
		List<String> out;

		public Info(String id, List<String> in, List<String> out) {
			super();
			this.id = id;
			this.in = in;
			this.out = out;
		}

		public int calcFees() {
			int fee = 0;
			
			if(out.size() < in.size()) {
				out.add("23:59");
			}
			int stayMinutes = 0;
			for(int i = 0; i < in.size(); i++) {
				String inTime = in.get(i);
				String outTime = out.get(i);
				int inHour = Integer.parseInt(inTime.split(":")[0]);
				int inMinute = Integer.parseInt(inTime.split(":")[1]);
				int outHour = Integer.parseInt(outTime.split(":")[0]);
				int outMinute = Integer.parseInt(outTime.split(":")[1]);
				stayMinutes += calcStayMinutes(inHour, inMinute, outHour, outMinute);
				
				fee = calcFee(stayMinutes);
			}
			
			return fee;
		}
		
		public int calcFee(int stayMinutes) {
			if (stayMinutes <= minMinutes) {
				return minFee;
			}

			int overMinutes = stayMinutes - minMinutes;
			int times = overMinutes / unitMinutes;
			int fee = minFee + times * unitFee;
			if (overMinutes % unitMinutes != 0) {
				fee += unitFee;
			}

			return fee;
		}

		private int calcStayMinutes(int inHour, int inMinute, int outHour, int outMinute) {
			if (outMinute < inMinute) {
				outMinute += 60;
				outHour--;
			}
			int stayMinutes = (outHour - inHour) * 60 + (outMinute - inMinute);
			return stayMinutes;
		}
	}

	

	public static int[] solution(int[] fees, String[] records) {
		List<Integer> result = new ArrayList<>();
		Map<String, Info> carMap = new HashMap<>();
		Set<String> carSet = new HashSet<>();

		minMinutes = fees[0];
		minFee = fees[1];
		unitMinutes = fees[2];
		unitFee = fees[3];

		for (String record : records) {
			String[] in = record.split(" ");
			String time = in[0];
			String id = in[1];
			String type = in[2];
			if (carSet.contains(id)) {
				if(type.equals("IN")) {
					carMap.get(id).in.add(time);
				} else {
					carMap.get(id).out.add(time);
				}
			} else {
				List<String> inList = new ArrayList<>();
				inList.add(time);
				Info i = new Info(id, inList, new ArrayList<>());

				carSet.add(id);
				carMap.put(id, i);
			}
		}

		List<String> carList = new ArrayList<>(carSet);
		Collections.sort(carList, (c1, c2) -> Integer.parseInt(c1) - Integer.parseInt(c2));
		for (String car : carList) {
			result.add(carMap.get(car).calcFees());
		}

		return result.stream().mapToInt(i -> i).toArray();
	}

	public static void main(String[] args) {
		int[] fees = new int[] { 180, 5000, 10, 600 };
		String[] records = new String[] { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
				"07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };
		int[] result = solution(fees, records);
		System.out.println(Arrays.toString(result));
	}
}
