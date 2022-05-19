package com.example.vuespringboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer u_id;
    private String id;
    private String pwd;
    private String name;

    public Integer getU_id() { return u_id; }
    public void setU_id(Integer u_id) { this.u_id = u_id; }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getPwd() { return pwd; }
    public void setPwd( String pwd ) { this.pwd = pwd; }
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
}
