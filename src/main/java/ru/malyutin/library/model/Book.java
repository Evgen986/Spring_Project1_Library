package ru.malyutin.library.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Book {
    private int bookId;
    @NotEmpty(message = "Название книги не может быть пустым")
    @NotNull(message = "Название книги не может быть пустым")
    private String nameBook;
    @NotEmpty(message = "Введите имя автора")
    @NotNull(message = "Введите имя автора")
    private String author;
    private int yearEditions;

    public Book(int bookId, int personId, String nameBook, String author, int yearEditions) {
        this.bookId = bookId;
        this.nameBook = nameBook;
        this.author = author;
        this.yearEditions = yearEditions;
    }

    public Book() {
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearEditions() {
        return yearEditions;
    }

    public void setYearEditions(int yearEditions) {
        this.yearEditions = yearEditions;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

}
