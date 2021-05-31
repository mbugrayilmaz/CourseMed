package org.coursemed.gui.teacher;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.coursemed.App;
import org.coursemed.classes.Context;
import org.coursemed.classes.Course;
import org.coursemed.classes.LoggingManager;
import org.coursemed.classes.Teacher;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;

public class TeacherManageCoursesController {
    private final ObservableList<Course> courseList = FXCollections.observableArrayList();

    @FXML
    public Button editCourseButton;

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
    private void onCreateCourse(ActionEvent event) {
        try {
            App.setRoot("teacher_create_course");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditCourse(ActionEvent event) {
        try {
            if (courseTable.getSelectionModel().getSelectedIndex() != -1) {
                Context.pushContext(courseTable.getSelectionModel().getSelectedItem());

                App.setRoot("teacher_edit_course");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onManageSubjects(ActionEvent event) {
        try {
            if (courseTable.getSelectionModel().getSelectedIndex() != -1) {
                Context.pushContext(courseTable.getSelectionModel().getSelectedItem());

                App.setRoot("teacher_manage_subjects");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteCourse(ActionEvent event) {
        if (courseTable.getSelectionModel().getSelectedIndex() != -1) {
            Course course = courseTable.getSelectionModel().getSelectedItem();

            CustomDbTools.deleteItem(course, "course");

            courseList.remove(course);
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            App.setRoot("teacher_main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        try {
            TableColumn<Course, Integer> studentsColumn = new TableColumn<>("# of Students");

            studentsColumn.prefWidthProperty().bind(courseTable.widthProperty().multiply(0.2));

            studentsColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(CustomDbTools.getNumberOfStudents(cellData.getValue())));

            courseTable.getColumns().add(studentsColumn);

            courseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    manageSubjectsButton.setDisable(false);
                    editCourseButton.setDisable(false);
                    deleteCourseButton.setDisable(false);
                }
            });

            courseList.addAll(CustomDbTools.getCourses((Teacher) LoggingManager.getLoggedUser()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
