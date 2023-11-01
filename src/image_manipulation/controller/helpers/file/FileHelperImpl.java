package image_manipulation.controller.helpers.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileHelperImpl implements FileHelper {
    @Override
    public String readFile(String filepath) throws IOException {
        File file = new File(filepath);
        FileInputStream fis = new FileInputStream(file);
        Scanner sc = new Scanner(fis);
        StringBuilder builder = new StringBuilder();


        // read the file line by line, and populate a string and throw away any comment lines
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            // empty lines will be ignored.
            if (s.isEmpty()) {
                continue;
            }
            if (s.charAt(0) != '#') {
                builder.append(s).append(System.lineSeparator());
            }
        }

        return builder.toString();
    }

}
