package org.coursemed.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.coursemed.classes.Course;
import org.coursemed.classes.LoggingManager;
import org.coursemed.classes.Teacher;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;

public class TeacherCreateCourseController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private Label infoLabel;

    @FXML
    private void onCreateCourse(ActionEvent event) {
        if (isValid()) {
            Course course = new Course();

            course.setName(nameField.getText());
            course.setPrice(Double.parseDouble(priceField.getText()));
            course.setTeacher((Teacher) LoggingManager.getLoggedUser());

            CustomDbTools.addCourse(course);

            try {
                App.setRoot("teacher_manage_courses");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        try {
            App.setRoot("teacher_manage_courses");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValid() {
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
