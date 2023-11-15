
package com.example.interviewskeleton.service;

import com.example.interviewskeleton.entity.Book;
import com.example.interviewskeleton.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookService = new BookService(bookRepository);
    }

    @Test
    void testAddBook_ShouldGenerateValidSeoName() {
        var book = new Book();
        book.setTitle("A Great Title");
        book.setAuthor("John Doe");
        book.setYear(2023);

        bookService.addBook(book);

        assertTrue(book.getSeoName().equals("John Doe - A Great Title (2023)"));

        assertEquals("0100.011...1.00110.01001..2023.", book.getProductCode());
    }
}
