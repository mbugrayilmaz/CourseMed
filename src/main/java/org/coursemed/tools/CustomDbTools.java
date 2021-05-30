package org.coursemed.tools;

import org.coursemed.classes.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomDbTools {
    private CustomDbTools() {
    }

    public static boolean addSubject(Subject subject, Course course) {
        String query = """
                INSERT INTO subject(title, video_url, course_id)
                VALUES(?,?,?);
                """;

        try (PreparedStatement preparedStatement = DbTools.prepareStatement(query)) {

            preparedStatement.setString(1, subject.getTitle());
            preparedStatement.setString(2, subject.getVideoUrl());
            preparedStatement.setInt(3, course.getId());

            preparedStatement.execute();

            subject.setId(preparedStatement.getGeneratedKeys().getInt(1));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean addCourse(Course course) {
        String query = """
                INSERT INTO course(name,price,teacher_id)
                VALUES(?,?,?);
                """;

        try (PreparedStatement preparedStatement = DbTools.prepareStatement(query)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setDouble(2, course.getPrice());
            preparedStatement.setInt(3, course.getTeacher().getId());

            preparedStatement.execute();

            course.setId(preparedStatement.getGeneratedKeys().getInt(1));

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean addStudent(Student student) {
        String query = """
                INSERT INTO student(username,password,first_name,last_name,balance)
                VALUES(?,?,?,?,?);
                """;

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        try {
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getFirstName());
            preparedStatement.setString(4, student.getLastName());
            preparedStatement.setDouble(5, student.getBalance());

            preparedStatement.execute();

            student.setId(preparedStatement.getGeneratedKeys().getInt(1));

            preparedStatement.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean updateStudent(Student student) {
        String query = """
                UPDATE student
                SET username=?,password=?,first_name=?,last_name=?,balance=?
                WHERE id=?;
                """;

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        try {
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getFirstName());
            preparedStatement.setString(4, student.getLastName());
            preparedStatement.setDouble(5, student.getBalance());
            preparedStatement.setInt(6, student.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean addTeacher(Teacher teacher) {
        String query = """
                INSERT INTO teacher(username,password,first_name,last_name,balance)
                VALUES(?,?,?,?,?);
                """;

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        try {
            preparedStatement.setString(1, teacher.getUsername());
            preparedStatement.setString(2, teacher.getPassword());
            preparedStatement.setString(3, teacher.getFirstName());
            preparedStatement.setString(4, teacher.getLastName());
            preparedStatement.setDouble(5, teacher.getBalance());

            preparedStatement.execute();

            teacher.setId(preparedStatement.getGeneratedKeys().getInt(1));

            preparedStatement.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean updateTeacher(Teacher teacher) {
        String query = """
                UPDATE teacher
                SET username=?,password=?,first_name=?,last_name=?,balance=?
                WHERE id=?;
                """;

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        try {
            preparedStatement.setString(1, teacher.getUsername());
            preparedStatement.setString(2, teacher.getPassword());
            preparedStatement.setString(3, teacher.getFirstName());
            preparedStatement.setString(4, teacher.getLastName());
            preparedStatement.setDouble(5, teacher.getBalance());
            preparedStatement.setInt(6, teacher.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean addAdmin(Admin admin) {
        String query = """
                INSERT INTO admin(username,password,first_name,last_name)
                VALUES(?,?,?,?);
                """;

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        try {
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getFirstName());
            preparedStatement.setString(4, admin.getLastName());

            preparedStatement.execute();

            admin.setId(preparedStatement.getGeneratedKeys().getInt(1));

            preparedStatement.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean updateUser(User user, String tableName) {
        String query = """
                UPDATE ?
                SET username=?,password=?,first_name=?,last_name=?
                WHERE id=?;
                """;

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        try {
            preparedStatement.setString(1, tableName);
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean deleteItem(Item item, String table) {
        String query = "DELETE FROM " + table + " WHERE id=?;";

        try (PreparedStatement preparedStatement = DbTools.prepareStatement(query)) {
            preparedStatement.setInt(1, item.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static void enroll(Student student, Course course) throws SQLException {
        createEnrollmentIfNotExists();

        String query = """
                INSERT INTO enrollment(student_id, course_id)
                VALUES(?,?);
                """;

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        preparedStatement.setInt(1, student.getId());
        preparedStatement.setInt(2, course.getId());

        preparedStatement.execute();

        preparedStatement.close();
    }

    public static void dropCourse(Student student, Course course) throws SQLException {
        String query = """
                DELETE FROM enrollment
                WHERE student_id=? AND course_id=?;
                """;

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        preparedStatement.setInt(1, student.getId());
        preparedStatement.setInt(2, course.getId());

        preparedStatement.execute();

        preparedStatement.close();
    }

    public static void upsertRating(Student student, Teacher teacher, double rating) throws SQLException {
        double oldRating = getRating(student, teacher);

        String query;

        PreparedStatement preparedStatement;

        if (oldRating == 0) {
            query = """
                    INSERT INTO ratings(student_id,teacher_id,rating)
                    VALUES(?,?,?)
                    """;

            preparedStatement = DbTools.prepareStatement(query);

            preparedStatement.setInt(1, student.getId());
            preparedStatement.setInt(2, teacher.getId());
            preparedStatement.setDouble(3, rating);
        } else {
            query = """
                    UPDATE ratings
                    SET rating=?
                    WHERE student_id=? AND teacher_id=?;
                    """;

            preparedStatement = DbTools.prepareStatement(query);

            preparedStatement.setDouble(1, rating);
            preparedStatement.setInt(2, student.getId());
            preparedStatement.setInt(3, teacher.getId());
        }

        preparedStatement.execute();

        preparedStatement.close();

        teacher.setRatingList(new ArrayList<>(getRatings(teacher)));
    }

    public static double getRating(Student student, Teacher teacher) {
        createRatingsIfNotExists();

        double rating = 0;

        String query = """
                SELECT rating
                FROM ratings
                WHERE student_id=? AND teacher_id=?;
                """;

        try (PreparedStatement preparedStatement = DbTools.prepareStatement(query)) {

            preparedStatement.setInt(1, student.getId());
            preparedStatement.setInt(2, teacher.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                rating = resultSet.getDouble(1);
            }

            return rating;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    public static ArrayList<Double> getRatings(Teacher teacher) {
        createRatingsIfNotExists();

        ArrayList<Double> ratingList;

        String query = """
                SELECT rating
                FROM ratings
                WHERE teacher_id=?;
                """;

        try (PreparedStatement preparedStatement = DbTools.prepareStatement(query)) {

            preparedStatement.setInt(1, teacher.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            ratingList = new ArrayList<>();

            while (resultSet.next()) {
                ratingList.add(resultSet.getDouble(1));
            }

            teacher.setRatingList(new ArrayList<>(ratingList));

            return ratingList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Course> getAvailableCourses(Student student) throws SQLException {
        createEnrollmentIfNotExists();

        String query = """
                SELECT course.*
                    FROM course
                    LEFT JOIN enrollment
                    ON course.id=enrollment.course_id
                    WHERE student_id!=? OR student_id IS NULL
                """;

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        preparedStatement.setInt(1, student.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Course> courseList = new ArrayList<>();

        while (resultSet.next()) {
            Course course = new Course();

            course.setId(resultSet.getInt(1));
            course.setName(resultSet.getString(2));
            course.setPrice(resultSet.getDouble(3));

            course.setTeacher(getTeacher(resultSet.getInt("teacher_id")));

            courseList.add(course);
        }

        preparedStatement.close();

        return courseList;
    }


    public static ArrayList<Subject> getSubjects(Course course) throws SQLException {
        createSubjectIfNotExists();

        String query = "SELECT * FROM subject WHERE course_id=?";

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        preparedStatement.setInt(1, course.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Subject> subjectList = new ArrayList<>();

        while (resultSet.next()) {
            Subject subject = new Subject();

            subject.setId(resultSet.getInt(1));
            subject.setTitle(resultSet.getString(2));
            subject.setVideoUrl(resultSet.getString(3));

            subjectList.add(subject);
        }

        preparedStatement.close();

        return subjectList;
    }

    public static ArrayList<Course> getCourses(Student student) throws SQLException {
        createEnrollmentIfNotExists();

        String query = "SELECT course_id FROM enrollment WHERE student_id=?";

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        preparedStatement.setInt(1, student.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Course> courseList = new ArrayList<>();

        while (resultSet.next()) {
            int courseId = resultSet.getInt(1);

            Course course = getCourse(courseId);

            courseList.add(course);
        }

        preparedStatement.close();

        return courseList;
    }

    public static ArrayList<Course> getCourses(Teacher teacher) throws SQLException {
        createCourseIfNotExists();

        String query = "SELECT * FROM course WHERE teacher_id=?";

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        preparedStatement.setInt(1, teacher.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Course> courseList = new ArrayList<>();

        while (resultSet.next()) {
            Course course = new Course();

            course.setId(resultSet.getInt(1));
            course.setName(resultSet.getString(2));
            course.setPrice(resultSet.getDouble(3));

            course.setTeacher(teacher);

            courseList.add(course);
        }

        preparedStatement.close();

        return courseList;
    }

    public static ArrayList<Student> getStudents() throws SQLException {
        createStudentIfNotExists();

        String query = "SELECT * FROM student";

        Statement statement = DbTools.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<Student> studentList = new ArrayList<>();

        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt(1));
            student.setUsername(resultSet.getString(2));
            student.setPassword(resultSet.getString(3));
            student.setFirstName(resultSet.getString(4));
            student.setLastName(resultSet.getString(5));
            student.setBalance(resultSet.getDouble(6));

            studentList.add(student);
        }

        statement.close();

        return studentList;
    }

    public static ArrayList<Teacher> getTeachers() throws SQLException {
        createTeacherIfNotExists();

        String query = "SELECT * FROM teacher";

        Statement statement = DbTools.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<Teacher> teacherList = new ArrayList<>();

        while (resultSet.next()) {
            Teacher teacher = new Teacher();
            teacher.setId(resultSet.getInt(1));
            teacher.setUsername(resultSet.getString(2));
            teacher.setPassword(resultSet.getString(3));
            teacher.setFirstName(resultSet.getString(4));
            teacher.setLastName(resultSet.getString(5));
            teacher.setBalance(resultSet.getDouble(6));

            getRatings(teacher);

            teacherList.add(teacher);
        }

        statement.close();

        return teacherList;
    }

    public static ArrayList<Admin> getAdmins() throws SQLException {
        createAdminIfNotExists();

        String query = "SELECT * FROM admin";

        Statement statement = DbTools.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<Admin> adminList = new ArrayList<>();

        while (resultSet.next()) {
            Admin admin = new Admin();
            admin.setId(resultSet.getInt(1));
            admin.setUsername(resultSet.getString(2));
            admin.setPassword(resultSet.getString(3));
            admin.setFirstName(resultSet.getString(4));
            admin.setLastName(resultSet.getString(5));

            adminList.add(admin);
        }

        statement.close();

        return adminList;
    }

    public static Course getCourse(int courseId) throws SQLException {
        String query = "SELECT * FROM course WHERE id=?";

        PreparedStatement preparedStatement = DbTools.prepareStatement(query);

        preparedStatement.setInt(1, courseId);

        ResultSet resultSet = preparedStatement.executeQuery();

        Course course = new Course();

        course.setId(resultSet.getInt(1));
        course.setName(resultSet.getString(2));
        course.setPrice(resultSet.getDouble(3));

        course.setTeacher(getTeacher(resultSet.getInt("teacher_id")));

        return course;
    }

    public static Teacher getTeacher(int teacherId) {
        String query = "SELECT * FROM teacher WHERE id=?";


        try (PreparedStatement preparedStatement = DbTools.prepareStatement(query)) {

            preparedStatement.setInt(1, teacherId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Teacher teacher = new Teacher();
            teacher.setId(resultSet.getInt(1));
            teacher.setUsername(resultSet.getString(2));
            teacher.setPassword(resultSet.getString(3));
            teacher.setFirstName(resultSet.getString(4));
            teacher.setLastName(resultSet.getString(5));
            teacher.setBalance(resultSet.getDouble(6));

            teacher.setRatingList(getRatings(teacher));

            preparedStatement.close();

            return teacher;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    private static void createRatingsIfNotExists() {
        String query = """
                CREATE TABLE IF NOT EXISTS ratings(
                id INTEGER PRIMARY KEY,
                teacher_id INTEGER NOT NULL,
                student_id INTEGER NOT NULL,
                rating REAL NOT NULL,
                FOREIGN KEY(teacher_id) REFERENCES teacher(id)
                    ON DELETE CASCADE ON UPDATE CASCADE,
                FOREIGN KEY(student_id) REFERENCES student(id)
                    ON DELETE CASCADE ON UPDATE CASCADE,
                UNIQUE(teacher_id,student_id));
                """;

        DbTools.executeQuery(query);
    }

    private static void createEnrollmentIfNotExists() {
        String query = """
                CREATE TABLE IF NOT EXISTS enrollment(
                id INTEGER PRIMARY KEY,
                student_id INTEGER NOT NULL,
                course_id INTEGER NOT NULL,
                FOREIGN KEY(student_id) REFERENCES student(id)
                    ON DELETE CASCADE ON UPDATE CASCADE,
                FOREIGN KEY(course_id) REFERENCES course(id)
                    ON DELETE CASCADE ON UPDATE CASCADE,
                UNIQUE(student_id,course_id));
                """;

        DbTools.executeQuery(query);
    }

    private static void createSubjectIfNotExists() {
        String query = """
                CREATE TABLE IF NOT EXISTS subject(
                id INTEGER PRIMARY KEY,
                title TEXT NOT NULL,
                video_url TEXT NOT NULL,
                course_id INTEGER NOT NULL,
                FOREIGN KEY(course_id) REFERENCES course(id)
                    ON DELETE CASCADE ON UPDATE CASCADE)
                """;

        DbTools.executeQuery(query);
    }

    private static void createCourseIfNotExists() {
        String query = """
                CREATE TABLE IF NOT EXISTS course(
                id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                price REAL DEFAULT 0 NOT NULL,
                teacher_id INTEGER NOT NULL,
                FOREIGN KEY(teacher_id) REFERENCES teacher(id)
                    ON DELETE CASCADE ON UPDATE CASCADE)
                """;

        DbTools.executeQuery(query);
    }

    private static void createStudentIfNotExists() {
        String query = """
                    CREATE TABLE IF NOT EXISTS student(
                    id INTEGER PRIMARY KEY,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    first_name TEXT NOT NULL,
                    last_name TEXT NOT NULL,
                    balance REAL DEFAULT 0 NOT NULL);
                """;

        DbTools.executeQuery(query);
    }

    private static void createTeacherIfNotExists() {
        String query = """
                    CREATE TABLE IF NOT EXISTS teacher(
                    id INTEGER PRIMARY KEY,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    first_name TEXT NOT NULL,
                    last_name TEXT NOT NULL,
                    balance REAL DEFAULT 0 NOT NULL);
                """;

        DbTools.executeQuery(query);
    }

    private static void createAdminIfNotExists() {
        String query = """
                    CREATE TABLE IF NOT EXISTS admin(
                    id INTEGER PRIMARY KEY,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    first_name TEXT NOT NULL,
                    last_name TEXT NOT NULL);
                """;

        DbTools.executeQuery(query);

        if (DbTools.isTableEmpty("admin")) {
            String defaultAdmin = """
                    INSERT INTO admin(username,password,first_name,last_name)
                    VALUES('admin','admin','default','admin');
                    """;

            DbTools.executeQuery(defaultAdmin);
        }

    }
}
