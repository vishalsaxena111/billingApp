package com.example.BillingApp.Services;

import com.example.BillingApp.DTO.BillingRequestDTO;
import com.example.BillingApp.Models.BillingDashBoard;
import com.example.BillingApp.Repositories.IBillingDashBoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillingDashBoardService {

    @Autowired
    private IBillingDashBoardDao dao;

    public List<BillingDashBoard> getAllBillingDashBoardDetails(){
        return dao.findAll();
    }

    public BillingDashBoard createBilling(BillingRequestDTO requestObj) {



        Integer latestId = dao.getLatestIdFromBillingDashBoard();

        int nextNumber = (latestId == null) ? 1 : latestId + 1;

        BillingDashBoard newObj = new BillingDashBoard();

        newObj.setCustomerName(requestObj.getCustomerName());
        newObj.setDriverName(requestObj.getDriverName());
        newObj.setBookingDateTime(LocalDateTime.now());
        newObj.setSerialNumber("SRN-" + nextNumber);

        System.out.println(newObj);

        return dao.save(newObj);
    }
}
