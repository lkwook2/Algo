public class TheAlmostLuckyNumbersDivTwo {

	public int find(int a, int b) {
		int result = 0;
		for (int i = a; i <= b; i++) {
			if (i < 10) {
				// 1 digit is almost lucky.
				result++;
			} else {
				String temp = "";
				temp = temp + i;
				int defferntdigit = 0;
				for (int j = 0; j < temp.length(); j++) {
					if (temp.substring(j, j + 1).equals("4")) {

					} else if (temp.substring(j, j + 1).equals("7")) {

					} else {
						defferntdigit++;
					}
				}
				if (defferntdigit < 2) {
					result++;
				}
			}
		}
		return result;
	}
}