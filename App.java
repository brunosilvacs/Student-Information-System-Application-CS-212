import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class App 
{
    public static void main (String[] args) throws FileNotFoundException
    {
        TreeMap<Integer, Student> container = new TreeMap<>();
        Scanner input = new Scanner(System.in);

        File file = new File("students.txt");
        Scanner fileInput = new Scanner (file);

        while (fileInput.hasNext())
        {
            String s = fileInput.nextLine();
            String[] students = s.split(",");
            container.put(Integer.parseInt(students[0]), new Student(Integer.parseInt(students[0]), students[1], students[2], Double.parseDouble(students[3])));
        }

        while (true)
        {
            System.out.println("+--------------------------+");
            System.out.println("|   Available Menu Items   |");
            System.out.println("+--------------------------+");
            System.out.println("1. Print all students");
            System.out.println("2. Add new student");
            System.out.println("3. Delete an existing student:");
            System.out.println("4. Find a student with id");
            System.out.println("5. Quit the app");
            System.out.print("Your Choice[1-5]: ");

            try
            {
                int choice = input.nextInt();
                System.out.println();

                switch (choice)
                {
                    case 1: 
                    {
                        System.out.println("We Have " + container.size() + "Students as follows...");
                        System.out.println();
                    
                        System.out.println("+----------+----------------------+----------------------+----------+");
                        System.out.printf("| %-8s | %-20s | %-20s | %-8s |%n", "   ID   ", "   First Name   ", "   Last Name   ", "   GPA  ");                    
                        System.out.println("+----------+----------------------+----------------------+----------+");
                        for (Student student : container.values()) 
                        {
                            System.out.printf("| % -8d | %20s | %20s | % -8.2f |%n", student.getId(), student.getFirstName(), student.getLastName(), student.getGpa());
                        }
                        System.out.println("+----------+----------------------+----------------------+----------+");
                        System.out.println();

                        break;
                    }

                    case 2: {
                        int _id = 0, key;
                        String _firstName = "", _lastName = "";
                        double _gpa = 0.0;
                    
                        System.out.println("Adding New Student...");
                        System.out.println();
                    
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Enter Student ID (1-99): ");
                                _id = input.nextInt();
                                if (_id < 1 || _id > 99) 
                                {
                                    throw new IllegalArgumentException("\nStudent ID must be >= 1 && <= 99");
                                }
                    
                                key = _id;
                    
                                if (container.containsKey(key)) 
                                {
                                    System.out.println("\nStudent with id == " + _id + " already exists!\n");
                                }

                                else
                                {
                                    break;
                                }
                            } 
                            catch (InputMismatchException e) 
                            {
                                System.out.println("\nStudent ID must be an integer\n");
                                input.nextLine(); 
                            } 
                            catch (IllegalArgumentException e) 
                            {
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                        }
                    
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Enter First Name: ");
                                _firstName = input.next().trim();

                                if (_firstName.length() < 2) 
                                {
                                    throw new IllegalArgumentException("\nFirst Name Must Have At Least 2 Characters!\n");
                                }
                                break;
                            }  
                            catch (IllegalArgumentException e) 
                            {
                                System.out.println(e.getMessage());
                            }
                        }
                    
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Enter Last Name: ");
                                _lastName = input.next().trim();
                                if (_lastName.length() < 2) 
                                {
                                    throw new IllegalArgumentException("\nLast Name Must Have At Least 2 Characters!\n");
                                }
                                break;
                            }  
                            catch (IllegalArgumentException e) 
                            {
                                System.out.println(e.getMessage());
                            }
                        }
                    
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Enter Student GPA: ");
                                _gpa = input.nextDouble();
                                if (_gpa < 0.0 || _gpa > 4.0) 
                                {
                                    throw new IllegalArgumentException("\nGPA Must be >= 0.0 && <= 4.0\n");
                                }
                                break;
                            } 
                            catch (InputMismatchException e) 
                            {
                                System.out.println("Invalid input. Please enter a valid GPA.\n");
                                input.nextLine(); 
                            } 
                            catch (IllegalArgumentException e) 
                            {
                                System.out.println(e.getMessage());
                            }
                        }
                    
                        Student student = new Student(_id, _firstName, _lastName, _gpa);
                        container.put(_id, student);
                        System.out.println("Added 1 Student");
                        System.out.println("+----------+----------------------+----------------------+----------+");
                        System.out.printf("|   ID     |    First Name        |     Last Name        |   GPA    |%n");
                        System.out.println("+----------+----------------------+----------------------+----------+");
                        System.out.printf("| % -8d | %20s | %20s | % -8.2f |%n", student.getId(), student.getFirstName(), student.getLastName(), student.getGpa());
                        System.out.println("+----------+----------------------+----------------------+----------+");
                        System.out.println();
                        break;
                    }

                    case 3:
                    {
                        System.out.print("Enter a Student ID to remove: ");
                        int key = input.nextInt();

                        if (container.containsKey(key))
                        {
                            Student student = container.get(key);
                            container.remove(key);
                            System.out.println("\nDeleting 1 Student\n");
                            System.out.println("+----------+----------------------+----------------------+----------+");
                            System.out.printf("|   ID     |    First Name        |     Last Name        |   GPA    |%n");
                            System.out.println("+----------+----------------------+----------------------+----------+");
                            System.out.printf("| % -8d | %20s | %20s | % -8.2f |%n", student.getId(), student.getFirstName(), student.getLastName(), student.getGpa());
                            System.out.println("+----------+----------------------+----------------------+----------+");
                            System.out.println();
                        }

                        else
                        {
                            System.out.println("\nNo Existing Student to Delete\n");
                        }

                        break;
                    }

                    case 4:
                    {
                        System.out.print("Enter a Student ID: ");
                        int key = input.nextInt();
                        System.out.println();

                        if (container.containsKey(key))
                        {
                            for (Student student: container.values())
                            {
                                if (student.getId() == key)
                                {
                                    System.out.println("Student With ID = " + key + " Found");
                                    System.out.println("+----------+----------------------+----------------------+----------+");
                                    System.out.printf("|   ID     |    First Name        |     Last Name        |   GPA    |%n");
                                    System.out.println("+----------+----------------------+----------------------+----------+");
                                    System.out.printf("| % -8d | %20s | %20s | % -8.2f |%n", student.getId(), student.getFirstName(), student.getLastName(), student.getGpa());
                                    System.out.println("+----------+----------------------+----------------------+----------+");
                                    System.out.println();
                                    break;
                                }
                            }
                        }

                        else
                        {
                            System.out.println("\nNo Existing Student to Display\n");
                        }

                        break;
                    }

                    case 5:
                    {
                        try (FileWriter fileWriter = new FileWriter(file)) 
                        {
                            for (Student student : container.values()) 
                            {
                                fileWriter.write(student.getId() + "," + student.getFirstName() + ","
                                         + student.getLastName() + "," + student.getGpa() + "\n");
                            }
                            System.out.println("Student information has been successfully saved to the file.");
                        } 
                        catch (IOException e) 
                        {
                            System.out.println("Unable to write to the file.");
                        }
                        
                        System.out.println("Now Exiting Goodbye!");
                        System.exit(0);
                        break;
                    }

                    default:
                    {
                        System.out.println("Illegal Menu Item Selected");
                        System.out.println();
                        break;
                    }
                }
            }
            catch (InputMismatchException ex)
            {
                System.out.print("\nInput must be a number\n");
                input.nextLine();
            }
        }
    }
}