package org.jastdev.todoweb.repositories;

import org.jastdev.todoweb.entities.ToDoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItemEntity, Integer> {
    @Query(value = "SELECT todo FROM ToDoItemEntity todo WHERE todo.deletedAt IS NULL order by todo.plannedAt desc")
    List<ToDoItemEntity> getAll();
}
