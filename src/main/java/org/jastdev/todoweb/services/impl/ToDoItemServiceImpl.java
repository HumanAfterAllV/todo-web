package org.jastdev.todoweb.services.impl;

import org.jastdev.todoweb.dto.ToDoItemDTO;
import org.jastdev.todoweb.entities.ToDoItemEntity;
import org.jastdev.todoweb.mappers.ToDoItemMapper;
import org.jastdev.todoweb.repositories.ToDoItemRepository;
import org.jastdev.todoweb.services.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ToDoItemServiceImpl implements ToDoItemService {

    private ToDoItemRepository toDoItemRepository;

    @Autowired
    public ToDoItemServiceImpl(ToDoItemRepository toDoItemRepository){
        this.toDoItemRepository = toDoItemRepository;
    }

    @Override
    public ToDoItemDTO getById(Integer id) {
        ToDoItemEntity entity = toDoItemRepository.findById(id).get();
        return ToDoItemMapper.toDto(entity);
    }

    @Override
    public List<ToDoItemDTO> getAll() {
        return this.toDoItemRepository.getAll()
                .stream()
                .map(ToDoItemMapper::toDto)
                .toList();
    }

    @Override
    public void add(ToDoItemDTO toDoItem) {
        ToDoItemEntity entity = ToDoItemMapper.toEntity(toDoItem);
        toDoItemRepository.save(entity);
    }

    @Override
    public void update(Integer id, ToDoItemDTO toDoItem) {
        ToDoItemEntity entity = toDoItemRepository.findById(id).get();
        entity.setTitle(toDoItem.getTitle());
        entity.setDescription(toDoItem.getDescription());
        entity.setUpdatedAt(LocalDateTime.now());

        toDoItemRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        ToDoItemEntity entity = toDoItemRepository.findById(id).get();
        entity.setDeletedAt(LocalDateTime.now());

        toDoItemRepository.save(entity);
    }
}
