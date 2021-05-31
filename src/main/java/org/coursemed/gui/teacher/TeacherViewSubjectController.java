package org.coursemed.gui.teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import org.coursemed.App;
import org.coursemed.classes.Context;
import org.coursemed.classes.Subject;

import java.io.IOException;

public class TeacherViewSubjectController {

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

            App.setRoot("teacher_manage_subjects");
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
