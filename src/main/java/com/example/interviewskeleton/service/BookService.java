package com.example.interviewskeleton.service;

import com.example.interviewskeleton.entity.Book;
import com.example.interviewskeleton.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book addBook(Book book) {
        book.generateSeoName();
        book.generateProductCode();
        return bookRepository.save(book);
    }
}
