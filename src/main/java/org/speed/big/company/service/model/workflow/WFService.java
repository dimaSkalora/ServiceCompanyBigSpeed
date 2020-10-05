package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NamedQueries({
        @NamedQuery(name = WFService.DELETE, query = "delete from WFService wfs where wfs.id=:id"),
        @NamedQuery(name = WFService.GET, query = "select wfs from WFService wfs where wfs.id=:id"),
        @NamedQuery(name = WFService.ALL_SORTED, query = "select wfs from WFService wfs \n" +
                " where wfs.id=:id order by wfs.name")
})
@Entity
@Table(name = "wf_service")
public class WFService extends AbstractBaseEntity {
    public static final String DELETE = "WFService.delete";
    public static final String GET = "WFService.get";
    public static final String ALL_SORTED = "WFService.allSorted";

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
