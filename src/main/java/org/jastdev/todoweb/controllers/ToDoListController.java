package org.jastdev.todoweb.controllers;


import org.jastdev.todoweb.dto.ToDoItemDTO;
import org.jastdev.todoweb.services.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ToDoListController {

    private ToDoItemService toDoItemService;

    @Autowired
    public ToDoListController(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    @GetMapping("/")
    public String getToDoList(Model model) {
        List<ToDoItemDTO> toDoList = toDoItemService.getAll();
        model.addAttribute("list", toDoList);
        return "to-do-list";
    }

    @DeleteMapping("/to-do-items/{id}")
    public String initWithItem(@PathVariable("id") Integer id){
        toDoItemService.delete(id);

        return "redirect:/";
    }

}