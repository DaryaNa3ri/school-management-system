package repository;

import exeption.NotFoundException;
import model.Teacher;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface BaseRepository<T> {

    void saveOrUpdate(T object) throws SQLException;

    void delete(Integer id) throws SQLException, NotFoundException;

    Optional<T> findById(Integer id) throws SQLException;

    Set<T> getAll() throws SQLException;

}
