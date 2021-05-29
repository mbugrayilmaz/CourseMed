package org.coursemed.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.coursemed.classes.Context;
import org.coursemed.classes.Course;
import org.coursemed.classes.Subject;
import org.coursemed.tools.CustomDbTools;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherManageSubjectsController {
    private Course course;
    private final ObservableList<Subject> subjectList = FXCollections.observableArrayList();

    @FXML
    private TableView<Subject> subjectTable;

    public ObservableList<Subject> getSubjectList() {
        return subjectList;
    }

    @FXML
    private void onAddSubject(ActionEvent event) {
        try {
            Context.pushContext(course);

            App.setRoot("teacher_add_subject");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onViewSubject(ActionEvent event) {
        try {
            Context.pushContext(course);
            Context.pushContext(subjectTable.getSelectionModel().getSelectedItem());

            App.setRoot("teacher_view_subject");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteSubject(ActionEvent event) {
        if (subjectTable.getSelectionModel().getSelectedIndex()!=-1){
            Subject subject=subjectTable.getSelectionModel().getSelectedItem();

            CustomDbTools.deleteItem(subject,"subject");

            subjectList.remove(subject);
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            App.setRoot("teacher_manage_courses");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        course = (Course) Context.popContext();

        ArrayList<Subject> subjects = new ArrayList<>();

        try {
            subjects = CustomDbTools.getSubjects(course);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        course.setSubjects(subjects);

        subjectList.addAll(subjects);
    }
}
