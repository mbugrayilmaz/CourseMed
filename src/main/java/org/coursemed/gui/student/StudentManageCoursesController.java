package org.coursemed.gui.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import org.coursemed.classes.*;
import org.coursemed.gui.App;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;

public class StudentManageCoursesController {
    private final ObservableList<Course> courseList = FXCollections.observableArrayList();

    @FXML
    private Button viewCourseButton;

    @FXML
    private Button dropCourseButton;

    public ObservableList<Course> getCourseList() {
        return courseList;
    }

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private void onViewCourse(ActionEvent event) {
        if (courseTable.getSelectionModel().getSelectedIndex() != -1) {
            try {
                Context.pushContext(courseTable.getSelectionModel().getSelectedItem());
                App.setRoot("student_view_course");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onDropCourse(ActionEvent event) {
        if (courseTable.getSelectionModel().getSelectedIndex() != -1) {
            Alert alert =
                    new Alert(Alert.AlertType.WARNING,
                            "You won't get a refund. Do you still want to drop the course?",
                            ButtonType.YES,
                            ButtonType.NO);
            alert.setTitle("Drop Course");
            alert.showAndWait().ifPresent(a -> {
                if (a == ButtonType.YES) {
                    Course course = courseTable.getSelectionModel().getSelectedItem();

                    try {
                        CustomDbTools.dropCourse((Student) LoggingManager.getLoggedUser(), course);

                        courseList.remove(course);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
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
        try {
            courseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    viewCourseButton.setDisable(false);
                    dropCourseButton.setDisable(false);
                }
            });

            courseList.addAll(CustomDbTools.getCourses((Student) LoggingManager.getLoggedUser()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
