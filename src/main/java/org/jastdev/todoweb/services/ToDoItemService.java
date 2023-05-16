package org.jastdev.todoweb.services;

import org.jastdev.todoweb.dto.ToDoItemDTO;

import java.util.List;

public interface ToDoItemService {
    public ToDoItemDTO getById(Integer id);
    public List<ToDoItemDTO> getAll();
    public void add(ToDoItemDTO toDoItem);
    public void update(Integer id, ToDoItemDTO toDoItem);
    public void delete(Integer id);
}
