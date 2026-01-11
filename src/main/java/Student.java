public class Student {

    private String Name;
    private int Age;
    private String Date;

    public Student(String name, int age, String date) {
        Name = name;
        Age = age;
        Date = date;
    }

    public String GetName() {
        return Name;
    }

    public int GetAge() {
        return Age;
    }

    public String GetDate() {
        return Date;
    }

    public String ToString() {
        return Name + " " + Integer.toString(Age) + " " + Date;
    }

    public static Student Parse(String str) throws WrongStudentName, WrongStudentAge, WrongDateOfBirth {
        String[] data = str.split(" ");
        if (data[0].contains(" ") || !(data[0] instanceof String))
            throw new WrongStudentName();
        try {
            if (Integer.parseInt(data[1]) < 1 || Integer.parseInt(data[1]) > 99)
                throw new WrongStudentAge();
        } catch (NumberFormatException e) {
            throw new WrongStudentAge();
        }
        if (!data[2].matches("\\d{2}-\\d{2}-\\d{4}") || !(data[2] instanceof String))
            throw new WrongDateOfBirth();
        if (data.length != 3)
            return new Student("Parse Error", -1, "");
        return new Student(data[0], Integer.parseInt(data[1]), data[2]);
    }
}