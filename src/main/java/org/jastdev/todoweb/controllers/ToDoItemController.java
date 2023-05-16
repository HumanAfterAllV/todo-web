package org.jastdev.todoweb.controllers;

import jakarta.validation.Valid;
import org.jastdev.todoweb.dto.ToDoItemDTO;
import org.jastdev.todoweb.services.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ToDoItemController {

    private ToDoItemService toDoItemService;

    @Autowired
    public ToDoItemController(ToDoItemService toDoItemService){
        this.toDoItemService = toDoItemService;
    }

    @GetMapping("/to-do-items/new")
    public String initEmptyItem(Model model){
        model.addAttribute("item", new ToDoItemDTO());

        return "to-do-item";
    }

    @GetMapping("/to-do-items/{id}/edit")
    public String initWithItem(@PathVariable("id") Integer id, Model model){
        ToDoItemDTO toDoItem = toDoItemService.getById(id);
        model.addAttribute("item", toDoItem);

        return "to-do-item";
    }

    @PostMapping("/to-do-items/save")
    public String save(@Valid ToDoItemDTO toDoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("item", toDoItem);
            model.addAttribute("validator", result);
            return "to-do-item";
        }

        if (toDoItem.getId() == null) {
            toDoItemService.add(toDoItem);
        } else {
            toDoItemService.update(toDoItem.getId(), toDoItem);
        }

        return "redirect:/";
    }
}