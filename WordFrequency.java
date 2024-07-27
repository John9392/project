import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WordFrequency {
    public static void main(String[] args) {

        String filename = "path/to/your/textfile.txt"; 

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            Map<String, Integer> wordCountMap = new HashMap<>();
            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\W+"); 
                for (String word : words) {
                    if (!word.trim().isEmpty()) {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }

            List<Entry<String, Integer>> sortedWordCountList = wordCountMap.entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .limit(10)
                    .collect(Collectors.toList());

            System.out.println("Top 10 most frequent words:");
            for (Entry<String, Integer> entry : sortedWordCountList) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}