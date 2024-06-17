package filefinder;

import java.io.File;
import java.io.IOException;

public class ParallelFileFinder {
    private final File rootDir;

    public ParallelFileFinder(String root) throws IOException {
        rootDir = new File(root);
        if (!(rootDir.exists() && rootDir.isDirectory())) {
            throw new IOException(root + " is not a directory");
        }
    }

    public void findFile(String file) throws InterruptedException {
        find(rootDir, file);
    }

    private static void find(File currentDir, String fileName) {
        File[] files = currentDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(fileName)) {
                    System.out.println("Found at: " + file.getAbsolutePath());
                } else if (file.isDirectory()) {
                    Thread thread = new Thread(new ParallelRun(file, fileName));
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class ParallelRun implements Runnable {
        public File directory;
        public String file;
        public ParallelRun(File directory, String file){
            this.directory = directory;
            this.file = file;
        }

        @Override
        public void run() {
            find(directory, file);
        }

    }
}
