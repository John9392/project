import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramGrouper {
    public static void main(String[] args) {

        String filename = "path/to/your/wordlist.txt"; // specify the path to your list of words

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            Map<String, List<String>> anagramGroups = new HashMap<>();
            String word;

            while ((word = br.readLine()) != null) {
                String sortedWord = sortLetters(word.trim().toLowerCase());
                anagramGroups.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word.trim());
            }

            System.out.println("Groups of Anagrams:");
            for (Map.Entry<String, List<String>> entry : anagramGroups.entrySet()) {
                System.out.println(entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String sortLetters(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}