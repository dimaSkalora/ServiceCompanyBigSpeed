package org.speed.big.company.service.model;

public class RoleType extends AbstractBaseEntity{
    private String name;

    public RoleType() {
    }

    public RoleType(String name) {
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
        return "RoleType{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
