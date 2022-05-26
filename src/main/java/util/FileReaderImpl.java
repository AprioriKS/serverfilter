package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class FileReaderImpl {

public long readFromFile(String filePath, Set<String> allowed, long modified) {
        File file = new File(filePath);
        long lastModified = file.lastModified();
        if (lastModified > modified) {
            try {
                BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
                try {
                    String values = reader.readLine();
                    while (values != null) {
                        allowed.add(values);
                        values = reader.readLine();
                    }
                    return lastModified;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Can't open the file " + e);
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't read the data from file" + e);
            }
        }
        return modified;
    }
}