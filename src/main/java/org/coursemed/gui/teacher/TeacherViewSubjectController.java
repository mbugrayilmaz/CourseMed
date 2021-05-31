package org.coursemed.gui.teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import org.coursemed.classes.Context;
import org.coursemed.classes.Subject;
import org.coursemed.App;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

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
