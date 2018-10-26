/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : advertise_platform_core_db

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2018-08-29 19:31:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account_tb`
-- ----------------------------
DROP TABLE IF EXISTS `account_tb`;
CREATE TABLE `account_tb` (
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  `phone` varchar(255) DEFAULT NULL COMMENT '注册手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `icon` varchar(255) DEFAULT NULL COMMENT '图像',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别,默认为0未知，为1男性，为2女性',
  `country` varchar(255) DEFAULT NULL COMMENT '国家,默认中国',
  `realname` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(255) DEFAULT NULL COMMENT 'email',
  `safety_grade` tinyint(4) DEFAULT NULL COMMENT '安全等级，1低，2中，3高',
  `auth` tinyint(4) DEFAULT NULL COMMENT '认证，0没认证，1审核中，2已认证',
  `identity_cards` varchar(255) DEFAULT NULL COMMENT '身份证',
  `identity_cards_hold_img` varchar(255) DEFAULT NULL COMMENT '手持身份证上半身照',
  `identity_cards_front_img` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `identity_cards_back_img` varchar(255) DEFAULT NULL COMMENT '身份证反面',
  `telephone` varchar(20) DEFAULT NULL COMMENT '电话',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `login_date` datetime DEFAULT NULL COMMENT '登陆时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态，默认0正常，1锁定，2，异常',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id外键',
  PRIMARY KEY (`account_id`),
  KEY `INDEX_AUTH` (`auth`) USING BTREE,
  KEY `INDEX_PHONE` (`phone`) USING BTREE,
  KEY `INDEX_REALNAME` (`realname`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_LOGINDATE` (`login_date`) USING BTREE,
  KEY `INDEX_ROLEID` (`role_id`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户表';

-- ----------------------------
-- Records of account_tb
-- ----------------------------
INSERT INTO `account_tb` VALUES ('1000', '1000', '11874bb6149dd45428da628c9766b252', '聂跃', null, '0', null, null, '1000@qq.com', null, null, null, null, null, null, null, '2018-07-26 18:35:48', '2018-08-29 10:04:38', '0', '1000');
INSERT INTO `account_tb` VALUES ('1029655983884234753', '100000', '11874bb6149dd45428da628c9766b252', 'gdfgdf', null, '0', null, null, null, null, null, null, null, null, null, null, '2018-08-15 17:08:11', '2018-08-15 18:00:02', '0', '1001');
INSERT INTO `account_tb` VALUES ('1030344761938505729', '1001', '11874bb6149dd45428da628c9766b252', '媒体主1', 'http://localhost:8080/uploaderPath/img/20180817/1534488130438.jpg', '0', '中国', 'nie yue', '1001@qq.com', '1', '0', null, 'http://localhost:8080/uploaderPath/img/20180817/1534488158349.jpg', 'http://localhost:8080/uploaderPath/img/20180817/1534488291470.jpg', 'http://localhost:8080/uploaderPath/img/20180817/1534488299181.jpg', null, '2018-08-17 14:45:09', '2018-08-24 11:41:17', '0', '1002');
INSERT INTO `account_tb` VALUES ('1030356221427150849', '1002', '11874bb6149dd45428da628c9766b252', '广告主1', 'http://localhost:8080/uploaderPath/img/20180817/1534491019197.png', '0', '中国', 'nie yue', '1002@qq.com', '1', '0', null, 'http://localhost:8080/uploaderPath/img/20180817/1534491033603.jpg', 'http://localhost:8080/uploaderPath/img/20180817/1534491036646.jpg', 'http://localhost:8080/uploaderPath/img/20180817/1534491038457.png', null, '2018-08-17 15:30:41', '2018-08-17 15:30:41', '0', '1003');
INSERT INTO `account_tb` VALUES ('1032837470389411842', null, '11874bb6149dd45428da628c9766b252', null, null, '0', null, null, '278076303@qq.com', '1', '0', null, null, null, null, null, '2018-08-24 11:50:17', '2018-08-28 17:41:59', '0', '1002');
INSERT INTO `account_tb` VALUES ('1032837843552444418', null, '11874bb6149dd45428da628c9766b252', null, null, '0', null, null, '278076304@qq.com', '1', '0', null, null, null, null, null, '2018-08-24 11:51:46', '2018-08-29 18:42:43', '0', '1003');

-- ----------------------------
-- Table structure for `bank_card_tb`
-- ----------------------------
DROP TABLE IF EXISTS `bank_card_tb`;
CREATE TABLE `bank_card_tb` (
  `bank_card_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '银行卡id',
  `realname` varchar(255) DEFAULT NULL COMMENT '姓名',
  `identity` varchar(255) DEFAULT NULL COMMENT '身份证',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名',
  `number` varchar(255) DEFAULT NULL COMMENT '银行卡卡号',
  `phone` varchar(255) DEFAULT NULL COMMENT '预留手机号',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id',
  PRIMARY KEY (`bank_card_id`),
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1030395372893597698 DEFAULT CHARSET=utf8 COMMENT='银行卡表';

-- ----------------------------
-- Records of bank_card_tb
-- ----------------------------
INSERT INTO `bank_card_tb` VALUES ('1030395372893597697', 'sdfg34', '34', '3434', '434', '34', '2018-08-17 18:06:15', '2018-08-17 18:06:15', '1030344761938505729');

-- ----------------------------
-- Table structure for `collect_tb`
-- ----------------------------
DROP TABLE IF EXISTS `collect_tb`;
CREATE TABLE `collect_tb` (
  `collect_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '汇总id',
  `wait_release` int(11) DEFAULT NULL COMMENT '待发布',
  `released` int(11) DEFAULT NULL COMMENT '发布中',
  `completed` int(11) DEFAULT NULL COMMENT '已完成',
  `cancelled` int(11) DEFAULT NULL COMMENT '已取消',
  `settled` int(11) DEFAULT NULL COMMENT '已结算',
  `funs` bigint(20) DEFAULT NULL COMMENT '覆盖粉丝数',
  `reading_number` bigint(20) DEFAULT NULL COMMENT '实际阅读量',
  `consume` decimal(13,2) DEFAULT '0.00' COMMENT '实际消费',
  `income` decimal(13,2) DEFAULT '0.00' COMMENT '实际收入',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id,外键',
  PRIMARY KEY (`collect_id`),
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1032837843552444423 DEFAULT CHARSET=utf8 COMMENT='汇总表';

-- ----------------------------
-- Records of collect_tb
-- ----------------------------
INSERT INTO `collect_tb` VALUES ('1000', '0', '0', '0', '0', '0', '0', '0', '0.00', '0.00', '2018-08-20 16:26:50', '2018-08-20 16:26:52', '1000');
INSERT INTO `collect_tb` VALUES ('1029655983884234753', '0', '0', '0', '0', '0', '0', '0', '0.00', '0.00', '2018-08-20 16:27:22', '2018-08-20 16:27:24', '1029655983884234753');
INSERT INTO `collect_tb` VALUES ('1030344761938505729', '0', '0', '0', '0', '0', '0', '0', '0.00', '0.00', '2018-08-20 16:28:08', '2018-08-20 16:28:05', '1030344761938505729');
INSERT INTO `collect_tb` VALUES ('1030356221427150849', '0', '0', '0', '0', '0', '0', '0', '0.00', '0.00', '2018-08-20 16:28:40', '2018-08-20 16:28:42', '1030356221427150849');
INSERT INTO `collect_tb` VALUES ('1032837470498463746', '0', '0', '0', '0', '0', '0', '0', '0.00', '0.00', '2018-08-24 11:50:17', '2018-08-24 11:50:17', '1032837470389411842');
INSERT INTO `collect_tb` VALUES ('1032837843552444422', '0', '0', '0', '0', '0', '0', '0', '0.00', '0.00', '2018-08-24 11:51:46', '2018-08-24 11:51:46', '1032837843552444418');

-- ----------------------------
-- Table structure for `company_tb`
-- ----------------------------
DROP TABLE IF EXISTS `company_tb`;
CREATE TABLE `company_tb` (
  `company_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公司id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `industry` varchar(255) DEFAULT NULL COMMENT '行业',
  `address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `website` varchar(255) DEFAULT NULL COMMENT '公司网址',
  `description` varchar(800) DEFAULT NULL COMMENT '公司描述',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id',
  PRIMARY KEY (`company_id`),
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1032837843552444421 DEFAULT CHARSET=utf8 COMMENT='公司表';

-- ----------------------------
-- Records of company_tb
-- ----------------------------
INSERT INTO `company_tb` VALUES ('1000', null, null, null, null, null, '2018-08-20 10:43:41', '2018-08-20 10:43:39', '1000');
INSERT INTO `company_tb` VALUES ('1029655983955537922', null, null, null, null, null, '2018-08-20 10:44:07', '2018-08-20 10:44:10', '1029655983884234753');
INSERT INTO `company_tb` VALUES ('1030356221439733762', null, null, null, null, null, '2018-08-20 10:44:57', '2018-08-20 10:45:00', '1030356221427150849');
INSERT INTO `company_tb` VALUES ('1031361614873833473', '雅耀（湖南）科技有限公司', '广告', '湖南长沙岳麓区麓谷企业广场', 'http://www.yayao8.com', '一个有责任的公司！积极进取，牵头往上，激流勇进！', '2018-08-20 10:05:45', '2018-08-20 10:07:00', '1030344761938505729');
INSERT INTO `company_tb` VALUES ('1032837470448132098', null, null, null, null, null, '2018-08-24 11:50:17', '2018-08-24 11:50:17', '1032837470389411842');
INSERT INTO `company_tb` VALUES ('1032837843552444420', null, null, null, null, null, '2018-08-24 11:51:46', '2018-08-24 11:51:46', '1032837843552444418');

-- ----------------------------
-- Table structure for `config_tb`
-- ----------------------------
DROP TABLE IF EXISTS `config_tb`;
CREATE TABLE `config_tb` (
  `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '配置id',
  `platform_name` varchar(255) DEFAULT NULL COMMENT '平台开户名称',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '平台开户银行',
  `bank_account` varchar(255) DEFAULT NULL COMMENT '平台银行账户',
  `service_phone` varchar(255) DEFAULT NULL COMMENT '平台联系电话',
  `service_qq` varchar(255) DEFAULT NULL COMMENT '平台联系qq',
  `service_proportion` decimal(11,2) DEFAULT '0.00' COMMENT '平台服务费率，单位%',
  `tax_rate` decimal(11,2) DEFAULT '0.00' COMMENT '税率，单位%',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='配置表';

-- ----------------------------
-- Records of config_tb
-- ----------------------------
INSERT INTO `config_tb` VALUES ('1000', '聂跃', '中国银行', '123213123123', '400-2323-323', '158433909', '0.00', '0.00', '2018-08-20 15:53:27', '2018-08-20 16:01:51');

-- ----------------------------
-- Table structure for `finance_record_tb`
-- ----------------------------
DROP TABLE IF EXISTS `finance_record_tb`;
CREATE TABLE `finance_record_tb` (
  `finance_record_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '财务记录id',
  `method` tinyint(4) DEFAULT NULL COMMENT '方式，1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型，1充值、2消费，3退款，4冻结，5解冻，6收入，7提现',
  `transaction_number` varchar(255) DEFAULT NULL COMMENT '交易单号',
  `advertise_name` varchar(255) DEFAULT NULL COMMENT '广告名称',
  `media_name` varchar(255) DEFAULT NULL COMMENT '媒体名称',
  `money` decimal(13,2) DEFAULT '0.00' COMMENT '金额',
  `tax` decimal(13,2) DEFAULT '0.00' COMMENT '税金',
  `brokerage` decimal(13,2) DEFAULT '0.00' COMMENT '平台服务费',
  `real_money` decimal(13,2) DEFAULT '0.00' COMMENT '实际金额',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，默认1待处理，2成功，3已拒绝',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `advertise_id` bigint(20) DEFAULT NULL COMMENT '广告id,外键',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id,外键',
  PRIMARY KEY (`finance_record_id`),
  KEY `INDEX_ADVERTISEID` (`advertise_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE,
  KEY `INDEX_METHOD` (`method`) USING BTREE,
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_TRANSACTIONNUMBER` (`transaction_number`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务记录表';

-- ----------------------------
-- Records of finance_record_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `finance_tb`
-- ----------------------------
DROP TABLE IF EXISTS `finance_tb`;
CREATE TABLE `finance_tb` (
  `finance_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '财务id',
  `password` varchar(255) DEFAULT NULL COMMENT '安全密码',
  `money` decimal(13,2) DEFAULT '0.00' COMMENT '余额',
  `recharge` decimal(13,2) DEFAULT '0.00' COMMENT '充值金额',
  `consume` decimal(13,2) DEFAULT '0.00' COMMENT '消费金额',
  `income` decimal(13,2) DEFAULT '0.00' COMMENT '收入金额',
  `withdrawals` decimal(13,2) DEFAULT '0.00' COMMENT '提现金额',
  `refund` decimal(13,2) DEFAULT '0.00' COMMENT '退款金额',
  `frozen` decimal(13,2) DEFAULT '0.00' COMMENT '冻结金额',
  `base_profit` decimal(13,2) DEFAULT '0.00' COMMENT '赠送金钱',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id外键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`finance_id`),
  KEY `INDEX_MONEY` (`money`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1032837843552444420 DEFAULT CHARSET=utf8 COMMENT='财务表';

-- ----------------------------
-- Records of finance_tb
-- ----------------------------
INSERT INTO `finance_tb` VALUES ('1000', 'd3265abc117009bb706d223fc944f868', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1000', '2018-08-17 16:03:50', '2018-08-17 16:03:50');
INSERT INTO `finance_tb` VALUES ('1029655983921983490', 'd3265abc117009bb706d223fc944f868', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1029655983884234753', '2018-08-15 17:08:11', '2018-08-15 17:08:11');
INSERT INTO `finance_tb` VALUES ('1030344761955282945', 'd3265abc117009bb706d223fc944f868', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1030344761938505729', '2018-08-17 14:45:09', '2018-08-17 14:45:09');
INSERT INTO `finance_tb` VALUES ('1030356221431345153', 'd3265abc117009bb706d223fc944f868', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1030356221427150849', '2018-08-17 15:30:41', '2018-08-17 15:30:41');
INSERT INTO `finance_tb` VALUES ('1032837470431354882', 'd3265abc117009bb706d223fc944f868', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1032837470389411842', '2018-08-24 11:50:17', '2018-08-24 11:50:17');
INSERT INTO `finance_tb` VALUES ('1032837843552444419', 'd3265abc117009bb706d223fc944f868', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1032837843552444418', '2018-08-24 11:51:46', '2018-08-24 11:51:46');

-- ----------------------------
-- Table structure for `media_cate_tb`
-- ----------------------------
DROP TABLE IF EXISTS `media_cate_tb`;
CREATE TABLE `media_cate_tb` (
  `media_cate_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '媒体类型id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `summary` varchar(255) DEFAULT NULL COMMENT '描述',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`media_cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1031484142774616066 DEFAULT CHARSET=utf8 COMMENT='媒体类型表';

-- ----------------------------
-- Records of media_cate_tb
-- ----------------------------
INSERT INTO `media_cate_tb` VALUES ('1031483109868535810', '旅行摄影', '旅行摄影', '2018-08-20 18:09:03');
INSERT INTO `media_cate_tb` VALUES ('1031483147256561665', '生活', '生活', '2018-08-20 18:08:41');
INSERT INTO `media_cate_tb` VALUES ('1031483172514660353', '幽默搞笑', '幽默搞笑', '2018-08-20 18:08:47');
INSERT INTO `media_cate_tb` VALUES ('1031483198087331842', '时尚潮流', '时尚潮流', '2018-08-20 18:08:53');
INSERT INTO `media_cate_tb` VALUES ('1031483342849540098', '新闻资讯', '新闻资讯', '2018-08-20 18:09:28');
INSERT INTO `media_cate_tb` VALUES ('1031483379713277953', '互联网', '互联网', '2018-08-20 18:09:36');
INSERT INTO `media_cate_tb` VALUES ('1031483403671142402', '美容美妆', '美容美妆', '2018-08-20 18:09:42');
INSERT INTO `media_cate_tb` VALUES ('1031483431970111489', '汽车', '汽车', '2018-08-20 18:09:49');
INSERT INTO `media_cate_tb` VALUES ('1031483459379888129', '金融财经', '金融财经', '2018-08-20 18:09:55');
INSERT INTO `media_cate_tb` VALUES ('1031483486932271106', '情感心理', '情感心理', '2018-08-20 18:10:02');
INSERT INTO `media_cate_tb` VALUES ('1031483510038691841', '健康养生', '健康养生', '2018-08-20 18:10:07');
INSERT INTO `media_cate_tb` VALUES ('1031483534772502530', '母婴亲子', '母婴亲子', '2018-08-20 18:10:13');
INSERT INTO `media_cate_tb` VALUES ('1031483561959981057', '运动瘦身', '运动瘦身', '2018-08-20 18:10:20');
INSERT INTO `media_cate_tb` VALUES ('1031483596948865025', '娱乐八卦', '娱乐八卦', '2018-08-20 18:10:28');
INSERT INTO `media_cate_tb` VALUES ('1031483625465937922', '美食', '美食', '2018-08-20 18:10:35');
INSERT INTO `media_cate_tb` VALUES ('1031483661113327618', '星座风水', '星座风水', '2018-08-20 18:10:44');
INSERT INTO `media_cate_tb` VALUES ('1031483684056170497', '科技IT', '科技IT', '2018-08-20 18:10:49');
INSERT INTO `media_cate_tb` VALUES ('1031483705879134210', '文化艺术', '文化艺术', '2018-08-20 18:10:54');
INSERT INTO `media_cate_tb` VALUES ('1031483727270084610', '电商购物', '电商购物', '2018-08-20 18:10:59');
INSERT INTO `media_cate_tb` VALUES ('1031483748451319809', '体育', '体育', '2018-08-20 18:11:04');
INSERT INTO `media_cate_tb` VALUES ('1031483777400406017', '游戏动漫', '游戏动漫', '2018-08-20 18:11:11');
INSERT INTO `media_cate_tb` VALUES ('1031483799248535554', '法律', '法律', '2018-08-20 18:11:16');
INSERT INTO `media_cate_tb` VALUES ('1031483822262681601', '地区号', '地区号', '2018-08-20 18:11:22');
INSERT INTO `media_cate_tb` VALUES ('1031483848200257537', '报刊杂志', '报刊杂志', '2018-08-20 18:11:28');
INSERT INTO `media_cate_tb` VALUES ('1031483870866276353', '音乐', '音乐', '2018-08-20 18:11:34');
INSERT INTO `media_cate_tb` VALUES ('1031483899832139778', '商业职场', '商业职场', '2018-08-20 18:11:40');
INSERT INTO `media_cate_tb` VALUES ('1031483921952899073', '教育培训', '教育培训', '2018-08-20 18:11:46');
INSERT INTO `media_cate_tb` VALUES ('1031483944400814081', '文学阅读', '文学阅读', '2018-08-20 18:11:51');
INSERT INTO `media_cate_tb` VALUES ('1031483971378577410', '家居房产', '家居房产', '2018-08-20 18:11:57');
INSERT INTO `media_cate_tb` VALUES ('1031483991716757506', '两性知识', '两性知识', '2018-08-20 18:12:02');
INSERT INTO `media_cate_tb` VALUES ('1031484018992316417', '视觉设计', '视觉设计', '2018-08-20 18:12:09');
INSERT INTO `media_cate_tb` VALUES ('1031484046708277249', '政治军事', '政治军事', '2018-08-20 18:12:15');
INSERT INTO `media_cate_tb` VALUES ('1031484071333036034', '影视', '影视', '2018-08-20 18:12:21');
INSERT INTO `media_cate_tb` VALUES ('1031484092946284546', '萌宠', '萌宠', '2018-08-20 18:12:26');
INSERT INTO `media_cate_tb` VALUES ('1031484114798608386', '历史', '历史', '2018-08-20 18:12:32');
INSERT INTO `media_cate_tb` VALUES ('1031484142774616065', '其他', '其他', '2018-08-20 18:12:38');

-- ----------------------------
-- Table structure for `media_collection_tb`
-- ----------------------------
DROP TABLE IF EXISTS `media_collection_tb`;
CREATE TABLE `media_collection_tb` (
  `media_collection_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '媒体收藏id',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `media_id` bigint(20) DEFAULT NULL COMMENT '媒体id,外键',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id,外键',
  PRIMARY KEY (`media_collection_id`),
  KEY `INDEX_MEDIAID` (`media_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体收藏表';

-- ----------------------------
-- Records of media_collection_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `media_daily_data_tb`
-- ----------------------------
DROP TABLE IF EXISTS `media_daily_data_tb`;
CREATE TABLE `media_daily_data_tb` (
  `media_daily_data_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '媒体日数据id',
  `article_number` bigint(20) DEFAULT NULL COMMENT '文章发布数',
  `top_reading_number` bigint(20) DEFAULT NULL COMMENT 'top阅读数',
  `total_reading_number` bigint(20) DEFAULT NULL COMMENT '总阅读数',
  `avg_reading_number` bigint(20) DEFAULT NULL COMMENT '平均阅读数',
  `top1_reading_number` bigint(20) DEFAULT NULL COMMENT '头条阅读数',
  `point_number` bigint(20) DEFAULT NULL COMMENT '点赞数',
  `reading_number` bigint(20) DEFAULT NULL COMMENT '阅读数',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `media_id` bigint(20) DEFAULT NULL COMMENT '媒体id外键',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id外键',
  PRIMARY KEY (`media_daily_data_id`),
  UNIQUE KEY `DAY_DATA` (`create_date`,`media_id`,`account_id`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_MEDIAID` (`media_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='媒体日数据表';

-- ----------------------------
-- Records of media_daily_data_tb
-- ----------------------------
INSERT INTO `media_daily_data_tb` VALUES ('1', '222', '3333', '44434', '3545', '34534', '345345', '345345', '2018-08-28 15:15:53', '2018-08-28 15:15:55', null, '1032837843552444418');
INSERT INTO `media_daily_data_tb` VALUES ('2', '34543', '5345', '345', '345', '345', '345', '345', '2018-08-26 15:16:34', '2018-08-28 15:16:39', null, '1032837843552444418');

-- ----------------------------
-- Table structure for `media_data_tb`
-- ----------------------------
DROP TABLE IF EXISTS `media_data_tb`;
CREATE TABLE `media_data_tb` (
  `media_data_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '媒体详细数据id',
  `name` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `position` tinyint(4) DEFAULT NULL COMMENT '文章位置,1头条，2二条，3三条，4四条，5五条，6六条',
  `reading_number` bigint(20) DEFAULT NULL COMMENT '阅读数',
  `point_number` bigint(20) DEFAULT NULL COMMENT '点赞数',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `media_id` bigint(20) DEFAULT NULL COMMENT '媒体id外键',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id外键',
  PRIMARY KEY (`media_data_id`),
  UNIQUE KEY `DAY_DATA` (`create_date`,`media_id`,`account_id`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_MEDIAID` (`media_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体详细数据表';

-- ----------------------------
-- Records of media_data_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `media_tb`
-- ----------------------------
DROP TABLE IF EXISTS `media_tb`;
CREATE TABLE `media_tb` (
  `media_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '媒体id',
  `name` varchar(255) DEFAULT NULL COMMENT '媒体名称',
  `account_name` varchar(255) DEFAULT NULL COMMENT '媒体账号',
  `wechat_img` varchar(255) DEFAULT NULL COMMENT '微信头像',
  `qr_code` varchar(255) DEFAULT NULL COMMENT '二维码',
  `funs` bigint(20) DEFAULT NULL COMMENT '粉丝数',
  `funs_img` varchar(255) DEFAULT NULL COMMENT '粉丝截图',
  `user_img` varchar(255) DEFAULT NULL COMMENT '用户属性',
  `reading_number` bigint(20) DEFAULT NULL COMMENT '阅读量',
  `sex_man_scale` decimal(11,2) DEFAULT NULL COMMENT '性别男比例,单位%',
  `sex_woman_scale` decimal(11,2) DEFAULT NULL COMMENT '性别女比例,单位%',
  `funs_top1_address` varchar(255) DEFAULT NULL COMMENT '粉丝top1地址',
  `funs_top2_address` varchar(255) DEFAULT NULL COMMENT '粉丝top2地址',
  `funs_top3_address` varchar(255) DEFAULT NULL COMMENT '粉丝top3地址',
  `auth` tinyint(4) DEFAULT NULL COMMENT '授权,1未授权，2已授权',
  `take_order` tinyint(4) DEFAULT NULL COMMENT '是否接单,1是，2否',
  `top1_price` decimal(13,2) DEFAULT NULL COMMENT '多图文头条报价',
  `top2_price` decimal(13,2) DEFAULT NULL COMMENT '多图文二条报价',
  `top3n_price` decimal(13,2) DEFAULT NULL COMMENT '多图文3-N条报价',
  `single_price` decimal(13,2) DEFAULT NULL COMMENT '单图文报价',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，1审核中、2正常、3审核未通过',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `media_cate_id` bigint(20) DEFAULT NULL COMMENT '媒体类型id,外键',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id,外键',
  PRIMARY KEY (`media_id`),
  KEY `INDEX_READINGNUMBER` (`reading_number`) USING BTREE,
  KEY `INDEX_SEXMANSCALE` (`sex_man_scale`) USING BTREE,
  KEY `INDEX_SEXWOMANSCALE` (`sex_woman_scale`) USING BTREE,
  KEY `INDEX_FUNSTOP1ADDRESS` (`funs_top1_address`) USING BTREE,
  KEY `INDEX_FUNSTOP2ADDRESS` (`funs_top2_address`) USING BTREE,
  KEY `INDEX_FUNSTOP3ADDRESS` (`funs_top3_address`) USING BTREE,
  KEY `INDEX_AUTH` (`auth`) USING BTREE,
  KEY `INDEX_TAKEORDER` (`take_order`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE,
  KEY `INDEX_MEDIACATEID` (`media_cate_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1032151354715877378 DEFAULT CHARSET=utf8 COMMENT='媒体表';

-- ----------------------------
-- Records of media_tb
-- ----------------------------
INSERT INTO `media_tb` VALUES ('1032151354715877377', 'dfsgzdfg梵蒂冈23', 'fdg342d', 'http://localhost:8080/uploaderPath/img/20180822/1534918989652.jpg', 'http://localhost:8080/uploaderPath/img/20180822/1534919002786.jpg', '12344', 'http://localhost:8080/uploaderPath/img/20180822/153491900831.jpg', 'http://localhost:8080/uploaderPath/img/20180822/1534919012855.jpg', '5543', '33.00', '55.00', '天津市', '河北省', '全国', '2', '1', '555.00', '333.00', '222.00', '666.00', '2', '2018-08-22 14:23:54', '2018-08-23 14:53:36', '1031484142774616065', '1030344761938505729');

-- ----------------------------
-- Table structure for `notice_tb`
-- ----------------------------
DROP TABLE IF EXISTS `notice_tb`;
CREATE TABLE `notice_tb` (
  `notice_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `region` tinyint(4) DEFAULT NULL COMMENT '范围，1全局，2个人',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型，默认1系统消息，2充值，3退款，4提现',
  `is_read` tinyint(4) DEFAULT NULL COMMENT '是否阅读，默认1未读，2已读',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `img_address` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `content` longtext COMMENT '内容',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，默认为0正常，1审核中，2申请成功，3申请失败',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id',
  PRIMARY KEY (`notice_id`),
  KEY `INDEX_REGION` (`region`) USING BTREE,
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_ISREAD` (`is_read`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1031425463522111492 DEFAULT CHARSET=utf8 COMMENT='通知表';

-- ----------------------------
-- Records of notice_tb
-- ----------------------------
INSERT INTO `notice_tb` VALUES ('1031425463480168450', '1', '1', '1', '系统消息', 'http://localhost:8080/uploaderPath/img/20180820/1534745489463.jpg', '盛大发生的', '0', '2018-08-20 14:19:28', '2018-08-20 14:19:28', null);
INSERT INTO `notice_tb` VALUES ('1031425463509528577', '2', '1', '1', '系统消息', 'http://localhost:8080/uploaderPath/img/20180820/1534745489463.jpg', '盛大发生的', '0', '2018-08-20 14:19:28', '2018-08-20 14:19:28', '1000');
INSERT INTO `notice_tb` VALUES ('1031425463513722882', '2', '1', '1', '系统消息', 'http://localhost:8080/uploaderPath/img/20180820/1534745489463.jpg', '盛大发生的', '0', '2018-08-20 14:19:28', '2018-08-20 14:19:28', '1029655983884234753');
INSERT INTO `notice_tb` VALUES ('1031425463513722883', '2', '1', '1', '系统消息', 'http://localhost:8080/uploaderPath/img/20180820/1534745489463.jpg', '盛大发生的', '0', '2018-08-20 14:19:28', '2018-08-20 14:19:28', '1030344761938505729');
INSERT INTO `notice_tb` VALUES ('1031425463522111490', '2', '1', '1', '系统消息', 'http://localhost:8080/uploaderPath/img/20180820/1534745489463.jpg', '盛大发生的', '0', '2018-08-20 14:19:28', '2018-08-20 14:19:28', '1030356221427150849');
INSERT INTO `notice_tb` VALUES ('1031425463522111491', '2', '2', '1', 'gdfgdf', null, 'dfg', '1', '2018-08-20 14:20:55', null, '1000');

-- ----------------------------
-- Table structure for `permission_tb`
-- ----------------------------
DROP TABLE IF EXISTS `permission_tb`;
CREATE TABLE `permission_tb` (
  `permission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `type` tinyint(4) DEFAULT NULL COMMENT '权限类型，默认0开放，1鉴权',
  `manager_name` varchar(255) DEFAULT NULL COMMENT '权限管理名',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名',
  `route` varchar(255) DEFAULT NULL COMMENT '权限路由',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `route` (`route`) USING BTREE,
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_MANAGERNAME` (`manager_name`) USING BTREE,
  KEY `INDEX_NAME` (`name`) USING BTREE,
  KEY `INDEX_ROUTE` (`route`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1030366929330073603 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permission_tb
-- ----------------------------
INSERT INTO `permission_tb` VALUES ('1022441591719522306', '1', '账户管理', '账户增加', '/account/add', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441591799214081', '1', '账户管理', '账户实名认证', '/account/auth', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441591824379906', '0', '账户管理', '账户数量', '/account/count', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441591853740034', '1', '账户管理', '账户删除', '/account/delete', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441591883100162', '0', '账户管理', '是否登录', '/account/islogin', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441591908265985', '0', '账户管理', '账户列表', '/account/list', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441591941820418', '0', '账户管理', '账户单个加载', '/account/load', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441591941820419', '0', '账户管理', '管理员登录', '/account/login', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592013123586', '0', '账户管理', '登出', '/account/loginout', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592050872322', '1', '账户管理', '手机号账户是否存在', '/account/phoneIsExist', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592092815361', '1', '账户管理', '账户修改', '/account/update', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592122175490', '1', '账户管理', '账户修改用户信息', '/account/updateInfo', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592143147010', '1', '账户管理', '账户修改密码', '/account/updatePassword', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592143147011', '0', '账户管理', 'web用户登录', '/account/weblogin', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592227033090', '1', '权限管理', '权限增加', '/permission/add', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592256393217', '0', '权限管理', '权限数量', '/permission/count', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592273170434', '1', '权限管理', '权限删除', '/permission/delete', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592327696386', '1', '权限管理', '初始化权限', '/permission/init', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592344473602', '0', '权限管理', '权限列表', '/permission/list', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592386416642', '0', '权限管理', '权限单个加载', '/permission/load', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592394805250', '1', '权限管理', '权限修改', '/permission/update', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592436748289', '1', '收货信息管理', '收货信息增加', '/receiptInfo/add', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592466108418', '0', '收货信息管理', '收货信息数量', '/receiptInfo/count', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592503857154', '1', '收货信息管理', '收货信息删除', '/receiptInfo/delete', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592524828674', '0', '收货信息管理', '收货信息', '/receiptInfo/list', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592847790082', '0', '收货信息管理', '收货信息单个加载', '/receiptInfo/load', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592860372993', '1', '收货信息管理', 'openid收货信息增加', '/receiptInfo/openidAdd', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592898121729', '1', '收货信息管理', '设置默认收货信息', '/receiptInfo/setIsDefault', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592898121730', '1', '收货信息管理', '收货信息修改', '/receiptInfo/update', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592952647681', '1', '角色管理', '角色增加', '/role/add', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441592973619202', '0', '角色管理', '角色数量', '/role/count', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441593002979329', '1', '角色管理', '角色删除', '/role/delete', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441593032339458', '0', '角色管理', '角色列表', '/role/list', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441593032339459', '0', '角色管理', '角色单个加载', '/role/load', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441593032339460', '1', '角色管理', '角色修改', '/role/update', '2018-07-26 19:20:46');
INSERT INTO `permission_tb` VALUES ('1022441593103642625', '1', '角色权限管理', '角色权限增加', '/rolePermission/add', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593120419842', '0', '角色权限管理', '角色权限数量', '/rolePermission/count', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593153974273', '1', '角色权限管理', '角色权限删除', '/rolePermission/delete', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593179140097', '0', '角色权限管理', '角色权限列表', '/rolePermission/list', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593208500226', '0', '角色权限管理', '角色权限单个加载', '/rolePermission/load', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593229471746', '1', '角色权限管理', '角色权限修改', '/rolePermission/update', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593258831874', '1', '工作计划管理', '工作计划增加', '/scheduleJob/add', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593283997697', '0', '工作计划管理', '工作计划数量', '/scheduleJob/count', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593304969218', '1', '工作计划管理', '工作计划删除', '/scheduleJob/delete', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593346912258', '0', '工作计划管理', '工作计划列表', '/scheduleJob/list', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593359495169', '0', '工作计划管理', '工作计划列表（quartz内存中）', '/scheduleJob/listquartz', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593359495170', '0', '工作计划管理', '工作计划单个加载', '/scheduleJob/load', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593359495171', '1', '工作计划管理', '工作计划列表', '/scheduleJob/turn', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593455964161', '1', '工作计划管理', '工作计划修改', '/scheduleJob/update', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593481129986', '1', '第三方信息管理', '第三方信息增加', '/thirdInfo/add', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593506295810', '0', '第三方信息管理', '第三方信息数量', '/thirdInfo/count', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593523073026', '1', '第三方信息管理', '第三方信息删除', '/thirdInfo/delete', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593544044546', '0', '第三方信息管理', '第三方信息列表', '/thirdInfo/list', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593573404673', '0', '第三方信息管理', '第三方信息单个加载', '/thirdInfo/load', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593598570498', '1', '第三方信息管理', '第三方信息修改', '/thirdInfo/update', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593623736321', '0', '工具接口管理', '上传文件', '/tool/file/add', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593644707842', '0', '工具接口管理', '获取sessionId', '/tool/getSession', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593669873666', '0', '工具接口管理', '验证码', '/tool/getVerificationCode', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593695039490', '0', '工具接口管理', '上传图片', '/tool/img/add', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1022441593695039491', '0', '工具接口管理', 'test', '/tool/test', '2018-07-26 19:20:47');
INSERT INTO `permission_tb` VALUES ('1026412396644130817', '0', '账户管理', 'web用户注册', '/account/webregister', '2018-08-06 18:19:20');
INSERT INTO `permission_tb` VALUES ('1026420361035923457', '0', '账户管理', '手机验证码发送/邮箱验证链接', '/account/validCode', '2018-08-06 18:50:59');
INSERT INTO `permission_tb` VALUES ('1027739904941436930', '1', '银行卡管理', '银行卡增加', '/bankCard/add', '2018-08-10 10:14:22');
INSERT INTO `permission_tb` VALUES ('1027739905033711618', '0', '银行卡管理', '银行卡数量', '/bankCard/count', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905109209089', '1', '银行卡管理', '银行卡删除', '/bankCard/delete', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905117597697', '0', '银行卡管理', '银行卡列表', '/bankCard/list', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905117597698', '0', '银行卡管理', '银行卡单个加载', '/bankCard/load', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905117597699', '1', '银行卡管理', '银行卡修改', '/bankCard/update', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905117597700', '1', '公司管理', '公司增加', '/company/add', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905117597701', '0', '公司管理', '公司数量', '/company/count', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905117597702', '1', '公司管理', '公司删除', '/company/delete', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905432170498', '0', '公司管理', '公司列表', '/company/list', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905453142017', '0', '公司管理', '公司单个加载', '/company/load', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905495085058', '1', '公司管理', '公司修改', '/company/update', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905520250882', '1', '财务管理', '财务增加', '/finance/add', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905545416706', '0', '财务管理', '财务数量', '/finance/count', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905566388225', '1', '财务管理', '财务删除', '/finance/delete', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905587359746', '0', '财务管理', '财务列表', '/finance/list', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905616719874', '0', '财务管理', '财务单个加载', '/finance/load', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905637691393', '1', '财务管理', '财务修改', '/finance/update', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905658662913', '1', '财务记录管理', '财务记录增加', '/financeRecord/add', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905679634433', '0', '财务记录管理', '财务记录数量', '/financeRecord/count', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905696411649', '1', '财务记录管理', '财务记录删除', '/financeRecord/delete', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905717383169', '0', '财务记录管理', '财务记录列表', '/financeRecord/list', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905746743298', '0', '财务记录管理', '财务记录单个加载', '/financeRecord/load', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905755131905', '1', '财务记录管理', '财务记录修改', '/financeRecord/update', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905776103425', '1', '通知管理', '通知增加', '/notice/add', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905776103426', '0', '通知管理', '通知数量', '/notice/count', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905776103427', '1', '通知管理', '通知删除', '/notice/delete', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905776103428', '0', '通知管理', '通知列表', '/notice/list', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905910321153', '0', '通知管理', '通知单个加载', '/notice/load', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1027739905910321154', '1', '通知管理', '通知修改', '/notice/update', '2018-08-10 10:14:23');
INSERT INTO `permission_tb` VALUES ('1028957469424422913', '1', '汇总管理', '汇总增加', '/collect/add', '2018-08-13 18:52:32');
INSERT INTO `permission_tb` VALUES ('1028957469499920386', '0', '汇总管理', '汇总数量', '/collect/count', '2018-08-13 18:52:32');
INSERT INTO `permission_tb` VALUES ('1028957469537669121', '1', '汇总管理', '汇总删除', '/collect/delete', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469575417857', '0', '汇总管理', '汇总列表', '/collect/list', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469604777986', '0', '汇总管理', '汇总单个加载', '/collect/load', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469638332418', '1', '汇总管理', '汇总修改', '/collect/update', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469718024193', '1', '配置管理', '配置增加', '/config/add', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469743190017', '0', '配置管理', '配置数量', '/config/count', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469768355842', '1', '配置管理', '配置删除', '/config/delete', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469793521666', '0', '配置管理', '配置列表', '/config/list', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469818687489', '0', '配置管理', '配置单个加载', '/config/load', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469839659009', '1', '配置管理', '配置修改', '/config/update', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469961293825', '1', '媒体管理', '媒体增加', '/media/add', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469982265345', '0', '媒体管理', '媒体数量', '/media/count', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957469999042562', '1', '媒体管理', '媒体删除', '/media/delete', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470024208385', '0', '媒体管理', '媒体列表', '/media/list', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470040985602', '0', '媒体管理', '媒体单个加载', '/media/load', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470070345729', '1', '媒体管理', '媒体修改', '/media/update', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470091317249', '1', '媒体类型管理', '媒体类型增加', '/mediaCate/add', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470116483073', '0', '媒体类型管理', '媒体类型数量', '/mediaCate/count', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470137454593', '1', '媒体类型管理', '媒体类型删除', '/mediaCate/delete', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470162620418', '0', '媒体类型管理', '媒体类型列表', '/mediaCate/list', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470183591937', '0', '媒体类型管理', '媒体类型单个加载', '/mediaCate/load', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470204563458', '1', '媒体类型管理', '媒体类型修改', '/mediaCate/update', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470229729282', '1', '媒体收藏管理', '媒体收藏增加', '/mediaCollection/add', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470254895105', '0', '媒体收藏管理', '媒体收藏数量', '/mediaCollection/count', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470275866625', '1', '媒体收藏管理', '媒体收藏删除', '/mediaCollection/delete', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470296838145', '0', '媒体收藏管理', '媒体收藏列表', '/mediaCollection/list', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470322003970', '0', '媒体收藏管理', '媒体收藏单个加载', '/mediaCollection/load', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470351364097', '1', '媒体收藏管理', '媒体收藏修改', '/mediaCollection/update', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470372335617', '1', '媒体日数据管理', '媒体日数据增加', '/mediaDailyData/add', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470397501441', '0', '媒体日数据管理', '媒体日数据数量', '/mediaDailyData/count', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470422667266', '1', '媒体日数据管理', '媒体日数据删除', '/mediaDailyData/delete', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470443638785', '0', '媒体日数据管理', '媒体日数据列表', '/mediaDailyData/list', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470468804609', '0', '媒体日数据管理', '媒体日数据单个加载', '/mediaDailyData/load', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470493970434', '1', '媒体日数据管理', '媒体日数据修改', '/mediaDailyData/update', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470514941954', '1', '媒体详细数据管理', '媒体详细数据增加', '/mediaData/add', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470535913474', '0', '媒体详细数据管理', '媒体详细数据数量', '/mediaData/count', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470556884993', '1', '媒体详细数据管理', '媒体详细数据删除', '/mediaData/delete', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470582050818', '0', '媒体详细数据管理', '媒体详细数据列表', '/mediaData/list', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470607216642', '0', '媒体详细数据管理', '媒体详细数据单个加载', '/mediaData/load', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470628188161', '1', '媒体详细数据管理', '媒体详细数据修改', '/mediaData/update', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470745628674', '1', '位置广告管理', '位置广告增加', '/positionAdvertise/add', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470766600194', '0', '位置广告管理', '位置广告数量', '/positionAdvertise/count', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470787571714', '1', '位置广告管理', '位置广告删除', '/positionAdvertise/delete', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470808543234', '0', '位置广告管理', '位置广告列表', '/positionAdvertise/list', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470825320449', '0', '位置广告管理', '位置广告单个加载', '/positionAdvertise/load', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470846291969', '1', '位置广告管理', '位置广告修改', '/positionAdvertise/update', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470863069186', '1', '位置媒体管理', '位置媒体增加', '/positionMedia/add', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470884040706', '0', '位置媒体管理', '位置媒体数量', '/positionMedia/count', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470900817921', '1', '位置媒体管理', '位置媒体删除', '/positionMedia/delete', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470925983745', '0', '位置媒体管理', '位置媒体列表', '/positionMedia/list', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470951149570', '0', '位置媒体管理', '位置媒体单个加载', '/positionMedia/load', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470972121089', '1', '位置媒体管理', '位置媒体修改', '/positionMedia/update', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957470997286914', '1', '阅读广告管理', '阅读广告增加', '/readAdvertise/add', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957471018258434', '0', '阅读广告管理', '阅读广告数量', '/readAdvertise/count', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957471035035650', '1', '阅读广告管理', '阅读广告删除', '/readAdvertise/delete', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957471056007170', '0', '阅读广告管理', '阅读广告列表', '/readAdvertise/list', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957471072784385', '0', '阅读广告管理', '阅读广告单个加载', '/readAdvertise/load', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957471093755906', '1', '阅读广告管理', '阅读广告修改', '/readAdvertise/update', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957471114727425', '1', '阅读媒体管理', '阅读媒体增加', '/readMedia/add', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957471131504641', '0', '阅读媒体管理', '阅读媒体数量', '/readMedia/count', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957471148281858', '1', '阅读媒体管理', '阅读媒体删除', '/readMedia/delete', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957471169253378', '0', '阅读媒体管理', '阅读媒体列表', '/readMedia/list', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957471186030594', '0', '阅读媒体管理', '阅读媒体单个加载', '/readMedia/load', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1028957471207002113', '1', '阅读媒体管理', '阅读媒体修改', '/readMedia/update', '2018-08-13 18:52:33');
INSERT INTO `permission_tb` VALUES ('1030366929330073602', '1', '财务管理', '管理员修改或增加交易密码', '/finance/updatePasswordByFinanceId', '2018-08-17 16:13:14');

-- ----------------------------
-- Table structure for `position_advertise_tb`
-- ----------------------------
DROP TABLE IF EXISTS `position_advertise_tb`;
CREATE TABLE `position_advertise_tb` (
  `position_advertise_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '位置广告id',
  `name` varchar(255) DEFAULT NULL COMMENT '广告名称',
  `position` tinyint(4) DEFAULT NULL COMMENT '广告位置,1多图文头条，2多图文二条，3多图文3-N条，4单图文',
  `consume_price` decimal(13,2) DEFAULT NULL COMMENT '广告金额',
  `final_price` decimal(13,2) DEFAULT NULL COMMENT '实际金额',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `top_img` varchar(255) DEFAULT NULL COMMENT '封面',
  `content` longtext COMMENT '正文内容',
  `origin_url` varchar(255) DEFAULT NULL COMMENT '原文链接',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，1待支付、2待审核、3投放中、4暂停中、5已完成',
  `start_date` datetime DEFAULT NULL COMMENT '投放开始时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id,外键',
  PRIMARY KEY (`position_advertise_id`),
  KEY `INDEX_POSITION` (`position`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1034638450613518339 DEFAULT CHARSET=utf8 COMMENT='位置广告表';

-- ----------------------------
-- Records of position_advertise_tb
-- ----------------------------
INSERT INTO `position_advertise_tb` VALUES ('1032539107563646978', 'sadfsdg23', '2', '0.00', '0.00', '盛大发生的', '的说法撒的发收到', 'http://localhost:8080/uploaderPath/img/20180823/1535011380675.jpg', ' <table cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td align=\"left\"><div><div><ul><li><div\"><div></div></div\"></li></ul></div></div><div id=\"con-ar\"><div tpl=\"right_recommends_merge\"><div><div><div><span title=\"历史人物\">历史人物</span></div><div><div><divrsv_re_ename\':\'慧思\',\'rsv_re_uri\':\'c1b90307949e495bb49cb319120bf84c\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E6%85%A7%E6%80%9D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=c1b90307949e495bb49cb319120bf84c\"><img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3635717794,3006329220&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E6%85%A7%E6%80%9D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=c1b90307949e495bb49cb319120bf84c\"></a></div><div><a target=\"_blank\" title=\"慧思\" href=\"https://www.baidu.com/s?wd=%E6%85%A7%E6%80%9D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=c1b90307949e495bb49cb319120bf84c\">慧思</a></div><div><p>南北朝时高僧</p></div></divrsv_re_ename\':\'慧思\',\'rsv_re_uri\':\'c1b90307949e495bb49cb319120bf84c\'}\"></div><divrsv_re_ename\':\'百丈禅师\',\'rsv_re_uri\':\'aab82cfc2e4b48b18315fe8b401286ea\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E7%99%BE%E4%B8%88%E7%A6%85%E5%B8%88&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=aab82cfc2e4b48b18315fe8b401286ea\"><img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2609317315,2369270672&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E7%99%BE%E4%B8%88%E7%A6%85%E5%B8%88&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=aab82cfc2e4b48b18315fe8b401286ea\"></a></div><div><a target=\"_blank\" title=\"百丈禅师\" href=\"https://www.baidu.com/s?wd=%E7%99%BE%E4%B8%88%E7%A6%85%E5%B8%88&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=aab82cfc2e4b48b18315fe8b401286ea\">百丈禅师</a></div><div><p>唐代禅宗高僧</p></div></divrsv_re_ename\':\'百丈禅师\',\'rsv_re_uri\':\'aab82cfc2e4b48b18315fe8b401286ea\'}\"></div><divrsv_re_ename\':\'炎帝\',\'rsv_re_uri\':\'02158550aef449fabfc33105fd140149\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E7%82%8E%E5%B8%9D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=02158550aef449fabfc33105fd140149\"><img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3906743848,4010720171&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E7%82%8E%E5%B8%9D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=02158550aef449fabfc33105fd140149\"></a></div><div><a target=\"_blank\" title=\"炎帝\" href=\"https://www.baidu.com/s?wd=%E7%82%8E%E5%B8%9D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=02158550aef449fabfc33105fd140149\">炎帝</a></div><div><p>中国上古部落首领</p></div></divrsv_re_ename\':\'炎帝\',\'rsv_re_uri\':\'02158550aef449fabfc33105fd140149\'}\"></div><divrsv_re_ename\':\'良价\',\'rsv_re_uri\':\'4553b326a54d429e99dcc3309ca51b0f\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E8%89%AF%E4%BB%B7&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=4553b326a54d429e99dcc3309ca51b0f\"><img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2706430624,2407247422&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E8%89%AF%E4%BB%B7&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=4553b326a54d429e99dcc3309ca51b0f\"></a></div><div><a target=\"_blank\" title=\"良价\" href=\"https://www.baidu.com/s?wd=%E8%89%AF%E4%BB%B7&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=4553b326a54d429e99dcc3309ca51b0f\">良价</a></div><div><p>中国佛教曹洞宗开山祖</p></div></divrsv_re_ename\':\'良价\',\'rsv_re_uri\':\'4553b326a54d429e99dcc3309ca51b0f\'}\"></div></div><div><divrsv_re_ename\':\'曹植\',\'rsv_re_uri\':\'f6f0d41e926d4bd5a313b3f9a5f57df8\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E6%9B%B9%E6%A4%8D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=f6f0d41e926d4bd5a313b3f9a5f57df8\"><img src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2702713176,3174502170&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E6%9B%B9%E6%A4%8D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=f6f0d41e926d4bd5a313b3f9a5f57df8\"></a></div><div><a target=\"_blank\" title=\"曹植\" href=\"https://www.baidu.com/s?wd=%E6%9B%B9%E6%A4%8D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=f6f0d41e926d4bd5a313b3f9a5f57df8\">曹植</a></div><div><p>建安文学的代表人物</p></div></divrsv_re_ename\':\'曹植\',\'rsv_re_uri\':\'f6f0d41e926d4bd5a313b3f9a5f57df8\'}\"></div><divrsv_re_ename\':\'黄帝\',\'rsv_re_uri\':\'97724b87bbbc4154a2b7b2b9a53a1a84\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E9%BB%84%E5%B8%9D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=97724b87bbbc4154a2b7b2b9a53a1a84\"><img src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=33466947,2415730669&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E9%BB%84%E5%B8%9D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=97724b87bbbc4154a2b7b2b9a53a1a84\"></a></div><div><a target=\"_blank\" title=\"黄帝\" href=\"https://www.baidu.com/s?wd=%E9%BB%84%E5%B8%9D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=97724b87bbbc4154a2b7b2b9a53a1a84\">黄帝</a></div><div><p>古华夏部落联盟首领</p></div></divrsv_re_ename\':\'黄帝\',\'rsv_re_uri\':\'97724b87bbbc4154a2b7b2b9a53a1a84\'}\"></div><divrsv_re_ename\':\'怀海\',\'rsv_re_uri\':\'8744eaa2d359403d88b906658d27b5e5\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E6%80%80%E6%B5%B7&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=8744eaa2d359403d88b906658d27b5e5\"><img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1398710533,3621907800&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E6%80%80%E6%B5%B7&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=8744eaa2d359403d88b906658d27b5e5\"></a></div><div><a target=\"_blank\" title=\"怀海\" href=\"https://www.baidu.com/s?wd=%E6%80%80%E6%B5%B7&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=8744eaa2d359403d88b906658d27b5e5\">怀海</a></div><div><p>唐代著名的佛教改革家</p></div><divrsv_re_ename\':\'希运\',\'rsv_re_uri\':\'da598b78083d404fa05d5d7508b3fa4b\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E5%B8%8C%E8%BF%90&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=da598b78083d404fa05d5d7508b3fa4b\"><img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3804586471,2265541975&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E5%B8%8C%E8%BF%90&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=da598b78083d404fa05d5d7508b3fa4b\"></a></div><div><a target=\"_blank\" title=\"希运\" href=\"https://www.baidu.com/s?wd=%E5%B8%8C%E8%BF%90&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E5%8E%86%E5%8F%B2%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=da598b78083d404fa05d5d7508b3fa4b\">希运</a></div><div><p>在鹫峰山建寺弘法</p></div><div><a href=\"javascript:;\" onclick=\"return false;\" fm\':\'beha\'}\"=\"\">展开<i></i></a><span title=\"相关人物\">相关人物</span></div><div><div><divrsv_re_ename\':\'释证严\',\'rsv_re_uri\':\'bda2c2ccf16442b28925505cfcfe07d9\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E9%87%8A%E8%AF%81%E4%B8%A5&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=bda2c2ccf16442b28925505cfcfe07d9\"><img src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2403098376,252416829&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E9%87%8A%E8%AF%81%E4%B8%A5&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=bda2c2ccf16442b28925505cfcfe07d9\"></a></div><div><a target=\"_blank\" title=\"释证严\" href=\"https://www.baidu.com/s?wd=%E9%87%8A%E8%AF%81%E4%B8%A5&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=bda2c2ccf16442b28925505cfcfe07d9\">释证严</a></div><div><p>被称为台湾德蕾莎修女</p></div></divrsv_re_ename\':\'释证严\',\'rsv_re_uri\':\'bda2c2ccf16442b28925505cfcfe07d9\'}\"></div><divrsv_re_ename\':\'宽见法师\',\'rsv_re_uri\':\'6833cb6aa9274ec7849db5af7d089ca9\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E5%AE%BD%E8%A7%81%E6%B3%95%E5%B8%88&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=6833cb6aa9274ec7849db5af7d089ca9\"><img src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2681090511,2520716623&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E5%AE%BD%E8%A7%81%E6%B3%95%E5%B8%88&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=6833cb6aa9274ec7849db5af7d089ca9\"></a></div><div><a target=\"_blank\" title=\"宽见法师\" href=\"https://www.baidu.com/s?wd=%E5%AE%BD%E8%A7%81%E6%B3%95%E5%B8%88&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=6833cb6aa9274ec7849db5af7d089ca9\">宽见法师</a></div><div><p>安徽广德佛教协会会长</p></div></divrsv_re_ename\':\'宽见法师\',\'rsv_re_uri\':\'6833cb6aa9274ec7849db5af7d089ca9\'}\"></div><divrsv_re_ename\':\'禅师\',\'rsv_re_uri\':\'5cf5d27a6511453d8ad1403f4e52c540\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E7%A6%85%E5%B8%88&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=5cf5d27a6511453d8ad1403f4e52c540\"><img src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=666517770,20035503&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E7%A6%85%E5%B8%88&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=5cf5d27a6511453d8ad1403f4e52c540\"></a></div><div><a target=\"_blank\" title=\"禅师\" href=\"https://www.baidu.com/s?wd=%E7%A6%85%E5%B8%88&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=5cf5d27a6511453d8ad1403f4e52c540\">禅师</a></div><div><p>指和尚之尊称</p></div><divrsv_re_ename\':\'陈健民\',\'rsv_re_uri\':\'85c88ce3277849d99de1b5e28ca555bf\'}\"><div><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E9%99%88%E5%81%A5%E6%B0%91&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=85c88ce3277849d99de1b5e28ca555bf\"><img src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2794547847,932698097&amp;fm=58\"></a><a target=\"_blank\" href=\"https://www.baidu.com/s?wd=%E9%99%88%E5%81%A5%E6%B0%91&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=85c88ce3277849d99de1b5e28ca555bf\"></a></div><div><a target=\"_blank\" title=\"陈健民\" href=\"https://www.baidu.com/s?wd=%E9%99%88%E5%81%A5%E6%B0%91&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_recommends_merge_21102&amp;cq=%E7%A5%9E%E8%B5%9E&amp;srcid=28310&amp;rt=%E7%9B%B8%E5%85%B3%E4%BA%BA%E7%89%A9&amp;recid=21102&amp;euri=85c88ce3277849d99de1b5e28ca555bf\">陈健民</a></div><div><p>原美国佛教协会主席</p></div><div tpl=\"right_toplist\"><div><div><div title=\"搜索热点\"><divbeha\'}\"><i></i><i></i></divbeha\'}\"></div>搜索热点</div><table><thead><tr><th>排名</th><th>搜索指数</th></tr></thead><tbody><tr><td>1<a target=\"_blank\" title=\"高铁座霸真实身份\" href=\"https://www.baidu.com/s?wd=%E9%AB%98%E9%93%81%E5%BA%A7%E9%9C%B8%E7%9C%9F%E5%AE%9E%E8%BA%AB%E4%BB%BD&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_fyb_pchot_20811\">高铁座霸真实身份</a></td><td>449275<i></i></td></tr><tr><td>2<a target=\"_blank\" title=\"最美天路垃圾成山\" href=\"https://www.baidu.com/s?wd=%E6%9C%80%E7%BE%8E%E5%A4%A9%E8%B7%AF%E5%9E%83%E5%9C%BE%E6%88%90%E5%B1%B1&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_fyb_pchot_20811\">最美天路垃圾成山</a></td><td>403611<i></i></td></tr><tr><td>3<a target=\"_blank\" title=\"张歆艺怀孕\" href=\"https://www.baidu.com/s?wd=%E5%BC%A0%E6%AD%86%E8%89%BA%E6%80%80%E5%AD%95&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_fyb_pchot_20811\">张歆艺怀孕</a></td><td>385953<i></i></td></tr><tr><td>4<a target=\"_blank\" title=\"杭州小学万元校服\" href=\"https://www.baidu.com/s?wd=%E6%9D%AD%E5%B7%9E%E5%B0%8F%E5%AD%A6%E4%B8%87%E5%85%83%E6%A0%A1%E6%9C%8D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_fyb_pchot_20811\">杭州小学万元校服</a></td><td>381117<i></i></td></tr><tr><td>5<a target=\"_blank\" title=\"眼镜蛇钻老人被窝\" href=\"https://www.baidu.com/s?wd=%E7%9C%BC%E9%95%9C%E8%9B%87%E9%92%BB%E8%80%81%E4%BA%BA%E8%A2%AB%E7%AA%9D&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_fyb_pchot_20811\">眼镜蛇钻老人被窝</a></td><td>372794<i></i></td></tr><tr><td>6<a target=\"_blank\" title=\"吴亦凡获赔3万元\" href=\"https://www.baidu.com/s?wd=%E5%90%B4%E4%BA%A6%E5%87%A1%E8%8E%B7%E8%B5%943%E4%B8%87%E5%85%83&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_fyb_pchot_20811\">吴亦凡获赔3万元</a></td><td>365146<i></i></td></tr><tr><td>7<a target=\"_blank\" title=\"9岁女孩沉迷抖音\" href=\"https://www.baidu.com/s?wd=9%E5%B2%81%E5%A5%B3%E5%AD%A9%E6%B2%89%E8%BF%B7%E6%8A%96%E9%9F%B3&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_fyb_pchot_20811\">9岁女孩沉迷抖音</a></td><td>355698<i></i></td></tr><tr><td>8<a target=\"_blank\" title=\"拼多多双打行动\" href=\"https://www.baidu.com/s?wd=%E6%8B%BC%E5%A4%9A%E5%A4%9A%E5%8F%8C%E6%89%93%E8%A1%8C%E5%8A%A8&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_fyb_pchot_20811\">拼多多双打行动</a></td><td>345576<i></i></td></tr><tr><td>9<a target=\"_blank\" title=\"一出好戏否认抄袭\" href=\"https://www.baidu.com/s?wd=%E4%B8%80%E5%87%BA%E5%A5%BD%E6%88%8F%E5%90%A6%E8%AE%A4%E6%8A%84%E8%A2%AD&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_fyb_pchot_20811\">一出好戏否认抄袭</a></td><td>340514<i></i></td></tr><tr><td>10<a target=\"_blank\" title=\"社科院回应\" href=\"https://www.baidu.com/s?wd=%E7%A4%BE%E7%A7%91%E9%99%A2%E5%9B%9E%E5%BA%94&amp;rsv_idx=2&amp;tn=25017023_10_pg&amp;ie=utf-8&amp;rsv_cq=%E7%A5%9E%E8%B5%9E&amp;rsv_dl=0_right_fyb_pchot_20811\">社科院回应</a></td><td>316895<i></i></td></tr></tbody></table><div>来源：<a target=\"_blank\" href=\"http://www.baidu.com/link?url=q11c2B1n4nmX0Gq99YBfLyUDmBRsKcDQz9WFW2ljl4P2ckMbOHokGrXuVEJzjWIV\">百度风云榜</a>&nbsp;-&nbsp;<a target=\"_blank\" href=\"http://www.baidu.com/link?url=_xY1xohBp_TgQIlfbdGLVQEqXK9Nlj1WHeo4uX0n3U0s0y7EDnIKq7iFBtLY4kNrM9JWgDe9SMBMO53JjVnJHq\">实时热点</a></div></div></div><div><div id=\"m3012665277_canvas\"><div><div id=\"w-cyp4rv\"><div><div><h2><a title2=\"标题\" target=\"_blank\" href=\"http://bzclk.baidu.com/adrc.php?t=060a00c00f7Ul0D0Kn9f0jNfAsa9mM4U00000cq9vH300000TxuJlj.THLFtIHX0A3qnWRsnHTsnWPxnHKxTAT0T1d-PWuBPHcdP10snjTkrH7b0ZRqnbm3Pj63n1nkPW-KrHb3rHKAfWPjwbfYfYuDfbm3fWn0mHdL5iuVmv-b5HnsnHcvPWRzP1ThTZFEuA-b5Hcd0ARqpZwYTjCEQvR8mh7GuZR8mvqVQ1qzuyu-TWYknjc0mLFW5HfkP1c1&amp;tpl=tpl_10341_15816_1&amp;l=1054433679\">▶想在此推广您的产品吗?</a></h2><a title2=\"header描述\" target=\"_blank\" href=\"http://bzclk.baidu.com/adrc.php?t=060a00c00f7Ul0D0Kn9f0jNfAsa9mM4U00000FK9vH300000TxuJlj.THLFtIHX0A3qnWRsnHTsnWPxnHKxTAT0T1d-PWuBPHcdP10snjTkrH7b0ZRqnbm3Pj63n1nkPW-KrHb3rHKAfWPjwbfYfYuDfbm3fWn0mHdL5iuVmv-b5HnsnHcvPWRzP1ThTZFEuA-b5Hcd0ARqpZwYTjCEQvR8mh7GuZR8mvqVQ1qzuyu-TWYknjc0mLFW5Hc1n1Dz&amp;tpl=tpl_10341_15816_1&amp;l=1054433680\">咨询热线：400-800-8888</a><a title2=\"header底部连接\" target=\"_blank\" href=\"http://bzclk.baidu.com/adrc.php?t=060a00c00f7Ul0D0Kn9f0jNfAsa9mM4U00000F79vH300000TxuJlj.THLFtIHX0A3qnWRsnHTsnWPxnHKxTAT0T1d-PWuBPHcdP10snjTkrH7b0ZRqnbm3Pj63n1nkPW-KrHb3rHKAfWPjwbfYfYuDfbm3fWn0mHdL5iuVmv-b5HnsnHcvPWRzP1ThTZFEuA-b5Hcd0ARqpZwYTjCEQvR8mh7GuZR8mvqVQ1qzuyu-TWYknjc0mLFW5Hf1Pjbv&amp;tpl=tpl_10341_15816_1&amp;l=1054433681\">e.baidu.com</a></div></div></div></div></div></div></divrsv_re_ename\':\'陈健民\',\'rsv_re_uri\':\'85c88ce3277849d99de1b5e28ca555bf\'}\"></divrsv_re_ename\':\'禅师\',\'rsv_re_uri\':\'5cf5d27a6511453d8ad1403f4e52c540\'}\"></divrsv_re_ename\':\'希运\',\'rsv_re_uri\':\'da598b78083d404fa05d5d7508b3fa4b\'}\"></divrsv_re_ename\':\'怀海\',\'rsv_re_uri\':\'8744eaa2d359403d88b906658d27b5e5\'}\"></td></tr></tbody></table>', 'http://www.baidu.com', '1', '2018-08-24 00:00:00', '2018-08-23 16:04:42', '2018-08-23 16:21:06', '1030356221427150849');
INSERT INTO `position_advertise_tb` VALUES ('1034638450613518338', 'dsfsadf', '1', '0.00', '0.00', '广汽新能源GE3 530上市 腾讯车联覆盖新能源汽车领域', 'erter', 'https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=43814567,2716313864&fm=173&app=25&f=JPEG?w=640&h=427&s=B2D231C4422A954D00C8CD990300E09B', ' <p>8月27日，在百位准车主及全国超两百位媒体的见证下，“更长续航，因AI而来——广汽新能源GE3 530全国上市发布会”于北京奥林匹克塔举行。作为全球首款长续航AI纯电SUV，GE3 530在一场别开生面的“AI加持”发布会中震撼登场。新车分为乐享版、智享版、互联网悦享版（带AI系统）、互联网尊享版（带AI系统）共计4款车型，补贴后售价区间为12.98万元-15.58万元。凭借长续航、更智能、高安全三大产品优势，GE3 530在业内树立起纯电SUV新标准。</p><div><img src=\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=43814567,2716313864&amp;fm=173&amp;app=25&amp;f=JPEG?w=640&amp;h=427&amp;s=B2D231C4422A954D00C8CD990300E09B\"></div><div><img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=633118561,3468683169&amp;fm=173&amp;app=25&amp;f=JPEG?w=640&amp;h=377&amp;s=128B41A64E533CC404AB18D803001099\"></div><p>在发布会上，广汽新能源总经理古惠南表示，首次搭载广汽新能源AI系统的GE3 530的上市，充分体现了广汽新能源“更聪明的陪伴”的品牌口号和智能、情感、信赖的品牌内涵。腾讯车联网总经理钟学丹表示，“更聪明的陪伴”与腾讯作为中国互联网服务者，对人工智能、科技创新的理解和目标相一致，今后腾讯将持续携手广汽新能源把更多的AI创新体验和服务带给用户。</p><div><img src=\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1325103329,665140193&amp;fm=173&amp;app=25&amp;f=JPEG?w=640&amp;h=427&amp;s=7C83609C0E33BFD844524E810300A08F\"></div><p>【广汽新能源董事、总经理 古惠南】</p><p>车主专享终身免费大礼</p><p>发布会现场，广汽新能源副总经理肖勇公布了GE3 530的车主专享终身免费大礼——广汽新能源AI系统车型可享：（1）智能语音 、AI找桩、OTA无线升级等AI基础服务及流量终身免费；（2）实时导航、社交出行服务及流量终身免费；（3）QQ音乐及娱乐流量5年免费不限量。购GE3 530全系车型赠送充电桩、并免费安装，免购置税，免车船税，免费获得新能源牌照，无惧限行。按揭购车客户还可享3年0利率，置换购车即享5000元现金补贴。</p><div><img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=2232415492,3654888643&amp;fm=173&amp;app=25&amp;f=JPEG?w=640&amp;h=352&amp;s=BA1771840B920CCC30FB319B03003098\"></div><p>GE3 530更是目前唯一可享1.32倍国家最高补贴的“新政爆款第一车”。自6月11日启动全国预售以来，GE3 530销售势头非常火爆，截至上市当天，广汽新能源已收到GE3 530的新车订单超3000台。</p><p>全球首款长续航AI纯电SUV</p><p>GE3 530是纯电智联SUV GE3的长续航AI升级版本，凭借一直坚持的正向研发理念及业内领先的技术研发实力，广汽新能源将GE3 530的60km/h等速最长续航提升至530km，NEDC综合工况续航提升至410km，并拥有业界最高能量密度160Wh/kg的电池系统，电池容量提升至54.75kWh，百公里电耗同级最低仅为14.7kWh，优于国家标准值25%以上。车主可任性地来一场说走就走的城际畅享之旅，突破过往纯电动车只能作为市内通勤用车的局限。</p><p>作为一款基于纯电专属平台打造的新能源车，GE3 530的“三电”布局更合理，安全性更高，乘坐空间、储物空间也更充裕。广汽第三代智能温控系统及BMS精准控制的加持，可确保电池始终处于高效工作状态，并有效延长电池寿命。此外，GE3 530充分考虑到整车及电池系统的主被动安全设计，并通过严苛测试。</p><div><img src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2009921145,4112728922&amp;fm=173&amp;app=25&amp;f=JPEG?w=640&amp;h=427&amp;s=9223D1A619DBAAC6306B349E0300F08A\"></div><p>AI汽车时代正式来临</p><p>当前，中国汽车产业正朝着电动化、网联化、智能化、共享化的方向发展。随着智能技术的不断升级迭代，处在时代风口的新能源汽车及AI技术正经历一场科技融合与演变。GE3 530正是第一款搭载“更聪明AI”——广汽新能源AI 系统的纯电车型，进一步增强产品实力，满足年轻客户群体的用车诉求，正式宣告车载系统AI汽车时代的到来。</p><div><img src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=4104107025,3327617868&amp;fm=173&amp;app=25&amp;f=JPEG?w=640&amp;h=427&amp;s=5A054584064A16EC6A2EB88E03007099\"></div><p>【腾讯车联网总经理 钟学丹】</p><p>广汽新能源AI系统是广汽集团与腾讯公司深度战略合作的最新成果，拥有智能语音、实时导航、AI找桩、QQ音乐、社交出行等五大核心功能，堪称目前最懂生活的车载AI系统。其中，高精度语音识别技术可帮助驾驶者在双手不离开方向盘的安全前提下，快速准确完成语音点歌，语音控车、语音查询等功能，实现驾驶控车“动口不动手”；而纯电动车专属功能“AI找桩”的智能充电路线规划，帮助GE3 530车主更快更准找到最近的充电桩，彻底解决“里程焦虑”与“充电难”的新能源车用车痛点。另外，广汽新能源AI系统还配套带有陀螺仪的高精实时在线地图，并创业界先河提供双地图核心（腾讯地图与高德地图），实现全路况精准导航。全国超过12000个ETCP停车场无停顿、不等待免密微信支付，进出快人一步；超1700万最高品质曲库的QQ音乐首次车机应用，给车主与家人带来让人羡慕的“移动音乐厅”……广汽新能源AI系统丰富而强大的功能性带给GE3 530车主“更聪明的陪伴”。</p><div><img src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1834520055,594365264&amp;fm=173&amp;app=25&amp;f=JPEG?w=640&amp;h=427&amp;s=1A90498274028357C67A7D8D03006089\"></div><p>新能源明星品牌再添“爆款“</p><p>为了给新能源车消费者提供更便捷的出行服务，广汽新能源正加速打造“太阳、月亮、星星”的多层级渠道体系。今年初，广汽新能源已在一线城市完成25 hours体验中心的重点布局。自5月起又迎来新店开业“爆发期”，几乎每周都有全新的25hours体验中心或展示中心在全国各大城市开业，数量已超40家，为当地消费者带来“每天多一小时乐享时光”。</p><div><img src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2531032488,32311771&amp;fm=173&amp;app=25&amp;f=JPEG?w=640&amp;h=427&amp;s=30717186054024F686DE09030300B0DA\"></div><p>今年以来，凭借业内领先的产品、技术实力，广汽新能源实现了1-7月累计销售同比劲增7倍的佳绩，创业界奇迹。GE3连续斩获同级SUV“销冠”，更在6.18新能源购车节上成为“电商销冠”，实现线上线下同热销。另一款明星车型GS4 PHEV销量持续破千，跻身插电混动SUV三强，成为消费者的新主流选择。</p><div><img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3156750934,1235966358&amp;fm=173&amp;app=25&amp;f=JPEG?w=640&amp;h=427&amp;s=2241F1020B31B5DECE75291F0300C080\"></div><p>GE3 530的震撼上市，将进一步丰富广汽新能源的产品矩阵，在已有热销产品阵容的基础上再添新“爆款“，为消费者带来更高品质纯电出行生活的同时，也必将以更强的产品力吸引业界及消费者的关注，带动销售的持续性增长。返回腾讯网首页&gt;&gt;</p>', 'https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_1618841883325155401%22%7D&n_type=0&p_from=1', '1', '2018-08-22 00:00:00', '2018-08-29 11:06:44', '2018-08-29 11:06:44', '1032837843552444418');

-- ----------------------------
-- Table structure for `position_media_tb`
-- ----------------------------
DROP TABLE IF EXISTS `position_media_tb`;
CREATE TABLE `position_media_tb` (
  `position_media_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '位置媒体id',
  `media_name` varchar(255) DEFAULT NULL COMMENT '媒体名称',
  `funs` bigint(20) DEFAULT NULL COMMENT '粉丝数',
  `advertise_name` varchar(255) DEFAULT NULL COMMENT '广告名称',
  `position` tinyint(4) DEFAULT NULL COMMENT '广告位置,1头条，2二条，3三条，4四条，5五条，6六条',
  `consume_price` decimal(13,2) DEFAULT NULL COMMENT '广告金额',
  `quality_price` decimal(13,2) DEFAULT NULL COMMENT '质检扣款',
  `final_price` decimal(13,2) DEFAULT NULL COMMENT '广告收入',
  `url` varchar(255) DEFAULT NULL COMMENT '发布链接',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，1待接单，2已接单，3已拒单，4执行中，5已完成，6已取消',
  `pay_status` tinyint(4) DEFAULT NULL COMMENT '支付状态，1待支付，2已支付',
  `expect_date` datetime DEFAULT NULL COMMENT '预计发布时间',
  `final_date` datetime DEFAULT NULL COMMENT '实际发布时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `media_id` bigint(20) DEFAULT NULL COMMENT '媒体id,外键',
  `position_advertise_id` bigint(20) DEFAULT NULL COMMENT '位置广告id,外键',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id,外键',
  PRIMARY KEY (`position_media_id`),
  KEY `INDEX_POSITION` (`position`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE,
  KEY `INDEX_PAYSTATUS` (`pay_status`) USING BTREE,
  KEY `INDEX_MEDIAID` (`media_id`) USING BTREE,
  KEY `INDEX_POSITIONADVERTISEID` (`position_advertise_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1032553187825397762 DEFAULT CHARSET=utf8 COMMENT='位置媒体表';

-- ----------------------------
-- Records of position_media_tb
-- ----------------------------
INSERT INTO `position_media_tb` VALUES ('1032553187825397761', 'fhfgh', '25325', '撒打发', '4', '0.00', '0.00', '0.00', 'http://www.baidu.com', '2', '1', '2018-08-16 00:00:00', '2018-09-13 00:00:00', '2018-08-23 17:00:39', '2018-08-23 17:00:39', null, null, '1030344761938505729');

-- ----------------------------
-- Table structure for `read_advertise_tb`
-- ----------------------------
DROP TABLE IF EXISTS `read_advertise_tb`;
CREATE TABLE `read_advertise_tb` (
  `read_advertise_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '阅读广告id',
  `name` varchar(255) DEFAULT NULL COMMENT '广告名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '广告形式,1图文、2贴片',
  `unit_price` decimal(13,2) DEFAULT NULL COMMENT '广告单价',
  `total_price` decimal(13,2) DEFAULT NULL COMMENT '广告预算（总额）',
  `consume_price` decimal(13,2) DEFAULT NULL COMMENT '广告金额',
  `quality_price` decimal(13,2) DEFAULT NULL COMMENT '质检返款',
  `final_price` decimal(13,2) DEFAULT NULL COMMENT '实际消费',
  `guide_content` varchar(255) DEFAULT NULL COMMENT '引导文案',
  `guide_img` varchar(255) DEFAULT NULL COMMENT '广告图片（690* 420或500* 800,px）',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `top_img` varchar(255) DEFAULT NULL COMMENT '头条封面',
  `notop_img` varchar(255) DEFAULT NULL COMMENT '非头条封面',
  `content` longtext COMMENT '正文内容',
  `origin_url` varchar(255) DEFAULT NULL COMMENT '原文链接',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，1待支付、2待审核、3投放中、4暂停中、5已完成',
  `start_date` datetime DEFAULT NULL COMMENT '投放开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '投放结束时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id,外键',
  PRIMARY KEY (`read_advertise_id`),
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1034628644154605570 DEFAULT CHARSET=utf8 COMMENT='阅读广告表';

-- ----------------------------
-- Records of read_advertise_tb
-- ----------------------------
INSERT INTO `read_advertise_tb` VALUES ('1032189311208337410', '图文测试', '1', '34343.00', '343434.00', '0.00', '0.00', '0.00', null, null, '中国老年人口已超2.4亿，用机器人养老靠谱吗？', '澎湃新闻', 'http://localhost:8080/uploaderPath/img/20180822/1534927950155.jpg', 'http://localhost:8080/uploaderPath/img/20180822/1534927953669.jpg', '<p>中新网客户端北京8月22日电，中国60周岁及以上老年人口已经超过2.4亿，随着人口老龄化加重，养老压力不断增加，养老服务机器人也日渐兴起。然而，养老服务机器人真的靠谱吗？会有多少人愿意和机器人走完生命最后一程？</p><div><img width=\"500px\" src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2495397207,133806789&amp;fm=173&amp;app=25&amp;f=JPEG?w=500&amp;h=330&amp;s=072048A70FD338DC22693D2503007040\"></div><p>图为Pepper人形机器人带着老人进行午后锻炼。</p><p>养老服务机器人吸引眼球</p><p>来自民政部的数据显示，截至2017年年底，全国60岁以上老年人口达2.4亿，占总人口比重达17.3%，平均近4个劳动力赡养一位老人。</p><p>有需求就有市场，在年轻人赡养压力不断增加的当下，协助老年人生活的机器人应运而生。</p><p>近日，2018年世界机器人大会在北京举行，现场百余家机器人生产商在大会上展示了各个领域机器人研发的最新成果。其中，养老和机器人结合的“养老服务机器人”吸引了很多人的眼球。</p><div><img width=\"500px\" src=\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1068968528,1710847403&amp;fm=173&amp;app=25&amp;f=JPEG?w=500&amp;h=332&amp;s=6D104B9D27E360A25EF985DA0300C0B3\"></div><p>8月15日，2018世界机器人大会上160余家中外机器人领军企业带来的前沿产品集体亮相，让观众直接感受科技改变生活。</p><p>在大会现场，记者在一家养老服务机器人的展台，看到一款人形机器人在现场走动。</p><p>据了解，这款机器人目前的主要定位是养老服务，包括移动辅助、卫生保洁、行动助力、聊天提醒、安防保护、洗澡清洁、睡眠辅助、健康体检7大项30余小项功能，是适合在老年人家中和养老院使用的服务型机器人。</p><p>台湾大学讲座教授罗仁权在大会论坛上表示，“养老陪护服务型机器人”可以解决老人养护问题，未来有着巨大的市场，甚至未来人们一谈到“老人照护”，就会想到陪护机器人产品。</p><p>业内人士认为，未来机器人会在助老助残上大显身手，机器人产业如何为中国未来的养老提出解决方案至关重要。</p><p>养老机器人应用能力待提升</p><p>尽管养老服务机器人已具雏形，前景广阔，但从现实的应用情况来看，养老服务机器人的使用似乎还有更大的改进空间。</p><p>根据媒体报道，杭州市社会福利中心2016年曾引进一批智能养老机器人，名叫“阿铁”。它们的任务是监护沉痾病人，与老人聊天，提示他们准时吃药，以及给老人的家人打视频电话，等等。</p><div><img width=\"500px\" src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3281092426,3897584041&amp;fm=173&amp;app=25&amp;f=JPEG?w=500&amp;h=332&amp;s=96A241A59A5623D2588578BE0300E011\"></div><p>图为多种类型的机器人在养老院里分担了大部分人类的陪护工作，老人们很乐意与它们进行互动。</p><p>对于服务机器人的使用情况，杭州社会福利中心相关工作人员21日对中新网记者表示，目前机器人用得不多了，“阿铁”的使用范围仅限于提供一些娱乐功能，“唱唱歌什么的”，并表示中心与“阿铁”的生产商目前已经不再合作。</p><p>“在后期的使用过程中，我们发现这个机器人和我们的工作流程衔接不是很紧密。”该工作人员说。</p><p>媒体曾上传过一段视频，视频中，老人略带口音地说：“阿铁，我非常喜欢你！”，但“阿铁”的回答却是：“主人，请你说中国话。”</p><p>业内专家表示，目前国内很多服务机器人产品自主性不高，技术含量还应再加强，语音识别技术的准确度与运算速度都要提高。</p><p>多家养老服务机器人的生产商对记者表示，他们的产品大多都还在研发阶段，并未投入市场使用，“正在和一些医院和养老院谈”。</p><p>其中一位机器人生产商认为，未来机器人为了更大限度满足人类的需求，设计上应该从人机交互体验上越来越倾向于体察人类的情感，机器人也会有更拟人化的形象。</p><p>机器人养老，你咋看？</p><p>目前，对一款真正意义上的养老服务机器人，许多人既报以期待，又持以怀疑。</p><div><img width=\"500px\" src=\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=629368127,2463104209&amp;fm=173&amp;app=25&amp;f=JPEG?w=500&amp;h=383&amp;s=5400F9154F1246D2861CE9DA03005033\"></div><p>图为机器人大会现场。</p><p>海外留学归来的张晨，已经3年没跟父母一起过年了。张晨对中新网记者表示，“家里子女上班忙，没有时间照顾老人，养猫养狗能解决问题吗？而机器人现在可以做到辅助老年人日常生活。”</p><p>张晨现在某互联网企业做程序员。他认为，在目前这样的技术环境下，机器人服务的内容也有局限性，未来需要做到更智能、更人性化。</p><p>对于声称“可以照顾老人”的机器人，远离父母工作的独生女雯雯（化名）提出了自己的担忧：“机器人的发展还没有那么成熟，它有自己的盲区和局限性，如果在机器人视线范围之外，老人出现意外了怎么办？”</p><p>雯雯刚刚新婚，未来面临赡养两个家庭老人的情况。她表示，机器人目前还不能和人类进行感情交互，如果把本来就缺少陪伴的父母扔给一个无法交流的机器人，那会让情况更糟糕。</p><p>同样的，抖音知名博主金蛋曾转发过一个机器人陪老人走完最后一程的视频，播放量达到3000多万。</p><p>金蛋认为，虽然机器人养老是一种趋势，并且也可能是充满人性的，但是，“把机器人作为替代亲情的工具，很可悲的。”</p><p>显然，对于养老服务机器人，憧憬和担忧同在。</p><p>于是，问题来了，如果是你，你会选择和机器人度过生命最后一程吗？或者，你会选择让一个机器人陪家里的老人吗？</p><p>（原题为《中国老年人口已超2.4亿 用机器人养老靠谱吗？》）</p><p><br></p>', 'https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_7099607092427102645%22%7D&n_type=0&p_from=1', '1', '2018-08-22 00:00:00', '2022-09-15 00:00:00', '2018-08-22 16:54:44', '2018-08-23 11:49:44', '1030356221427150849');
INSERT INTO `read_advertise_tb` VALUES ('1034628644154605569', 'dsfsdadf', '1', '43443.00', '3546.00', '0.00', '345.00', '0.00', null, null, 'gdfgfd', 'dfgdfgdfg', 'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=3070534515,3326271226&fm=173&app=25&f=JPEG?w=480&h=320&s=8DE4D34E8C29B45792DA62BD0300C006', 'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=3070534515,3326271226&fm=173&app=25&f=JPEG?w=480&h=320&s=8DE4D34E8C29B45792DA62BD0300C006', ' <div>北京时间8月28日，2018年雅加达亚运会乒乓球比赛男团决赛，中国队3-0完胜韩国队实现七连冠。从1994年到2018年，中国男乒在亚运会男团决赛里连续七次战胜同一个对手韩国队，创造了亚运男团七连冠辉煌。<br></div><div><p>小组赛阶段，中国男队以4个3-0战胜小组对手朝鲜、马来西亚、尼泊尔、老挝获得小组第一。韩国队小组阶段同样是以不败战绩获得小组第一，其中与中国香港队的焦点之战韩国队是3-2胜出。男团八强战中国队3-0完胜中国香港，韩国队则3-2淘汰朝鲜队。男团半决赛中国队3-1击败中国台北，韩国队3-0胜印度。</p><p></p><div><img width=\"480px\" src=\"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=3070534515,3326271226&amp;fm=173&amp;app=25&amp;f=JPEG?w=480&amp;h=320&amp;s=8DE4D34E8C29B45792DA62BD0300C006\"></div><p></p><p>决赛里中国队的出场阵容没有变化，依旧是樊振东、林高远和王楚钦，王楚钦继续作为第三单打。韩国队派出了李尚洙、郑荣植和张宇镇，张宇镇作为第三单打。</p><p>林高远在首场比赛迎战李尚洙。开赛后林高远就积极上手抢攻，他迅速拉开比分领跑，他以两个11比3先胜两局。第三局林高远乘胜追击，迅速以9比1领先，此后李尚洙已无力回天，11比2，林高远再胜一局以3比0拿下。中国队以1比0领先。</p><div><img src=\"https://hiphotos.baidu.com/feed/pic/item/cf1b9d16fdfaaf51edd5d19d815494eef01f7ad9.jpg\"></div><p>第二场比赛在樊振东和郑荣植之间进行。首局开始后郑荣植拼得很凶，他在场上频频搏杀，双方比分紧紧咬住。10平后郑荣植凶搏连得两分，他以12比10先声夺人。从第二局开始樊振东更适应对手的凶搏，他迅速确立领跑位置，他抵挡住郑荣植的几波冲击以11比8拿下。第三局樊振东又是早早领先，他以10比7拿到局点。郑荣植追回1分，樊振东迅速还击以11比8再胜一局。</p><div><img src=\"https://hiphotos.baidu.com/feed/pic/item/a8014c086e061d95a471671a76f40ad162d9cad9.jpg\"></div><p>双方的比分在第四局又迅速拉开，8比4后樊振东先拿1分，之后他拉了一个擦边球，裁判最终把这一分判给了郑荣植。樊振东未受影响，他又得1分以10比5拿到赛点。郑荣植连救两个赛点，樊振东请求暂停，回到场上他反手暴冲斜线得分，11比7，他赢下第四局以3比1获胜。中国队把总比分改写为2比0。</p><div><img width=\"383px\" src=\"https://hiphotos.baidu.com/feed/pic/item/8601a18b87d6277f3a7b621a25381f30e924fca3.jpg\"></div><div><img width=\"383px\" src=\"https://hiphotos.baidu.com/feed/pic/item/a1ec08fa513d269799d8587f58fbb2fb4316d88d.jpg\"></div><p>第三场，王楚钦对阵张宇镇。首局两人都发挥出极高的水平，比分一直紧咬，战至11平后王楚钦抓住机会，连得2分以13-11险胜。第二局伊始，两人战至3平后王楚钦失误增多，张宇镇连续进攻得手，王楚钦一度5-8落后。连追2分后，王楚钦连续被张宇镇打中，再输3分7-11失利。第三局王楚钦在前半段一直被动的情况下，中局阶段连续取分，实现8-7的反超。8平后王楚钦连取2分，张宇镇追回1个局点后，王楚钦暂停。暂停后王楚钦把握住机会，以11-9胜出2-1领先。第四局王楚钦气势已起，开局连续得手以5-1领先。张宇镇竭力反攻，但王楚钦还是牢牢控制住局面，相持对拉不落任何下风，最终11-6结束战斗，3-1战胜张宇镇为中国队锁定胜局。</p><p>这样中国队就以3-0的总比分击败韩国队，实现自1994年以来的亚运男团七连冠。</p><p>中国女乒3-0朝鲜 收获4连冠前无古人</p><p>北京时间8月28日，2018年亚运会乒乓球项目结束女团决赛的争夺，最终中国女团没有任何悬念就3-0击败了朝鲜女队。朱雨玲、陈梦都是3-0击败对手，而王曼昱丢一局后顺利3-1拿下。至此从小组赛到决赛，中国女团全部3-0击败对手，实现了亚运会女团四连冠。</p><p>值得一提的是，在亚运会女团历史上，日本队实现过1次 三连冠，中国队实现过3次三连冠。这次中国女队夺冠后，首次实现了亚运会女团四连冠的奇迹，打破了此前没有队伍能连续四次夺冠的魔咒。</p><p>中国队在半决赛里3比0横扫韩国队，朝鲜队同样是3比0击败中国香港队。决赛里中国队沿用半决赛的阵容，依旧派出了朱雨玲、陈梦和王曼昱，王曼昱作为三单。朝鲜队则派出了金成伊、车孝心和金南海。</p><p>第一场：朱雨玲3-0金宋依</p><p>朱雨玲一上来就遭遇考验，双方战至9平后，朱雨玲还是率先抓住机会，接发球强拉兑现局点，首局11-9拿下。第二局比赛如出一辙，关键分上朱雨玲更为强势，金宋依削球打法却自身失误较多，朱雨玲11-8再赢一局。随后第三局的比赛彻底失去悬念，朱雨玲牢牢摸透对手的球路，没有给对手多少机会就11-6拿下，决赛3-0先拔头筹。</p><div><img width=\"328px\" src=\"https://hiphotos.baidu.com/feed/pic/item/d62a6059252dd42ac83f51890e3b5bb5c9eab8da.jpg\"></div><div><img width=\"328px\" src=\"https://hiphotos.baidu.com/feed/pic/item/d1160924ab18972bba3050e8ebcd7b899e510a8d.jpg\"></div><p>第二场：陈梦3-0车孝心</p><p>陈梦第二个上阵状态更出色，面对攻防都明显落后于自己的对手，陈梦首局比赛进入局中开始掌控比赛，11-6顺利拿下。随后一局陈梦依然掌控比赛主动，车孝心并未获得太多机会，陈梦11-8再赢一局。第三局比赛，车孝心孤注一掷全力一搏，陈梦遭遇考验，不过局点面前车孝心把握能力不佳，陈梦在12平的情况下连续发力，14-12拿下对手，总比分3-0再胜一场。</p><div><img width=\"328px\" src=\"https://hiphotos.baidu.com/feed/pic/item/8601a18b87d6277f3a11621a25381f30e924fc95.jpg\"></div><div><img width=\"328px\" src=\"https://hiphotos.baidu.com/feed/pic/item/6a63f6246b600c33507a8eea174c510fd9f9a195.jpg\"></div><p>第三场：王曼昱3-1金南海</p><p>相比于朱雨玲、陈梦开局都还算稳健，小将王曼昱出场后对朝鲜选手的特点似乎有些不适应，一度6-9落后，王曼昱此后依然被动，首局8-11告负。随后王曼昱展开反扑，适应对手的球路后，11-5拿下后将比分扳平。随后一局同样没有太多悬念，王曼昱进攻牢牢压制住对手，11-4轻松再赢一局。第四局，王曼昱在局中开始发力，比赛变成她掌控之中后，王曼昱11-7再赢一局，总比分3-1击败对手，中国队就此3-0成功夺冠。</p><div><img width=\"328px\" src=\"https://hiphotos.baidu.com/feed/pic/item/c995d143ad4bd11308c7e71057afa40f4bfb058d.jpg\"></div><p>第四场：陈梦VS金宋依（取消）</p><p>第五场：朱雨玲VS车孝心（取消）</p></div>', 'https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9225251411244910537%22%7D&n_type=0&p_from=1', '1', '2018-08-29 00:00:00', '2018-09-05 00:00:00', '2018-08-29 10:27:46', '2018-08-29 10:27:46', '1032837843552444418');

-- ----------------------------
-- Table structure for `read_media_tb`
-- ----------------------------
DROP TABLE IF EXISTS `read_media_tb`;
CREATE TABLE `read_media_tb` (
  `read_media_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '阅读媒体id',
  `name` varchar(255) DEFAULT NULL COMMENT '媒体名称',
  `funs` bigint(20) DEFAULT NULL COMMENT '粉丝数',
  `position` tinyint(4) DEFAULT NULL COMMENT '广告位置,1头条，2二条，3三条，4四条，5五条，6六条',
  `expect_reading_number` bigint(20) DEFAULT NULL COMMENT '预估阅读量',
  `final_reading_number` bigint(20) DEFAULT NULL COMMENT '实际阅读量',
  `unit_price` decimal(13,2) DEFAULT NULL COMMENT '广告单价',
  `consume_price` decimal(13,2) DEFAULT NULL COMMENT '广告金额',
  `quality_price` decimal(13,2) DEFAULT NULL COMMENT '质检返款',
  `final_price` decimal(13,2) DEFAULT NULL COMMENT '实际消费',
  `url` varchar(255) DEFAULT NULL COMMENT '发布链接',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，1待审核、2待发布、3发布中、4待结算、5已完成、6审核未通过、7已取消',
  `expect_date` datetime DEFAULT NULL COMMENT '预计发布时间',
  `final_date` datetime DEFAULT NULL COMMENT '实际发布时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `media_id` bigint(20) DEFAULT NULL COMMENT '媒体id,外键',
  `read_advertise_id` bigint(20) DEFAULT NULL COMMENT '阅读广告id,外键',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id,外键',
  PRIMARY KEY (`read_media_id`),
  KEY `INDEX_POSITION` (`position`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE,
  KEY `INDEX_MEDIAID` (`media_id`) USING BTREE,
  KEY `INDEX_READADVERTISEID` (`read_advertise_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1032510782363381763 DEFAULT CHARSET=utf8 COMMENT='阅读媒体表';

-- ----------------------------
-- Records of read_media_tb
-- ----------------------------
INSERT INTO `read_media_tb` VALUES ('1032510782363381762', '测试阅读媒体', '111333', '2', '123456', '43634634', '344.26', '0.00', '0.00', '0.00', 'http://www.baidu.com', '1', '2018-08-15 14:06:06', '2018-09-12 06:06:06', '2018-08-23 14:12:08', '2018-08-23 14:51:08', null, null, '1030344761938505729');

-- ----------------------------
-- Table structure for `role_permission_tb`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_tb`;
CREATE TABLE `role_permission_tb` (
  `role_permission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色权限id',
  `region` tinyint(4) DEFAULT NULL COMMENT '范围，1公共，2自身',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id,外键',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限id,外键',
  PRIMARY KEY (`role_permission_id`),
  UNIQUE KEY `UNIQUE_ROLEID_PERMISSIONID` (`role_id`,`permission_id`) USING BTREE,
  KEY `INDEX_REGION` (`region`) USING BTREE,
  KEY `INDEX_ROLEID` (`role_id`) USING BTREE,
  KEY `INDEX_PERMISSIONID` (`permission_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1034628169191620610 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission_tb
-- ----------------------------
INSERT INTO `role_permission_tb` VALUES ('1029303278296870914', '1', '1001', '2018-08-14 17:46:40', '1028957471207002113');
INSERT INTO `role_permission_tb` VALUES ('1029303278582083585', '1', '1001', '2018-08-14 17:46:40', '1028957471148281858');
INSERT INTO `role_permission_tb` VALUES ('1029303278988931074', '1', '1001', '2018-08-14 17:46:40', '1028957471114727425');
INSERT INTO `role_permission_tb` VALUES ('1029303279412555777', '1', '1001', '2018-08-14 17:46:40', '1028957471093755906');
INSERT INTO `role_permission_tb` VALUES ('1029303279831986178', '1', '1001', '2018-08-14 17:46:40', '1028957471035035650');
INSERT INTO `role_permission_tb` VALUES ('1029303280247222274', '1', '1001', '2018-08-14 17:46:40', '1028957470997286914');
INSERT INTO `role_permission_tb` VALUES ('1029303280670846977', '1', '1001', '2018-08-14 17:46:40', '1028957470972121089');
INSERT INTO `role_permission_tb` VALUES ('1029303281090277377', '1', '1001', '2018-08-14 17:46:40', '1028957470900817921');
INSERT INTO `role_permission_tb` VALUES ('1029303281509707778', '1', '1001', '2018-08-14 17:46:40', '1028957470863069186');
INSERT INTO `role_permission_tb` VALUES ('1029303281924943874', '1', '1001', '2018-08-14 17:46:41', '1028957470846291969');
INSERT INTO `role_permission_tb` VALUES ('1029303282344374273', '1', '1001', '2018-08-14 17:46:41', '1028957470787571714');
INSERT INTO `role_permission_tb` VALUES ('1029303282767998978', '1', '1001', '2018-08-14 17:46:41', '1028957470745628674');
INSERT INTO `role_permission_tb` VALUES ('1029303283183235074', '1', '1001', '2018-08-14 17:46:41', '1028957470628188161');
INSERT INTO `role_permission_tb` VALUES ('1029303283606859778', '1', '1001', '2018-08-14 17:46:41', '1028957470556884993');
INSERT INTO `role_permission_tb` VALUES ('1029303284026290177', '1', '1001', '2018-08-14 17:46:41', '1028957470514941954');
INSERT INTO `role_permission_tb` VALUES ('1029303284445720577', '1', '1001', '2018-08-14 17:46:41', '1028957470493970434');
INSERT INTO `role_permission_tb` VALUES ('1029303284860956674', '1', '1001', '2018-08-14 17:46:41', '1028957470422667266');
INSERT INTO `role_permission_tb` VALUES ('1029303285280387074', '1', '1001', '2018-08-14 17:46:41', '1028957470372335617');
INSERT INTO `role_permission_tb` VALUES ('1029303285699817474', '1', '1001', '2018-08-14 17:46:41', '1028957470351364097');
INSERT INTO `role_permission_tb` VALUES ('1029303286123442177', '1', '1001', '2018-08-14 17:46:42', '1028957470275866625');
INSERT INTO `role_permission_tb` VALUES ('1029303286538678273', '1', '1001', '2018-08-14 17:46:42', '1028957470229729282');
INSERT INTO `role_permission_tb` VALUES ('1029303286958108674', '1', '1001', '2018-08-14 17:46:42', '1028957470204563458');
INSERT INTO `role_permission_tb` VALUES ('1029303287381733378', '1', '1001', '2018-08-14 17:46:42', '1028957470137454593');
INSERT INTO `role_permission_tb` VALUES ('1029303287801163777', '1', '1001', '2018-08-14 17:46:42', '1028957470091317249');
INSERT INTO `role_permission_tb` VALUES ('1029303288224788482', '1', '1001', '2018-08-14 17:46:42', '1028957470070345729');
INSERT INTO `role_permission_tb` VALUES ('1029303288640024577', '1', '1001', '2018-08-14 17:46:42', '1028957469999042562');
INSERT INTO `role_permission_tb` VALUES ('1029303289063649282', '1', '1001', '2018-08-14 17:46:42', '1028957469961293825');
INSERT INTO `role_permission_tb` VALUES ('1029303289478885378', '1', '1001', '2018-08-14 17:46:42', '1028957469839659009');
INSERT INTO `role_permission_tb` VALUES ('1029303289902510081', '1', '1001', '2018-08-14 17:46:42', '1028957469768355842');
INSERT INTO `role_permission_tb` VALUES ('1029303290317746178', '1', '1001', '2018-08-14 17:46:43', '1028957469718024193');
INSERT INTO `role_permission_tb` VALUES ('1029303290737176577', '1', '1001', '2018-08-14 17:46:43', '1028957469638332418');
INSERT INTO `role_permission_tb` VALUES ('1029303291156606978', '1', '1001', '2018-08-14 17:46:43', '1028957469537669121');
INSERT INTO `role_permission_tb` VALUES ('1029303291580231682', '1', '1001', '2018-08-14 17:46:43', '1028957469424422913');
INSERT INTO `role_permission_tb` VALUES ('1029303291999662081', '1', '1001', '2018-08-14 17:46:43', '1027739905910321154');
INSERT INTO `role_permission_tb` VALUES ('1029303292410703873', '1', '1001', '2018-08-14 17:46:43', '1027739905776103427');
INSERT INTO `role_permission_tb` VALUES ('1029303292830134274', '1', '1001', '2018-08-14 17:46:43', '1027739905776103425');
INSERT INTO `role_permission_tb` VALUES ('1029303293262147585', '1', '1001', '2018-08-14 17:46:43', '1027739905755131905');
INSERT INTO `role_permission_tb` VALUES ('1029303293685772289', '1', '1001', '2018-08-14 17:46:43', '1027739905696411649');
INSERT INTO `role_permission_tb` VALUES ('1029303294088425474', '1', '1001', '2018-08-14 17:46:43', '1027739905658662913');
INSERT INTO `role_permission_tb` VALUES ('1029303294512050178', '1', '1001', '2018-08-14 17:46:44', '1027739905637691393');
INSERT INTO `role_permission_tb` VALUES ('1029303294931480578', '1', '1001', '2018-08-14 17:46:44', '1027739905566388225');
INSERT INTO `role_permission_tb` VALUES ('1029303295355105281', '1', '1001', '2018-08-14 17:46:44', '1027739905520250882');
INSERT INTO `role_permission_tb` VALUES ('1029303295766147073', '1', '1001', '2018-08-14 17:46:44', '1027739905495085058');
INSERT INTO `role_permission_tb` VALUES ('1029303296189771778', '1', '1001', '2018-08-14 17:46:44', '1027739905117597702');
INSERT INTO `role_permission_tb` VALUES ('1029303296613396482', '1', '1001', '2018-08-14 17:46:44', '1027739905117597700');
INSERT INTO `role_permission_tb` VALUES ('1029303297028632578', '1', '1001', '2018-08-14 17:46:44', '1027739905117597699');
INSERT INTO `role_permission_tb` VALUES ('1029303297452257282', '1', '1001', '2018-08-14 17:46:44', '1027739905109209089');
INSERT INTO `role_permission_tb` VALUES ('1029303297867493377', '1', '1001', '2018-08-14 17:46:44', '1027739904941436930');
INSERT INTO `role_permission_tb` VALUES ('1029303298286923777', '1', '1001', '2018-08-14 17:46:44', '1022441593598570498');
INSERT INTO `role_permission_tb` VALUES ('1029303298706354178', '1', '1001', '2018-08-14 17:46:45', '1022441593523073026');
INSERT INTO `role_permission_tb` VALUES ('1029303299125784578', '1', '1001', '2018-08-14 17:46:45', '1022441593481129986');
INSERT INTO `role_permission_tb` VALUES ('1029303299549409282', '1', '1001', '2018-08-14 17:46:45', '1022441593455964161');
INSERT INTO `role_permission_tb` VALUES ('1029303299973033985', '1', '1001', '2018-08-14 17:46:45', '1022441593359495171');
INSERT INTO `role_permission_tb` VALUES ('1029303300375687170', '1', '1001', '2018-08-14 17:46:45', '1022441593304969218');
INSERT INTO `role_permission_tb` VALUES ('1029303300795117569', '1', '1001', '2018-08-14 17:46:45', '1022441593258831874');
INSERT INTO `role_permission_tb` VALUES ('1029303301222936578', '1', '1001', '2018-08-14 17:46:45', '1022441593229471746');
INSERT INTO `role_permission_tb` VALUES ('1029303301650755585', '1', '1001', '2018-08-14 17:46:45', '1022441593153974273');
INSERT INTO `role_permission_tb` VALUES ('1029303302057603073', '1', '1001', '2018-08-14 17:46:45', '1022441593103642625');
INSERT INTO `role_permission_tb` VALUES ('1029303302485422081', '1', '1001', '2018-08-14 17:46:46', '1022441593032339460');
INSERT INTO `role_permission_tb` VALUES ('1029303302904852482', '1', '1001', '2018-08-14 17:46:46', '1022441593002979329');
INSERT INTO `role_permission_tb` VALUES ('1029303303315894273', '1', '1001', '2018-08-14 17:46:46', '1022441592952647681');
INSERT INTO `role_permission_tb` VALUES ('1029303303735324673', '1', '1001', '2018-08-14 17:46:46', '1022441592898121730');
INSERT INTO `role_permission_tb` VALUES ('1029303304163143681', '1', '1001', '2018-08-14 17:46:46', '1022441592898121729');
INSERT INTO `role_permission_tb` VALUES ('1029303304586768386', '1', '1001', '2018-08-14 17:46:46', '1022441592860372993');
INSERT INTO `role_permission_tb` VALUES ('1029303304997810178', '1', '1001', '2018-08-14 17:46:46', '1022441592503857154');
INSERT INTO `role_permission_tb` VALUES ('1029303305396269057', '1', '1001', '2018-08-14 17:46:46', '1022441592436748289');
INSERT INTO `role_permission_tb` VALUES ('1029303305840865282', '1', '1001', '2018-08-14 17:46:46', '1022441592394805250');
INSERT INTO `role_permission_tb` VALUES ('1029303306260295682', '1', '1001', '2018-08-14 17:46:46', '1022441592327696386');
INSERT INTO `role_permission_tb` VALUES ('1029303306679726081', '1', '1001', '2018-08-14 17:46:47', '1022441592273170434');
INSERT INTO `role_permission_tb` VALUES ('1029303307099156481', '1', '1001', '2018-08-14 17:46:47', '1022441592227033090');
INSERT INTO `role_permission_tb` VALUES ('1029303307522781186', '1', '1001', '2018-08-14 17:46:47', '1022441592143147010');
INSERT INTO `role_permission_tb` VALUES ('1029303307929628673', '1', '1001', '2018-08-14 17:46:47', '1022441592122175490');
INSERT INTO `role_permission_tb` VALUES ('1029303308382613506', '1', '1001', '2018-08-14 17:46:47', '1022441592092815361');
INSERT INTO `role_permission_tb` VALUES ('1029303308772683777', '1', '1001', '2018-08-14 17:46:47', '1022441592050872322');
INSERT INTO `role_permission_tb` VALUES ('1029303309192114177', '1', '1001', '2018-08-14 17:46:47', '1022441591853740034');
INSERT INTO `role_permission_tb` VALUES ('1029303309611544577', '1', '1001', '2018-08-14 17:46:47', '1022441591799214081');
INSERT INTO `role_permission_tb` VALUES ('1029303310030974978', '1', '1001', '2018-08-14 17:46:47', '1022441591719522306');
INSERT INTO `role_permission_tb` VALUES ('1030367018731663361', '1', '1001', '2018-08-17 16:13:35', '1030366929330073602');
INSERT INTO `role_permission_tb` VALUES ('1034623389731880961', '2', '1003', '2018-08-29 10:06:53', '1030366929330073602');
INSERT INTO `role_permission_tb` VALUES ('1034623390872731649', '2', '1003', '2018-08-29 10:06:53', '1028957471093755906');
INSERT INTO `role_permission_tb` VALUES ('1034623391728369665', '2', '1003', '2018-08-29 10:06:54', '1028957470997286914');
INSERT INTO `role_permission_tb` VALUES ('1034623393397702657', '2', '1003', '2018-08-29 10:06:54', '1028957470846291969');
INSERT INTO `role_permission_tb` VALUES ('1034623394232369154', '2', '1003', '2018-08-29 10:06:54', '1028957470745628674');
INSERT INTO `role_permission_tb` VALUES ('1034623405989003265', '2', '1003', '2018-08-29 10:06:57', '1027739905637691393');
INSERT INTO `role_permission_tb` VALUES ('1034623407230517249', '2', '1003', '2018-08-29 10:06:57', '1027739905495085058');
INSERT INTO `role_permission_tb` VALUES ('1034623408493002753', '2', '1003', '2018-08-29 10:06:58', '1027739905117597699');
INSERT INTO `role_permission_tb` VALUES ('1034623408912433154', '2', '1003', '2018-08-29 10:06:58', '1027739905109209089');
INSERT INTO `role_permission_tb` VALUES ('1034623409331863554', '2', '1003', '2018-08-29 10:06:58', '1027739904941436930');
INSERT INTO `role_permission_tb` VALUES ('1034623409755488258', '2', '1003', '2018-08-29 10:06:58', '1022441593598570498');
INSERT INTO `role_permission_tb` VALUES ('1034623411009585153', '2', '1003', '2018-08-29 10:06:58', '1022441593455964161');
INSERT INTO `role_permission_tb` VALUES ('1034623411429015554', '2', '1003', '2018-08-29 10:06:58', '1022441593359495171');
INSERT INTO `role_permission_tb` VALUES ('1034623411852640257', '2', '1003', '2018-08-29 10:06:58', '1022441593304969218');
INSERT INTO `role_permission_tb` VALUES ('1034623412267876353', '2', '1003', '2018-08-29 10:06:59', '1022441593258831874');
INSERT INTO `role_permission_tb` VALUES ('1034623415208083457', '2', '1003', '2018-08-29 10:06:59', '1022441592898121730');
INSERT INTO `role_permission_tb` VALUES ('1034623415627513857', '2', '1003', '2018-08-29 10:06:59', '1022441592898121729');
INSERT INTO `role_permission_tb` VALUES ('1034623416046944257', '2', '1003', '2018-08-29 10:06:59', '1022441592860372993');
INSERT INTO `role_permission_tb` VALUES ('1034623416462180353', '2', '1003', '2018-08-29 10:07:00', '1022441592503857154');
INSERT INTO `role_permission_tb` VALUES ('1034623416889999362', '2', '1003', '2018-08-29 10:07:00', '1022441592436748289');
INSERT INTO `role_permission_tb` VALUES ('1034623418999734274', '2', '1003', '2018-08-29 10:07:00', '1022441592143147010');
INSERT INTO `role_permission_tb` VALUES ('1034623419410776065', '2', '1003', '2018-08-29 10:07:00', '1022441592122175490');
INSERT INTO `role_permission_tb` VALUES ('1034623420249636866', '2', '1003', '2018-08-29 10:07:00', '1022441592050872322');
INSERT INTO `role_permission_tb` VALUES ('1034623421080109058', '2', '1003', '2018-08-29 10:07:01', '1022441591799214081');
INSERT INTO `role_permission_tb` VALUES ('1034625805206388737', '2', '1002', '2018-08-29 10:16:29', '1030366929330073602');
INSERT INTO `role_permission_tb` VALUES ('1034625826257600514', '2', '1002', '2018-08-29 10:16:34', '1028957471207002113');
INSERT INTO `role_permission_tb` VALUES ('1034625843806568450', '2', '1002', '2018-08-29 10:16:38', '1028957471114727425');
INSERT INTO `role_permission_tb` VALUES ('1034625879235854337', '2', '1002', '2018-08-29 10:16:47', '1028957471093755906');
INSERT INTO `role_permission_tb` VALUES ('1034625898840031233', '2', '1002', '2018-08-29 10:16:51', '1028957470997286914');
INSERT INTO `role_permission_tb` VALUES ('1034625917257220098', '2', '1002', '2018-08-29 10:16:56', '1028957470972121089');
INSERT INTO `role_permission_tb` VALUES ('1034625939273121793', '2', '1002', '2018-08-29 10:17:01', '1028957470863069186');
INSERT INTO `role_permission_tb` VALUES ('1034625980587016193', '2', '1002', '2018-08-29 10:17:11', '1028957470846291969');
INSERT INTO `role_permission_tb` VALUES ('1034626063072198657', '2', '1002', '2018-08-29 10:17:31', '1028957470745628674');
INSERT INTO `role_permission_tb` VALUES ('1034626150892535810', '2', '1002', '2018-08-29 10:17:51', '1028957470351364097');
INSERT INTO `role_permission_tb` VALUES ('1034627298181480450', '2', '1002', '2018-08-29 10:22:25', '1028957470229729282');
INSERT INTO `role_permission_tb` VALUES ('1034627351625302018', '2', '1002', '2018-08-29 10:22:38', '1028957470070345729');
INSERT INTO `role_permission_tb` VALUES ('1034627368503181313', '2', '1002', '2018-08-29 10:22:42', '1028957469961293825');
INSERT INTO `role_permission_tb` VALUES ('1034627492524556290', '2', '1002', '2018-08-29 10:23:11', '1027739905495085058');
INSERT INTO `role_permission_tb` VALUES ('1034627527710572545', '2', '1002', '2018-08-29 10:23:20', '1027739905117597699');
INSERT INTO `role_permission_tb` VALUES ('1034627543510515714', '2', '1002', '2018-08-29 10:23:23', '1027739905109209089');
INSERT INTO `role_permission_tb` VALUES ('1034627559654391809', '2', '1002', '2018-08-29 10:23:27', '1027739904941436930');
INSERT INTO `role_permission_tb` VALUES ('1034627595796709377', '2', '1002', '2018-08-29 10:23:36', '1022441593598570498');
INSERT INTO `role_permission_tb` VALUES ('1034627621293883393', '2', '1002', '2018-08-29 10:23:42', '1022441593455964161');
INSERT INTO `role_permission_tb` VALUES ('1034627642152157185', '2', '1002', '2018-08-29 10:23:47', '1022441593359495171');
INSERT INTO `role_permission_tb` VALUES ('1034627654714097665', '2', '1002', '2018-08-29 10:23:50', '1022441593304969218');
INSERT INTO `role_permission_tb` VALUES ('1034627668194590722', '2', '1002', '2018-08-29 10:23:53', '1022441593258831874');
INSERT INTO `role_permission_tb` VALUES ('1034627695835054081', '2', '1002', '2018-08-29 10:24:00', '1022441592898121730');
INSERT INTO `role_permission_tb` VALUES ('1034627732287750145', '2', '1002', '2018-08-29 10:24:08', '1022441592898121729');
INSERT INTO `role_permission_tb` VALUES ('1034627747584376834', '2', '1002', '2018-08-29 10:24:12', '1022441592860372993');
INSERT INTO `role_permission_tb` VALUES ('1034628046776664066', '2', '1002', '2018-08-29 10:25:23', '1022441592503857154');
INSERT INTO `role_permission_tb` VALUES ('1034628064237551618', '2', '1002', '2018-08-29 10:25:28', '1022441592436748289');
INSERT INTO `role_permission_tb` VALUES ('1034628093929029633', '2', '1002', '2018-08-29 10:25:35', '1022441592143147010');
INSERT INTO `role_permission_tb` VALUES ('1034628112216195073', '2', '1002', '2018-08-29 10:25:39', '1022441592122175490');
INSERT INTO `role_permission_tb` VALUES ('1034628139298816001', '2', '1002', '2018-08-29 10:25:46', '1022441592050872322');
INSERT INTO `role_permission_tb` VALUES ('1034628169191620609', '2', '1002', '2018-08-29 10:25:53', '1022441591799214081');

-- ----------------------------
-- Table structure for `role_tb`
-- ----------------------------
DROP TABLE IF EXISTS `role_tb`;
CREATE TABLE `role_tb` (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `duty` varchar(255) DEFAULT NULL COMMENT '角色职责',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role_tb
-- ----------------------------
INSERT INTO `role_tb` VALUES ('1000', '超级管理员', '超级管理员', '2018-07-26 18:35:48');
INSERT INTO `role_tb` VALUES ('1001', '普通管理员', '普通管理员', '2018-07-26 18:35:48');
INSERT INTO `role_tb` VALUES ('1002', '媒体主', '媒体主', '2018-07-26 18:35:48');
INSERT INTO `role_tb` VALUES ('1003', '广告主', '广告主', '2018-07-26 18:35:48');
INSERT INTO `role_tb` VALUES ('1004', '用户', '用户', '2018-07-26 18:35:48');

-- ----------------------------
-- Table structure for `schedule_job_tb`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_tb`;
CREATE TABLE `schedule_job_tb` (
  `schedule_job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '计划任务id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态,PAUSED暂停，NORMAL正常',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `description` varchar(255) DEFAULT NULL COMMENT '描述 ',
  `job_id` bigint(20) DEFAULT NULL COMMENT '任务Id ',
  `type` tinyint(4) DEFAULT NULL COMMENT '任务类型1.文章推送2.书籍推荐',
  `method_name` varchar(255) DEFAULT NULL COMMENT '任务调用的方法名',
  PRIMARY KEY (`schedule_job_id`),
  KEY `INDEX_JOBID` (`job_id`) USING BTREE,
  KEY `INDEX_TYPE` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='计划任务表';

-- ----------------------------
-- Records of schedule_job_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `third_info_tb`
-- ----------------------------
DROP TABLE IF EXISTS `third_info_tb`;
CREATE TABLE `third_info_tb` (
  `third_info_id` bigint(20) NOT NULL COMMENT '第三方信息id',
  `wx_openid` longtext COMMENT '微信openid，多个，格式[{subscriptionId:''1004908098840150018'',openid:''sdfsdfdsf2fd''}]',
  `wx_uuid` varchar(255) DEFAULT NULL COMMENT '微信uuid',
  `wechat` varchar(255) DEFAULT NULL COMMENT '微信号',
  `qq` varchar(255) DEFAULT NULL COMMENT 'qq号',
  `microblog` varchar(255) DEFAULT NULL COMMENT '微博号',
  `alipay` varchar(255) DEFAULT NULL COMMENT '支付宝号',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id外键',
  PRIMARY KEY (`third_info_id`),
  KEY `INDEX_WXUUID` (`wx_uuid`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方信息表';

-- ----------------------------
-- Records of third_info_tb
-- ----------------------------
INSERT INTO `third_info_tb` VALUES ('1000', null, null, null, null, null, null, '2018-07-26 18:35:48', '2018-07-26 18:35:48', '1000');
INSERT INTO `third_info_tb` VALUES ('1029655983955537922', null, null, null, null, null, null, '2018-08-15 17:08:11', '2018-08-15 17:08:11', '1029655983884234753');
INSERT INTO `third_info_tb` VALUES ('1030344761984643074', null, null, null, null, null, null, '2018-08-17 14:45:09', '2018-08-17 14:45:09', '1030344761938505729');
INSERT INTO `third_info_tb` VALUES ('1030356221439733762', null, null, null, null, null, null, '2018-08-17 15:30:41', '2018-08-17 15:30:41', '1030356221427150849');
INSERT INTO `third_info_tb` VALUES ('1032837470464909314', null, null, null, null, null, null, '2018-08-24 11:50:17', '2018-08-24 11:50:17', '1032837470389411842');
INSERT INTO `third_info_tb` VALUES ('1032837843552444421', null, null, null, null, null, null, '2018-08-24 11:51:46', '2018-08-24 11:51:46', '1032837843552444418');
