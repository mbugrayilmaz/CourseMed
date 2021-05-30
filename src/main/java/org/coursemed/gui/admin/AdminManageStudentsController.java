package org.coursemed.gui.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.coursemed.classes.Student;
import org.coursemed.gui.App;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;

public class AdminManageStudentsController {
    @FXML
    private TableView<Student> studentTable;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    public ObservableList<Student> getStudentList() {
        return studentList;
    }

    @FXML
    private void onDeleteStudent(ActionEvent event) {
        if (studentTable.getSelectionModel().getSelectedIndex() != -1) {
            Student student = studentTable.getSelectionModel().getSelectedItem();

            CustomDbTools.deleteItem(student, "student");

            studentList.remove(student);
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            App.setRoot("admin_main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        try {
            studentList.addAll(CustomDbTools.getStudents());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
