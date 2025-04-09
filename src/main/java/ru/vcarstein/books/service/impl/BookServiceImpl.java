package ru.vcarstein.books.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.vcarstein.books.dto.BookDTO;
import ru.vcarstein.books.model.Book;
import ru.vcarstein.books.repository.BookRepository;
import ru.vcarstein.books.service.BookService;
import ru.vcarstein.books.util.BookSpecification;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    public Page<Book> getBooks(String title, String brand, String year, Pageable pageable) {
        Specification<Book> spec = BookSpecification.filterBooks(title, brand, year);
        return repository.findAll(spec, pageable);
    }

    @Override
    public void saveBook(Book book) {
        repository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Book getBookById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    }

    public Book convertToEntity(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setBrand(dto.getBrand());
        book.setYear(dto.getYear());
        book.setStock(dto.getStock());
        book.setPrice(dto.getPrice());
        return book;
    }

    public BookDTO convertToDto(Book book) {
        return new BookDTO(
                book.getTitle(),
                book.getBrand(),
                book.getYear(),
                book.getStock(),
                book.getPrice()
        );
    }

    @Override
    public BookDTO saveBookDTO(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book savedBook = repository.save(book);
        return convertToDto(savedBook);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setBrand(bookDTO.getBrand());
        existingBook.setYear(bookDTO.getYear());
        existingBook.setStock(bookDTO.getStock());
        existingBook.setPrice(bookDTO.getPrice());
        Book updatedBook = repository.save(existingBook);
        return convertToDto(updatedBook);
    }
}
