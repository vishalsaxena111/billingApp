package com.example.BillingApp.Controllers;

import com.example.BillingApp.DTO.ToDoRequestDTO;
import com.example.BillingApp.DTO.UpdateToDoRequestDTO;
import com.example.BillingApp.Models.ToDo;
import com.example.BillingApp.Services.ToDoService;
import jakarta.servlet.http.HttpServletRequest;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    private ToDoService service;

    @Autowired
    public ToDoController(ToDoService service){
        this.service = service;
    }

    @PostMapping("api/createTodo")
    public ResponseEntity<String> createToDo (HttpServletRequest request, @RequestBody ToDoRequestDTO toDoObj){
        return ResponseEntity.ok().body(service.createToDo(request,toDoObj));
    }

    @GetMapping("api/todos/me")
    public ResponseEntity<List<ToDo>> getMyToDos(HttpServletRequest request){
        return ResponseEntity.ok().body(service.getMyToDos(request));
    }

    @GetMapping("/api/todos/user/{userId}")
    public ResponseEntity<List<ToDo>> getUserToDosForAdmin(HttpServletRequest request, @PathVariable     String userId){
        return ResponseEntity.ok().body(service.getUserToDosForAdmin(request,userId));
    }

    @PatchMapping("/api/todos/{id}/status")
    public ResponseEntity<String> updateStatus(HttpServletRequest request, @PathVariable Long id, @RequestBody UpdateToDoRequestDTO status) throws RuntimeException{
        return ResponseEntity.ok().body(service.updateStatus(request,id,status));
    }


}
