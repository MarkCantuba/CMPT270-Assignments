package assign1;

public class Gambler {

    public static void main(String[] args) {
        int successes = 0;
        int bets = 0;
        for (int i = 0; i < 1000; i++) {
            int stake = 100;
            bets = 0;
            while (stake > 0 && stake < 200) {
                double rand = Math.random();
                int play = (int) Math.round(rand);
                bets += 1;
                if (play == 0) {
                    stake = stake - 1;
                } else {
                    stake = stake + 1;

                }
            }
            if (stake == 200) {
                successes += 1;
            }

            System.out.println("stake = " + stake + 
                    " bets made = " + bets);
        }

        System.out.println("average success = " + (double) successes / 1000);
        System.out.println("average bets made = " + (double) bets / 1000);
    }
}
