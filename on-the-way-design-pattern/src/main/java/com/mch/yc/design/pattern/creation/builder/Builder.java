package com.mch.yc.design.pattern.creation.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yc
 *
 */
public class Builder {
 private List<ISender> list = new ArrayList<ISender>();  
       
     public void produceMailSender(int count){  
         for(int i=0; i<count; i++){  
             list.add(new MailSender());  
         }  
     }  
       
     public void produceSmsSender(int count){  
         for(int i=0; i<count; i++){  
             list.add(new SmsSender());  
         }  
     }  
}
