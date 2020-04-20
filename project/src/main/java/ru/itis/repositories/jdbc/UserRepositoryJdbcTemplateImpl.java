package ru.itis.repositories.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.models.Role;
import ru.itis.models.State;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryJdbcTemplateImpl implements UserRepository {

    @Autowired
    private JdbcTemplate template;

    //language=SQl
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM client WHERE id = ?;";

    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM client;";

    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO client(first_name, last_name, email, hash_password, " +
            "role, state, confirm_code, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM client WHERE id = ?;";

    //language=SQL
    private static final String SQL_SELECT_BY_CODE = "SELECT * FROM client WHERE confirm_code = ?;";

    //language=SQL
    private static final String SQL_UPDATE_STATE = "UPDATE client SET state = 'CONFIRMED' WHERE confirm_code = ?;";

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM client WHERE email = ?;";

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id"))
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .email(row.getString("email"))
                    .hashPassword(row.getString("hash_password"))
                    .role(Role.valueOf(row.getString("role")))
                    .state(State.valueOf(row.getString("state")))
                    .createdAt(LocalDateTime.parse(row.getString("created_at")))
                    .confirmCode(row.getString("confirm_code"))
                    .build();

    @Override
    public Optional<User> find(Long id) {
        try {
            User user = template.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return template.query(SQL_SELECT_ALL, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getHashPassword());
            statement.setString(5, String.valueOf(user.getRole()));
            statement.setString(6, String.valueOf(user.getState()));
            statement.setString(7, user.getConfirmCode());
            statement.setString(8, String.valueOf(user.getCreatedAt()));
            return statement;
        }, keyHolder);
        user.setId((Long) keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        template.update(SQL_DELETE, new Object[]{id});
    }

    @Override
    public void confirm(String code) {
        template.update(SQL_UPDATE_STATE, code);
    }

    @Override
    public boolean isConfirmed(String code) {
        User user = template.queryForObject(SQL_SELECT_BY_CODE, new Object[]{code}, userRowMapper);
        if (user != null) {
            return user.getState().equals(State.CONFIRMED);
        }
        return false;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = template.queryForObject(SQL_SELECT_BY_EMAIL, new Object[]{email}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}

