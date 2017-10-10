package com.banggood.scm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/8/31.
 */
@ApiModel(value = "用户视图",description = "显示用户信息")
public class UserinfoVo {

    @ApiModelProperty(value = "ID",notes = "主键")
    private Integer id;

    /**
     *
     */
    @ApiModelProperty(value = "姓名",notes = "用户姓名")
    private String username;

    /**
     *
     */
    @ApiModelProperty(value = "性别",notes = "用户性别")
    private Boolean sex;

    /**
     *
     */
    @ApiModelProperty(value = "年龄",notes = "用户年龄")
    private Integer age;


    /**
     *
     */
    @ApiModelProperty(value = "描述",notes = "用户描述")
    private String decp;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDecp() {
        return decp;
    }

    public void setDecp(String decp) {
        this.decp = decp;
    }

    @Override
    public String toString() {
        return "UserinfoVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", Decp='" + decp + '\'' +
                '}';
    }
}
