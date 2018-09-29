package com.lm.springtest.util;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义日期转换类
 */
public class StringToDateConverterUtils implements Converter<String, Date> {

    //日期格式
    private String format;

    //提供构造方法注入日期格式
    public StringToDateConverterUtils(String format){
        this.format=format;
    }

    //重写convert方法进行字符串对日期类型的转换
    @Override
    public Date convert(String str) {
        Date date=null;
        try {
            date=new SimpleDateFormat(format).parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

}
