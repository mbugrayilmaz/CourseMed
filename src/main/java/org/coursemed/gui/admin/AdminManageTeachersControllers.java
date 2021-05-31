package org.coursemed.gui.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.coursemed.classes.Teacher;
import org.coursemed.App;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;

public class AdminManageTeachersControllers {
    @FXML
    public Button deleteTeacherButton;
    @FXML
    private TableView<Teacher> teacherTable;

    private ObservableList<Teacher> teacherList = FXCollections.observableArrayList();

    public ObservableList<Teacher> getTeacherList() {
        return teacherList;
    }

    @FXML
    private void onDeleteTeacher(ActionEvent event) {
        if (teacherTable.getSelectionModel().getSelectedIndex() != -1) {
            Teacher teacher = teacherTable.getSelectionModel().getSelectedItem();

            CustomDbTools.deleteItem(teacher, "teacher");

            teacherList.remove(teacher);
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
            teacherList.addAll(CustomDbTools.getTeachers());

            teacherTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    deleteTeacherButton.setDisable(false);
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
