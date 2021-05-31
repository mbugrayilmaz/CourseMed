package org.coursemed.gui.teacher;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.coursemed.App;
import org.coursemed.classes.Context;
import org.coursemed.classes.LoggingManager;
import org.coursemed.classes.Teacher;

import java.io.IOException;
import java.util.List;

public class TeacherMainController {

    private SimpleObjectProperty<Teacher> loggedUser = new SimpleObjectProperty<>();

    public Teacher getLoggedUser() {
        return loggedUser.get();
    }

    public void setLoggedUser(Teacher loggedUser) {
        this.loggedUser.set(loggedUser);
    }

    @FXML
    private Label ratingLabel;

    @FXML
    private void onManageCourses(ActionEvent event) {
        try {
            App.setRoot("teacher_manage_courses");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditProfile(ActionEvent event) {
        try {
            Context.pushContext("teacher");

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
        setLoggedUser((Teacher) LoggingManager.getLoggedUser());

        setRatingLabel();
    }

    private void setRatingLabel() {
        List<Double> ratingList = loggedUser.get().getRatingList();

        double rating = loggedUser.get().getRating();

        ratingLabel.setText("Rating: " + rating + "/10 (" + ratingList.size() + " total ratings)");
    }
}
