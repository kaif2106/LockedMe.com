import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class LockedMe {
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final String DATA_DIRECTORY_PATH = CURRENT_DIRECTORY + "/Data";
    private static final File DATA_DIRECTORY = new File(DATA_DIRECTORY_PATH);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayWelcomeScreen();
        createDataDirectory();
        displayMainMenu();
    }

    private static void displayWelcomeScreen() {
        System.out.println("==================================================");
        System.out.println("       Welcome to LockedMe.com File Management Application");
        System.out.println("                  Developed by: Mohammed Kaif san");
        System.out.println("==================================================");
        System.out.println("This application allows you to manage files in a directory.");
        System.out.println("You can perform the following operations:");
        System.out.println("1. List files in ascending order");
        System.out.println("2. Access business-level operations (add, delete, search files)");
        System.out.println("3. Exit the application");
        System.out.println("==================================================");
    }

    private static void createDataDirectory() {
        if (!DATA_DIRECTORY.exists()) {
            DATA_DIRECTORY.mkdir();
        }
    }

    private static void displayMainMenu() {
        System.out.println("Select an option:");
        System.out.println("1. List files in ascending order");
        System.out.println("2. Business-level operations");
        System.out.println("3. Exit");

        int choice = getValidIntInput();

        switch (choice) {
            case 1:
                listFilesInAscendingOrder();
                displayMainMenu();
                break;
            case 2:
                displayBusinessOperationsMenu();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
                displayMainMenu();
        }
    }

    private static void displayBusinessOperationsMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Add a new file");
        System.out.println("2. Delete a file");
        System.out.println("3. Search for a file");
        System.out.println("4. List files by last modified date");
        System.out.println("5. Back to main menu");

        int choice = getValidIntInput();

        switch (choice) {
            case 1:
                addNewFile();
                displayBusinessOperationsMenu();
                break;
            case 2:
                deleteFile();
                displayBusinessOperationsMenu();
                break;
            case 3:
                searchFile();
                displayBusinessOperationsMenu();
                break;
            case 4:
                listFilesByLastModifiedDate();
                displayBusinessOperationsMenu();
                break;
            case 5:
                displayMainMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayBusinessOperationsMenu();
        }
    }

    private static void listFilesInAscendingOrder() {
        File[] files = DATA_DIRECTORY.listFiles();

        if (files != null && files.length > 0) {
            Arrays.sort(files);
            System.out.println("Files in the Data directory (" + DATA_DIRECTORY_PATH + ") in ascending order:");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("The Data directory is empty.");
        }
    }

    private static void addNewFile() {
        System.out.println("Enter the name of the file to be added:");
        String fileName = scanner.nextLine().trim().toLowerCase();

        File file = new File(DATA_DIRECTORY, fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + file.getAbsolutePath());
            } else {
                System.out.println("A file with the same name already exists. Cannot overwrite the existing file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    private static void deleteFile() {
        System.out.println("Enter the name of the file to delete:");
        String fileName = scanner.nextLine().trim();

        File[] files = DATA_DIRECTORY.listFiles();
        Optional<File> fileToDelete = Arrays.stream(files)
                .filter(file -> file.getName().equals(fileName))
                .findFirst();

        if (fileToDelete.isPresent()) {
            File file = fileToDelete.get();
            if (file.delete()) {
                System.out.println("File deleted successfully: " + file.getAbsolutePath());
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("The file does not exist.");
        }
    }

    private static void searchFile() {
        System.out.println("Enter the name of the file to search for:");
        String fileName = scanner.nextLine().trim();

        File[] files = DATA_DIRECTORY.listFiles();
        List<String> fileNames = Arrays.stream(files)
                .map(File::getName)
                .collect(Collectors.toList());

        if (fileNames.contains(fileName)) {
            System.out.println("File found: " + new File(DATA_DIRECTORY, fileName).getAbsolutePath());
        } else {
            System.out.println("The file does not exist in the Data directory.");
        }
    }

    private static void listFilesByLastModifiedDate() {
        File[] files = DATA_DIRECTORY.listFiles();

        if (files != null && files.length > 0) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
            System.out.println("Files in the Data directory (" + DATA_DIRECTORY_PATH + ") ordered by last modified date:");
            for (File file : files) {
                System.out.println(file.getName() + " (Last Modified: " + new Date(file.lastModified()) + ")");
            }
        } else {
            System.out.println("The Data directory is empty.");
        }
    }

    private static int getValidIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}