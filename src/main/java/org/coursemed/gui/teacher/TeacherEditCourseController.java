package org.coursemed.gui.teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.coursemed.App;
import org.coursemed.classes.Context;
import org.coursemed.classes.Course;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;

public class TeacherEditCourseController {

    private TextField nameField;
    private TextField priceField;
    private Label infoLabel;

    private Course course;

    @FXML
    private TeacherUpsertCourseController teacherEditUpsertCourseController;

    @FXML
    private void onSaveChanges(ActionEvent event) {
        try {
            if (teacherEditUpsertCourseController.isValid()) {
                course.setName(nameField.getText());
                course.setPrice(Double.parseDouble(priceField.getText()));

                CustomDbTools.updateCourse(course);

                App.setRoot("teacher_manage_courses");
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    @FXML
    private void initialize() {
        course = (Course) Context.popContext();

        nameField = teacherEditUpsertCourseController.getNameField();
        priceField = teacherEditUpsertCourseController.getPriceField();
        infoLabel = teacherEditUpsertCourseController.getInfoLabel();

        nameField.setText(course.getName());
        priceField.setText(String.valueOf(course.getPrice()));
    }
}
