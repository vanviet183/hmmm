package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.BillRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.services.BillService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.entities.Bill;
import com.hit.product.domains.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Long id) {
        Optional<Bill> bill = billRepository.findById(id);
        checkBillException(bill);
        return bill.get();
    }

    @Override
    public Bill createBill(Long idUser, BillDto billDto) {
        Optional<User> user = userRepository.findById(idUser);
        checkUserException(user);
        Bill bill = modelMapper.map(billDto, Bill.class);
        bill.setUser(user.get());
        return billRepository.save(bill);
    }

    @Override
    public Bill updateBill(Long id, BillDto billDto) {
        Optional<Bill> bill = billRepository.findById(id);
        checkBillException(bill);
        modelMapper.map(billDto, bill.get());
        return billRepository.save(bill.get());
    }

    @Override
    public TrueFalseResponse deleteBill(Long id) {
        Optional<Bill> bill = billRepository.findById(id);
        checkBillException(bill);
        billRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    private void checkUserException(Optional<User> user) {
        if(user.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkBillException(Optional<Bill> bill) {
        if(bill.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
