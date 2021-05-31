package org.coursemed.gui.teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.coursemed.classes.Context;
import org.coursemed.classes.Course;
import org.coursemed.classes.Subject;
import org.coursemed.App;
import org.coursemed.tools.CustomDbTools;
import org.coursemed.tools.Tools;

import java.io.IOException;

public class TeacherAddSubjectController {
    private TextField titleField;
    private TextField videoUrlField;

    @FXML
    private TeacherUpsertSubjectController teacherAddUpsertSubjectController;

    @FXML
    private void onAddSubject(ActionEvent event) {
        if (teacherAddUpsertSubjectController.isValid()) {
            Subject subject = new Subject();

            Course course = (Course) Context.popContext();

            subject.setTitle(titleField.getText());
            subject.setVideoUrl(Tools.getEmbed(videoUrlField.getText()));

            CustomDbTools.addSubject(subject, course);

            try {
                Context.pushContext(course);

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
        titleField = teacherAddUpsertSubjectController.getTitleField();
        videoUrlField = teacherAddUpsertSubjectController.getVideoUrlField();
    }
}
