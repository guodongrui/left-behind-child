import org.eclipse.swt.widgets.Display;

import org.lbchild.window.TestWindow;

public class Main {
    public static void main(String[] args) {
        TestWindow window = new TestWindow();
        window.setBlockOnOpen(true);
        window.open();
        Display.getCurrent().dispose();
    }
}
