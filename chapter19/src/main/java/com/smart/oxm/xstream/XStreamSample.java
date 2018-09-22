package com.smart.oxm.xstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import com.smart.domain.LoginLog;
import com.smart.domain.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamSample {

    private static XStream xstream;

    static {
        // new DomDriver()，这种方式不需要加xpp3包
        xstream = new XStream(new DomDriver());
    }

    public static User getUser() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("xstream");
        LoginLog log1 = new LoginLog();
        log1.setIp("192.168.1.91");
        log1.setLoginDate(new Date());
        user.addLoginLog(log1);
        LoginLog log2 = new LoginLog();
        log2.setIp("192.168.1.92");
        log2.setLoginDate(new Date());
        user.addLoginLog(log2);
        return user;
    }

    public static void objectToXml() throws Exception {
        User user = getUser();
        FileOutputStream outputStream = new FileOutputStream("chapter19/out/XStreamSample.xml");
        xstream.toXML(user, outputStream);
    }

    public static User xmlToObject() throws Exception {
        FileInputStream fis = new FileInputStream("chapter19/out/XStreamSample.xml");
        User u = (User) xstream.fromXML(fis);
        for (LoginLog log : u.getLogs()) {
            if (log != null) {
                System.out.println("访问IP: " + log.getIp());
                System.out.println("访问时间: " + log.getLoginDate());
            }
        }
        return u;
    }

    public static void main(String[] args) throws Exception {
        objectToXml();
        xmlToObject();
    }
}
