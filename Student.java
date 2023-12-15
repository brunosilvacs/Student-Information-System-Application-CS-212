public class Student 
{
    private int id;
    private String firstName;
    private String lastName;
    private double gpa;

    public Student(int id, String firstName, String lastName, double gpa)
    {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setGpa(gpa);
    }

    public Student setId(int id)
    {
        if (id < 1 || id > 99)
        {
            throw new IllegalArgumentException("Invalid ID");
        }

        this.id = id;
        return this;
    }

    public Student setFirstName(String firstName)
    {
        if (firstName == null || firstName.length() < 2)
        {
            throw new IllegalArgumentException("Invalid first name");
        }

        this.firstName = firstName;
        return this;
    }

    public Student setLastName(String lastName)
    {
        if (lastName == null || lastName.length() < 2)
        {
            throw new IllegalArgumentException("Invalid last name");
        }

        this.lastName = lastName;
        return this;
    }

    public Student setGpa(double gpa)
    {
        if (gpa < 0 || gpa > 4.0)
        {
            throw new IllegalArgumentException("Invalid gpa");
        }

        this.gpa = gpa;
        return this;
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public double getGpa()
    {
        return gpa;
    }

    public String toString() 
    {
        return "Student ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", GPA: " + gpa;
    }
}


