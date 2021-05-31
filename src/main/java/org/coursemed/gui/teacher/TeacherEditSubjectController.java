package org.coursemed.gui.teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.coursemed.App;
import org.coursemed.classes.Context;
import org.coursemed.classes.Subject;
import org.coursemed.tools.CustomDbTools;
import org.coursemed.tools.Tools;

import java.io.IOException;

public class TeacherEditSubjectController {
    private TextField titleField;
    private TextField videoUrlField;

    @FXML
    private TeacherUpsertSubjectController teacherEditUpsertSubjectController;

    private Subject subject;

    @FXML
    private void onSaveChanges(ActionEvent event) {
        if (teacherEditUpsertSubjectController.isValid()) {
            subject.setTitle(titleField.getText());
            subject.setVideoUrl(Tools.getEmbed(videoUrlField.getText()));

            CustomDbTools.updateSubject(subject);

            try {
                App.setRoot("teacher_manage_subjects");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        try {
            App.setRoot("teacher_manage_subjects");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        subject = (Subject) Context.popContext();

        titleField = teacherEditUpsertSubjectController.getTitleField();
        videoUrlField = teacherEditUpsertSubjectController.getVideoUrlField();

        titleField.setText(subject.getTitle());
        videoUrlField.setText(subject.getVideoUrl());
    }
}
