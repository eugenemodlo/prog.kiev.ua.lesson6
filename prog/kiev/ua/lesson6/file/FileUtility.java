package prog.kiev.ua.lesson6.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
    private final File sourceDirectory;
    private final File outDirectory;

    public FileUtility() {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream("dir.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sourceDirectory = new File(property.getProperty("SOURCE_DIR"));
        this.outDirectory = new File(property.getProperty("OUT_DIR"));
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
}