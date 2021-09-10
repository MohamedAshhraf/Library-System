package librarysystem;

import java.util.ArrayList;

public class Book {

    private String name;
    private String author;
    private int count;
    private int copyNo;
    private float price;
    private ArrayList<User> subscribers;

    public Book(String bookName, int count, String bookAuthor, int copyNo, float bookPrice) {

        this.author = bookAuthor;
        this.count = count;
        this.name = bookName;
        this.copyNo = copyNo;
        this.price = bookPrice;
        subscribers = new ArrayList<>();
    }

    public String getBookInfo() {
        return " Name: " + name
                + " \n Author: " + author
                + " \nCopy Count: " + count
                + " \n Copy Number: " + copyNo
                + " \n Price: " + price;
    }

    public boolean book() {
        if (count == 0) return false;
        count--;
        return true;
    }

    public void increaseCount(int val) {
        count += val;
        notifyUsers();
    }

    public void subscribe(User user) {
        subscribers.add(user);
    }

    public void unsubscribe(User user) {
        subscribers.remove(user);
    }

    public void notifyUsers() {
        for (User user: subscribers) {
            user.notify("Book " + name + " is available in the library");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCopyNo() {
        return copyNo;
    }

    public void setCopyNo(int copyNo) {
        this.copyNo = copyNo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}