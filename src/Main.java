

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
    Stark, Lannister, Targaryen, Baratheon, Greyjoy, Martell, Tyrell
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

    public static void displayMembersByInitial(List<Event> events, char initial) {
        events.stream()
                .map(Event::getMitgliedsname)
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


        }
    }
}