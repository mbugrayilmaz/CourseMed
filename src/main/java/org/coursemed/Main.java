package org.coursemed;

import org.coursemed.tools.CustomDbTools;

public class Main {
    public static void main(String[] args) {
        CustomDbTools.initialize("coursemed.db");
        App.main(args);
    }
}
