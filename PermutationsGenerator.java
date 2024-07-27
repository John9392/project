public class PermutationsGenerator {

    public static void main(String[] args) {
        String str = "ABC";
        generatePermutations(str, "");
    }

    
    public static void generatePermutations(String str, String permutation) {
        if (str.isEmpty()) {
            System.out.println(permutation);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            String remainingStr = str.substring(0, i) + str.substring(i + 1);

            generatePermutations(remainingStr, permutation + currentChar);
        }
    }
}