package com.example.BillingApp.Services;

import com.example.BillingApp.DTO.ToDoRequestDTO;
import com.example.BillingApp.DTO.UpdateToDoRequestDTO;
import com.example.BillingApp.Models.Status;
import com.example.BillingApp.Models.ToDo;
import com.example.BillingApp.Repositories.IToDoDao;
import jakarta.servlet.http.HttpServletRequest;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ToDoService {

    @Autowired
    private IToDoDao toDoDao;


    public String createToDo(HttpServletRequest request, ToDoRequestDTO toDoObjRequest){

            String requestUserId = (String) request.getAttribute("USER_ID");
            String requestRole = (String) request.getAttribute("ROLE");

            if("ADMIN".equals(requestRole)  || (toDoObjRequest.getUserId()!=null && requestUserId.equals(toDoObjRequest.getUserId()))){

                ToDo toDoObject = new ToDo();
                toDoObject.setUserId(toDoObjRequest.getUserId());
                toDoObject.setDescription(toDoObjRequest.getDescription());
                toDoObject.setStatus(toDoObjRequest.getStatus());
                toDoObject.setTitle(toDoObjRequest.getTitle());

                toDoDao.save(toDoObject);
                return "Entry Created Successfully";
            }else{
                return "Entry will not created";
            }


    }

    public List<ToDo> getMyToDos(HttpServletRequest request) {

        String userId = (String) request.getAttribute("USER_ID");

        return toDoDao.findByUserId(userId);

    }

    public List<ToDo> getUserToDosForAdmin(HttpServletRequest request, String userId) {

        String reqRole = (String) request.getAttribute("ROLE");

        if("ADMIN".equals(reqRole))
            return toDoDao.findByUserId(userId);
        else
            return Collections.emptyList();


    }

    public String updateStatus(HttpServletRequest request, Long id, UpdateToDoRequestDTO obj) {
        String requestUserId = (String) request.getAttribute("USER_ID");
        String requestRole = (String) request.getAttribute("ROLE");

        ToDo toDo = toDoDao.findById(id)
                .orElseThrow(()-> new RuntimeException("Todo Not Found") );

        if ("ADMIN".equals(requestRole) || (toDo.getUserId() != null && requestUserId.equals(toDo.getUserId()))) {
            toDo.setStatus((Status) obj.getStatus());
            toDoDao.save(toDo);
            return "Record Updated Successfully";
        } else {
            return "Record not Updated";
        }
    }
}
