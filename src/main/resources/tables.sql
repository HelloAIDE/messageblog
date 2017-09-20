--Table: post
Create Table: CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text CHARACTER SET utf8 NOT NULL,
  `content` longtext CHARACTER SET utf8 NOT NULL,
  `city` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `u_id` int(11) NOT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `lastmodifytime` datetime DEFAULT NULL,
  `index_top` varchar(255) DEFAULT '0' COMMENT '置顶',
  `index_hot` varchar(255) DEFAULT '0' COMMENT '热门',
  `index_essence` varchar(255) DEFAULT '0' COMMENT '精
  `view_number` int(11) DEFAULT '0' COMMENT '阅读人数'
  `resources` text COMMENT '资源',
  `state` int(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=gbk;
  

--Table: reply
Create Table: CREATE TABLE `reply` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `u_name` varchar(255) DEFAULT NULL,
  `u_id` int(11) NOT NULL,
  `content` longtext CHARACTER SET utf8,
  `createtime` varchar(255) DEFAULT '0',
  `state` varchar(255) DEFAULT '0',
  PRIMARY KEY (`r_id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=gbk;


--Table: type
Create Table: CREATE TABLE `type` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '板块名称'
  `image` varchar(255) DEFAULT NULL COMMENT '板块图片
  `manager_id` varchar(255) DEFAULT NULL COMMENT '版主
  PRIMARY KEY (`t_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=gbk
  
--Table: user
Create Table: CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `introduct` varchar(255) DEFAULT NULL,
  `user` varchar(20) DEFAULT NULL,
  `pass` varchar(36) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phone` varchar(18) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `qq_number` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `createTime` varchar(30) NOT NULL DEFAULT '0',
  `lastLoginTime` varchar(30) DEFAULT '0',
  `lastModifyTime` varchar(30) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=gbk