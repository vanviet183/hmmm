package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.repositories.VoucherRepository;
import com.hit.product.applications.services.VoucherService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.VoucherDto;
import com.hit.product.domains.entities.User;
import com.hit.product.domains.entities.Voucher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Voucher> getVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher getVoucherById(Long id) {
        Optional<Voucher> voucher = voucherRepository.findById(id);
        checkVoucherException(voucher);
        return voucher.get();
    }

    @Override
    public Voucher createVoucher(VoucherDto voucherDto) {
        return createOrUpdate(new Voucher(), voucherDto);
    }

    @Override
    public Voucher updateVoucher(Long id, VoucherDto voucherDto) {
        Optional<Voucher> voucher = voucherRepository.findById(id);
        checkVoucherException(voucher);
        return createOrUpdate(voucher.get(), voucherDto);
    }

    private Voucher createOrUpdate(Voucher voucher, VoucherDto voucherDto) {
        modelMapper.map(voucherDto, voucher);
        return voucherRepository.save(voucher);
    }

    @Override
    public TrueFalseResponse deleteVoucher(Long id) {
        Optional<Voucher> voucher = voucherRepository.findById(id);
        checkVoucherException(voucher);
        voucherRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    @Override
    public TrueFalseResponse addVoucherToUser(Long idUser, Long idVoucher) {
        Optional<User> user = userRepository.findById(idUser);
        checkUserException(user);

        Optional<Voucher> voucher = voucherRepository.findById(idVoucher);
        checkVoucherException(voucher);

        voucher.get().setUser(user.get());
        voucherRepository.save(voucher.get());
        return new TrueFalseResponse(true);
    }

    private void checkVoucherException(Optional<Voucher> voucher) {
        if(voucher.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkUserException(Optional<User> user) {
        if(user.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
