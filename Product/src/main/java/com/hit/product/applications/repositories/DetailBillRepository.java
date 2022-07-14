package com.hit.product.applications.repositories;

import com.hit.product.domains.entities.DetailBill;
import com.hit.product.domains.entities.DetailBillTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailBillRepository extends JpaRepository<DetailBill, Long> {

    DetailBill findByBill_Id(Long idBill);
}
