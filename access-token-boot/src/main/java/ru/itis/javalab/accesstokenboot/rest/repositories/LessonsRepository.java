package ru.itis.javalab.accesstokenboot.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.accesstokenboot.rest.models.Lesson;


/**
 * 24.03.2021
 * 04. REST API
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface LessonsRepository extends JpaRepository<Lesson, Long> {
}
