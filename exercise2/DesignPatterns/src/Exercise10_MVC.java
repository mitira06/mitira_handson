public class Exercise10_MVC {

    // MODEL - holds data
    static class Student {
        private String name;
        private int id;
        private String grade;

        public Student(String name, int id, String grade) {
            this.name  = name;
            this.id    = id;
            this.grade = grade;
        }

        public String getName()  { return name; }
        public int    getId()    { return id; }
        public String getGrade() { return grade; }

        public void setName(String name)   { this.name  = name; }
        public void setGrade(String grade) { this.grade = grade; }
    }

    // VIEW - displays data
    static class StudentView {
        public void displayStudentDetails(String name, int id, String grade) {
            System.out.println("  +--------------------------+");
            System.out.println("  | Student Details          |");
            System.out.println("  | Name  : " + name);
            System.out.println("  | ID    : " + id);
            System.out.println("  | Grade : " + grade);
            System.out.println("  +--------------------------+");
        }
    }

    // CONTROLLER - connects Model and View
    static class StudentController {
        private Student     model;
        private StudentView view;

        public StudentController(Student model, StudentView view) {
            this.model = model;
            this.view  = view;
        }

        public void updateStudentName(String name)   { model.setName(name); }
        public void updateStudentGrade(String grade) { model.setGrade(grade); }

        public void displayStudentDetails() {
            view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 10: MVC Pattern =====\n");

        // Create model
        Student student = new Student("Ravi Kumar", 101, "B+");

        // Create view
        StudentView view = new StudentView();

        // Create controller linking model and view
        StudentController controller = new StudentController(student, view);

        System.out.println("  Initial Student Details:");
        controller.displayStudentDetails();

        // Update via controller
        System.out.println("\n  Updating name and grade via Controller...");
        controller.updateStudentName("Ravi Kumar Sharma");
        controller.updateStudentGrade("A");

        System.out.println("\n  Updated Student Details:");
        controller.displayStudentDetails();

        System.out.println("\n  MVC separates data (Model), display (View), and logic (Controller)!");
    }
}
