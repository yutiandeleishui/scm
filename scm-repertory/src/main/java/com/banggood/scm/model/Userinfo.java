package com.banggood.scm.model;

import java.io.Serializable;

public class Userinfo implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private Boolean sex;

    /**
     * 
     */
    private Integer age;

    /**
     * 
     */
    private String Decp;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public Userinfo withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Userinfo withUsername(String username) {
        this.setUsername(username);
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getSex() {
        return sex;
    }

    public Userinfo withSex(Boolean sex) {
        this.setSex(sex);
        return this;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public Userinfo withAge(Integer age) {
        this.setAge(age);
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDecp() {
        return Decp;
    }

    public Userinfo withDecp(String Decp) {
        this.setDecp(Decp);
        return this;
    }

    public void setDecp(String Decp) {
        this.Decp = Decp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", Decp=").append(Decp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}