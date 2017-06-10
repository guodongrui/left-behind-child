package org.lbchild.xml;

import java.io.File;
import java.util.List;

import org.lbchild.model.TrashNewsList;
import org.lbchild.window.MainWindow;
import org.lbchild.window.MainWindow.Newspaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLSelectionIdWriter extends XMLWriter {
    
    private static Logger logger = LoggerFactory.getLogger(XMLSelectionIdWriter.class);

    @Override
    public void updateXml(int selectionId, String delete) {
        try {
            
            int writeId = lineIdInFile(selectionId);
            
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
            } else {}
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateEncodedContent(String encodedContent, int selectionId) {
        int writeId = lineIdInFile(selectionId);
        if (writeId < guangmingWriteId()) {
            super.file = new File("src/main/resources/guangming2.xml");
            super.updateEncodedContent(encodedContent, writeId);
        } else if (writeId < guangmingWriteId() + nanfangWriteId()) {
            super.file = new File("src/main/resources/nanfangdailyId2.xml");
            super.updateEncodedContent(encodedContent, writeId - guangmingWriteId());
        } else if (writeId < guangmingWriteId() + nanfangWriteId() + sichuanWriteId()) {
            super.file = new File("src/main/resources/sichuan2.xml");
            super.updateEncodedContent(encodedContent, writeId - guangmingWriteId() - nanfangWriteId());
        } else {}
    }

    public static int lineIdInFile(int selectionId) {
        int sum = 0;
        List<Integer> deleteIndexes = TrashNewsList.getInstance().getIndices();
        for (int index = 0; index < deleteIndexes.size(); index++) {
            int d_id = deleteIndexes.get(index);
            if (selectionId + sum >= d_id && sum <= d_id) {
                sum++;
            }
        }
        
        int writeId = selectionId + sum;
        logger.info("xml update encoded content id: " + writeId);
        return writeId;
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
