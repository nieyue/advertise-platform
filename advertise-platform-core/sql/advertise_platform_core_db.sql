# 数据库 
#创建数据库
DROP DATABASE IF EXISTS advertise_platform_core_db;
CREATE DATABASE advertise_platform_core_db;

#使用数据库 
use advertise_platform_core_db;

#创建计划任务表
CREATE TABLE schedule_job_tb(
schedule_job_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '计划任务id',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
job_name varchar(255) COMMENT '任务名称',
job_group varchar(255) COMMENT '任务分组',
job_status varchar(255) COMMENT '任务状态,PAUSED暂停，NORMAL正常',
cron_expression varchar(255) COMMENT 'cron表达式',
description varchar(255) COMMENT '描述',
job_id bigint(20) COMMENT '任务Id ',
type tinyint(4) COMMENT '任务类型1.系统通知',
method_name varchar(255) COMMENT '任务调用的方法名',
PRIMARY KEY (schedule_job_id),
INDEX INDEX_JOBID (job_id) USING BTREE,
INDEX INDEX_TYPE (type) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='计划任务表';

#创建角色表
CREATE TABLE role_tb(
role_id bigint(20) NOT NULL  COMMENT '角色id',
name varchar(255) COMMENT '角色名',
duty varchar(255) COMMENT '角色职责',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (role_id)
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='角色表';

#创建权限表
CREATE TABLE permission_tb(
permission_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
type tinyint(4) COMMENT '权限类型，默认0开放，1鉴权',
manager_name varchar(255) COMMENT '权限管理名',
name varchar(255) COMMENT '权限名',
route varchar(255) unique COMMENT '权限路由',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (permission_id),
INDEX INDEX_TYPE (type) USING BTREE,
INDEX INDEX_MANAGERNAME (manager_name) USING BTREE,
INDEX INDEX_NAME (name) USING BTREE,
INDEX INDEX_ROUTE (route) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='权限表';

#创建角色权限表
CREATE TABLE role_permission_tb(
role_permission_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色权限id',
region tinyint(4) COMMENT '范围，1公共，2自身',
role_id int(11) COMMENT '角色id,外键',
update_date datetime COMMENT '更新时间',
permission_id bigint(20) COMMENT '权限id,外键',
PRIMARY KEY (role_permission_id),
UNIQUE KEY UNIQUE_ROLEID_PERMISSIONID (role_id,permission_id),
INDEX INDEX_REGION (region) USING BTREE,
INDEX INDEX_ROLEID (role_id) USING BTREE,
INDEX INDEX_PERMISSIONID (permission_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='角色权限表';

#创建账户表 
CREATE TABLE account_tb(
account_id bigint(20) NOT NULL  COMMENT '账户id',
phone varchar(255) COMMENT '注册手机号',
password varchar(255) COMMENT '密码',
nickname varchar(255) COMMENT '昵称',
icon varchar(255) COMMENT '图像',
sex tinyint(4) DEFAULT 0 COMMENT '性别,默认为0未知，为1男性，为2女性',
country varchar(255) COMMENT '国家,默认中国',
realname varchar(255) COMMENT '真实姓名',
email varchar(255) COMMENT 'email',
safety_grade tinyint(4) COMMENT '安全等级，1低，2中，3高',
auth tinyint(4) COMMENT '认证，0没认证，1审核中，2已认证',
identity_cards varchar(255) COMMENT '身份证',
identity_cards_hold_img varchar(255) COMMENT '手持身份证上半身照',
identity_cards_front_img varchar(255) COMMENT '身份证正面',
identity_cards_back_img varchar(255) COMMENT '身份证反面',
telephone varchar(20) COMMENT '电话',
create_date datetime COMMENT '创建时间',
login_date datetime COMMENT '登陆时间',
status tinyint DEFAULT 0 COMMENT '状态，默认0正常，1封禁，2异常',
role_id bigint(20) COMMENT '角色id外键',
PRIMARY KEY (account_id),
INDEX INDEX_AUTH (auth) USING BTREE,
INDEX INDEX_PHONE (phone) USING BTREE,
INDEX INDEX_REALNAME (realname) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_LOGINDATE (login_date) USING BTREE,
INDEX INDEX_ROLEID (role_id) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='账户表';

#创建财务表
CREATE TABLE finance_tb(
  finance_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '财务id',
  password varchar(255) COMMENT '安全密码',
  money decimal(13,2) DEFAULT 0 COMMENT '余额',
  recharge decimal(13,2) DEFAULT 0 COMMENT '充值金额',
  consume decimal(13,2) DEFAULT 0 COMMENT '消费金额',
  income decimal(13,2) DEFAULT 0 COMMENT '收入金额',
  withdrawals decimal(13,2) DEFAULT 0 COMMENT '提现金额',
  refund decimal(13,2) DEFAULT 0 COMMENT '退款金额',
  frozen decimal(13,2) DEFAULT 0 COMMENT '冻结金额',
  base_profit decimal(13,2) DEFAULT 0 COMMENT '赠送金钱',
  account_id bigint(20) COMMENT '账户id外键',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  PRIMARY KEY (finance_id),
  INDEX INDEX_MONEY (money) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='财务表';

#创建财务记录表
CREATE TABLE finance_record_tb(
  finance_record_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '财务记录id',
  method tinyint(4) COMMENT '方式，1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额',
  type tinyint(4) COMMENT '类型，1充值、2消费，3退款，4冻结，5解冻，6收入，7提现',
  transaction_number varchar(255) COMMENT '交易单号',
  advertise_name varchar(255) COMMENT '广告名称',
  media_name varchar(255) COMMENT '媒体名称',
  money decimal(13,2) DEFAULT 0 COMMENT '金额',
  tax decimal(13,2) DEFAULT 0 COMMENT '税金',
  brokerage decimal(13,2) DEFAULT 0 COMMENT '平台服务费',
  real_money decimal(13,2) DEFAULT 0 COMMENT '实际金额',
  status tinyint(4) COMMENT '状态，默认1待处理，2成功，3已拒绝',
  create_date datetime   COMMENT '创建时间',
  update_date datetime   COMMENT '更新时间',
  advertise_id bigint(20) COMMENT '广告id,外键',
  account_id bigint(20) COMMENT '账户id,外键',
  PRIMARY KEY (finance_record_id),
  INDEX INDEX_ADVERTISEID (advertise_id) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
  INDEX INDEX_STATUS (status) USING BTREE,
  INDEX INDEX_METHOD (method) USING BTREE,
  INDEX INDEX_TYPE (type) USING BTREE,
  INDEX INDEX_TRANSACTIONNUMBER (transaction_number) USING BTREE,
  INDEX INDEX_CREATEDATE (create_date) USING BTREE,
  INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='财务记录表';

#创建银行卡表
CREATE TABLE bank_card_tb(
  bank_card_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '银行卡id',
  realname varchar(255) COMMENT '姓名',
  identity varchar(255) COMMENT '身份证',
  bank_name varchar(255) COMMENT '银行名',
  number varchar(255) COMMENT '银行卡卡号',
  phone varchar(255) COMMENT '预留手机号',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  account_id bigint(20) COMMENT '账户id',
  PRIMARY KEY (bank_card_id),
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='银行卡表';

#创建公司表
CREATE TABLE company_tb(
  company_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公司id',
  name varchar(255) COMMENT '名称',
  industry varchar(255) COMMENT '行业',
  address varchar(255) COMMENT '公司地址',
  website varchar(255) COMMENT '公司网址',
  description varchar(800) COMMENT '公司描述',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  account_id bigint(20) COMMENT '账户id',
  PRIMARY KEY (company_id),
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='公司表';

#创建第三方信息表
CREATE TABLE third_info_tb(
third_info_id bigint(20) NOT NULL  COMMENT '第三方信息id',
wx_uuid varchar(255) COMMENT '微信uuid',
wechat varchar(255) COMMENT '微信号',
qq varchar(255) COMMENT 'qq号',
microblog varchar(255) COMMENT '微博号',
alipay varchar(255) COMMENT '支付宝号',
wx_openid longtext COMMENT '微信openid，多个，格式[{subscriptionId:\'1004908098840150018\',openid:\'sdfsdfdsf2fd\'}]',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
account_id bigint(20) COMMENT '账户id外键',
PRIMARY KEY (third_info_id),
INDEX INDEX_WXUUID (wx_uuid) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='第三方信息表';

#创建通知表
CREATE TABLE notice_tb(
  notice_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  region tinyint(4) COMMENT '范围，1全局，2个人',
  type tinyint(4) COMMENT '类型，1系统消息，2充值，3退款，4提现',
  is_read tinyint(4) COMMENT '是否阅读，默认1未读，2已读',
  title varchar(255) COMMENT '标题',
  img_address varchar(255) COMMENT '图片地址',
  content longtext COMMENT '内容',
  status tinyint(4) COMMENT '状态，默认为0正常，1审核中，2申请成功，3申请失败',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  account_id bigint(20) COMMENT '账户id',
  PRIMARY KEY (notice_id),
  INDEX INDEX_REGION (region) USING BTREE,
  INDEX INDEX_TYPE (type) USING BTREE,
  INDEX INDEX_ISREAD (is_read) USING BTREE,
  INDEX INDEX_STATUS (status) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='通知表';

#创建配置表
CREATE TABLE config_tb(
  config_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '配置id',
  platform_name varchar(255)  COMMENT '平台开户名称',
  bank_name varchar(255)  COMMENT '平台开户银行',
  bank_account varchar(255)  COMMENT '平台银行账户',
  service_phone varchar(255)  COMMENT '平台联系电话',
  service_qq varchar(255)  COMMENT '平台联系qq',
  service_proportion decimal(11,2) DEFAULT 0  COMMENT '平台服务费率，单位%',
  tax_rate decimal(11,2) DEFAULT 0  COMMENT '税率，单位%',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  PRIMARY KEY (config_id)
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='配置表';

#创建汇总表
CREATE TABLE collect_tb(
  collect_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '汇总id',
  wait_release int(11)  COMMENT '待发布',
  released int(11)  COMMENT '发布中',
  completed int(11)  COMMENT '已完成',
  cancelled int(11)  COMMENT '已取消',
  settled int(11)  COMMENT '已结算',
  funs bigint(20)  COMMENT '覆盖粉丝数',
  reading_number bigint(20)  COMMENT '实际阅读量',
  consume decimal(13,2) DEFAULT 0  COMMENT '实际消费',
  income decimal(13,2) DEFAULT 0  COMMENT '实际收入',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  account_id bigint(20) COMMENT '账户id,外键',
  PRIMARY KEY (collect_id),
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='汇总表';

#创建媒体类型表
CREATE TABLE media_cate_tb(
  media_cate_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '媒体类型id',
  name varchar(255)  COMMENT '名称',
  summary varchar(255)  COMMENT '描述',
  update_date datetime COMMENT '更新时间',
  PRIMARY KEY (media_cate_id)
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='媒体类型表';

#创建阅读媒体表
CREATE TABLE media_tb(
  media_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '媒体id',
  name varchar(255)  COMMENT '媒体名称',
  account_name varchar(255)  COMMENT '媒体账号',
  wechat_img varchar(255) COMMENT '微信头像',
  qr_code varchar(255) COMMENT '二维码',
  funs bigint(20)  COMMENT '粉丝数',
  funs_img varchar(255) COMMENT '粉丝截图',
  user_img varchar(255) COMMENT '用户属性',
  reading_number bigint(20)  COMMENT '阅读量',
  sex_man_scale decimal(11,2)  COMMENT '性别男比例,单位%',
  sex_woman_scale decimal(11,2)  COMMENT '性别女比例,单位%',
  funs_top1_address varchar(255)  COMMENT '粉丝top1地址',
  funs_top2_address varchar(255)  COMMENT '粉丝top2地址',
  funs_top3_address varchar(255)  COMMENT '粉丝top3地址',
  auth tinyint(4)  COMMENT '授权,1未授权，2已授权',
  take_order tinyint(4)  COMMENT '是否接单,1是，2否',
  top1_price decimal(13,2)  COMMENT '多图文头条报价',
  top2_price decimal(13,2)  COMMENT '多图文二条报价',
  top3n_price decimal(13,2)  COMMENT '多图文3-N条报价',
  single_price decimal(13,2)  COMMENT '单图文报价',
  status tinyint(4) COMMENT '状态，1审核中、2正常、3审核未通过',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  media_cate_id bigint(20) COMMENT '媒体类型id,外键',
  account_id bigint(20) COMMENT '账户id,外键',
  PRIMARY KEY (media_id),
  INDEX INDEX_READINGNUMBER (reading_number) USING BTREE,
  INDEX INDEX_SEXMANSCALE (sex_man_scale) USING BTREE,
  INDEX INDEX_SEXWOMANSCALE (sex_woman_scale) USING BTREE,
  INDEX INDEX_FUNSTOP1ADDRESS (funs_top1_address) USING BTREE,
  INDEX INDEX_FUNSTOP2ADDRESS (funs_top2_address) USING BTREE,
  INDEX INDEX_FUNSTOP3ADDRESS (funs_top3_address) USING BTREE,
  INDEX INDEX_AUTH (auth) USING BTREE,
  INDEX INDEX_TAKEORDER (take_order) USING BTREE,
  INDEX INDEX_STATUS (status) USING BTREE,
  INDEX INDEX_MEDIACATEID (media_cate_id) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='媒体表';

#创建媒体收藏表
CREATE TABLE media_collection_tb(
  media_collection_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '媒体收藏id',
  update_date datetime COMMENT '更新时间',
  media_id bigint(20) COMMENT '媒体id,外键',
  account_id bigint(20) COMMENT '账户id,外键',
  PRIMARY KEY (media_collection_id),
  INDEX INDEX_MEDIAID (media_id) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='媒体收藏表';

#创建阅读广告表
CREATE TABLE read_advertise_tb(
  read_advertise_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '阅读广告id',
  name varchar(255)  COMMENT '广告名称',
  type tinyint(4)  COMMENT '广告形式,1图文、2贴片',
  unit_price decimal(13,2)  COMMENT '广告单价',
  total_price decimal(13,2)  COMMENT '广告预算（总额）',
  consume_price decimal(13,2)  COMMENT '广告金额',
  quality_price decimal(13,2)  COMMENT '质检返款',
  final_price decimal(13,2)  COMMENT '实际消费',
  guide_content varchar(255)  COMMENT '引导文案',
  guide_img varchar(255)  COMMENT '广告图片（690* 420或500* 800,px）',
  title varchar(255)  COMMENT '标题',
  author varchar(255)  COMMENT '作者',
  top_img varchar(255)  COMMENT '头条封面',
  notop_img varchar(255)  COMMENT '非头条封面',
  content longtext  COMMENT '正文内容',
  origin_url varchar(255)  COMMENT '原文链接',
  status tinyint(4) COMMENT '状态，1待支付、2待审核、3投放中、4暂停中、5已完成',
  start_date datetime COMMENT '投放开始时间',
  end_date datetime COMMENT '投放结束时间',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  account_id bigint(20) COMMENT '账户id,外键',
  PRIMARY KEY (read_advertise_id),
  INDEX INDEX_TYPE (type) USING BTREE,
  INDEX INDEX_STATUS (status) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='阅读广告表';

#创建阅读媒体表
CREATE TABLE read_media_tb(
  read_media_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '阅读媒体id',
  name varchar(255)  COMMENT '媒体名称',
  funs bigint(20)  COMMENT '粉丝数',
  position tinyint(4)  COMMENT '广告位置,1头条，2二条，3三条，4四条，5五条，6六条',
  expect_reading_number bigint(20)  COMMENT '预估阅读量',
  final_reading_number bigint(20)  COMMENT '实际阅读量',
  unit_price decimal(13,2)  COMMENT '广告单价',
  consume_price decimal(13,2)  COMMENT '广告金额',
  quality_price decimal(13,2)  COMMENT '质检返款',
  final_price decimal(13,2)  COMMENT '实际消费',
  url varchar(255)  COMMENT '发布链接',
  status tinyint(4) COMMENT '状态，1待审核、2待发布、3发布中、4待结算、5已完成、6审核未通过、7已取消',
  expect_date datetime COMMENT '预计发布时间',
  final_date datetime COMMENT '实际发布时间',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  media_id bigint(20) COMMENT '媒体id,外键',
  read_advertise_id bigint(20) COMMENT '阅读广告id,外键',
  account_id bigint(20) COMMENT '账户id,外键',
  PRIMARY KEY (read_media_id),
  INDEX INDEX_POSITION (position) USING BTREE,
  INDEX INDEX_STATUS (status) USING BTREE,
  INDEX INDEX_MEDIAID (media_id) USING BTREE,
  INDEX INDEX_READADVERTISEID (read_advertise_id) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='阅读媒体表';

#创建位置广告表
CREATE TABLE position_advertise_tb(
  position_advertise_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '位置广告id',
  name varchar(255)  COMMENT '广告名称',
  position tinyint(4)  COMMENT '广告位置,1多图文头条，2多图文二条，3多图文3-N条，4单图文',
  consume_price decimal(13,2)  COMMENT '广告金额',
  final_price decimal(13,2)  COMMENT '实际金额',
  title varchar(255)  COMMENT '标题',
  author varchar(255)  COMMENT '作者',
  top_img varchar(255)  COMMENT '封面',
  content longtext  COMMENT '正文内容',
  origin_url varchar(255)  COMMENT '原文链接',
  status tinyint(4) COMMENT '状态，1待支付、2待审核、3投放中、4暂停中、5已完成',
  start_date datetime COMMENT '投放开始时间',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  account_id bigint(20) COMMENT '账户id,外键',
  PRIMARY KEY (position_advertise_id),
  INDEX INDEX_POSITION (position) USING BTREE,
  INDEX INDEX_STATUS (status) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='位置广告表';

#创建位置媒体表
CREATE TABLE position_media_tb(
  position_media_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '位置媒体id',
  media_name varchar(255)  COMMENT '媒体名称',
  funs bigint(20)  COMMENT '粉丝数',
  advertise_name varchar(255) COMMENT '广告名称',
  position tinyint(4)  COMMENT '广告位置,1头条，2二条，3三条，4四条，5五条，6六条',
  consume_price decimal(13,2)  COMMENT '广告金额',
  quality_price decimal(13,2)  COMMENT '质检扣款',
  final_price decimal(13,2)  COMMENT '广告收入',
  url varchar(255)  COMMENT '发布链接',
  status tinyint(4) COMMENT '状态，1待接单，2已接单，3已拒单，4执行中，5已完成，6已取消',
  pay_status tinyint(4) COMMENT '支付状态，1待支付，2已支付',
  expect_date datetime COMMENT '预计发布时间',
  final_date datetime COMMENT '实际发布时间',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  media_id bigint(20) COMMENT '媒体id,外键',
  position_advertise_id bigint(20) COMMENT '位置广告id,外键',
  account_id bigint(20) COMMENT '账户id,外键',
  PRIMARY KEY (position_media_id),
  INDEX INDEX_POSITION (position) USING BTREE,
  INDEX INDEX_STATUS (status) USING BTREE,
  INDEX INDEX_PAYSTATUS (pay_status) USING BTREE,
  INDEX INDEX_MEDIAID (media_id) USING BTREE,
  INDEX INDEX_POSITIONADVERTISEID (position_advertise_id) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='位置媒体表';

#创建媒体日数据表
CREATE TABLE media_daily_data_tb(
  media_daily_data_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '媒体日数据id',
  article_number bigint(20) COMMENT '文章发布数',
  top_reading_number bigint(20) COMMENT 'top阅读数',
  total_reading_number bigint(20) COMMENT '总阅读数',
  avg_reading_number bigint(20) COMMENT '平均阅读数',
  top1_reading_number bigint(20) COMMENT '头条阅读数',
  point_number bigint(20) COMMENT '点赞数',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  media_id bigint(20) COMMENT '媒体id外键',
  account_id bigint(20) COMMENT '账户id外键',
  PRIMARY KEY (media_daily_data_id),
  INDEX INDEX_CREATEDATE (create_date) USING BTREE,
  INDEX INDEX_MEDIAID (media_id) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
  UNIQUE INDEX DAY_DATA (create_date,media_id,account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='媒体日数据表';

#创建媒体详细数据表
CREATE TABLE media_data_tb(
  media_data_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '媒体详细数据id',
  name varchar(255) COMMENT '文章标题',
  position tinyint(4)  COMMENT '文章位置,1头条，2二条，3三条，4四条，5五条，6六条',
  reading_number bigint(20) COMMENT '阅读数',
  point_number bigint(20) COMMENT '点赞数',
  create_date datetime COMMENT '创建时间',
  update_date datetime COMMENT '更新时间',
  media_id bigint(20) COMMENT '媒体id外键',
  account_id bigint(20) COMMENT '账户id外键',
  PRIMARY KEY (media_data_id),
  INDEX INDEX_CREATEDATE (create_date) USING BTREE,
  INDEX INDEX_MEDIAID (media_id) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
  UNIQUE INDEX DAY_DATA (create_date,media_id,account_id) USING BTREE
)ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='媒体详细数据表';

#创建支付表
CREATE TABLE payment_tb(
  payment_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '支付id',
  subject varchar(255) COMMENT '主题',
  body varchar(255) COMMENT '内容',
  notify_url varchar(255) COMMENT '异步通知',
  type tinyint(4) COMMENT '支付类型，1支付宝，2微信,3百度钱包,4Paypal,5网银',
  order_number varchar(255) COMMENT '平台订单号',
  money decimal(11,2) COMMENT '金额',
  status tinyint(4) DEFAULT 1 COMMENT '状态，1已下单-未支付，2支付成功，3支付失败,4异常',
  business_type tinyint(4) COMMENT '业务类型，1充值，2提现，3退款',
  business_id bigint(20) COMMENT '业务id,外键',
  business_notify_url longtext COMMENT '业务回调,外键',
  account_id bigint(20) COMMENT '账户id,外键',
  create_date datetime  COMMENT '创建时间',
  update_date datetime  COMMENT '更新时间',
  PRIMARY KEY (payment_id),
  INDEX INDEX_ORDERNUMBER (order_number) USING BTREE,
  INDEX INDEX_TYPE (type) USING BTREE,
  INDEX INDEX_BUSINESSTYPE (business_type) USING BTREE,
  INDEX INDEX_BUSINESSID (business_id) USING BTREE,
  INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
  INDEX INDEX_STATUS (status) USING BTREE,
  INDEX INDEX_CREATEDATE (create_date) USING BTREE,
  INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='支付表';

#设置初始角色
INSERT IGNORE INTO role_tb (role_id,name,duty,update_date) 
VALUES (1000,"超级管理员","超级管理员",now());
INSERT IGNORE INTO role_tb (role_id,name,duty,update_date) 
VALUES (1001,"普通管理员","普通管理员",now());
INSERT IGNORE INTO role_tb (role_id,name,duty,update_date)
VALUES (1002,"媒体主","媒体主",now());
INSERT IGNORE INTO role_tb (role_id,name,duty,update_date) 
VALUES (1003,"广告主","广告主",now());
INSERT IGNORE INTO role_tb (role_id,name,duty,update_date) 
VALUES (1004,"用户","用户",now());

#初始化配置
INSERT IGNORE INTO config_tb (config_id,service_proportion,tax_rate,create_date,update_date)
VALUES (1000,0,0,now(),now());

#设置初始管理员密码MD5加密123456
INSERT IGNORE INTO account_tb (account_id,nickname,phone,email,password,create_date,login_date,role_id) 
VALUES (1000,"聂跃","1000","1000@qq.com","11874bb6149dd45428da628c9766b252",now(),now(),1000);
#初始为888888
INSERT IGNORE INTO finance_tb (finance_id,password,money,recharge,consume,income,withdrawals,refund,frozen,base_profit,account_id,create_date,update_date)
VALUES (1000,"d3265abc117009bb706d223fc944f868",0,0,0,0,0,0,0,0,1000,now(),now());
#公司
INSERT IGNORE INTO company_tb (company_id,create_date,update_date,account_id)
VALUES (1000,now(),now(),1000);

#第三方信息
INSERT IGNORE INTO third_info_tb (third_info_id,create_date,update_date,account_id)
VALUES (1000,now(),now(),1000); 
