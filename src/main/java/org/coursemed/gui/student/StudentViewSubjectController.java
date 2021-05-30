package org.coursemed.gui.student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import org.coursemed.classes.Context;
import org.coursemed.classes.Subject;
import org.coursemed.gui.App;

import java.io.IOException;

public class StudentViewSubjectController {
    private Subject subject;

    @FXML
    private WebView webView;

    public Subject getSubject() {
        return subject;
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            webView.getEngine().load(null);

            App.setRoot("student_view_course");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        subject = (Subject) Context.popContext();

        webView.getEngine().load(subject.getVideoUrl());
    }
}
