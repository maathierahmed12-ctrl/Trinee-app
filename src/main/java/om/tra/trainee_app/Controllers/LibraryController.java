package om.tra.trainee_app.Controllers;

import om.tra.trainee_app.Entities.Author;
import om.tra.trainee_app.Entities.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryController {

    private static List<Author> authorList = new ArrayList<>();
    private static List<Book> bookList = new ArrayList<>();

    @GetMapping("/add-author")
    public String addAuthor(@RequestParam int id,
                            @RequestParam String name,
                            @RequestParam String biography) {

        Author author = new Author(id, name, biography);
        authorList.add(author);

        return "Author added successfully! ID: " + id + ", Name: " + name;
    }

    @GetMapping("/all-authors")
    public List<Author> getAllAuthors() {
        return authorList;
    }

    @GetMapping("/add-relational-book")
    public String addRelationalBook(@RequestParam int id,
                                    @RequestParam String name,
                                    @RequestParam int authorId) {

        boolean authorExists = false;

        for (Author a : authorList) {
            if (a.getId() == authorId) {
                authorExists = true;
                break;
            }
        }

        if (!authorExists) {
            return "Error: Author ID " + authorId + " does not exist. Book not saved.";
        }

        Book book = new Book(id, name);
        bookList.add(book);

        return "Book added successfully with Author ID: " + authorId;
    }

    @GetMapping("/author-report")
    public String getAuthorReport(@RequestParam String authorName) {

        Author foundAuthor = null;

        // 🔍 1. البحث عن المؤلف
        for (Author a : authorList) {
            if (a.getName().equalsIgnoreCase(authorName)) {
                foundAuthor = a;
                break;
            }
        }

        if (foundAuthor == null) {
            return "Error: Author with name '" + authorName + "' not found.";
        }

        StringBuilder report = new StringBuilder();

        report.append("Author Report\n");
        report.append("--------------\n");
        report.append("ID: ").append(foundAuthor.getId()).append("\n");
        report.append("Name: ").append(foundAuthor.getName()).append("\n");
        report.append("Biography: ").append(foundAuthor.getBiography()).append("\n\n");

        report.append("Books Written: ");

        boolean hasBooks = false;

        for (Book b : bookList) {
            if (b.getId() == foundAuthor.getId()) {
                report.append("\n - ").append(b.getName());
                hasBooks = true;
            }
        }

        if (!hasBooks) {
            report.append("None");
        }

        return report.toString();
    }
}

