
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    <ObjectMapper> readEvents(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filename), new TypeReference<List<Event>>() {
        });
    }
    public static void displayMembersByInitial(List<Event> events, char initial) {
        events.stream()
                .map(event -> event.getMitgliedsname())
                .filter(name -> name.startsWith(String.valueOf(initial)))
                .distinct()
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        try {
            List<Event> events = readEvents("evenimente.json");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter an uppercase letter: ");
            char initial = scanner.next().charAt(0);
            displayMembersByInitial(events, initial);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Assume readEvents method is implemented here
}