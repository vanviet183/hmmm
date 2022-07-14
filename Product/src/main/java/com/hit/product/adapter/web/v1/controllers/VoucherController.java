package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.RestApiV1;
import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.constants.UrlConstant;
import com.hit.product.applications.services.VoucherService;
import com.hit.product.domains.dtos.VoucherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class VoucherController {
    
    @Autowired
    VoucherService voucherService;

    @GetMapping(UrlConstant.Voucher.DATA_VOUCHER)
    public ResponseEntity<?> getVouchers() {
        return VsResponseUtil.ok(voucherService.getVouchers());
    }

    @GetMapping(UrlConstant.Voucher.DATA_VOUCHER_ID)
    public ResponseEntity<?> getVoucherById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(voucherService.getVoucherById(id));
    }

    @PostMapping(UrlConstant.Voucher.DATA_VOUCHER_CREATE)
    public ResponseEntity<?> createVoucher(@RequestBody VoucherDto VoucherDto) {
        return VsResponseUtil.ok(voucherService.createVoucher(VoucherDto));
    }

    @PostMapping(UrlConstant.Voucher.DATA_VOUCHER_GET_VOUCHER)
    public ResponseEntity<?> addVoucherToUser(@PathVariable("idUser") Long idUser, @PathVariable("idVoucher") Long idVoucher) {
        return VsResponseUtil.ok(voucherService.addVoucherToUser(idUser, idVoucher));
    }

    @PatchMapping(UrlConstant.Voucher.DATA_VOUCHER_ID)
    public ResponseEntity<?> updateVoucher(@PathVariable("id") Long id, @RequestBody VoucherDto VoucherDto) {
        return VsResponseUtil.ok(voucherService.updateVoucher(id, VoucherDto));
    }

    @DeleteMapping(UrlConstant.Voucher.DATA_VOUCHER_ID)
    public ResponseEntity<?> deleteVoucher(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(voucherService.deleteVoucher(id));
    }
}
