package com.hit.product.domains.entities;

import com.hit.product.domains.entities.base.AbstractAuditingEntity;
import com.hit.product.domains.keys.DetailBillKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "detail_bills")
public class DetailBillTest {

    @EmbeddedId
    DetailBillKey idDetailBill;

    private Integer amount;

    private Double VAT = 5.0;

    private Double ship = 40000.0;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId("idBill")
    @JoinColumn(name = "id_bill")
    private Bill bill;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @MapsId("idProduct")
    @JoinColumn(name = "id_product")
    private Product product;
}
