package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;

public class WFPackageStatus extends AbstractBaseEntity {
    private String name;

    public WFPackageStatus() {
    }

    public WFPackageStatus(Integer id, String name) {
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
        return "WFPackageStatus{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
