package com.mustceng.dropshipping.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    @JsonIgnore
    @Column(name = "uuid", updatable = false)
    private String uuid = java.util.UUID.randomUUID().toString();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        BaseEntity other = (BaseEntity) obj;
        if (id != null && other.id != null) {
            return id.longValue() == other.id.longValue();
        }

        return this.getUuid().equals(other.getUuid());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (uuid == null ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " ( uuid=" + uuid + ")";
    }


}
