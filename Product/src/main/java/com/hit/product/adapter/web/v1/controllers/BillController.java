package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.BillService;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.entities.DetailBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
public class BillController {

    @Autowired
    BillService billService;

    @GetMapping(UrlConstant.Bill.DATA_BILL)
    public ResponseEntity<?> getBills() {
        return VsResponseUtil.ok(billService.getBills());
    }

    @GetMapping(UrlConstant.Bill.DATA_BILL_ID)
    public ResponseEntity<?> getBillById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(billService.getBillById(id));
    }

    @PostMapping(UrlConstant.Bill.DATA_BILL_CREATE)
    public ResponseEntity<?> createBill(@PathVariable("idUser") Long idUser, @RequestBody BillDto billDto, @RequestBody List<DetailBill> detailBills) {
        return VsResponseUtil.ok(billService.createBill(idUser, billDto));
    }

    @DeleteMapping(UrlConstant.Bill.DATA_BILL_ID)
    public ResponseEntity<?> deleteBill(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(billService.deleteBill(id));
    }

}
