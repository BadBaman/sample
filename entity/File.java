package com.fabric.entity;

import org.apache.commons.beanutils.BeanUtils;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Calendar;
import java.sql.Date;
import org.joda.time.DateTime;

@Entity
public class File {


    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    //private String localUrl;

    //private String visitUrl;

    private long size;

    //private long createTime;

    //private int userId;
    //private String description;

    //private int downloadTimes;



    //private int categoryId;

    //private Timestamp lastModifyTime;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

/*
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
*/

    /*public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }*/

    public File() {
    }
    public File(int id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
        //this.createTime = createTime;
        //this.userId = userId;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                /*", createTime=" + createTime +*/
                /*", userId=" + userId +*/
                '}';
    }

}