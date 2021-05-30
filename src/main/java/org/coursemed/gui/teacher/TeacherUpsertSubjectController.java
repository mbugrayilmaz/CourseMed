package org.coursemed.gui.teacher;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TeacherUpsertSubjectController {
    @FXML
    private TextField titleField;

    @FXML
    private TextField videoUrlField;

    @FXML
    private Label infoLabel;

    public TextField getTitleField() {
        return titleField;
    }

    public TextField getVideoUrlField() {
        return videoUrlField;
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public boolean isValid() {
        if (titleField.getText().isEmpty() || videoUrlField.getText().isEmpty()) {
            infoLabel.setText("Please fill in all fields");

            return false;
        }

        infoLabel.setText("");

        return true;
    }
}
