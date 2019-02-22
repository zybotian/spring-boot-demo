CREATE DATABASE `db_test` DEFAULT CHARACTER SET utf8;

USE `db_test`;

CREATE TABLE IF NOT EXISTS `db_test`.`student`(
  `id`              INT             NOT NULL AUTO_INCREMENT  COMMENT '自增主键',
  `name`            VARCHAR(32)     NOT NULL DEFAULT ''      COMMENT '姓名',
  `sno`             VARCHAR(16)     NOT NULL DEFAULT ''      COMMENT '学号',
  `major`           VARCHAR(32)     NOT NULL DEFAULT ''      COMMENT '专业',
  `grade`           INT             NOT NULL DEFAULT 0       COMMENT '年级',
  `create_time`     BIGINT          NOT NULL DEFAULT 0       COMMENT '创建时间',
  `update_time`     BIGINT          NOT NULL DEFAULT 0       COMMENT '更新时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;