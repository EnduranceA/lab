package ru.itis.repositories.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.models.Song;
import ru.itis.models.User;
import ru.itis.repositories.SongRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class SongRepositoryJdbcTemplateImpl implements SongRepository {

    //language=SQL
    private final String SQL_INSERT = "INSERT INTO song (storage_fileName, original_fileName, size," +
            "type, url) VALUES (?, ? ,?, ?, ?);";

    //language=SQl
    private final String SQL_SELECT_BY_NAME = "SELECT * FROM song WHERE storage_fileName = ?;";

    @Autowired
    private JdbcTemplate template;

    private RowMapper<Song> fileInfoRowMapper = (row, rowNumber) ->
            Song.builder()
                    .id(row.getLong("id"))
                    .storageFileName(row.getString("storage_fileName"))
                    .originalFileName(row.getString("original_fileName"))
                    .size(Long.valueOf(row.getString("size")))
                    .type(row.getString("type"))
                    .url(row.getString("url"))
                    .build();

    @Override
    public Optional<Song> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Song> findAll() {
        return null;
    }

    @Override
    public void save(Song file) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, file.getStorageFileName());
            statement.setString(2, file.getOriginalFileName());
            statement.setLong(3, file.getSize());
            statement.setString(4,  file.getType());
            statement.setString(5, file.getUrl());
            return statement;
        }, keyHolder);
        file.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Optional<Song> findByName(String fileName) {
        try {
           Song song = template.queryForObject(SQL_SELECT_BY_NAME, new Object[]{fileName},
                    fileInfoRowMapper);
            return Optional.ofNullable(song);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Song> findByUserId(User user) {
        return null;
    }
}
