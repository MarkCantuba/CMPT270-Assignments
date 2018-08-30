package assign1;

public class CaptialsCounter {

    public static void main(String[] args) {
        if (countCaps("IHaveFiveCaptialLetters") == 5) {
            System.out.println("countCaps succcess :)");
        }
        else {
            System.out.println("countCaps failure :(");            
        }
    }
    
    public static int countCaps(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}
