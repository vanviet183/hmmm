package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.dtos.DetailBillDto;
import com.hit.product.domains.entities.DetailBill;
import com.hit.product.domains.entities.DetailBillTest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailBillService {

    List<DetailBill> getDetailBills();

    DetailBill getDetailBillById(Long id);

//    DetailBill createDetailBill(DetailBillDto detailBillDto);

//    DetailBill createDetailBill(Long idBill, Long idProduct, DetailBillDto detailBillDto);

    DetailBill createDetailBill(Long idProduct, DetailBillDto detailBillDto);

    DetailBill updateDetailBill(Long id, DetailBillDto detailBillDto);

    DetailBill pay(Long id, BillDto billDto);

    TrueFalseResponse deleteDetailBill(Long id);
}
