import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String toString() {
        return "Roll No: " + rollNumber + " | Name: " + name + " | Grade: " + grade;
    }
}

public class StudentManagementSystem {

    private static ArrayList<Student> students = new ArrayList<>();
    private static final String FILE = "students.dat";

    public static void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            students = (ArrayList<Student>) in.readObject();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }

    public static void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(students);
        } catch (Exception e) {
            System.out.println("⚠ Error saving data.");
        }
    }

    public static void addStudent(Scanner sc) {
        sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter roll number: ");
        int roll = sc.nextInt();

        System.out.print("Enter grade: ");
        String grade = sc.next();

        students.add(new Student(name, roll, grade));
        saveData();
        System.out.println("✅ Student added successfully!");
    }

    public static void editStudent(Scanner sc) {
        System.out.print("Enter roll number to edit: ");
        int roll = sc.nextInt();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                sc.nextLine();
                System.out.print("Enter new name: ");
                s.setName(sc.nextLine());

                System.out.print("Enter new grade: ");
                s.setGrade(sc.next());

                saveData();
                System.out.println("✅ Student updated!");
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }

    public static void removeStudent(Scanner sc) {
        System.out.print("Enter roll number to remove: ");
        int roll = sc.nextInt();

        students.removeIf(s -> s.getRollNumber() == roll);
        saveData();
        System.out.println("✅ Student removed.");
    }

    public static void searchStudent(Scanner sc) {
        System.out.print("Enter roll number to search: ");
        int roll = sc.nextInt();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println("🎯 " + s);
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }

    public static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("📂 No student records found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        loadData();
        int choice;

        do {
            System.out.println("\n--- STUDENT MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> editStudent(sc);
                case 3 -> removeStudent(sc);
                case 4 -> searchStudent(sc);
                case 5 -> displayStudents();
                case 6 -> System.out.println("👋 Exiting...");
                default -> System.out.println("❌ Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}
