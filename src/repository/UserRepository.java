package repository;

import exception.UserNotFoundException;
import model.Course;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<Course>course;

    public UserRepository() {
        this.course = new ArrayList<>();
    }

    public void addCourse(Course course) {
        course.save(course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(course);
    }

    public Course findCourseById(int id) throws UserNotFoundException {
        for (Course course : course) {
            if (course.getId() == id) {
                return course;
            }
        }
        throw new UserNotFoundException("Course with ID " + id + " not found.");
    }

//    public Course findCourseByTitle(String title) throws UserNotFoundException {
//        for (Course course : course) {
//            if (course.getTitle().equalsIgnoreCase(title)) {
//                return course;
//            }
//        }
//        throw new UserNotFoundException("Course with title \"" + title + "\" not found.");
//    }

    public void renameCourse(int id, String newTitle) throws UserNotFoundException {
        Course course = findCourseById(id);
        course.setTitle(newTitle);
    }
    // CourseService.java
    public class CourseService {

        private UserRepository repository;
        private Course course;


        public CourseService(UserRepository  repository) {
            this.repository = repository;
        }

        public void addCourse() {
            addCourse(null);
        }

        public void addCourse(Course course) {
            this.course = course;
            repository.save(course); // Assuming save method in repository
        }
    }

    private void save(Course course) {
    }

}
