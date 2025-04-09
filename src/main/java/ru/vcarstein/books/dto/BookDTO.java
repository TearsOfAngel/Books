package ru.vcarstein.books.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @NotBlank(message = "Название книги обязательно для заполнения")
    @Size(min = 1, max = 255, message = "Название книги должно содержать от 1 до 255 символов")
    private String title;

    @NotBlank(message = "Бренд обязателен для заполнения")
    @Size(min = 1, max = 100, message = "Бренд должен содержать от 1 до 100 символов")
    private String brand;

    @NotBlank(message = "Год выпуска обязателен для заполнения")
    private String year;

    @NotNull(message = "Количество на складе обязательно для заполнения")
    @Positive(message = "Количество на складе должно быть положительным числом")
    private Integer stock;

    @NotNull(message = "Цена обязательна для заполнения")
    @Positive(message = "Цена должна быть положительным числом")
    private BigDecimal price;
}
