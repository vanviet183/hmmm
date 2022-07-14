package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.VoucherDto;
import com.hit.product.domains.entities.Voucher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherService {

    List<Voucher> getVouchers();

    Voucher getVoucherById(Long id);

    Voucher createVoucher(VoucherDto voucherDto);

    Voucher updateVoucher(Long id, VoucherDto voucherDto);

    TrueFalseResponse deleteVoucher(Long id);

    TrueFalseResponse addVoucherToUser(Long idUser, Long idVoucher);
}
