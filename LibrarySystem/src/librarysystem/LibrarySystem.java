package librarysystem;

public class LibrarySystem {

    public static void main(String[] args) {
        Library library = Library.getInstance();
        library.menu();
    }
    
}
