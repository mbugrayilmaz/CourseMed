package org.coursemed.gui.student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import org.coursemed.classes.Context;
import org.coursemed.classes.Course;
import org.coursemed.classes.LoggingManager;
import org.coursemed.classes.Student;
import org.coursemed.gui.App;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;

public class StudentRateTeacherController {
    private Course course;
    private Student loggedUser;
    private double userRating;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label userRatingLabel;

    @FXML
    private Spinner<Double> ratingSpinner;

    public Course getCourse() {
        return course;
    }

    @FXML
    private void onSaveRating(ActionEvent event) {
        userRating = ratingSpinner.getValue();

        try {
            CustomDbTools.upsertRating(loggedUser, course.getTeacher(), userRating);

            setRatingLabel();

            setUserRatingLabel();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            Context.pushContext(course);

            App.setRoot("student_view_course");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        course = (Course) Context.popContext();

        loggedUser = (Student) LoggingManager.getLoggedUser();

        userRating = CustomDbTools.getRating(loggedUser, course.getTeacher());

        setRatingLabel();

        setUserRatingLabel();
    }

    private void setRatingLabel() {
        ratingLabel.setText(course.getTeacher().getFullName() + " (Rating: " + course.getTeacher().getRating() +
                ", Total ratings: " + course.getTeacher().getRatingList().size() + ")");
    }

    private void setUserRatingLabel() {
        if (userRating == 0) {
            userRatingLabel.setText("You haven't rated this teacher yet");
            ratingSpinner.getValueFactory().setValue(10.0);
        } else {
            userRatingLabel.setText("Your rating: " + userRating);
            ratingSpinner.getValueFactory().setValue(userRating);
        }
    }
}
