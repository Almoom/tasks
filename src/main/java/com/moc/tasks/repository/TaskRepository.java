package com.moc.tasks.repository;

import com.moc.tasks.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Modifying
    @Query("UPDATE TaskEntity t SET t.done = TRUE WHERE t.id =:id")
    void markAsDone(@Param("id") Long id);

}
