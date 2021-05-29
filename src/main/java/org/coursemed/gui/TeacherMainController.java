package org.coursemed.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.coursemed.classes.LoggingManager;
import org.coursemed.classes.Teacher;

import java.io.IOException;

public class TeacherMainController {

    private SimpleObjectProperty<Teacher> loggedUser = new SimpleObjectProperty<>();

    public Teacher getLoggedUser() {
        return loggedUser.get();
    }

    public void setLoggedUser(Teacher loggedUser) {
        this.loggedUser.set(loggedUser);
    }

    @FXML
    private void onManageCourses(ActionEvent event) {
        try {
            App.setRoot("teacher_manage_courses");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogout(ActionEvent event) {
        LoggingManager.logout();

        try {
            App.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        setLoggedUser((Teacher) LoggingManager.getLoggedUser());
    }

}
