package com.yuelei.entity;

import javax.persistence.*;

@Entity
@Table(name = "roompicture", schema = "lay")
public class RoompictureEntity {
    private int id;
    private String type;
    private String picture1;
    private String picture2;
    private String picture3;
    private String picture4;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "picture1")
    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    @Basic
    @Column(name = "picture2")
    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    @Basic
    @Column(name = "picture3")
    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    @Basic
    @Column(name = "picture4")
    public String getPicture4() {
        return picture4;
    }

    public void setPicture4(String picture4) {
        this.picture4 = picture4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoompictureEntity that = (RoompictureEntity) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (picture1 != null ? !picture1.equals(that.picture1) : that.picture1 != null) return false;
        if (picture2 != null ? !picture2.equals(that.picture2) : that.picture2 != null) return false;
        if (picture3 != null ? !picture3.equals(that.picture3) : that.picture3 != null) return false;
        if (picture4 != null ? !picture4.equals(that.picture4) : that.picture4 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (picture1 != null ? picture1.hashCode() : 0);
        result = 31 * result + (picture2 != null ? picture2.hashCode() : 0);
        result = 31 * result + (picture3 != null ? picture3.hashCode() : 0);
        result = 31 * result + (picture4 != null ? picture4.hashCode() : 0);
        return result;
    }
}
