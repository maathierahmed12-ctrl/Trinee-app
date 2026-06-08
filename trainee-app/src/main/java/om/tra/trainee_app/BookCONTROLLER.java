package om.tra.trainee_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
 public class BookCONTROLLER {

    public List<Book> books = new ArrayList<>();

    @GetMapping("/add-book")
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
}
