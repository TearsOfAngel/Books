package ru.vcarstein.books.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vcarstein.books.model.Book;
import ru.vcarstein.books.service.BookService;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping()
    public String listBooks(@RequestParam(required = false) String title,
                            @RequestParam(required = false) String brand,
                            @RequestParam(required = false) String year,
                            @RequestParam(defaultValue = "0") int page,
                            Model model) {

        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").ascending());

        title = (title != null && !title.isBlank()) ? title : "";
        brand = (brand != null && !brand.isBlank()) ? brand : "";
        year = (year != null && !year.isBlank()) ? year : "";

        Page<Book> books = bookService.getBooks(title, brand, year, pageable);

        model.addAttribute("books", books);
        model.addAttribute("title", title);
        model.addAttribute("brand", brand);
        model.addAttribute("year", year);
        return "books";
    }

    @PostMapping
    public String saveBook(Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        Book existingBook = bookService.getBookById(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setBrand(book.getBrand());
        existingBook.setYear(book.getYear());
        existingBook.setStock(book.getStock());
        existingBook.setPrice(book.getPrice());
        bookService.saveBook(existingBook);
        return "redirect:/books";
    }
}