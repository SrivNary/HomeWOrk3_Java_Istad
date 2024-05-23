package view;

import exception.UserNotFoundException;
import lombok.SneakyThrows;
import model.Course;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;
import service.CourseService;
import service.InvalidInputException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CourseDashboard {
//    private static final UserService userService = new ServiceImp();
    private static final Scanner scanner = new Scanner(System.in);
    @SneakyThrows
    public static void view(){
        option();
        while (true){

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addNewCourse();
                    break;
                case 2:
                    listAllCourses();
                    break;
                case 3:
                    findCourseById();
                    break;
                case 4:
                    findCourseByTitle();
                    break;
                case 5:
                    removeCourse();
                    break;
                case 0:
                case 99:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
                    break;
            }
        }
    }
    private static void option(){

        System.out.println("1. Add new course");
        System.out.println("2. List all courses");
        System.out.println("3. Find course by ID");
        System.out.println("4. Find course by Title");
        System.out.println("5. Rename Course");
        System.out.println("0/99. Exit");
        System.out.println("Select an option:");
    }
    private static void addNewCourse() {
        try {
            System.out.print("Input course title: ");
            String title = scanner.nextLine();
            System.out.print("Input instructor names : ");
            List<String> instructorNames = Arrays.asList(scanner.nextLine().split(","));
            System.out.print("Input instructor requirements : ");
            List<String> instructorRequirements = Arrays.asList(scanner.nextLine().split(","));
            System.out.print("Input course start date (yyyy-MM-dd): ");
            String dateString = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(dateString);
            CourseService.addCourse(title, instructorNames, instructorRequirements, startDate);
            System.out.println("Course added successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }

    }
    private static void listAllCourses() {
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader("StudentsShow.txt"));
            String data;
            Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER);
            for (int i = 0; i < 5; i++) {
                table.setColumnWidth(i, 20, 20);
            }
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Date of Birth");
            table.addCell("ClassRoom");
            table.addCell("Subject");
            String[] result = null;


            while ((data = bufferedReader.readLine()) != null) {
                result = data.split(",");
                table.addCell(result[0], 1);
                table.addCell(result[1], 1);
                table.addCell(result[2], 1);
                table.addCell(result[3], 1);
                table.addCell(result[4], 1);
            }

            System.out.println(table.render());
        } catch (Exception ignore) {
        }
    }
    private static void findCourseById() {
        System.out.print("Enter course ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        try {
            Course course = CourseService.findCourseById(id);
            System.out.println("Course found:");
            displayCourseInfo(course);
        } catch (UserNotFoundException exception) {
            System.out.println("Course not found: " + exception.getMessage());
        }
    }

    private static void findCourseByTitle() {
        System.out.print("Enter course title: ");
        String title = scanner.nextLine().toLowerCase(); // Convert input to lowercase
        try {
            Course course = CourseService.findCourseByTitle(title);
            System.out.println("Course found:");
            displayCourseInfo(course);
        } catch (UserNotFoundException exception) {
            System.out.println("Course not found: " + exception.getMessage());
        }
    }

    private static void displayCourseInfo(Course course) {
        System.out.println("ID\tTitle\tInstructor Names\tInstructor Requirements\tStart Date");
        System.out.println(course.getId() + "\t" + course.getTitle() + "\t" +
                String.join(",", course.getInstructorNames()) + "\t" +
                String.join(",", course.getInstructorRequirements()) + "\t" +
                course.getStartDate());
    }


    private static void removeCourse() throws UserNotFoundException {
        System.out.print("Enter course ID to rename: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        CourseService.removeCourse(id, newTitle);
        System.out.println("Course renamed successfully.");
    }
}

