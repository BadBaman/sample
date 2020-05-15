package com.fabric.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Organization {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotEmpty(message="组织号不能为空！")
    @Id
    private int orgid ;
    @NotEmpty(message="组织名不能为空！")
    private String orgname;

    @Override
    public String toString() {
        return "Organization{" +
                "orgid=" + orgid +
                ", orgname='" + orgname + '\'' +
                '}';
    }

    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }
}
