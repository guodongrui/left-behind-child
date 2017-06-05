import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.lbchild.model.User;
import org.lbchild.window.LoginDialog;
import org.lbchild.window.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        User user = User.getInstance();
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/user.txt"));
            String userName = in.readLine();
            user.setUserName(userName);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Display display = new Display();
        Shell shell = new Shell(display);

        int status = LoginDialog.LOGIN_ID;
        if (user.getUserName() == null || user.getUserName().equals("")) {
            LoginDialog dialog = new LoginDialog(shell);
            status = dialog.open(); // 对话框取消时，status 变为 LOGOUT_ID
            logger.info("status: " + status);
        }
        if (status == LoginDialog.LOGIN_ID) {
            String dirName = "src/main/resources/" + user.getUserName();
            File dir = new File(dirName);
            if (dir.exists()) {
            } else {
                dir.mkdir();
            }
            logger.info("create " + dirName + " directory");

            MainWindow window = new MainWindow();
            window.setBlockOnOpen(true);
            window.open();
        } else {
        }
        Display.getCurrent().dispose();

    }
}
