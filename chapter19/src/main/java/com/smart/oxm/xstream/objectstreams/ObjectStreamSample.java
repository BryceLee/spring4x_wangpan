package com.smart.oxm.xstream.objectstreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;

import com.smart.domain.LoginLog;
import com.smart.domain.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;

public class ObjectStreamSample {

    private static XStream xstream;

    static {
        xstream = new XStream();
    }

    public User xmlToObject() throws Exception {
        FileReader fileReader = new FileReader("chapter19\\out\\ObjectStreamSample.xml");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ObjectInputStream objectInputStream = xstream.createObjectInputStream(bufferedReader);
        User user = (User) objectInputStream.readObject();
        return user;
    }

    public static User getUser() {
        LoginLog log1 = new LoginLog();
        LoginLog log2 = new LoginLog();
        log1.setIp("192.168.1.91");
        log1.setLoginDate(new Date());
        log2.setIp("192.168.1.92");
        log2.setLoginDate(new Date());
        User user = new User();
        user.setUserId(1);
        user.setUserName("xstream");
        user.addLoginLog(log1);
        user.addLoginLog(log2);
        return user;
    }

    public void objectToXml() throws Exception {
        User user = getUser();
        PrintWriter printWriter = new PrintWriter("chapter19\\out\\ObjectStreamSample.xml");
        PrettyPrintWriter prettyPrintWriter = new PrettyPrintWriter(printWriter);
        ObjectOutputStream objectOutputStream = xstream.createObjectOutputStream(prettyPrintWriter);
        objectOutputStream.writeObject(user);
        objectOutputStream.close();
    }

    public static void main(String[] args) throws Exception {
        ObjectStreamSample converter = new ObjectStreamSample();
        converter.objectToXml();
        User u = converter.xmlToObject();
        for (LoginLog log : u.getLogs()) {
            if (log != null) {
                System.out.println("访问IP: " + log.getIp());
                System.out.println("访问时间: " + log.getLoginDate());
            }
        }

    }
}
