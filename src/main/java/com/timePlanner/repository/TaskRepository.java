package com.timePlanner.repository;

import com.timePlanner.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

// https://spring.io/guides/gs/crud-with-vaadin/
public interface TaskRepository extends JpaRepository<Tasks, Integer> {
    List<Tasks> findByTaskDate(Date taskDate);
}
