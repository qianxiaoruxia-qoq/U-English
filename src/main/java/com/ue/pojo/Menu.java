package com.ue.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Table(name = "menu")
public class Menu {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "menu_name")
    private String menuName;

    private String url;

    @Column(name = "p_id")
    private Integer pId;

    private Integer grade;

    private String pic;

    @Transient
    private List<Menu> subMenus;

}
