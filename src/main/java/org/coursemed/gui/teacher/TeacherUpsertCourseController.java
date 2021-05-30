package org.coursemed.gui.teacher;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TeacherUpsertCourseController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private Label infoLabel;

    public TextField getNameField() {
        return nameField;
    }

    public TextField getPriceField() {
        return priceField;
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public boolean isValid() {
        double price = 0;

        if (nameField.getText().isEmpty() || priceField.getText().isEmpty()) {
            infoLabel.setText("Please fill in all fields");

            return false;
        }

        try {
            price = Double.parseDouble(priceField.getText());
        } catch (final NumberFormatException e) {
            infoLabel.setText("Price must be numerical");

            return false;
        }

        if (price < 0) {
            infoLabel.setText("Price must be positive");

            return false;
        }

        infoLabel.setText("");

        return true;
    }
}
