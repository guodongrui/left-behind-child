package org.lbchild.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.lbchild.model.NewsList;
import org.lbchild.window.MainWindow;
import org.lbchild.window.ReadMoreWindow;
import org.lbchild.xml.XMLWriter;

public class DeleteAction extends Action {

    private int selectionId;

    private NewsList newsList;

    private File file;

    public DeleteAction(NewsList newsList) {
        super();
        file = new File("src/main/resources/nanfangdaily2.xml");
        this.newsList = newsList;
    }

    public void run() {
        MessageBox msgbox = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        msgbox.setMessage("确认删除吗？");
        int rc = msgbox.open();
        if (rc == SWT.YES) {
            selectionId = ReadMoreWindow.getReadMoreWindow().getLineId();
            int sum = 0;
            List<Integer> deleteIndex = MainWindow.getDeleteInstance();
            for (int index = 0; index < deleteIndex.size(); index++) {
                int d_id = deleteIndex.get(index);
                if (selectionId + sum >= d_id && sum <= d_id) {
                    sum++;
                }
            }
            System.out.println(sum);
//            i = ReadMoreWindow.getReadMoreWindow().getLineId() + NewsList.countDeleted;
//
//            MainWindow.getMainWindow().getNewsSummaryList().remove(selectionId);
//            if (selectionId < i) {
//                new XMLWriter(file).updateXml(selectionId + NewsList.jj, "true");
//                NewsList.jj++;
//            } else {
//                new XMLWriter(file).updateXml(i, "true");
//            }
//            NewsList.countDeleted++;
            MainWindow.getMainWindow().getNewsSummaryList().remove(selectionId);
            new XMLWriter(file).updateXml(selectionId + sum, "true");
            
            deleteIndex.add(selectionId + sum);
            Collections.sort(deleteIndex);
            MainWindow.writeFile();
            newsList.deleteIndex(selectionId);
            deleteIndex.clear();
            
            
            // 删除后显示下一条新闻
            ReadMoreWindow.getReadMoreWindow().setLineId(ReadMoreWindow.getReadMoreWindow().getLineId());
            ReadMoreWindow.getReadMoreWindow()
                    .setTitle(newsList.getNewsItem(ReadMoreWindow.getReadMoreWindow().getLineId()).getTitle());
            ReadMoreWindow.getReadMoreWindow()
                    .setDate("日期：" + newsList.getNewsItem(ReadMoreWindow.getReadMoreWindow().getLineId()).getDate());
            String newsContent = newsList.getNewsItem(ReadMoreWindow.getReadMoreWindow().getLineId()).getContent();
            if (newsContent != null) {
                ReadMoreWindow.getReadMoreWindow().setContent(newsContent);
            }

        }
        if (rc == SWT.NO)
            return;
    }

}
