package om.tra.trainee_app.Controllers;

import om.tra.trainee_app.Entities.Book;
import om.tra.trainee_app.Services.InventoryBook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static om.tra.trainee_app.Controllers.Controller.catalog;

@RestController
 public class BookCONTROLLER {

    private static List<Book> books = new ArrayList<>();

    @GetMapping("/addBook")
    public String addBook(@RequestParam int id,
                          @RequestParam String name) {

        Book b = new Book(id, name);
        books.add(b);

        return "Book added successfully! ID: " + id + ", Name: " + name;
    }

    @GetMapping("/all-books")
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/find-id")
    public Book findById(@RequestParam int id) {

        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }

        return null;
    }

    @GetMapping("/check-stock")
    public String checkStock(@RequestParam int bookId) {

        for (InventoryBook book : catalog) {

            if (book.getBookId() == bookId) {

                if (book.getStockCount() > 0) {
                    return "Available: " + book.getTitle()
                            + " | Price: " + book.getPrice();
                } else {
                    return "Sold Out: " + book.getTitle();
                }
            }
        }

        return "The bookstore does not carry a book with ID: " + bookId;
    }

    @RestController
    public class BooksController {

        private List<InventoryBook> catalog = new ArrayList<>();

        @GetMapping("/add-inventory-book")
        public String addInventoryBook(
                @RequestParam int bookId,
                @RequestParam String title,
                @RequestParam double price,
                @RequestParam int stockCount) {

            InventoryBook book =
                    new InventoryBook(bookId, title, price, stockCount);

            catalog.add(book);

            return "Book added successfully to the bookstore catalog: " + title;
        }

        @GetMapping("/check-stock")
        public String checkStock(@RequestParam int bookId) {

            for (InventoryBook book : catalog) {

                if (book.getBookId() == bookId) {

                    if (book.getStockCount() > 0) {
                        return "Available: " + book.getTitle()
                                + " | Price: " + book.getPrice();
                    } else {
                        return "Sold Out: " + book.getTitle();
                    }
                }
            }

            return "The bookstore does not carry a book with ID: " + bookId;
        }
    }

    @RestController

    public class booksController {

        private List<InventoryBook> catalog = new ArrayList<>();

    }

    @GetMapping("/add-inventory-book")
    public String addInventoryBook(@RequestParam int bookId,
                                   @RequestParam String title,
                                   @RequestParam double price,
                                   @RequestParam int stockCount) {

        InventoryBook book =
                new InventoryBook(bookId, title, price, stockCount);

        catalog.add(book);

        return "Book added successfully!";
    }



    @GetMapping("/low-stock-report")
    public String lowStockReport(@RequestParam int threshold) {

        StringBuilder report = new StringBuilder();

        for (InventoryBook book : catalog) {

            if (book.getStockCount() <= threshold) {

                report.append("Title: ")
                        .append(book.getTitle())
                        .append(" | Stock: ")
                        .append(book.getStockCount())
                        .append("\n");
            }
        }

        if (report.length() == 0) {
            return "No books currently need reordering.";
        }

        return report.toString();
    }
}