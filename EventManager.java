import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EventManager {
    public static void main(String[] args) {

        String filename = "path/to/your/eventsfile.txt"; // specify the path to your file with events

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            List<Event> events = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // assuming the format is name,startTime,endTime
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    int startTime = Integer.parseInt(parts[1].trim());
                    int endTime = Integer.parseInt(parts[2].trim());
                    events.add(new Event(name, startTime, endTime));
                }
            }

            // Sort events by start time
            events.sort(Comparator.comparingInt(Event::getStartTime));

            // Remove overlapping events
            List<Event> nonOverlappingEvents = new ArrayList<>();
            int lastEndTime = -1;
            for (Event event : events) {
                if (event.getStartTime() >= lastEndTime) {
                    nonOverlappingEvents.add(event);
                    lastEndTime = event.getEndTime();
                }
            }

            // Print the sorted list of non-overlapping events
            System.out.println("Sorted list of non-overlapping events:");
            for (Event event : nonOverlappingEvents) {
                System.out.println(event);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}