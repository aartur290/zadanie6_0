import java.io.IOException;
import java.util.Scanner;

class WrongStudentName extends Exception {
}

class WrongStudentAge extends Exception {
}

class WrongDateOfBirth extends Exception {
}

class MenuChoiceError extends Exception {
}

class Main {
  public static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    while (true) {
      try {
        int ex = menu();
        switch (ex) {
          case 1:
            exercise1();
            break;
          case 2:
            exercise2();
            break;
          case 3:
            exercise3();
            break;
          default:
            return;
        }
      } catch (IOException e) {
      } catch (WrongStudentName e) {
        System.out.println("Invalid student's name! (no spaces allowed)");
      } catch (WrongStudentAge e) {
        System.out.println("Invalid student's age! (1-99)");
      } catch (WrongDateOfBirth e) {
        System.out.println("Invalid student's date! (DD-MM-YYYY)");
      } catch (MenuChoiceError e) {
        System.out.println("Invalid menu choice! (Not a number)");
      }

    }
  }

  public static int menu() throws MenuChoiceError {
    System.out.println("Wciśnij:");
    System.out.println("1 - aby dodać studenta");
    System.out.println("2 - aby wypisać wszystkich studentów");
    System.out.println("3 - aby wyszukać studenta po imieniu");
    System.out.println("0 - aby wyjść z programu");
    System.out.print("");
    var choice = scan.next();
    if (!choice.matches("\\d+"))
      throw new MenuChoiceError();
    return Integer.parseInt(choice);

  }

  public static String ReadName() throws WrongStudentName {
    scan.nextLine();
    System.out.println("Podaj imię: ");
    String name = scan.nextLine();
    if (name.contains(" "))
      throw new WrongStudentName();

    return name;
  }

  public static int ReadAge() throws WrongStudentAge {
    System.out.println("Podaj wiek: ");
    var age = scan.nextInt();
    scan.nextLine();
    if (age < 1 || age > 99)
      throw new WrongStudentAge();
    return age;
  }

  public static String ReadDate() throws WrongDateOfBirth {
    System.out.println("Podaj datę urodzenia DD-MM-YYYY");
    var date = scan.nextLine();
    if (!date.matches("\\d{2}-\\d{2}-\\d{4}"))
      throw new WrongDateOfBirth();
    return date;
  }

  public static void exercise1() throws IOException, WrongStudentName, WrongStudentAge, WrongDateOfBirth {
    var name = ReadName();
    var age = ReadAge();
    var date = ReadDate();
    (new Service()).addStudent(new Student(name, age, date));
  }

  public static void exercise2() throws IOException {
    var students = (new Service()).getStudents();
    for (Student current : students) {
      System.out.println(current.ToString());
    }
  }

  public static void exercise3() throws IOException, WrongStudentName {
    var name = ReadName();
    var wanted = (new Service()).findStudentByName(name);
    if (wanted == null)
      System.out.println("Nie znaleziono...");
    else {
      System.out.println("Znaleziono: ");
      System.out.println(wanted.ToString());
    }
  }
}
