package pl.karol.lingu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

@Controller
public class LinguController {
    private static final int UNDEFINED = -1;
    private static final int ADD_ENTRY = 0;
    private static final int TEST = 1;
    private static final int CLOSE_APP = 2;

    private EntryRepository entryRepository;
    private FileService fileService;
    private Scanner sc;
    private ConsoleOutputWriter consoleOutputWriter;

    @Autowired
    public LinguController(EntryRepository entryRepository, FileService fileService, Scanner sc, ConsoleOutputWriter consoleOutputWriter) {
        this.entryRepository = entryRepository;
        this.fileService = fileService;
        this.sc = sc;
        this.consoleOutputWriter = consoleOutputWriter;
    }

    public void mainLoop() {
        consoleOutputWriter.println("Welcome to the LinguApplication");
        int option = UNDEFINED;
        while(option != CLOSE_APP) {
            printMenu();
            option = chooseOption();
            executeOption(option);
        }
    }

    private void executeOption(int option) {
        switch(option) {
            case ADD_ENTRY:
                addEntry();
                break;
            case TEST:
                test();
                break;
            case CLOSE_APP:
                close();
                break;
            default:
                consoleOutputWriter.println("Option not defined in the system");
        }
    }

    private void test() {
        if(entryRepository.isEmpty()) {
            consoleOutputWriter.println("You need to add at least one word to the database");
            return;
        }
        final int testSize = entryRepository.size() > 10 ? 10 : entryRepository.size();
        Set<Entry> randomEntries = entryRepository.getRandomEntries(testSize);
        int score = 0;
        for (Entry entry : randomEntries) {
            consoleOutputWriter.println(String.format("Give a translation for :\"%s\"", entry.getOriginal()));
            String translation = sc.nextLine();
            if(entry.getTranslation().equalsIgnoreCase(translation)) {
                consoleOutputWriter.println("Good answear");
                score++;
            } else {
                consoleOutputWriter.println("Wrong answear - " + entry.getTranslation());
            }
        }
        consoleOutputWriter.println(String.format("Your score: %d/%d\n", score, testSize));
    }

    private void addEntry() {
        consoleOutputWriter.println("Give the original meaning");
        String original = sc.nextLine();
        consoleOutputWriter.println("Give translation");
        String translation = sc.nextLine();
        Entry entry = new Entry(original, translation);
        entryRepository.add(entry);
    }

    private void close() {
        try {
            fileService.saveEntries(entryRepository.getAll());
            consoleOutputWriter.println("Saved app condition");
        } catch (IOException e) {
            consoleOutputWriter.println("There was an error saving app");
        }
        consoleOutputWriter.println("Bye bye");
    }

    private void printMenu() {
        consoleOutputWriter.println("Choose option:");
        consoleOutputWriter.println("0 - Add word");
        consoleOutputWriter.println("1 - Test");
        consoleOutputWriter.println("2 - End");
    }

    private int chooseOption() {
        int option;
        try {
            option = sc.nextInt();
        } catch (InputMismatchException e) {
            option = UNDEFINED;
        } finally {
            sc.nextLine();
        }
        if(option > UNDEFINED && option <= CLOSE_APP)
            return option;
        else
            return UNDEFINED;
    }
}
