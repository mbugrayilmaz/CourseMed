module org.coursemed {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires sqlite.jdbc;
    requires jdk.crypto.cryptoki;

    opens org.coursemed to javafx.fxml, javafx.graphics;
    opens org.coursemed.classes to javafx.fxml, javafx.graphics, javafx.base;
    opens org.coursemed.gui to javafx.fxml, javafx.graphics;
    opens org.coursemed.gui.student to javafx.fxml, javafx.graphics;
    opens org.coursemed.gui.teacher to javafx.fxml, javafx.graphics;
    opens org.coursemed.gui.admin to javafx.fxml, javafx.graphics;
    opens org.coursemed.gui.generic to javafx.fxml, javafx.graphics;
    exports org.coursemed;
}