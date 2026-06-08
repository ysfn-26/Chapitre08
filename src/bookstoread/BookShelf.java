package bookstoread;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookShelf {
    // La liste stocke désormais de vrais objets Book
    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void add(Book... booksToAdd) {
        books.addAll(Arrays.asList(booksToAdd));
    }

    // Regroupement par année de publication
    public Map<Year, List<Book>> groupByPublicationYear() {
        return books.stream().collect(Collectors.groupingBy(book ->
                Year.of(book.getPublishedOn().getYear())));
    }

    // Regroupement générique selon n'importe quel critère
    public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
        return books.stream().collect(Collectors.groupingBy(fx));
    }
}