package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.DetailBillService;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.dtos.DetailBillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class DetailBillController {

    @Autowired
    DetailBillService detailBillService;

    @GetMapping(UrlConstant.DetailBill.DATA_DETAIL_BILL)
    public ResponseEntity<?> getDetailBills() {
        return VsResponseUtil.ok(detailBillService.getDetailBills());
    }

    @GetMapping(UrlConstant.DetailBill.DATA_DETAIL_BILL_ID)
    public ResponseEntity<?> getDetailBillById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(detailBillService.getDetailBillById(id));
    }

//    @PostMapping("/{idBill}/{idProduct}")
//    public ResponseEntity<?> createDetailBill(@PathVariable("idBill") Long idBill, @PathVariable("idProduct") Long idProduct, @RequestBody DetailBillDto detailBillDto) {
//        return VsResponseUtil.ok(detailBillService.createDetailBill(idBill, idProduct, detailBillDto));
//    }

    @PostMapping(UrlConstant.DetailBill.DATA_DETAIL_BILL_CREATE)
    public ResponseEntity<?> createDetailBill(@PathVariable("idProduct") Long idProduct, @RequestBody DetailBillDto detailBillDto) {
        return VsResponseUtil.ok(detailBillService.createDetailBill(idProduct, detailBillDto));
    }

    @PatchMapping(UrlConstant.DetailBill.DATA_DETAIL_BILL_ID)
    public ResponseEntity<?> updateDetailBill(@PathVariable("id") Long id, @RequestBody DetailBillDto detailBillDto) {
        return VsResponseUtil.ok(detailBillService.updateDetailBill(id, detailBillDto));
    }

    @PostMapping("/{idDetailBill}/pay")
    public ResponseEntity<?> pay(@PathVariable("idDetailBill") Long idDetailBill, @RequestBody BillDto billDto) {
        return VsResponseUtil.ok(detailBillService.pay(idDetailBill, billDto));
    }

    @DeleteMapping(UrlConstant.DetailBill.DATA_DETAIL_BILL_ID)
    public ResponseEntity<?> deleteDetailBill(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(detailBillService.deleteDetailBill(id));
    }

}
