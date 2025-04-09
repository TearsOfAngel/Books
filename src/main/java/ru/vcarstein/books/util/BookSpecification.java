package ru.vcarstein.books.util;

import org.springframework.data.jpa.domain.Specification;
import ru.vcarstein.books.model.Book;

public class BookSpecification {

    public static Specification<Book> filterBooks(String title, String brand, String year) {
        return (root, criteriaQuery, cb) -> {

            if (title != null && !title.isBlank()) {
                return cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
            }
            if (brand != null && !brand.isBlank()) {
                return cb.like(cb.lower(root.get("brand")), "%" + brand.toLowerCase() + "%");
            }
            if (year != null && !year.isBlank()) {
                return cb.equal(root.get("year"), year);
            }
            return cb.conjunction();
        };
    }
}