package com.hit.product.domains.entities;

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
@Table(name = "notifications")
public class Notification extends AbstractAuditingEntity {

    @Nationalized
    private String title;

    @Nationalized
    private String content;

    private Boolean status = Boolean.TRUE;

    @ManyToMany(mappedBy = "notifications")
    private List<User> users;

}
