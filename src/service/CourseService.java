package service;

import exception.UserNotFoundException;
import model.Course;

import java.util.Date;
import java.util.List;

public  class CourseService {
    private static CourseService repository;
    private int id;
    private String newTitle;



    public void UserService (CourseService repository) {
        this.repository = repository;
    }

    public static void addCourse(String title, List<String> instructorNames, List<String> instructorRequirements, Date startDate) throws InvalidInputException {
        if (title.isBlank() || instructorNames.isEmpty() || instructorRequirements.isEmpty()) {
            throw new InvalidInputException("Title, instructor names, and instructor requirements cannot be empty.");
        }
        Course course;
        course = new Course(title, instructorNames, instructorRequirements, startDate);
        repository.addCourse(course);
    }

    private void addCourse(Course course) {

    }

    public List<Course> getAllCourse() {
        return repository.getAllCourse();
    }



    public static Course findCourseById(int id) throws UserNotFoundException {
        return repository.findeCourseById(id);
    }

    private Course findeCourseById(int id) {

        return null;
    }

    public static Course findCourseByTitle(String title) throws UserNotFoundException {
        return repository.findCourseByTitle(title);
    }

    public static void removeCourse(int id, String newTitle) throws UserNotFoundException{
//        this.id = id;
//        this.newTitle = newTitle;
        repository.removeCourse(id, newTitle);
    }

}
