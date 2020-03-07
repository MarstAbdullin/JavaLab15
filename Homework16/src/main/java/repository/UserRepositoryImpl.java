package repository;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id")).email(row.getString("email")).password(row.getString("hash"))
                    .build();

    @Override
    public Optional<User> create(User user) {
        {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection
                        .prepareStatement(SQL_INSERT);
                statement.setString(1, user.getEmail());
                statement.setString(2, passwordEncoder.encode(user.getPassword()));
                return statement;
            }, keyHolder);

            user.setId((Long) keyHolder.getKey());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> read(Long id) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    private static final String SQL_SELECT_BY_ID = "select * from course where id = ?";
    //language=MySQL
    private static final String SQL_INSERT = "insert into db6.user (email, hash) values (?, ?)";
}
