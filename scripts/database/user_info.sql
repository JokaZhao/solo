CREATE TABLE IF NOT EXISTS  `solo`.`b3_solo_user_login_info` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_name` varchar(255) NOT NULL COMMENT '用户名',
    `user_type` varchar(10) NOT NULL COMMENT '用户类型：ADMIN or COMMON',
    `password` varchar(255) NOT NULL COMMENT '用户密码',
    `salt` varchar(64) NOT NULL COMMENT '盐值',
    `ext_info` varchar(1024) DEFAULT NULL COMMENT '扩展字段',
    `is_delete` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除',
    `creator` varchar(32) NOT NULL DEFAULT 'SYSTEM' COMMENT '创建者',
    `modifier` varchar(32) NOT NULL DEFAULT 'SYSTEM' COMMENT '修改者',
    `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_user_name` (`user_name`)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE='utf8_general_ci'
COMMENT='用户登录表'
AUTO_INCREMENT=1000000;