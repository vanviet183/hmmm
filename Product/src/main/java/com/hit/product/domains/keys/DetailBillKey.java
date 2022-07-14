package com.hit.product.domains.keys;

import com.hit.product.domains.entities.Product;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetailBillKey implements Serializable {

    @Column(name = "id_bill")
    private Long idBill;

    @Column(name = "id_product")
    private Long idProduct;

    public DetailBillKey(Long idBill, Long idProduct) {
        this.idBill = idBill;
        this.idProduct = idProduct;
    }

    public DetailBillKey() {
    }

    public Long getIdBill() {
        return idBill;
    }

    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailBillKey that = (DetailBillKey) o;
        return Objects.equals(idBill, that.idBill) && Objects.equals(idProduct, that.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBill, idProduct);
    }
}
