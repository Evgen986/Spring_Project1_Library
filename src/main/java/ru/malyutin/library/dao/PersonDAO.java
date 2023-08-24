package ru.malyutin.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.malyutin.library.model.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> show() {
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person index(int id){
        return jdbcTemplate.query("SELECT * FROM person where personid=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void create(Person newPerson){
        jdbcTemplate.update("INSERT INTO person(fullname, yearofbirth) VALUES (?, ?)",
                newPerson.getFullName(),
                newPerson.getYearOfBirth());
    }

    public void update(Person person, int id){
        jdbcTemplate.update("UPDATE person SET fullname = ?, yearofbirth = ? where personid = ?",
                person.getFullName(), person.getYearOfBirth(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE personid=?", id);
    }

    public Person haveBook(int id){
        return jdbcTemplate.query("select * from person where personid = (select personid from book where bookid = ?)",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
}
