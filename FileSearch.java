import java.io.File;

public class FileSearch {

    public static void main(String[] args) {
        String directoryPath = "path/to/start/directory";  
        String fileExtension = ".txt";   

        searchFiles(new File(directoryPath), fileExtension);
    }

    public static void searchFiles(File directory, String extension) {
        if (!directory.isDirectory()) {
            System.out.println(directory.getAbsolutePath() + " is not a directory");
            return;
        }

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchFiles(file, extension);
                } else if (file.getName().endsWith(extension)) {
                    System.out.println("Found file: " + file.getAbsolutePath());
                }
            }
        }
    }
}