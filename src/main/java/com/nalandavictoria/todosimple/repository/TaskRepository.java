package com.nalandavictoria.todosimple.repository;

import com.nalandavictoria.todosimple.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    @Query("""
            SELECT t FROM TaskModel t
            WHERE t.user.id = :userID
            """)
    List<TaskModel> findAllByUserID (@Param("userID") Long userID);

}
