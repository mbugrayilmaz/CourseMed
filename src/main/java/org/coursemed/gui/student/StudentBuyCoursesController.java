package org.coursemed.gui.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.coursemed.classes.Course;
import org.coursemed.classes.LoggingManager;
import org.coursemed.classes.Student;
import org.coursemed.classes.Teacher;
import org.coursemed.gui.App;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;

public class StudentBuyCoursesController {
    private final ObservableList<Course> courseList = FXCollections.observableArrayList();

    private Student loggedUser;

    @FXML
    private Button buyButton;

    @FXML
    private Label infoLabel;

    public ObservableList<Course> getCourseList() {
        return courseList;
    }

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private void onBuy(ActionEvent event) {
        if (courseTable.getSelectionModel().getSelectedIndex() != -1) {
            try {
                Course course = courseTable.getSelectionModel().getSelectedItem();

                if (loggedUser.withdraw(course.getPrice())) {
                    CustomDbTools.enroll(loggedUser, course);

                    courseList.remove(course);

                    CustomDbTools.updateStudent(loggedUser);

                    Teacher teacher = course.getTeacher();

                    teacher.deposit(course.getPrice());

                    CustomDbTools.updateTeacher(teacher);

                    infoLabel.setText("");
                } else {
                    infoLabel.setText("Insufficient funds");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            App.setRoot("student_main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        loggedUser = (Student) LoggingManager.getLoggedUser();

        try {
            courseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    buyButton.setDisable(false);
                }
            });

            courseList.addAll(CustomDbTools.getAvailableCourses((Student) LoggingManager.getLoggedUser()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
