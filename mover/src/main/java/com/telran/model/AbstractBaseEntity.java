package com.telran.model;

import javax.persistence.*;

/**
 * Do not manipulate new (transient) entries in HashSet/HashMap without overriding hashCode
 * http://stackoverflow.com/questions/5031614
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    protected Integer id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBaseEntity)) return false;

        AbstractBaseEntity that = (AbstractBaseEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 0 : getId();
    }

    public boolean isNew() {
        return getId() == null;
    }


}