package org.coursemed.gui.teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.coursemed.App;
import org.coursemed.classes.Course;
import org.coursemed.classes.LoggingManager;
import org.coursemed.classes.Teacher;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;

public class TeacherCreateCourseController {

    private TextField nameField;
    private TextField priceField;
    private Label infoLabel;

    @FXML
    private TeacherUpsertCourseController teacherCreateUpsertCourseController;

    @FXML
    private void onCreateCourse(ActionEvent event) {
        if (teacherCreateUpsertCourseController.isValid()) {
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


    @FXML
    private void initialize() {
        nameField = teacherCreateUpsertCourseController.getNameField();
        priceField = teacherCreateUpsertCourseController.getPriceField();
        infoLabel = teacherCreateUpsertCourseController.getInfoLabel();
    }
}
