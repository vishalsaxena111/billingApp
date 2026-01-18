package com.example.BillingApp.Repositories;

import com.example.BillingApp.Models.BillingDashBoard;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillingDashBoardDao  extends JpaRepository<BillingDashBoard, Id> {

    @Query(value = "SELECT id FROM billing_dash_board order by id desc limit 1", nativeQuery = true)
    Integer getLatestIdFromBillingDashBoard();
}
