package org.speed.big.company.service.model.workflow;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = WFPackageStatus.DELETE, query = "delete from WFPackageStatus wfps where wfps.id=:id"),
        @NamedQuery(name = WFPackageStatus.GET, query = "select wfps from WFPackageStatus wfps where wfps.id=:id"),
        @NamedQuery(name = WFPackageStatus.ALL_SORTED, query = "select wfps from WFPackageStatus wfps " +
                " order by wfps.name")
})
@Entity
@Table(name = "wf_package_status")
public class WFPackageStatus extends WFAbstractBaseEntity {
    public static final String DELETE = "WFPackageStatus.delete";
    public static final String GET = "WFPackageStatus.get";
    public static final String ALL_SORTED = "WFPackageStatus.allSorted";

    @NotBlank
    @Size(min = 4, max = 100)
    @Column(nullable = false)
    private String name;

    public WFPackageStatus() {
    }

    public WFPackageStatus(String name) {
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
        return "WFPackageStatus{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
