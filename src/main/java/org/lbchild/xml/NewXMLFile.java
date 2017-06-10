package org.lbchild.xml;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NewXMLFile {
    private File file;

    public NewXMLFile(File file) {
        this.file = file;
        initializeFile();
    }

    private void initializeFile() {
        try {
            if (!file.exists()) {
                PrintWriter out = new PrintWriter(new FileWriter(file));
                out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n\n" + "<ArrayOfNewsData>\n"
                        + "</ArrayOfNewsData>");
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
