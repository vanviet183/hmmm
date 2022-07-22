package com.hit.product.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hit.product.domains.entities.base.AbstractAuditingEntity;
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
//@Table(name = "product_sizes", uniqueConstraints = @UniqueConstraint(columnNames = {"id_product", "value"}))
@Table(name = "product_sizes")
public class ProductSize extends AbstractAuditingEntity {

    private Integer value;

    private Integer currentNumber;
}
