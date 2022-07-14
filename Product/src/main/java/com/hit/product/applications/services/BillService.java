package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.entities.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {

    List<Bill> getBills();

    Bill getBillById(Long id);

    Bill createBill(Long idUser, BillDto billDto);

    Bill updateBill(Long id, BillDto billDto);

    TrueFalseResponse deleteBill(Long id);
}
