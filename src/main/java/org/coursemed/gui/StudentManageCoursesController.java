package org.coursemed.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.coursemed.classes.*;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;

public class StudentManageCoursesController {
    private final ObservableList<Course> courseList = FXCollections.observableArrayList();

    @FXML
    private Button manageSubjectsButton;

    @FXML
    private Button deleteCourseButton;

    public ObservableList<Course> getCourseList() {
        return courseList;
    }

    @FXML
    private TableView<Course> courseTable;

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
                    manageSubjectsButton.setDisable(false);
                    deleteCourseButton.setDisable(false);
                }
            });

            courseList.addAll(CustomDbTools.getCourses((Student) LoggingManager.getLoggedUser()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
