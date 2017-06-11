package org.lbchild.window;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.lbchild.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.swt.layout.GridData;
import org.eclipse.jface.dialogs.IDialogConstants;

public class LoginDialog extends Dialog {
    public static final int LOGOUT_ID = 1;
    public static final int LOGIN_ID = 0;
    public static final String LOGIN_LABEL = "登录";
    public static final String LOGOUT_LABEL = "退出";
    private Text userName;
    private User user = User.getInstance();
    private static Logger logger = LoggerFactory.getLogger(LoginDialog.class);

    /**
     * Create the dialog.
     * 
     * @param parentShell
     */
    public LoginDialog(Shell parentShell) {
        super(parentShell);
        // shell = new Shell(parentShell, SWT.CLOSE | SWT.BORDER | SWT.TITLE);
    }

    /**
     * Create contents of the dialog.
     * 
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        this.getShell().setText("登录");
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

        userName = new Text(container, SWT.BORDER);
        GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
        gd_text.widthHint = 280;
        userName.setLayoutData(gd_text);

        return container;
    }

    /**
     * Create contents of the button bar.
     * 
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, LOGIN_ID, LOGIN_LABEL, true);
        createButton(parent, IDialogConstants.CLOSE_ID, LOGOUT_LABEL, false);
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(425, 239);
    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (LoginDialog.LOGIN_ID == buttonId) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("src/main/resources/user.txt"));
                out.write(userName.getText());
                out.close();

                logger.info("read user.txt, user is " + userName.getText());

            } catch (IOException exception) {
                exception.printStackTrace();
            }

            String inputName = userName.getText();
            if (inputName.equals("") || inputName == null) {
                return;
            }

            user.setUserName(inputName);
            close();
        } else {
            cancelPressed();
        }

    }

}
