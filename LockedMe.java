import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LockedMe {
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final String DATA_DIRECTORY = CURRENT_DIRECTORY + "/Data";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayWelcomeScreen();
        createDataDirectory();
        displayInitialMenu();
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
        File dataDir = new File(DATA_DIRECTORY);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
    }

    private static void displayInitialMenu() {
        System.out.println("Select an option:");
        System.out.println("1. List files in ascending order");
        System.out.println("2. Business level operations");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                listFilesInAscendingOrder();
                displayInitialMenu();
                break;
            case 2:
                displayBusinessOperationsMenu();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
                displayInitialMenu();
        }
    }

    private static void displayBusinessOperationsMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Add a new file");
        System.out.println("2. Delete a file");
        System.out.println("3. Search for a file");
        System.out.println("4. List files by last modified date");
        System.out.println("5. Back to main menu");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                addNewFile();
                displayBusinessOperationsMenu();
                break;
            case 2:
                // deleteFile();
                displayBusinessOperationsMenu();
                break;
            case 3:
                // searchFile();
                displayBusinessOperationsMenu();
                break;
            case 4:
                // listFilesByLastModifiedDate();
                displayBusinessOperationsMenu();
                break;
            case 5:
                displayInitialMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayBusinessOperationsMenu();
        }
    }

    private static void listFilesInAscendingOrder() {
        File directory = new File(DATA_DIRECTORY);
        File[] files = directory.listFiles();

        if (files != null) {
            Arrays.sort(files); 

            System.out.println("Files in the Data directory (" + DATA_DIRECTORY + ") in ascending order:");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("The Data directory is empty.");
        }
    }

    private static void addNewFile() {
        System.out.println("Enter the name of the file to be added:");
        String fileName = scanner.nextLine().toLowerCase(); 

        File file = new File(DATA_DIRECTORY, fileName);

        if (file.exists()) {
            System.out.println("A file with the same name already exists. Cannot overwrite the existing file.");
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created successfully: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create the file.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }
}