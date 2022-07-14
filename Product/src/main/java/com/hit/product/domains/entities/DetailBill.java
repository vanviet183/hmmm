package com.hit.product.domains.entities;

import com.hit.product.domains.keys.DetailBillKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detail_bills", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id_bill")
})
public class DetailBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    private Double VAT = 5.0;

    private Double ship = 40000.0;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bill")
    private Bill bill;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;

    private Boolean status = Boolean.FALSE;
}
