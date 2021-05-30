package org.coursemed.gui.student;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.coursemed.classes.Context;
import org.coursemed.classes.LoggingManager;
import org.coursemed.classes.Student;
import org.coursemed.gui.App;

import java.io.IOException;

public class StudentMainController {
    private final SimpleObjectProperty<Student> loggedUser = new SimpleObjectProperty<>();

    public Student getLoggedUser() {
        return loggedUser.get();
    }

    public void setLoggedUser(Student loggedUser) {
        this.loggedUser.set(loggedUser);
    }

    @FXML
    private void onManageCourses(ActionEvent event) {
        try {
            App.setRoot("student_manage_courses");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBuyCourses(ActionEvent event) {
        try {
            App.setRoot("student_buy_courses");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDepositMoney(ActionEvent event) {
        try {
            App.setRoot("student_deposit_money");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditProfile(ActionEvent event) {
        try {
            Context.pushContext("student");

            App.setRoot("edit_profile");
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
        setLoggedUser((Student) LoggingManager.getLoggedUser());

    }
}
