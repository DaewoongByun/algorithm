package kakao;

public class P5 {
	static int result = 0;

	static class Node {
		int id;
		// 0 = 양 1 = 늑대
		int type;
		int left;
		int right;
		int parent;

		public Node(int id, int type, int left, int right, int parent) {
			super();
			this.id = id;
			this.type = type;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}

	}

	static class Person {
		int location;
		int sheep;
		int wolf;

		public Person(int location, int sheep, int wolf) {
			super();
			this.location = location;
			this.sheep = sheep;
			this.wolf = wolf;
		}
	}

	public static int solution(int[] info, int[][] edges) {
		Node[] nodes = new Node[info.length];
		Person person = new Person(0, 0, 0);

		for (int i = 0; i < info.length; i++) {
			nodes[i] = new Node(i, info[i], -1, -1, -1);
		}
		for (int[] e : edges) {
			int from = e[0];
			int to = e[1];
			if (nodes[from].left == -1) {
				nodes[from].left = to;
			} else {
				nodes[from].right = to;
			}
			nodes[to].parent = from;
		}

		boolean[][][] v = new boolean[18][18][info.length];
		v[1][0][0] = true;
		person.sheep = 1;
		nodes[0].type = -1;
		find(nodes, person, v);
		return result;
	}

	private static void find(Node[] nodes, Person person, boolean[][][] v) {
		result = Math.max(person.sheep, result);

		Node curNode = nodes[person.location];
		// 부모
		if (curNode.parent != -1 && !v[person.sheep][person.wolf][curNode.parent]) {
			Node[] copyNodes = nodesCopy(nodes);
			Person copyPerson = personCopy(person);
			copyPerson.location = curNode.parent;
			v[copyPerson.sheep][copyPerson.wolf][curNode.parent] = true;
			find(copyNodes, copyPerson, v);
		}
		// 왼쪽
		if (curNode.left != -1 && !v[person.sheep][person.wolf][curNode.left]) {
			Node[] copyNodes = nodesCopy(nodes);
			Person copyPerson = personCopy(person);
			if (nodes[curNode.left].type == 0) {
				copyPerson.location = curNode.left;
				copyPerson.sheep++;
				v[copyPerson.sheep][copyPerson.wolf][curNode.left] = true;
				copyNodes[curNode.left].type = -1;
				find(copyNodes, copyPerson, v);
			} else if (nodes[curNode.left].type == 1) {
				if (person.sheep > person.wolf + 1) {
					copyPerson.location = curNode.left;
					copyPerson.wolf++;
					v[copyPerson.sheep][copyPerson.wolf][curNode.left] = true;
					copyNodes[curNode.left].type = -1;
					find(copyNodes, copyPerson, v);
				}
			} else {
				copyPerson.location = curNode.left;
				v[person.sheep][copyPerson.wolf][curNode.left] = true;
				find(copyNodes, copyPerson, v);
			}
		}
		// 오른쪽
		if (curNode.right != -1 && !v[person.sheep][person.wolf][curNode.right]) {
			Node[] copyNodes = nodesCopy(nodes);
			Person copyPerson = personCopy(person);
			if (nodes[curNode.right].type == 0) {
				copyPerson.location = curNode.right;
				copyPerson.sheep++;
				v[copyPerson.sheep][copyPerson.wolf][curNode.right] = true;
				copyNodes[curNode.right].type = -1;
				find(copyNodes, copyPerson, v);
			} else if (nodes[curNode.right].type == 1) {
				if (person.sheep > person.wolf + 1) {
					copyPerson.location = curNode.right;
					copyPerson.wolf++;
					v[copyPerson.sheep][copyPerson.wolf][curNode.right] = true;
					copyNodes[curNode.right].type = -1;
					find(copyNodes, copyPerson, v);
				}
			} else {
				copyPerson.location = curNode.right;
				v[copyPerson.sheep][copyPerson.wolf][curNode.right] = true;
				find(copyNodes, copyPerson, v);
			}
		}
	}

	private static Person personCopy(Person person) {
		Person newPerson = new Person(person.location, person.sheep, person.wolf);
		return newPerson;
	}

	private static Node[] nodesCopy(Node[] nodes) {
		Node[] newNodes = new Node[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			newNodes[i] = new Node(nodes[i].id, nodes[i].type, nodes[i].left, nodes[i].right, nodes[i].parent);
		}
		return newNodes;
	}

	public static void main(String[] args) {
//		int[] info = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
//		int[][] edges = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
//				{ 4, 6 }, { 8, 9 } };
		int[] info = { 0,1,1, 0};
		int[][] edges = {{0,1},{0,2},{1,3}};
		System.out.println(solution(info, edges));
	}
}
