package org.lbchild.window;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.lbchild.controller.SelectUserListener;
import org.lbchild.model.ConsistencyCheck;
import org.lbchild.model.NewsItem;
import org.lbchild.model.NewsList;
import org.lbchild.util.Base64Content;
import org.lbchild.util.LabelCompare;
import org.lbchild.xml.XMLReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

public class ConsistencyCheckDialog extends Dialog {
    private Table table;
    private Text rateText;
    private NewsList newsList;
    private TableViewer tableViewer;
    private LabelCompare lc;
    private String userpath;
    private String user_1path;
    private SelectUserListener listener1;
    private SelectUserListener listener2;
    private List<String> directoryList = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(ConsistencyCheckDialog.class);
    
    /**
     * Create the dialog.
     * 
     * @param parentShell
     */
    public ConsistencyCheckDialog(Shell parentShell) {
        super(parentShell);
        newsList = new NewsList();
        readFile("src/main/resources/guangming.xml");
        readFile("src/main/resources/nanfangdaily.xml");
        readFile("src/main/resources/sichuan.xml");
        getTrainDirectory();
    }
    
    private void getTrainDirectory() {
        File file = new File("src/main/resources/");
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isDirectory()) {
                for (int j = 0; j < tempList[i].listFiles().length; j++) {
                    if (tempList[i].listFiles()[j].getName().contains("training")) {
                        directoryList.add(tempList[i].getName());
                    }
                }
            }
        }
    }
    
    private void readFile(String path) {
        try {
            File file = new File(path);
            XMLReader in = new XMLReader(file);
            ArrayList<Map<String, String>> list = in.readXml();

            int size = list.size();
            ArrayList<NewsItem> li = new ArrayList<>();
            for (int i = 0; i < (3 < size ? 3 : size); ++i) {
                NewsItem newsItem = new NewsItem();
                newsItem.setDate(list.get(i).get("Date"));
                newsItem.setTitle(list.get(i).get("Title"));
                String encodedContent = list.get(i).get("EncodedContent");
                String content = null;

                if (encodedContent != null) {
                    content = Base64Content.decode(encodedContent).replaceAll("</?html>|</?body>|</?P>", "");

                } else {
                    content = list.get(i).get("TrueUrl");
                    if (content == null)
                        content = list.get(i).get("Url");
                }
                newsItem.setContent(content);
                newsItem.setDeleted(Boolean.parseBoolean(list.get(i).get("IsDeleted")));
                newsItem.setLocation(list.get(i).get("Location"));
                newsItem.setId(list.get(i).get("ID"));
                li.add(newsItem);

            }

            newsList.addNewList(li);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public NewsList getNewsList() {
        return newsList;
    }
    

    /**
     * Create contents of the dialog.
     * 
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        this.getShell().setText("一致性检测");
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new GridLayout(2, false));
        
        Combo combo = new Combo(container, SWT.NONE);
        String[] arr = (String[]) directoryList.toArray(new String[0]);
        
        combo.setItems(arr);
        combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        listener1 = new SelectUserListener();
        combo.addSelectionListener(listener1);

        Combo combo_1 = new Combo(container, SWT.NONE);
        combo_1.setItems(arr);
        combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        
        listener2 = new SelectUserListener();;
        combo_1.addSelectionListener(listener2);
        
        logger.info("listener1.getUser(): " + listener1.getUser());
        logger.info("listener2.getUser(): " + listener2.getUser());

        Label label = new Label(container, SWT.NONE);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

        Label label_1 = new Label(container, SWT.NONE);
        label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

        ScrolledComposite scrolledComposite = new ScrolledComposite(container,
                SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
        scrolledComposite.setLayout(new FillLayout());
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);

        tableViewer = new TableViewer(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
        table = tableViewer.getTable();

        String columns[] = { "新闻位置", "标签种类", "用户1标签", "用户2标签", "新闻标题" };
        for (int i = 0; i < columns.length; ++i) {
            new TableColumn(table, SWT.LEFT).setText(columns[i]);
            table.getColumn(i).pack();
        }

        table.setHeaderVisible(true);
        table.setLinesVisible(false);
        
        scrolledComposite.setContent(table);
        scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        
        rateText = new Text(container, SWT.READ_ONLY | SWT.CENTER);
        rateText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
//        new Label(container, SWT.NONE);
//        new Label(container, SWT.NONE);

        return container;
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        Button ok_bt = createButton(parent, IDialogConstants.YES_ID, "Check", true);
        ok_bt.addSelectionListener(new SelectionAdapter() {
            
            @Override
            public void widgetSelected(SelectionEvent e) {
                userpath = "src/main/resources/" + listener1.getUser() + "/training.xml";
                user_1path = "src/main/resources/" + listener2.getUser() + "/training.xml";
                logger.info("user_path: " + userpath);
                logger.info("user_1path: " + user_1path);
                
                lc = new LabelCompare(newsList, userpath, user_1path);
               
                tableViewer.setContentProvider(new IStructuredContentProvider() {
                    
                    @Override
                    public void dispose() {                   
                    }
                   
                    @Override
                    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {                   
                    }
                   
                    @Override
                    public Object[] getElements(Object inputElement) {
                        logger.info("ConsistencyCheck list to array" + Arrays.asList(((List<ConsistencyCheck>)inputElement).toArray()));
                        return ((List<ConsistencyCheck>)inputElement).toArray();
                    }
               
                });
                tableViewer.setLabelProvider(new ITableLabelProvider() {
                    
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
                        ConsistencyCheck consistencyCheck = (ConsistencyCheck) element;
                        if (columnIndex == 0)
                            return consistencyCheck.getDifferentLineIndex() + "";
                        else if (columnIndex == 1) 
                            return consistencyCheck.getNewsMarksType();
                        else if (columnIndex == 2)
                            return consistencyCheck.getUser1differentMarks();
                        else if (columnIndex == 3)
                            return consistencyCheck.getUser2differentMarks();
                        else if (columnIndex == 4)
                            return consistencyCheck.getTitle();
                        else
                            return "";
                    }
               
                });
                tableViewer.setInput(lc.getConsistencyCheckTable());
                NumberFormat nf = NumberFormat.getNumberInstance();
                nf.setMaximumFractionDigits(2);
                rateText.setText("一致率检测: " + nf.format(lc.getRate()) + "%");
                
            }
        });
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }
    
    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(536, 603);
    }

}
