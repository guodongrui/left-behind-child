package org.lbchild.window;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.lbchild.controller.AddMarksAction;
import org.lbchild.controller.DeleteAction;
import org.lbchild.model.NewsList;

import org.eclipse.swt.widgets.Text;
import org.lbchild.res.management.SWTResourceManager;
import org.lbchild.url.UrlContentDownloader;

public class ReadMoreWindow extends ApplicationWindow {
    private DeleteAction deleteToolItem;
    private AddMarksAction addMarksToolItem;
    private NewsList newsList;
    private int lineId;
    private Text title;
    private Text date;
    private Text content;
    private static ReadMoreWindow readMoreWindow;

    private String path;

    /**
     * Create the application window.
     * 
     * @param path
     */
    public ReadMoreWindow(NewsList newsList, int lineId, String path) {
        super(null);
        this.newsList = newsList;
        this.lineId = lineId;
        this.path = path;
        readMoreWindow = this;
        createActions();
        addToolBar(SWT.FLAT | SWT.WRAP);
        addMenuBar();
        addStatusLine();
    }

    /**
     * Create contents of the application window.
     * 
     * @param parent
     */
    @Override
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
        container.setLayout(new GridLayout(1, true));

        {
            Composite composite_title = new Composite(container, SWT.NONE);
            composite_title.setLayout(new FillLayout(SWT.HORIZONTAL));
            GridData gd_composite_title = new GridData(SWT.FILL, SWT.CENTER, false, false);
            gd_composite_title.widthHint = 866;
            composite_title.setLayoutData(gd_composite_title);
            {
                title = new Text(composite_title, SWT.NONE);
                title.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.BOLD));
                title.setEditable(false);
                title.setRedraw(true);
            }
            setTitle(newsList.getNewsItem(getLineId()).getTitle());
        }
        {
            Composite composite_date = new Composite(container, SWT.NONE);
            composite_date.setLayout(new FillLayout(SWT.HORIZONTAL));
            GridData gd_composite_date = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
            gd_composite_date.widthHint = 859;
            composite_date.setLayoutData(gd_composite_date);
            {
                date = new Text(composite_date, SWT.BORDER);
                date.setEditable(false);
                date.setRedraw(true);
            }
            setDate("日期: " + newsList.getNewsItem(getLineId()).getDate());
        }
        {
            Composite composite_content = new Composite(container, SWT.NONE);
            composite_content.setLayout(new FillLayout(SWT.HORIZONTAL));
            GridData gd_composite_content = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
            gd_composite_content.widthHint = 858;
            composite_content.setLayoutData(gd_composite_content);
            {
                content = new Text(composite_content, SWT.BORDER | SWT.WRAP);
                content.setEditable(false);
            }

            String newsContent = newsList.getNewsItem(getLineId()).getContent();
            if (content != null) {
                setContent(newsContent);
            }
        }
        return container;
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        // Create the actions

        deleteToolItem = new DeleteAction(newsList);
        deleteToolItem.setText("Delete");
        addMarksToolItem = new AddMarksAction(newsList, lineId, path);
        addMarksToolItem.setText("AddMarks");
    }

    /**
     * Create the menu manager.
     * 
     * @return the menu manager
     */
    @Override
    protected MenuManager createMenuManager() {
        MenuManager menuManager = new MenuManager("menu");
        return menuManager;
    }

    /**
     * Create the toolbar manager.
     * 
     * @return the toolbar manager
     */
    @Override
    protected ToolBarManager createToolBarManager(int style) {
        ToolBarManager toolBarManager = new ToolBarManager(style);
        toolBarManager.add(deleteToolItem);
        toolBarManager.add(addMarksToolItem);
        return toolBarManager;
    }

    /**
     * Create the status line manager.
     * 
     * @return the status line manager
     */
    @Override
    protected StatusLineManager createStatusLineManager() {
        StatusLineManager statusLineManager = new StatusLineManager();
        return statusLineManager;
    }

    /**
     * Configure the shell.
     * 
     * @param newShell
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Read More");
    }

    /**
     * Return the initial size of the window.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(863, 669);
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDate(String date) {
        this.date.setText(date);
    }

    public void setContent(String content) {
        if (content.startsWith("http:")) {
            UrlContentDownloader.writeEncodedContent(newsList, lineId);
            this.content.setText(newsList.getNewsList().get(lineId).getContent());
        } else {
            this.content.setText(content);
        }
    }

    public static ReadMoreWindow getReadMoreWindow() {
        return readMoreWindow;
    }

}
