package org.lbchild.window;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.lbchild.model.NewsItem;
import org.lbchild.model.NewsList;
import org.lbchild.model.TrashNewsList;
import org.lbchild.xml.XMLWriteIdWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;

public class TrashDialog extends Dialog {

    private Composite composite;
    private NewsList newsList;
    private Table table;
    private CheckboxTableViewer checkboxTableViewer;
    private Logger logger = LoggerFactory.getLogger(TrashDialog.class);

    /**
     * Create the dialog.
     * 
     * @param parentShell
     */
    public TrashDialog(Shell parentShell, NewsList newsList) {
        super(parentShell);
        this.newsList = newsList;
    }

    /**
     * Create contents of the dialog.
     * 
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new FillLayout());

        ScrolledComposite scrolledComposite = new ScrolledComposite(container,
                SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        scrolledComposite.setLayout(new FillLayout());
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);

        composite = new Composite(scrolledComposite, SWT.NONE);
        composite.setLayout(new FillLayout());

        checkboxTableViewer = CheckboxTableViewer.newCheckList(composite, SWT.BORDER | SWT.FULL_SELECTION);
        table = checkboxTableViewer.getTable();

        String columns[] = { "删除新闻标题" };
        for (int i = 0; i < columns.length; ++i) {
            new TableColumn(table, SWT.LEFT).setText(columns[i]);
            table.getColumn(i).setWidth(420);
        }

        table.setHeaderVisible(true);
        table.setLinesVisible(false);

        checkboxTableViewer.setContentProvider(new IStructuredContentProvider() {

            @Override
            public void dispose() {
            }

            @Override
            public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
            }

            @Override
            public Object[] getElements(Object inputElement) {
                return ((TrashNewsList) inputElement).getNewsItems().toArray();
            }

        });
        checkboxTableViewer.setLabelProvider(new ITableLabelProvider() {

            @Override
            public void addListener(ILabelProviderListener arg0) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public boolean isLabelProperty(Object arg0, String arg1) {
                return false;
            }

            @Override
            public void removeListener(ILabelProviderListener arg0) {
            }

            @Override
            public Image getColumnImage(Object arg0, int arg1) {
                return null;
            }

            @Override
            public String getColumnText(Object element, int columnIndex) {
                NewsItem newsItem = (NewsItem) element;
                return newsItem.getTitle();
            }

        });

        TrashNewsList trashNewsList = TrashNewsList.getInstance();
        checkboxTableViewer.setInput(trashNewsList);

        scrolledComposite.setContent(composite);
        scrolledComposite.layout();

        return container;
    }

    /**
     * Create contents of the button bar.
     * 
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        Button button = createButton(parent, IDialogConstants.YES_ID, "Restore", true);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                // 通过 TrashNewsList 返回的 ArrayList，找到选择下标所对的需要恢复的文件位置下标
                TrashNewsList trashList = TrashNewsList.getInstance();
                ArrayList<Integer> deleteIndices = trashList.getIndices();

                // 遍历所有的 trashList
                int hasDeletedNum = 0;
                for (int i = 0; i < deleteIndices.size(); ++i) {
                    int truePos = i - hasDeletedNum;
                    if (checkboxTableViewer.getChecked(checkboxTableViewer.getElementAt(truePos))) {
                        int fileIndex = deleteIndices.get(i);
                        logger.info("file index of deleted item: " + fileIndex);

                        // 删除回收站列表里的恢复项
                        NewsItem n = trashList.remove(fileIndex);
                        logger.info("title of deleted item: " + n.getTitle());

                        // TrashDialog 去掉恢复项
                        table.remove(truePos);
                        logger.info("table index of deleted item: " + truePos);

                        // 计算 fileIndex 前面没有恢复的项有多少个
                        int sum = 0;
                        ArrayList<Integer> deleteIndexes = TrashNewsList.getInstance().getIndices();
                        for (int j = 0; j < deleteIndexes.size(); j++) {
                            int d_id = deleteIndexes.get(j);
                            if (fileIndex > d_id) {
                                sum++;
                            }
                        }
                        logger.info("deleted item before has " + sum + " items");

                        // 恢复 newsList
                        newsList.add(fileIndex - sum, n);
                        logger.info("add pos: " + (fileIndex - sum));

                        // MainWindow 显示恢复项
                        List newsSummaryList = MainWindow.getMainWindow().getNewsSummaryList();
                        newsSummaryList.add(n.getTitle(), fileIndex - sum);
                        newsSummaryList.setSelection(fileIndex - sum);
                        logger.info("NewsSummaryList pos of recovering: " + (fileIndex - sum));

                        // 写文件 isDeleted
                        new XMLWriteIdWriter().updateXml(fileIndex, "");

                        ++hasDeletedNum;
                    }
                }

            }
        });
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(450, 356);
    }
}
