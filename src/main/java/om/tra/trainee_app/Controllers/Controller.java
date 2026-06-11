package om.tra.trainee_app.Controllers;

import om.tra.trainee_app.Services.InventoryBook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {


        static List<InventoryBook> catalog = new ArrayList<>();

        @GetMapping("/add-inventory-book")
        public String addInventoryBook(
                @RequestParam int bookId,
                @RequestParam String title,
                @RequestParam double price,
                @RequestParam int stockCount) {

            InventoryBook book =
                    new InventoryBook(bookId, title, price, stockCount);

            catalog.add(book);

            return "Book added successfully to the bookstore catalog: "
                    + title;
        }
    }



