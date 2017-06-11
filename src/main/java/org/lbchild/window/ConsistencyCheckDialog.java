package org.lbchild.window;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.lbchild.controller.SelectUserListener;
import org.lbchild.model.ConsistencyCheck;
import org.lbchild.model.NewsList;
import org.lbchild.util.LabelCompare;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

public class ConsistencyCheckDialog extends Dialog {
    private Table table;
    private Table table_1;
    private NewsList newsList;

    /**
     * Create the dialog.
     * @param parentShell
     */
    public ConsistencyCheckDialog(Shell parentShell, NewsList newsList) {
        super(parentShell);
        this.newsList = newsList;
    }

    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new GridLayout(2, false));

        Combo combo = new Combo(container, SWT.NONE);
        combo.setItems(new String[] {"admin", "guodongrui"});
        combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        SelectUserListener listener1 = new SelectUserListener();
        combo.addSelectionListener(listener1);
        
        Combo combo_1 = new Combo(container, SWT.NONE);
        combo_1.setItems(new String[] {"admin", "guodongrui"});
        combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        SelectUserListener listener2 = new SelectUserListener();
        combo_1.addSelectionListener(listener2);
        
        String userpath = "src/main/resources/" + listener1.getUser() + "/training.xml";
        String user_1path = "src/main/resources/" + listener2.getUser() + "/training.xml";

        //LabelCompare lc = new LabelCompare(newsList, userpath, user_1path);
        
        Label label = new Label(container, SWT.NONE);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        
        Label label_1 = new Label(container, SWT.NONE);
        label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        
        ScrolledComposite scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
        scrolledComposite.setLayout(new FillLayout());
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);
        
        TableViewer tableViewer = new TableViewer(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
        table = tableViewer.getTable();
        
        String columns[] = { "删除新闻位置", "用户1标签", "用户2标签", "新闻标题" };
        for (int i = 0; i < columns.length; ++i) {
            new TableColumn(table, SWT.LEFT).setText(columns[i]);
            table.getColumn(i).pack();
        }

        table.setHeaderVisible(true);
        table.setLinesVisible(false);
        
        
//        tableViewer.setContentProvider(new IStructuredContentProvider() {
//
//            @Override
//            public void dispose() {
//                // TODO Auto-generated method stub
//                
//            }
//
//            @Override
//            public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
//                // TODO Auto-generated method stub
//                
//            }
//
//            @Override
//            public Object[] getElements(Object inputElement) {
//                return ((List<ConsistencyCheck>)inputElement).toArray();
//            }
//            
//        });
//        
//        tableViewer.setLabelProvider(new ITableLabelProvider() {
//
//            @Override
//            public void addListener(ILabelProviderListener arg0) {                
//            }
//
//            @Override
//            public void dispose() {                
//            }
//
//            @Override
//            public boolean isLabelProperty(Object arg0, String arg1) {
//                return false;
//            }
//
//            @Override
//            public void removeListener(ILabelProviderListener arg0) {                
//            }
//
//            @Override
//            public Image getColumnImage(Object arg0, int arg1) {
//                return null;
//            }
//
//            @Override
//            public String getColumnText(Object element, int columnIndex) {
//                ConsistencyCheck consistencyCheck = (ConsistencyCheck)element;
//                if (columnIndex == 0)
//                    return consistencyCheck.getDifferentLineIndex() + "";
//                else if (columnIndex == 1)
//                    return consistencyCheck.getUser1differentMarks();
//                else if (columnIndex == 2)
//                    return consistencyCheck.getUser2differentMarks();
//                else if (columnIndex == 3)
//                    return consistencyCheck.getTitle();
//                else
//                    return "";
//            }
//            
//        });
//        
//        tableViewer.setInput(lc.getConsistencyCheckTable());
        
        scrolledComposite.setContent(table);
        scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        
//        ScrolledComposite scrolledComposite_1 = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
//        scrolledComposite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
//        scrolledComposite_1.setExpandHorizontal(true);
//        scrolledComposite_1.setExpandVertical(true);
//        
//        TableViewer tableViewer_1 = new TableViewer(scrolledComposite_1, SWT.BORDER | SWT.FULL_SELECTION);
//        table_1 = tableViewer_1.getTable();
//        
//        String columns_1[] = { "删除新闻标题", "错误标签" };
//        for (int i = 0; i < columns_1.length; ++i) {
//            new TableColumn(table_1, SWT.LEFT).setText(columns_1[i]);
//            table_1.getColumn(i).pack();
//        }
//        
//        scrolledComposite_1.setContent(table_1);
//        scrolledComposite_1.setMinSize(table_1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
//        
//        Label label_2 = new Label(container, SWT.NONE);
//        label_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
//        
//        Label label_3 = new Label(container, SWT.NONE);
//        label_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

        return container;
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(536, 413);
    }

}
