package filefinder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            String goal = "needle.txt";
            String root = "haystack";
            ParallelFileFinder pff = new ParallelFileFinder(root);
            pff.findFile(goal);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            System.err.println("File search interrupted.");
        }
    }
}
