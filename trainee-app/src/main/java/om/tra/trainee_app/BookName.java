package om.tra.trainee_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
class BookController {

    private List<Book> books = new ArrayList<>();

    @GetMapping("/find-by-name")
    public Book findByName(@RequestParam String name) {

        for (Book b : books) {

            if (b.getName().equalsIgnoreCase(name)) {
                return b;
            }
        }

        return null;
    }
}





