import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean issued;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public void issueBook() {
        issued = true;
    }

    public void returnBook() {
        issued = false;
    }

    @Override
    public String toString() {
        return String.format(
                "%-10d %-25s %-20s %-10s",
                bookId,
                title,
                author,
                issued ? "Issued" : "Available");
    }
}

class Library {

    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {

        for (Book b : books) {
            if (b.getBookId() == book.getBookId()) {
                System.out.println("Book ID already exists!");
                return;
            }
        }

        books.add(book);
        System.out.println("Book Added Successfully!");
    }

    public void displayBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n---------------------------------------------------------------------");
        System.out.printf("%-10s %-25s %-20s %-10s\n",
                "ID", "TITLE", "AUTHOR", "STATUS");
        System.out.println("---------------------------------------------------------------------");

        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("---------------------------------------------------------------------");
    }

    public void searchBookById(int id) {

        for (Book book : books) {
            if (book.getBookId() == id) {
                System.out.println("\nBook Found:");
                System.out.println(book);
                return;
            }
        }

        System.out.println("Book not found.");
    }

    public void searchBookByTitle(String title) {

        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().toLowerCase()
                    .contains(title.toLowerCase())) {

                if (!found) {
                    System.out.println("\nMatching Books:");
                }

                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    public void issueBook(int id) {

        for (Book book : books) {

            if (book.getBookId() == id) {

                if (book.isIssued()) {
                    System.out.println("Book already issued.");
                } else {
                    book.issueBook();
                    System.out.println("Book Issued Successfully!");
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    public void returnBook(int id) {

        for (Book book : books) {

            if (book.getBookId() == id) {

                if (!book.isIssued()) {
                    System.out.println("Book was not issued.");
                } else {
                    book.returnBook();
                    System.out.println("Book Returned Successfully!");
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    public void deleteBook(int id) {

        for (int i = 0; i < books.size(); i++) {

            if (books.get(i).getBookId() == id) {

                if (books.get(i).isIssued()) {
                    System.out.println("Cannot delete an issued book.");
                    return;
                }

                books.remove(i);
                System.out.println("Book Deleted Successfully!");
                return;
            }
        }

        System.out.println("Book not found.");
    }
}

public class LibraryManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {

            System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book By ID");
            System.out.println("4. Search Book By Title");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Delete Book");
            System.out.println("8. Exit");
            System.out.println("===============================================");

            System.out.print("Enter Choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Book ID: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid Book ID!");
                        sc.next();
                        break;
                    }

                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();

                    library.addBook(new Book(id, title, author));
                    break;

                case 2:
                    library.displayBooks();
                    break;

                case 3:

                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();

                    library.searchBookById(id);
                    break;

                case 4:

                    System.out.print("Enter Book Title: ");
                    title = sc.nextLine();

                    library.searchBookByTitle(title);
                    break;

                case 5:

                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();

                    library.issueBook(id);
                    break;

                case 6:

                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();

                    library.returnBook(id);
                    break;

                case 7:

                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();

                    library.deleteBook(id);
                    break;

                case 8:

                    System.out.println("Thank You for Using Library Management System!");
                    sc.close();
                    return;

                default:

                    System.out.println("Invalid Choice! Please try again.");
            }
        }
    }
}