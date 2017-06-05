package org.lbchild.window;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class LoginDialog extends Dialog {
    private static final int LOGOUT_ID = IDialogConstants.CLIENT_ID + 1;
    private static final int LOGIN_ID = IDialogConstants.CLIENT_ID + 0;
    private static final String LOGIN_LABEL = "登录";
    private static final String LOGOUT_LABEL = "退出";
    private Text text;

    /**
     * Create the dialog.
     * @param parentShell
     */
    public LoginDialog(Shell parentShell) {
        super(parentShell);
    }

    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        GridLayout gridLayout = (GridLayout) container.getLayout();
        gridLayout.numColumns = 4;
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        new Label(container, SWT.NONE);
        
        Label label = new Label(container, SWT.NONE);
        label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label.setText("用户名：");
        
        text = new Text(container, SWT.BORDER);
        GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
        gd_text.widthHint = 280;
        text.setLayoutData(gd_text);

        return container;
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, LOGIN_ID, LOGIN_LABEL, true);
        createButton(parent, LOGOUT_ID, LOGOUT_LABEL, false);
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(425, 239);
    }

}
