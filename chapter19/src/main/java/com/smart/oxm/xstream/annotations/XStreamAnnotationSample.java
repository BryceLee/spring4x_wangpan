package com.smart.oxm.xstream.annotations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamAnnotationSample {

    private static XStream xstream;

    static {
        xstream = new XStream(new DomDriver());
        //方式一：手工注册
        xstream.processAnnotations(User.class);
        xstream.processAnnotations(LoginLog.class);
        //方式二：自动注册
        //xstream.autodetectAnnotations(true); 
    }


    public static User getUser() {
        LoginLog log1 = new LoginLog();
        LoginLog log2 = new LoginLog();
        log1.setIp("192.168.52.78");
        log1.setLoginDate(new Date());
        log2.setIp("192.168.52.79");
        log2.setLoginDate(new Date());
        User user = new User();
        user.setUserId(1);
        user.setUserName("xstream");
        user.setPassword("123456");
        user.addLoginLog(log1);
        user.addLoginLog(log2);
        return user;
    }

    public static void objectToXml() throws Exception {
        User user = getUser();
        FileOutputStream fs = new FileOutputStream("chapter19/out/XStreamAnnotationSample.xml");
        OutputStreamWriter writer = new OutputStreamWriter(fs, Charset.forName("UTF-8"));
        xstream.toXML(user, writer);
    }

    public static User xmlToObject() throws Exception {
        FileInputStream fis = new FileInputStream("chapter19/out/XStreamAnnotationSample.xml");
        User user = (User) xstream.fromXML(fis);
        for (LoginLog log : user.getLogs()) {
            if (log != null) {
                System.out.println("访问IP: " + log.getIp());
                System.out.println("访问时间: " + log.getLoginDate());
            }
        }
        return user;
    }

    public static void main(String[] args) throws Exception {
        objectToXml();
        xmlToObject();
    }
}
