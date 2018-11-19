package com.lm.springredis.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

public class Type implements Serializable {

    private static final long serialVersionUID = -13424351324L;//被注释

    private String idString;

    private int anInt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //日期格式转换方法一，方法二是StringToDateConverterUtils类，在配置文件中配置
    private String aDate;//日期格式应使用String类型，如果不使用String类型，则需要传毫秒数："aDate":1538138845000,

    private Float aFloat;

    private Double aDouble;

    private boolean aBoolean;

    private char aChar;

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public String getaDate() {
        return aDate;
    }

    public void setaDate(String aDate) {
        this.aDate = aDate;
    }

    public Float getaFloat() {
        return aFloat;
    }

    public void setaFloat(Float aFloat) {
        this.aFloat = aFloat;
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public String toString() {
        return "Type{" +
                "idString='" + idString + '\'' +
                ", anInt=" + anInt +
                ", aDate='" + aDate + '\'' +
                ", aFloat=" + aFloat +
                ", aDouble=" + aDouble +
                ", aBoolean=" + aBoolean +
                ", aChar=" + aChar +
                '}';
    }
}
