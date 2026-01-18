package com.example.BillingApp.DTO;

import com.example.BillingApp.Models.Status;
import lombok.Data;

@Data
public class UpdateToDoRequestDTO {
    private Status status;
}
