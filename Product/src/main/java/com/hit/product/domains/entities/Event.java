package com.hit.product.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hit.product.domains.entities.base.AbstractAuditingEntity;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event extends AbstractAuditingEntity {

    private static final int EXPIRATION_TIME = 24;

    private static final int EXPIRATION_TIME_TWO_DAY = 48;

    private static final int EXPIRATION_TIME_THREE_DAY = 72;

    @Nationalized
    private String content;

    private Integer sale;

    private Date expirationTime;

    private Boolean status = Boolean.TRUE;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event")
    @JsonIgnore
    private List<Voucher> vouchers;

    public Event(String content) {
        super();
        this.content = content;
        this.expirationTime = calculateExpirationDAte(EXPIRATION_TIME);
    }

    private Date calculateExpirationDAte(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.HOUR, expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
