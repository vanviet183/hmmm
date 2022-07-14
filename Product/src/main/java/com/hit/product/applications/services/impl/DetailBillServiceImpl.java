package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.BillRepository;
import com.hit.product.applications.repositories.DetailBillRepository;
import com.hit.product.applications.repositories.ProductRepository;
import com.hit.product.applications.services.DetailBillService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.BillDto;
import com.hit.product.domains.dtos.DetailBillDto;
import com.hit.product.domains.entities.Bill;
import com.hit.product.domains.entities.DetailBill;
import com.hit.product.domains.entities.DetailBillTest;
import com.hit.product.domains.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailBillServiceImpl implements DetailBillService {

    @Autowired
    DetailBillRepository detailBillRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<DetailBill> getDetailBills() {
        return detailBillRepository.findAll();
    }

    @Override
    public DetailBill getDetailBillById(Long id) {
        Optional<Bill> bill = billRepository.findById(id);
        checkBillException(bill);
        return detailBillRepository.findByBill_Id(id);
    }

//    @Override
//    public DetailBill createDetailBill(DetailBillDto detailBillDto) {
//        return createOrUpdate(new DetailBill(), detailBillDto);
//    }

//    @Override
//    public DetailBill createDetailBill(Long idBill, Long idProduct, DetailBillDto detailBillDto) {
//        return createOrUpdate(idBill, idProduct, new DetailBill(), detailBillDto);
//    }

    @Override
    public DetailBill createDetailBill(Long idProduct, DetailBillDto detailBillDto) {
        return createOrUpdate(idProduct, new DetailBill(), detailBillDto);
    }

    private DetailBill createOrUpdate(Long idProduct, DetailBill detailBill, DetailBillDto detailBillDto) {
        Optional<Product> product = productRepository.findById(idProduct);
        checkProductException(product);
        modelMapper.map(detailBillDto, detailBill);
        detailBill.setProduct(product.get());

        return detailBillRepository.save(detailBill);
    }

    @Override
    public DetailBill updateDetailBill(Long id, DetailBillDto detailBillDto) {
        Optional<DetailBill> detailBill = detailBillRepository.findById(id);
        checkDetailBillException(detailBill);
        modelMapper.map(detailBillDto, detailBill.get());
        return detailBillRepository.save(detailBill.get());
    }

    @Override
    public DetailBill pay(Long id, BillDto billDto) {
        Optional<DetailBill> detailBill = detailBillRepository.findById(id);
        checkDetailBillException(detailBill);

        Bill bill = modelMapper.map(billDto, Bill.class);
        billRepository.save(bill);
        detailBill.get().setBill(bill);
        return detailBillRepository.save(detailBill.get());
    }

//    private DetailBill createOrUpdate(Long idBill, Long idProduct, DetailBill detailBill, DetailBillDto detailBillDto) {
//        Optional<Bill> bill = billRepository.findById(idBill);
//        checkBillException(bill);
//
//        Optional<Product> product = productRepository.findById(idProduct);
//        checkProductException(product);
//        modelMapper.map(detailBillDto, detailBill);
//        detailBill.setBill(bill.get());
//        detailBill.setProduct(product.get());
//
//        return detailBillRepository.save(detailBill);
//    }


    @Override
    public TrueFalseResponse deleteDetailBill(Long idBill) {
        Optional<Bill> bill = billRepository.findById(idBill);
        checkBillException(bill);
        billRepository.deleteById(idBill);
        return new TrueFalseResponse(true);
    }

    private void checkBillException(Optional<Bill> bill) {
        if(bill.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkProductException(Optional<Product> product) {
        if(product.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }

    private void checkDetailBillException(Optional<DetailBill> detailBill) {
        if(detailBill.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
