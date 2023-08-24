package ru.malyutin.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.malyutin.library.model.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> show(){
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> show(int id){
        return jdbcTemplate.query("SELECT * FROM book where personid=?",new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void create(Book newBook){
        jdbcTemplate.update("INSERT INTO book (namebook, author, yeareditions) VALUES (?, ?, ?)",
                newBook.getNameBook(),
                newBook.getAuthor(),
                newBook.getYearEditions());
    }

    public Book index(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE bookid=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void freeBook(int id){
        jdbcTemplate.update("UPDATE book SET  personid=null WHERE bookid=?", id);
    }

    public void busyBook(int idBook, int idPerson){
        jdbcTemplate.update("UPDATE book SET personid=? WHERE bookid=?", idPerson, idBook);
    }

    public void update(Book book, int id){
        jdbcTemplate.update("UPDATE book set namebook=?, author=?, yeareditions=? where bookid=?",
                book.getNameBook(), book.getAuthor(), book.getYearEditions(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE bookid=?", id);
    }
}
