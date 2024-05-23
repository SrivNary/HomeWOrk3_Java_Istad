package service;

import model.Course;

import java.util.List;
import java.util.Map;

public  interface UserServices {
    void generateDesignedObjectForWriting();
    int addNewStudent(Course course);
    List<Course> listAllStudents();
    List<Course> findCourseById(String id);
    List<Course> findCourseByTitle(String title);
    default Map<Integer, String> restoreData() {
        return null;
    }
}
