/*
 *Liz Mahoney
 *11/20/17
 *Launcher.java
 */

package launch;

import javafx.application.Application;
import view.TodoView;

/**
 * @author Liz Mahoney
 * @version 1.0
 */
public class Launcher {
    public static void main(String[] args) {

        Application.launch (TodoView.class, args);
    }


}
