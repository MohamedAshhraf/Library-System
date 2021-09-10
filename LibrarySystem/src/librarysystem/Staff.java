package librarysystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Staff {

    private int id;
    private static int idNum = 0;
    private String username;
    private String mobNumber;
    private String mail;
    private String password;
    private float salary;
    private Library library;
    Scanner sc = new Scanner(System.in);

    public Staff(String username, String password, String mobNumber, String mail, float salary, Library library) {
        this.id = idNum;
        idNum++;
        this.username = username;
        this.mobNumber = mobNumber;
        this.mail = mail;
        this.password = password;
        this.salary = salary;
        this.library = library;
    }

    public void menu() {
        while (true) {
            System.out.println("Hello " + username
                    + "\n1- View all books "
                    + "\n2- Add a book"
                    + "\n3- Delete a book"
                    + "\n4- Library Financials"
                    + "\n5- Log out");
            int option = sc.nextInt();
            if (option == 1) {
                viewBooks();
            } else if (option == 2) {
                addBook();
            } else if (option == 3) {
                deleteBook();
            } else if (option == 4) {
                financials();
            } else if (option == 5) {
                break;
            } else {
                System.out.println("Invalid Option! please try again");
            }
        }
    }

    private void deleteBook() {
        String name;
        System.out.println("Enter Book Name: ");
        sc.skip("\n");
        name = sc.nextLine();
        if (library.removeBook(name)) {
            System.out.println("Book removed successfully");
        } else {
            System.out.println("Book is not present in the library");
        }
    }

    public void viewBooks() {
        System.out.println("These are all the books in the library: \n");
        ArrayList<Book> books = library.getBooks();
        for (Book book : books) {
            System.out.println(book.getBookInfo());
            System.out.println();
        }
    }

    private void addBook() {
        String name;
        String author;
        int count;
        int copyNo;
        float price;
        float spending;

        System.out.println("Book name: ");
        sc.skip("\n");
        name = sc.nextLine();
        ArrayList<Book> books = library.getBooks();
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(name)) {
                System.out.println("Book is already in the library, how many copies you want to add?");
                count = sc.nextInt();
                if (count < 0) {
                    System.out.println("Invalid! copies count can't be negative");
                    return;
                }
                book.increaseCount(count);
                return;
            }
        }
        System.out.println("Author: ");
        author = sc.nextLine();
        System.out.println("Copies count: ");
        count = sc.nextInt();
        if (count < 0) {
            System.out.println("Invalid! copies count can't be negative");
            return;
        }
        System.out.println("Copy Number: ");
        copyNo = sc.nextInt();
        System.out.println("Spendings (Total money paid for these copies): ");
        spending = sc.nextFloat();
        if (spending < 0) {
            System.out.println("Invalid! spendings can't be negative");
        }
        System.out.println("Book Price (for selling): ");
        price = sc.nextFloat();
        if (price < 0) {
            System.out.println("Invalid! price can't be negative");
        }
        library.addBook(new Book(name, count, author, copyNo, price));
        library.financials.spendings.add(spending);
    }

    private void financials() {
        System.out.println("Average Salary: " + library.financials.getAverageSalary());
        System.out.println("Total Sales: " + library.financials.getTotalSales());
        System.out.println("Total Spendings: " + library.financials.getTotalSpendings());
        System.out.println("Total Profit: " + library.financials.getTotalProfit());
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public void setMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSalary() {
        return salary;
    }

    public boolean setSalary(float salary) {
        if (salary < 0) {
            return false;
        }
        library.financials.salaries.remove(this.salary);
        this.salary = salary;
        library.financials.salaries.add(salary);
        return true;
    }
}
