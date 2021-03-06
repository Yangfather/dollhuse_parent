package com.dollhouse.entity.comm;

import java.math.BigDecimal;
import com.dollhouse.core.annotation.Table;
import com.dollhouse.core.annotation.Column;
import com.dollhouse.core.entity.BaseEntity;
 
/**
 * This code is generated by FreeMarker
 */
 @Table(name="doll_type")
public class DollType extends BaseEntity {
    private static final long serialVersionUID = 1L;
    
    @Column(name="type_id")
    private Long typeId;
    
    @Column(name="doll_name_id")
    private Long dollNameId;
    
    @Column(name="doll_type_name")
    private String dollTypeName;
    
    @Column(name="doll_type_price")
    private BigDecimal dollTypePrice;
    
    @Column(name="doll_type_num")
    private Long dollTypeNum;

    public Long getTypeId() {
        return typeId;
    }
 
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getDollNameId() {
        return dollNameId;
    }
 
    public void setDollNameId(Long dollNameId) {
        this.dollNameId = dollNameId;
    }

    public String getDollTypeName() {
        return dollTypeName;
    }
 
    public void setDollTypeName(String dollTypeName) {
        this.dollTypeName = dollTypeName;
    }

    public BigDecimal getDollTypePrice() {
        return dollTypePrice;
    }
 
    public void setDollTypePrice(BigDecimal dollTypePrice) {
        this.dollTypePrice = dollTypePrice;
    }

    public Long getDollTypeNum() {
        return dollTypeNum;
    }
 
    public void setDollTypeNum(Long dollTypeNum) {
        this.dollTypeNum = dollTypeNum;
    }
}