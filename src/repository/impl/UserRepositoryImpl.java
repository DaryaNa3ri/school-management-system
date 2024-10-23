package repository.impl;

import exeption.NotFoundException;
import model.User;
import repository.BaseRepository;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class UserRepositoryImpl implements BaseRepository<User> {

    private final static String FIND_USER_BY_USERNAME_PASSWORD_QUERY = "SELECT * FROM users u " +
            "WHERE u.username = ? " +
            "AND u.password = ?";

    private Database db = new Database();

    public Optional<User> findByUsernameAndPassword(String username, String password) throws SQLException {

        PreparedStatement ps = db.getPreparedStatement(FIND_USER_BY_USERNAME_PASSWORD_QUERY);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        Optional<User> optionalUser = Optional.empty();
        while (rs.next()) {
            optionalUser = Optional.of(new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("permission")
            ));
        }
        return optionalUser;
    }

    @Override
    public void saveOrUpdate(User object) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException, NotFoundException {

    }

    @Override
    public Optional<User> findById(Integer id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Set<User> getAll() throws SQLException {
        return Collections.emptySet();
    }
}
