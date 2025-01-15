import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public enum House {
    Stark, Lannister, Targaryen, Baratheon, Greyjoy, Martell, Tyrell, TreeMap;

    public class Event {
    private int id;
    private String mitgliedsname;
    private House haus;
    private String ereignis;
    private LocalDate datum;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMitgliedsname() {
        return mitgliedsname;
    }

    public void setMitgliedsname(String mitgliedsname) {
        this.mitgliedsname = mitgliedsname;
    }

    public House getHaus() {
        return haus;
    }

    public void setHaus(House haus) {
        this.haus = haus;
    }

    public String getEreignis() {
        return ereignis;
    }

    public void setEreignis(String ereignis) {
        this.ereignis = ereignis;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}


public class Main {
    public List<Event> readEvents(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .map(line -> {
                    String[] parts = line.split("#");
                    Event event = new Event();
                    event.setId(Integer.parseInt(parts[0]));
                    event.setMitgliedsname(parts[1]);
                    event.setHaus(House.valueOf(parts[2]));
                    event.setEreignis(parts[3]);
                    event.setDatum(LocalDate.parse(parts[4]));
                    return event;
                })
                .collect(Collectors.toList());
}

    public static void displayMembersByInitial(List<Event> events, char initial) {
        events.stream()
                .map(Event::getMitgliedsname)
                .filter(name -> name.startsWith(String.valueOf(initial)))
                .distinct()
                .forEach(System.out::println);
    }

    public static void displayStarkEventsSorted(List<Event> events) {
        events.stream()
                .filter(event -> event.getHaus() == House.Stark)
                .sorted(Comparator.comparing(Event::getDatum))
                .forEach(event -> System.out.println(event.getDatum() + ": " + event.getMitgliedsname() + " - " + event.getEreignis()));
    }

    public static void saveEventCounts(List<Event> events, String filePath) throws IOException {
        Map<House, Long> eventCounts = events.stream()
                .collect(Collectors.groupingBy(Event::getHaus, TreeMap::new, Collectors.counting()));

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (Map.Entry<House, Long> entry : eventCounts.entrySet()) {
                writer.write(entry.getKey() + "#" + entry.getValue());
                writer.newLine();
            }
        }
    }

    public void main(String[] args) {
        try {
            List<Event> events = readEvents("evenimente.json");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter an uppercase letter: ");
            char initial = scanner.next().charAt(0);
            displayMembersByInitial(events, initial);

            System.out.println("\nEvents of House Stark sorted by date:");
            displayStarkEventsSorted(events);

            saveEventCounts(events, "ergebnis.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
}