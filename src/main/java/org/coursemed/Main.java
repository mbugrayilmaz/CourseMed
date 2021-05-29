package org.coursemed;

import org.coursemed.gui.App;
import org.coursemed.tools.DbTools;

public class Main {
    public static void main(String[] args) {
        DbTools.startConnection("jdbc:sqlite:coursemed.db");
        App.main(args);
    }
}
