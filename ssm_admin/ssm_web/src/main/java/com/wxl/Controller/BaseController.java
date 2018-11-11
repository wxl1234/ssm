package com.wxl.Controller;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {

    @InitBinder
    public void initBinderDate(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Date.class,new MyDateEditor());
    }


    /**
     * 完成时间的指定格式转换
     */
    private class MyDateEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(@Nullable String text) throws IllegalArgumentException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
            Date date =null;

            try {
                date = sdf.parse(text);
            } catch (ParseException e) {
                sdf = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    date = sdf.parse(text);
                } catch (ParseException e1) {
                    sdf = new SimpleDateFormat("yyyy-mm-dd HH:MM");
                    try {
                        date = sdf.parse(text);
                    } catch (ParseException e2) {
                        e2.printStackTrace();
                    }
                }
            }
           setValue(date);
        }
    }
}
