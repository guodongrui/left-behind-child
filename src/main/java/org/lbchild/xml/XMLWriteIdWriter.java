package org.lbchild.xml;

import java.io.File;

import org.lbchild.window.MainWindow;
import org.lbchild.window.MainWindow.Newspaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLWriteIdWriter extends XMLWriter {
    
    private static Logger logger = LoggerFactory.getLogger(XMLSelectionIdWriter.class);

    @Override
    public void updateXml(int writeId, String delete) {
        try {

            // 修改 xml 的 <IsDeleted> 标签写文件
            if (writeId < guangmingWriteId()) {
                super.file = new File("src/main/resources/guangming2.xml");
                super.updateXml(writeId, delete);
            } else if (writeId < guangmingWriteId() + nanfangWriteId()) {
                super.file = new File("src/main/resources/nanfangdaily2.xml");
                super.updateXml(writeId - guangmingWriteId(), delete);
            } else if (writeId < guangmingWriteId() + nanfangWriteId() + sichuanWriteId()) {
                super.file = new File("src/main/resources/sichuan2.xml");
                super.updateXml(writeId - guangmingWriteId() - nanfangWriteId(), delete);
            } else {
            }
            logger.info("write IsDeleted marks: " + writeId );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int nanfangWriteId() {
        return MainWindow.newsSourceFileLength[Newspaper.NANFANG.ordinal()];
    }

    private int guangmingWriteId() {
        return MainWindow.newsSourceFileLength[Newspaper.GUANGMING.ordinal()];
    }

    private int sichuanWriteId() {
        return MainWindow.newsSourceFileLength[Newspaper.SICHUAN.ordinal()];
    }

}
