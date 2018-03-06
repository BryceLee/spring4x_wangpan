package com.smart.connleak;

import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 陈雄华
 * @version 1.0
 */
@Service("jdbcUserService")
public class JdbcUserService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void logon(String userName) {
        try {
            // 直接从数据源获取连接，后续程序没有显示释放该连接====会造成连接池泄漏
            Connection conn = jdbcTemplate.getDataSource().getConnection();

            // 首先尝试从事务上下方中获取连接，失败后再从数据源中获取====不会造成连接池泄漏
            // Connection conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());

            String sql = "UPDATE t_user SET last_logon_time = ? WHERE user_name = ?";
            jdbcTemplate.update(sql, System.currentTimeMillis(), userName);
            // ②模拟程序代码的执行时间
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void asynchrLogon(JdbcUserService userService, String userName) {
        UserServiceRunner runner = new UserServiceRunner(userService, userName);
        runner.start();
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void reportConn(BasicDataSource basicDataSource) {
        System.out.println(
                "连接数[active:idle]-[" + basicDataSource.getNumActive() + ":" + basicDataSource.getNumIdle() + "]");
    }

    private static class UserServiceRunner extends Thread {
        private JdbcUserService userService;
        private String userName;

        public UserServiceRunner(JdbcUserService userService, String userName) {
            this.userService = userService;
            this.userName = userName;
        }

        @Override
        public void run() {
            userService.logon(userName);
        }
    }


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/connleak/applicatonContext.xml");
        JdbcUserService userService = (JdbcUserService) ctx.getBean("jdbcUserService");

        BasicDataSource basicDataSource = (BasicDataSource) ctx.getBean("dataSource");
        JdbcUserService.reportConn(basicDataSource);

        JdbcUserService.asynchrLogon(userService, "tom");
        JdbcUserService.sleep(500);
        JdbcUserService.reportConn(basicDataSource);

        JdbcUserService.sleep(2000);
        JdbcUserService.reportConn(basicDataSource);

        JdbcUserService.asynchrLogon(userService, "john");
        JdbcUserService.sleep(500);
        JdbcUserService.reportConn(basicDataSource);

        JdbcUserService.sleep(2000);
        JdbcUserService.reportConn(basicDataSource);

    }
}
