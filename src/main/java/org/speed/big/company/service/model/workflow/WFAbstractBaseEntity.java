package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.HasId;
import org.speed.big.company.service.model.AbstractBaseEntity;

import javax.persistence.*;

@MappedSuperclass
// http://stackoverflow.com/questions/594597/hibernate-annotations-which-is-better-field-or-property-access
@Access(AccessType.FIELD)
public abstract class WFAbstractBaseEntity implements HasId {
    public static final int START_SEQ = 1;

    @Id
    @SequenceGenerator(name = "workflow_global_seq", sequenceName = "workflow_global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workflow_global_seq")
    protected Integer id;

    protected WFAbstractBaseEntity() {
    }

    protected WFAbstractBaseEntity(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id=id;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WFAbstractBaseEntity that = (WFAbstractBaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

}
