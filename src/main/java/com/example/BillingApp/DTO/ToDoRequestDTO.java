package com.example.BillingApp.DTO;

import com.example.BillingApp.Models.Status;
import lombok.Data;

@Data
public class ToDoRequestDTO {


    private String userId;

    private String title;

    private String description;

    private Status status;

}
