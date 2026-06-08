package om.tra.trainee_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookID {

    private static List<Book> storage = new ArrayList<>();

    @GetMapping("/find-id")
    public Book findById(@RequestParam int id) {

        for (Book b : storage) {
            if (b.getId() == id) {
                return b;
            }
        }

        return null;
    }

    @GetMapping("/search-msg")
    public String searchMessage(@RequestParam int id) {

        for (Book book : storage) {
            if (book.getId() == id) {
                return "Found: " + book.getName();
            }
        }

        return "Sorry, that book ID is not available.";
    }
}