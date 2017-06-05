package org.lbchild.controller;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.lbchild.model.NewsList;
import org.lbchild.window.MainWindow;
import org.lbchild.window.ReadMoreWindow;
import org.lbchild.xml.XMLSelectionIdWriter;

public class DeleteAction extends Action {

    private int selectionId;

    private NewsList newsList;

    public DeleteAction(NewsList newsList) {
        super();
        this.newsList = newsList;
    }

    public void run() {
        MessageBox msgbox = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        msgbox.setMessage("确认删除吗？");
        int rc = msgbox.open();
        if (rc == SWT.YES) {
            selectionId = ReadMoreWindow.getReadMoreWindow().getLineId();

            MainWindow.getMainWindow().getNewsSummaryList().remove(selectionId);
            new XMLSelectionIdWriter().updateXml(selectionId, "true");
            newsList.deleteIndex(selectionId);
            
            // 删除后显示下一条新闻
            ReadMoreWindow.getReadMoreWindow().setLineId(ReadMoreWindow.getReadMoreWindow().getLineId() % (newsList.getNewsList().size()));
            MainWindow.getMainWindow().getNewsSummaryList().setSelection(ReadMoreWindow.getReadMoreWindow().getLineId());
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
