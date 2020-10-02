package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "wf_service")
public class WFService extends AbstractBaseEntity {
    @NotBlank
    @Column(nullable = false)
    private String name;

    public WFService() {
    }

    public WFService(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WFService{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
