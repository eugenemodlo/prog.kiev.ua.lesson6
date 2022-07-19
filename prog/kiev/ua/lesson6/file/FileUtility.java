package prog.kiev.ua.lesson6.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class FileUtility {
    private final File sourceDirectory;
    private final File outDirectory;

    public FileUtility() {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream("Resources/dir.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sourceDirectory = new File(property.getProperty("SOURCE_DIR"));
        this.outDirectory = new File(property.getProperty("OUT_DIR"));
    }

    public void CopyDirectory() {

        CopyFileTread[] copyFileTread = new CopyFileTread[Objects.requireNonNull(this.sourceDirectory.listFiles()).length];
        File[] sourceFiles = sourceDirectory.listFiles();

        for (int i = 0; i < copyFileTread.length; i++) {
            assert sourceFiles != null;
            copyFileTread[i] = new CopyFileTread(sourceFiles[i], new File(this.outDirectory, sourceFiles[i].getName()));
        }

        for (CopyFileTread fileTread : copyFileTread) {
            try {
                fileTread.getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (Arrays.stream(copyFileTread).anyMatch(copy -> !copy.getSuccessfulCopy())) {
            System.out.println("Some file(s) does not copied!");
        } else {
            System.out.println("All files copied successfully!");
        }

    }

    private class FileCopyDaemon implements Runnable {
        private final File inputFile;
        private final File outputFile;
        private final Thread thread;

        public FileCopyDaemon(File inputFile, File outputFile) {
            this.inputFile = inputFile;
            this.outputFile = outputFile;
            thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
        }

        @Override
        public void run() {
            while (true) {

            }
        }

        private ArrayList<File> getChangedFiles(File inputDir, File outputDir) {
            ArrayList<File> outputList = new ArrayList<>();
            HashMap<String, Long> inputFileMap = new HashMap<>();

            for (File file : Objects.requireNonNull(inputDir.listFiles())) {
                inputFileMap.put(file.getName(), file.lastModified());
            }

            for (File file : Objects.requireNonNull(outputDir.listFiles())) {
                if (!inputFileMap.containsKey(file.getName()) ||
                        file.lastModified() < inputFileMap.get(file.getName())) {
                    outputList.add(file);
                }
            }
            return outputList;
        }

    }

    private class CopyFileTread implements Runnable {
        private final File inputFile;
        private final File outputFile;
        private final Thread thread;

        private boolean successfulCopy = false;

        public CopyFileTread(File inputFile, File outputFile) {
            this.inputFile = inputFile;
            this.outputFile = outputFile;
            thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            if (copyFile(this.inputFile, this.outputFile)) {
                System.out.println("File " + inputFile.getName() + " was copied successfully!");
                this.successfulCopy = true;
            } else System.out.println("Can't copy file: " + inputFile.getName());
        }

        private boolean createNewFile(File file) {
            if (!file.exists()) {
                File dir = new File(file.getParent());
                if (!dir.exists()) {
                    if (!dir.mkdir()) {
                        System.out.println("Can't create directory at: " + file.getPath());
                        return false;
                    }
                }

                try {
                    if (file.createNewFile()) {
                        return true;
                    } else {
                        System.out.println("Can't create the file:" + file.getName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("File: " + file.getName() + " already exist");
            }
            return false;
        }

        private boolean copyFile(File inputFile, File outputFile) {
            if (createNewFile(outputFile)) {
                try (FileInputStream fis = new FileInputStream(inputFile);
                     FileOutputStream fos = new FileOutputStream(outputFile)) {
                    byte[] buffer = new byte[1024];
                    int byteRead;

                    while ((byteRead = fis.read(buffer)) > 0) {
                        fos.write(buffer, 0, byteRead);
                    }
                    return true;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return false;
        }

        public boolean getSuccessfulCopy() {
            return this.successfulCopy;
        }

        public Thread getThread() {
            return this.thread;
        }
    }
}