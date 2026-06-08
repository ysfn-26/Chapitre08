package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// Imports nécessaires pour AssertJ (assertThat) et JUnit 5
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BookShelfSpec {
    private BookShelf shelf;

    // Déclaration des livres pour qu'ils soient accessibles dans tous les tests
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void init() throws Exception {
        shelf = new BookShelf();

        // Initialisation des données de test
        effectiveJava = new Book("Effective Java", "Joshua Bloch", LocalDate.of(2008, 5, 8));
        codeComplete = new Book("Code Complete", "Steve McConnel", LocalDate.of(2004, 6, 9));
        mythicalManMonth = new Book("The Mythical Man-Month", "Frederick Phillips Brooks", LocalDate.of(1975, 1, 1));
        cleanCode = new Book("Clean Code", "Robert C. Martin", LocalDate.of(2008, 8, 1));
    }

    @Test
    public void shelfEmptyWhenNoBookAdded() throws Exception {
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
    }

    @Test
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
        shelf.add(effectiveJava, codeComplete);
        List<Book> books = shelf.books();
        assertEquals(2, books.size(), () -> "BookShelf should have two books.");
    }

    @Test
    public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
        shelf.add();
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), "BookShelf should be empty.");
    }

    @Test
    void booksReturnedFromBookShelfIsImmutableForClient() {
        shelf.add(effectiveJava, codeComplete);
        List<Book> books = shelf.books();
        try {
            Book extraBook = new Book("The Pragmatic Programmer", "Andrew Hunt", LocalDate.of(1999, 10, 30));
            books.add(extraBook);
            fail(() -> "Should not be able to add book to books");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }

    @Test
    @DisplayName("Les livres à l'intérieur de la bibliothèque sont regroupés selon les critères fournis par l'utilisateur (regroupés par nom d'auteur)")
    void groupBooksByUserProvidedCriteria() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<String, List<Book>> booksByAuthor = shelf.groupBy(Book::getAuthor);

        assertThat(booksByAuthor).containsKey("Joshua Bloch").containsValues(Collections.singletonList(effectiveJava));
        assertThat(booksByAuthor).containsKey("Steve McConnel").containsValues(Collections.singletonList(codeComplete));
        assertThat(booksByAuthor).containsKey("Frederick Phillips Brooks").containsValues(Collections.singletonList(mythicalManMonth));
        assertThat(booksByAuthor).containsKey("Robert C. Martin").containsValues(Collections.singletonList(cleanCode));
    }


}