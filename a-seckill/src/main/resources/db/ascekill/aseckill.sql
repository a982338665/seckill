/*
Navicat MySQL Data Transfer

Source Server         : a-192.168.1.230
Source Server Version : 50641
Source Host           : 192.168.1.230:3306
Source Database       : aseckill

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2018-10-12 15:17:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_goods
-- ----------------------------
DROP TABLE IF EXISTS `s_goods`;
CREATE TABLE `s_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(16) DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) DEFAULT NULL COMMENT '商品图片',
  `goods_detail` longtext COMMENT '商品详情介绍',
  `goods_price` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '商品单价',
  `goods_stock` int(11) DEFAULT '0' COMMENT '商品库存 -1表示无限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_goods
-- ----------------------------
INSERT INTO `s_goods` VALUES ('1', 'spring源码剖析', '标题...', '/img/1.png', 'spring详情展示...', '10.05', '100');
INSERT INTO `s_goods` VALUES ('2', 'java基础', 'Java基础标题', '/img/2.png', 'java详情展示...', '20.50', '100');
INSERT INTO `s_goods` VALUES ('3', 'iphoneX', 'iphoneX标题', '/img/2.png', 'iphoneX详情展示', '30.01', '1000');
INSERT INTO `s_goods` VALUES ('4', 'iphone10', 'iphone10标题', '/img/1.png', 'iphone10详情展示', '33.00', '1000');

-- ----------------------------
-- Table structure for s_goods_seckill
-- ----------------------------
DROP TABLE IF EXISTS `s_goods_seckill`;
CREATE TABLE `s_goods_seckill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀的商品表主键id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `seckill_price` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '秒杀价格',
  `seckill_stock` int(11) DEFAULT '0' COMMENT '商品库存数量 -1表示无限制',
  `start_time` datetime DEFAULT NULL COMMENT '商品详情介绍',
  `end_time` datetime DEFAULT NULL COMMENT '商品详情介绍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_goods_seckill
-- ----------------------------
INSERT INTO `s_goods_seckill` VALUES ('1', '1', '0.01', '46', '2018-10-11 17:08:12', '2018-10-20 17:08:16');
INSERT INTO `s_goods_seckill` VALUES ('2', '2', '0.02', '50', '2018-10-12 17:09:06', '2018-10-19 17:09:12');
INSERT INTO `s_goods_seckill` VALUES ('3', '3', '0.03', '55', '2018-10-14 09:43:42', '2018-10-16 09:43:47');
INSERT INTO `s_goods_seckill` VALUES ('4', '4', '0.25', '60', '2018-10-10 09:44:07', '2018-10-11 09:44:16');

-- ----------------------------
-- Table structure for s_order_info
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info`;
CREATE TABLE `s_order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `delivery_add_id` bigint(20) DEFAULT NULL COMMENT '收货地址id',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '冗余过来的商品名称',
  `goods_count` int(11) DEFAULT '0' COMMENT '商品数量',
  `goods_price` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '商品单价',
  `order_channel` tinyint(4) DEFAULT '0' COMMENT '1-pc 2-android 3-ios',
  `seckill_price` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '秒杀价格',
  `STATUS` tinyint(4) DEFAULT '0' COMMENT '订单状态：0-未支付 1-已支付 2-未发货 3-已发货 4.已退款 5-已完成 ',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='订单基础信息表';

-- ----------------------------
-- Records of s_order_info
-- ----------------------------
INSERT INTO `s_order_info` VALUES ('6', '17778004652', '1', '0', 'spring源码剖析', '1', '10.05', '1', '0.01', '0', '2018-10-12 15:15:28', null);

-- ----------------------------
-- Table structure for s_order_seckill
-- ----------------------------
DROP TABLE IF EXISTS `s_order_seckill`;
CREATE TABLE `s_order_seckill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='秒杀订单表';

-- ----------------------------
-- Records of s_order_seckill
-- ----------------------------
INSERT INTO `s_order_seckill` VALUES ('6', '17778004652', '1', '1');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` bigint(20) NOT NULL COMMENT '用户id 手机号码',
  `nickname` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL COMMENT 'Md5(Md5(pass明文+固定salt)+salt)',
  `salt` varchar(10) DEFAULT NULL,
  `head` varchar(28) DEFAULT NULL COMMENT '头像，云存储id',
  `register_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `last_login_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `login_count` int(11) DEFAULT '0' COMMENT '登陆次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('17778004652', 'luofeng', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', null, '2018-10-11 09:04:19', '2018-10-11 09:04:23', '1');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', 'Master', '1');
INSERT INTO `test` VALUES ('2', 'Jack', '0');
INSERT INTO `test` VALUES ('3', 'Peter', '1');
