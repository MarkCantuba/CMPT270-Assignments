public class q2 {
	public static void main(String[] args) {
		double success = 0;
		for (int x = 1; x <= 1000; x++) {
			double stake = 100;
			double bets = 0;
			while (stake > 0 && stake < 200) {
				double play = Math.round(Math.random());
				bets++;
				if (play == 0) {
					stake--;
				}
				else {
					stake++;
				}
			}
		if (stake == 200) {
			success++;
		}
		System.out.println("stake = " + Math.round(stake) + " | bets made = " + Math.round(bets));
		if (x == 1000) {
			System.out.println("average success = " + (success/1000));
			System.out.println("average bets = " + (bets/1000));
			}
		}
	}
}
