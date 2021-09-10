package librarysystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<User> users;
    private ArrayList<Staff> staff;
    protected Financial financials;
    Scanner sc = new Scanner(System.in);

    private static Library instance = null;

    private Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.staff = new ArrayList<>();
        financials = new Financial();
        users.add(new User("user", "user", "user@example.com", "0123456789", this));
        staff.add(new Staff("staff", "staff", "0113456789", "staff@example.com", 3000, this));
        financials.salaries.add(3000F);
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void menu() {
        while (true) {
            System.out.println("Hello! "
                    + "\nPlease choose from the following: "
                    + "\n1- Sign up as a user"
                    + "\n2- Sign up as a staff member"
                    + "\n3- Login as a user"
                    + "\n4- Login as a staff member"
                    + "\n5- Exit");
            int option = sc.nextInt();
            if (option == 1) {
                signUpUser();
            } else if (option == 2) {
                signUpStaff();
            } else if (option == 3) {
                loginUser();
            } else if (option == 4) {
                loginStaff();
            } else if (option == 5) {
                break;
            } else {
                System.out.println("Invalid Option! please try again");
            }
        }
    }

    public void signUpUser() {
        String email, password, username, phonenum;
        System.out.println("========== SIGN UP USER ==========");
        System.out.println("username: ");
        username = sc.next();
        while (true) {
            boolean taken = false;
            for (User user : users) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    System.out.println("Username is already taken, please try again");
                    taken = true;
                    break;
                }
            }
            if (!taken) {
                break;
            }
            System.out.println("username: ");
            username = sc.next();
        }
        System.out.println("password: ");
        password = sc.next();
        System.out.println("email: ");
        email = sc.next();
        while (true) {
            boolean taken = false;
            for (User user : users) {
                if (user.getMail().equalsIgnoreCase(email)) {
                    System.out.println("Email is already taken, please try again");
                    taken = true;
                    break;
                }
            }
            if (!taken) {
                break;
            }
            System.out.println("email: ");
            email = sc.next();
        }
        System.out.println("Phone Number: ");
        phonenum = sc.next();
        users.add(new User(username, password, email, phonenum, this));
        System.out.println("User Signed up Successfully");
    }

    private void signUpStaff() {
        String email, password, username, phonenum;
        System.out.println("========== SIGN UP STAFF ==========");
        System.out.println("username: ");
        username = sc.next();
        while (true) {
            boolean taken = false;
            for (Staff staffMember : staff) {
                if (staffMember.getUsername().equalsIgnoreCase(username)) {
                    System.out.println("Username is already taken, please try again");
                    taken = true;
                    break;
                }
            }
            if (!taken) {
                break;
            }
            System.out.println("username: ");
            username = sc.next();
        }
        System.out.println("password: ");
        password = sc.next();
        System.out.println("email: ");
        email = sc.next();
        while (true) {
            boolean taken = false;
            for (Staff staffMember : staff) {
                if (staffMember.getMail().equalsIgnoreCase(email)) {
                    System.out.println("Email is already taken, please try again");
                    taken = true;
                    break;
                }
            }
            if (!taken) {
                break;
            }
            System.out.println("email: ");
            email = sc.next();
        }
        System.out.println("Phone Number: ");
        phonenum = sc.next();
        System.out.println("Enter Salary: ");
        float salary = sc.nextFloat();
        while (salary < 0) {
            System.out.println("Salary can't be negative");
            System.out.println("Enter Salary: ");
            salary = sc.nextFloat();
        }
        staff.add(new Staff(username, password, email, phonenum, salary, this));
        System.out.println("Staff Member Signed up Successfully");
        financials.salaries.add(salary);
    }

    public void loginUser() {
        String username, password;

        System.out.println("========== LOGIN USER ==========");
        System.out.println("username: ");
        username = sc.next();
        System.out.println("password: ");
        password = sc.next();
        User loggedInUser = null;
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getPassword().equals(password)) {
                    loggedInUser = user;
                    break;
                }
            }
        }
        if (loggedInUser == null) {
            System.out.println("Username or password is incorrect, couldn't login");
        } else {
            System.out.println("Logged In Successfully");
            loggedInUser.menu();
        }
    }

    private void loginStaff() {
        String username, password;

        System.out.println("========== LOGIN STAFF ==========");
        System.out.println("username: ");
        username = sc.next();
        System.out.println("password: ");
        password = sc.next();
        Staff loggedInStaff = null;
        for (Staff staffMember : staff) {
            if (staffMember.getUsername().equalsIgnoreCase(username)) {
                if (staffMember.getPassword().equals(password)) {
                    loggedInStaff = staffMember;
                    break;
                }
            }
        }
        if (loggedInStaff == null) {
            System.out.println("Username or password is incorrect, couldn't login");
        } else {
            System.out.println("Logged In Successfully");
            loggedInStaff.menu();
        }
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public boolean removeBook(String bookName) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getName().equalsIgnoreCase(bookName)) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }

    Book searchBook(String bookName) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getName().equalsIgnoreCase(bookName)) {
                return books.get(i);
            }
        }
        return null;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
