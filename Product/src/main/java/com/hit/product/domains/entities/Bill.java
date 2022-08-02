package com.hit.product.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hit.product.domains.entities.base.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bills")
public class Bill extends AbstractAuditingEntity {

    @Nationalized
    private String fullName;

    private String email;

    private String phone;

    @Nationalized
    private String address;

    private Double totalMoney;

    @Nationalized
    private String note;

    private Integer amount;

    private Double VAT = 5.0;

    private Double ship = 40000.0;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bill")
    private List<CartItem> cartItems;
}
