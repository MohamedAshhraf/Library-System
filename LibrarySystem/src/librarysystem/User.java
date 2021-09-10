package librarysystem;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    private String username;
    private String password;
    private String mail;
    private String mobNumber;
    boolean membership;
    private Library library;
    ArrayList<String> notifications;

    Scanner sc = new Scanner(System.in);

    public User(String username, String userPass, String userMail, String userMobNumber, Library library) {

        this.mail = userMail;
        this.password = userPass;
        this.mobNumber = userMobNumber;
        this.username = username;
        this.library = library;
        notifications = new ArrayList<>();
    }

    public void menu() {
        while (true) {
            System.out.println("Hello " + username
                    + "\nPlease choose from the following: "
                    + "\n1- Search for a book"
                    + "\n2- View Notifications"
                    + "\n3- Activate membership"
                    + "\n4- Log out");
            int option = sc.nextInt();
            if (option == 1) {
                searchBook();
            } else if (option == 2) {
                viewNotifications();
            } else if (option == 3) {
                activateMembership();
            } else if (option == 4) {
                break;
            } else {
                System.out.println("Invalid Option! please try again");
            }
        }
    }

    public void activateMembership() {
        if (membership) {
            System.out.println("Your membership is already activated");
        } else {
            System.out.println("Your memberships is activated successfully");
        }
    }

    public void searchBook() {
        System.out.println("Enter book name: ");
        sc.skip("\n");
        String bookName = sc.nextLine();
        Book book = library.searchBook(bookName);
        if (book == null) {
            System.out.println("The book is not present in the library");
        } else {
            if (book.getCount() == 0) {
                System.out.println("The book is out of stock, do you want to be notified when available?");
                System.out.println("(y for yes, anything else for no): ");
                String choice = sc.next();
                if (choice.equalsIgnoreCase("y")) {
                    book.subscribe(this);
                    System.out.println("Subscribed successfully");
                }
            } else {
                System.out.println("The book is available in the library");
                System.out.println("Do you want to order? (y for yes, anything else for no)");
                String choice = sc.next();
                if (choice.equalsIgnoreCase("y")) {
                    orderBook(book);
                }
            }
        }
    }

    private void orderBook(Book book) {
        if (book == null) {
            System.out.println("The book is not present in the library");
        } else if (book.getCount() == 0) {
            System.out.println("The book is out of stock");
        } else {
            paymentMethod(book);
        }
    }

    private void paymentMethod(Book book) {
        while (true) {
            System.out.println("Do you want to pay cash on delivery or visa? 1 for cash & 2 for card");
            int num = sc.nextInt();
            if (num == 1) {
                book.book();
                library.financials.sales.add(book.getPrice());
                System.out.println("Book is ordered successfully with Cash on Delivery payment method");
                break;
            } else if (num == 2) {
                System.out.println("please enter the 14 number of the card");
                String cardNumber = sc.next();
                System.out.println("please enter the expiration date");
                String str = sc.next();
                book.book();
                library.financials.sales.add(book.getPrice());
                System.out.println("Book is ordered successfully with Credit Card payment method");
                break;
            } else {
                System.out.println("Invalid option! please try again");
            }
        }
    }

    public void notify(String notification) {
        notifications.add(notification);
    }

    public void viewNotifications() {
        System.out.println("Notifications: ");
        for (String not : notifications) {
            System.out.println(not);
        }
        System.out.println("\nDo you want to erase notifications? (y for yes, any other key for no): ");
        String erase = sc.next();
        if (erase.equalsIgnoreCase("y")) {
            notifications.clear();
            System.out.println("Notifications Erased successfully");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public void setMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
    }

    public boolean isMembership() {
        return membership;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }
}
