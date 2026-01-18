package com.example.BillingApp.Repositories;

import com.example.BillingApp.Models.ToDo;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IToDoDao extends JpaRepository<ToDo, Long> {
    List<ToDo> findByUserId(String userId);
}
