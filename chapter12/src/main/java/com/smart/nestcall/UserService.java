package com.smart.nestcall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 陈雄华
 * @version 1.0
 */
@Service("userService")
public class UserService extends BaseService {

    private JdbcTemplate jdbcTemplate;

    private ScoreService scoreService;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    /**
     * 该方法嵌套调用了本类的其它方法以及其它Service的方法
     * @param userName
     */
    public void logon(String userName) {
        System.out.println("before userService.updateLastLogonTime...");
        // 调用本类的其它方法
        updateLastLogonTime(userName);
        System.out.println("after userService.updateLastLogonTime...");

        System.out.println("before scoreService.addScore...");
        // 调用其它Service的方法
        scoreService.addScore(userName, 20);
        System.out.println("after scoreService.addScore...");

    }

    public void updateLastLogonTime(String userName) {
        String sql = "UPDATE t_user u SET u.last_logon_time = ? WHERE user_name =?";
        jdbcTemplate.update(sql, System.currentTimeMillis(), userName);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/nestcall/applicatonContext.xml");
        UserService service = (UserService) ctx.getBean("userService");

        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        jdbcTemplate.execute("DELETE FROM t_user WHERE user_name='tom'");
        // 插入一条记录，初始分数为10
        String sql = "INSERT INTO t_user(user_name, password, score, last_logon_time) VALUES('tom', '123456', 10, "
                + System.currentTimeMillis() + ")";
        jdbcTemplate.execute(sql);

        System.out.println("before userService.logon method...");
        service.logon("tom");
        System.out.println("after userService.logon method...");

        jdbcTemplate.execute("DELETE FROM t_user WHERE user_name='tom'");
    }
}
