package ru.itis.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.dto.InformationDto;
import ru.itis.models.User;

public interface UserRepositoryJpa extends JpaRepository<User, Long> {

    // JPQL
    @Query("select new ru.itis.dto.InformationDto(user.id, (sum(song.size) / 1024 / 1024) ) from User user left join user.songs " +
            "as song where user.id = :userId group by user.id")
    InformationDto getInformationByUser(@Param("userId") Long userId);

    @Modifying
    @Query("update User u set u.state ='CONFIRMED' where u.confirmCode = ?1")
    void setUserInfoByCode(String code);

    User findByEmail(String email);
}
