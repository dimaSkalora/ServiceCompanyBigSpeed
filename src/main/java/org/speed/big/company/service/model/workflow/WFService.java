package org.speed.big.company.service.model.workflow;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = WFService.DELETE, query = "delete from WFService wfs where wfs.id=:id"),
        @NamedQuery(name = WFService.GET, query = "select wfs from WFService wfs where wfs.id=:id"),
        @NamedQuery(name = WFService.ALL_SORTED, query = "select wfs from WFService wfs \n" +
                " order by wfs.name")
})
@Entity
@Table(name = "wf_service")
public class WFService extends WFAbstractBaseEntity {
    public static final String DELETE = "WFService.delete";
    public static final String GET = "WFService.get";
    public static final String ALL_SORTED = "WFService.allSorted";

    @NotBlank
    @Size(min = 5)
    @Column(nullable = false)
    private String name;

    public WFService() {
    }

    public WFService(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
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
