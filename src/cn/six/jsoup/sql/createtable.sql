CREATE TABLE `job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '职位标题',
  `company_name` varchar(30) DEFAULT NULL COMMENT '公司名称',
  `industry` varchar(50) DEFAULT NULL COMMENT '介绍',
  `money_left` bigint(20) DEFAULT NULL COMMENT '最低工资',
  `money_right` bigint(20) DEFAULT NULL COMMENT '最高工资',
  `create_time` varchar(20) DEFAULT NULL COMMENT '发布时间',
  `input_time` datetime DEFAULT NULL,
  `area` varchar(10) DEFAULT NULL,
  `experience` varchar(20) DEFAULT NULL,
  `education` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2150 DEFAULT CHARSET=utf8;

--------------------------------------

CREATE TABLE `job_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) DEFAULT NULL,
  `job_advantage` varchar(100) DEFAULT NULL COMMENT '工作优势',
  `description` text COMMENT '描述',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `hr_name` varchar(10) DEFAULT NULL COMMENT 'hr名字',
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1100 DEFAULT CHARSET=utf8;



