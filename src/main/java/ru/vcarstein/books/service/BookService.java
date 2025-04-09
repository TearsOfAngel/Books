package ru.vcarstein.books.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vcarstein.books.dto.BookDTO;
import ru.vcarstein.books.model.Book;

import java.util.List;

public interface BookService {
    Page<Book> getBooks(String title, String brand, String year, Pageable pageable);

    List<BookDTO> getAllBooks();

    void saveBook(Book book);

    void deleteBook(Long id);

    Book getBookById(Long id);

    BookDTO saveBookDTO(BookDTO bookDTO);

    BookDTO updateBook(Long id, BookDTO bookDTO);
}
