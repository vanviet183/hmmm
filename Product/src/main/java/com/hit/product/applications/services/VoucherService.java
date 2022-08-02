package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.VoucherDto;
import com.hit.product.domains.entities.Voucher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface VoucherService {

    List<Voucher> getVouchers();

    Voucher getVoucherById(Long id);

    Voucher createVoucher(VoucherDto voucherDto, MultipartFile multipartFile);

    Voucher updateVoucher(Long id, VoucherDto voucherDto, MultipartFile multipartFile);

    TrueFalseResponse deleteVoucher(Long id);

    TrueFalseResponse addVoucherToUser(Long idUser, Long idVoucher);

    Voucher createVoucherForProduct(Long idProduct, VoucherDto voucherDto, MultipartFile multipartFile);
}
