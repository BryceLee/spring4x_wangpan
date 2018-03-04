DROP DATABASE IF EXISTS spring4x;
CREATE DATABASE spring4x
  DEFAULT CHARACTER SET utf8;
USE spring4x;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
  user_name       VARCHAR(30) NOT NULL PRIMARY KEY,
  password        VARCHAR(30) NOT NULL,
  score           INT,
  last_logon_time LONG
)
  ENGINE = InnoDB;

INSERT INTO t_user (user_name, password, score, last_logon_time) VALUES ('tom', '123456', 10, 123213213);
COMMIT;

/*drop table t_dept;
create table t_dept(
   dept_code varchar2(30) primary key,
   dept_name varchar2(30)
);   */
