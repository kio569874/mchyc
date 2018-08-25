/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/26 19:40:37                           */
/*==============================================================*/


/*==============================================================*/
/* Table: t_checkcode_config                                    */
/*==============================================================*/
create table t_checkcode_config
(
   id                   bigint not null auto_increment comment '自增id',
   app_key              varchar(64) not null comment '应用标识，比如可以分配应用标识给web端，前端获取验证码时需要送应用标识用以区别不同应用。',
   valid_duration       int not null comment '某个应用下验证码的有效期
            单位秒：比如180S则为180',
   update_time          datetime comment '更新时间，每当验证时都需要更新这个时间',
   create_time          datetime not null comment '创建时间',
   app_name             varchar(64) not null comment '应用名称',
   primary key (id)
)
auto_increment = 1;

alter table t_checkcode_config comment '验证码配置表，可配置验证码的有效期等，后续可扩展配置';

/*==============================================================*/
/* Index: t_checkcode_config_index                              */
/*==============================================================*/
create unique index t_checkcode_config_index on t_checkcode_config
(
   app_key
);

/*==============================================================*/
/* Table: t_sms_checkcode_jnl                                   */
/*==============================================================*/
create table t_sms_checkcode_jnl
(
   id                   bigint not null auto_increment comment '自增id',
   phone_no             varchar(32) not null comment '用来生成验证码的标识，目前使用手机号码，也可以用用户id等，一样的道理',
   check_code           varchar(8) not null comment '生成的验证码',
   sms_token            varchar(64) not null comment '验证码token：同一个手机号多模块混用，用来做区别，可直接使用uuid',
   checkcode_type       char(2) comment '01-用户注册验证码 02-用户登录验证码 03-用户修改密码验证 00-其他',
   app_key              varchar(64) not null comment '应用标识',
   valid_duration       int comment '某个应用下验证码的有效期
            单位秒：比如180S则为180',
   create_time          datetime not null comment '验证码生成时间',
   check_result         char(1) comment '验证码验证结果',
   check_error_num      int comment '验证码验证错误次数',
   check_pass_time      datetime comment '验证码验证通过时间',
   update_time          datetime comment '更新时间，每当验证时都需要更新这个时间',
   primary key (id)
)
auto_increment = 1;

/*==============================================================*/
/* Table: t_sms_config                                          */
/*==============================================================*/
create table t_sms_config
(
   id                   bigint not null auto_increment comment '自增id',
   sms_config_id        varchar(64) not null comment '短信接入配置id',
   sms_key              varchar(10) not null comment '其他系统标识，用来标识其他系统：比如云片短信平台等',
   sms_name             varchar(128) comment '系统名称',
   sms_type             varchar(20) not null comment '类型',
   sms_desc             varchar(256) comment '备注',
   status               char(1) not null comment '0-启用 1-停用',
   send_url             varchar(256) comment '发送的url',
   rec_url              varchar(256) comment '异步接收的url',
   apikey               varchar(128) not null comment '接入标识，唯一标识',
   mould_context        varchar(512) not null comment '模板',
   impl_class           varchar(512),
   primary key (id)
)
auto_increment = 1;

alter table t_sms_config comment '配置接口的短信平台信息（如云片短信平台）';

/*==============================================================*/
/* Index: t_sms_config_index                                    */
/*==============================================================*/
create unique index t_sms_config_index on t_sms_config
(
   sms_config_id
);

/*==============================================================*/
/* Table: t_sms_send_jnl                                        */
/*==============================================================*/
create table t_sms_send_jnl
(
   id                   bigint not null auto_increment comment '自增id',
   sms_send_id          varchar(64) not null comment '短信发送唯一id',
   send_phone           varchar(11) not null comment '发送短信的手机号码',
   sms_context          varchar(512) not null comment '发送的完整短信内容',
   send_sms_type        char(1) not null comment '发送的短信类型
            1-验证码
            2-通知',
   send_count           int comment '发送条数，如果短信内容过多，会分为多条短信发送',
   send_fee             decimal comment '本条流水的费用，如果短信内容过多，会有多条短信，费用也会相应增多',
   fee_unit             varchar(32) comment '费用单位',
   send_return_code     varchar(10) comment '发送结果代码，用来标识发送结果',
   send_return_message  varchar(512) comment '发送结果信息',
   send_id              varchar(64) comment '对方系统id,比如发到云片网，会有一个对方系统id',
   send_time            datetime not null comment '短信发送时间',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   primary key (id)
)
auto_increment = 1;

alter table t_sms_send_jnl comment '短信发送流水表：记录发送的所有短信流水，包括验证码短信、通知短信等';

/*==============================================================*/
/* Index: t_sms_send_jnl_index                                  */
/*==============================================================*/
create unique index t_sms_send_jnl_index on t_sms_send_jnl
(
   sms_send_id
);

/*==============================================================*/
/* Table: t_system_user                                         */
/*==============================================================*/
create table t_system_user
(
   id                   bigint not null auto_increment comment '自增id',
   user_code            varchar(32) not null comment '用户代码',
   user_password        varchar(128) not null comment '用户密码',
   user_name            varchar(64) comment '用户姓名',
   user_phone           varchar(20) comment '手机号码',
   user_level           varchar(12) comment '用户级别',
   user_position        varchar(32) comment '用户职位',
   user_status          char(1) not null comment '用户状态，0:正常 1:停用',
   login_time           date not null comment '最后登录时间',
   login_ip             varchar(24) comment '登录ip',
   create_time          date not null comment '创建时间',
   update_time          date comment '修改时间',
   primary key (id)
)
auto_increment = 1;

alter table t_system_user comment '系统用户表';




/*==============================================================*/
/* Table: t_alipay_order_jnl                                    */
/*==============================================================*/
create table t_alipay_order_jnl
(
   id                   bigint not null auto_increment comment '自增id',
   order_pay_id         varchar(64) not null comment '订单id',
   trade_no             varchar(64) comment '支付宝订单号',
   order_no             varchar(64) not null comment '本系统订单号',
   total_amount         decimal(11,2) not null comment '订单金额',
   receipt_amount       decimal(11,2) comment '实收金额，单位为元，两位小数。该金额为本笔交易，商户账户能够实际收到的金额',
   buyer_pay_amount     decimal(11,2) comment '买家实付金额，单位为元，两位小数。该金额代表该笔交易买家实际支付的金额，不包含商户折扣等金额',
   point_amount         decimal(11,2) comment '支付宝返回的交易状态',
   invoice_amount       decimal(11,2) comment '交易中用户支付的可开具发票的金额，单位为元，两位小数。该金额代表该笔交易中可以给用户开具发票的金额',
   send_pay_date        datetime comment '本次交易打款给卖家的时间',
   pay_time             datetime comment '支付时间 默认本次交易打款给卖家的时间',
   order_status         char(1) comment '订单状态
            0:初始
            1:支付中
            2:支付成功
            3:支付失败',
   buyer_logon_id       varchar(32) comment '支付宝用户登录id',
   timeout_express      datetime comment '订单允许的最晚付款时间',
   receive_notify_time  datetime comment '收到支付结果通知时间',
   trade_status         varchar(32) comment '支付宝交易状态',
   version              bigint comment '版本，需要时用来做乐观锁',
   create_time          datetime comment '创建时间',
   primary key (id)
)
auto_increment = 1;

alter table t_alipay_order_jnl comment '支付宝订单信息';

/*==============================================================*/
/* Index: index_alipay_order_jnl_order_pay_id                   */
/*==============================================================*/
create unique index index_alipay_order_jnl_order_pay_id on t_alipay_order_jnl
(
   order_pay_id
);

/*==============================================================*/
/* Index: index_alipay_order_jnl_trade_no                       */
/*==============================================================*/
create index index_alipay_order_jnl_trade_no on t_alipay_order_jnl
(
   trade_no
);

/*==============================================================*/
/* Index: index_alipay_order_jnl_order_no                       */
/*==============================================================*/
create index index_alipay_order_jnl_order_no on t_alipay_order_jnl
(
   order_no
);

/*==============================================================*/
/* Table: t_alipay_order_jnl_ext                                */
/*==============================================================*/
create table t_alipay_order_jnl_ext
(
   id                   bigint not null auto_increment comment '自增id',
   order_pay_id         varchar(64) not null comment '订单支付id',
   notify_info          text comment '存放支付宝的结果信息大字段',
   create_time          datetime not null comment '创建时间',
   primary key (id)
)
auto_increment = 1;

alter table t_alipay_order_jnl_ext comment '支付宝结果信息上下文表';

/*==============================================================*/
/* Index: index_alipay_order_jnl_ext                            */
/*==============================================================*/
create unique index index_alipay_order_jnl_ext on t_alipay_order_jnl_ext
(
   order_pay_id
);

/*==============================================================*/
/* Table: t_alipay_refund_jnl                                   */
/*==============================================================*/
create table t_alipay_refund_jnl
(
   id                   bigint not null auto_increment,
   refund_id            varchar(64) not null comment '退款id',
   order_pay_id         varchar(64) not null comment '订单id',
   out_request_no       varchar(64) comment '退款请求流水号',
   refund_amount        decimal(11,2) comment '退款金额',
   refund_time          datetime comment '退款时间',
   refund_status        char(1) comment '退款状态
            0:初始
            1:退款中
            2:退款成功
            3:退款失败',
   refund_reason        varchar(1024) comment '退款原因',
   operator_id          varchar(16) comment '操作员id',
   gmt_refund_pay       varchar(32) comment '退款支付时间',
   version              bigint comment '版本，用来做乐观锁',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   primary key (id)
);

alter table t_alipay_refund_jnl comment '退款流水表。一笔订单可能会全额退款，多次退款，或者只退款一部分';

/*==============================================================*/
/* Index: index_alipay_refund_jnl_refund_id                     */
/*==============================================================*/
create unique index index_alipay_refund_jnl_refund_id on t_alipay_refund_jnl
(
   refund_id
);

/*==============================================================*/
/* Index: index_alipay_refund_jnl_order_pay_id                  */
/*==============================================================*/
create index index_alipay_refund_jnl_order_pay_id on t_alipay_refund_jnl
(
   order_pay_id
);

/*==============================================================*/
/* Index: index_alipay_refund_jnl_out_request_no                */
/*==============================================================*/
create index index_alipay_refund_jnl_out_request_no on t_alipay_refund_jnl
(
   out_request_no
);

/*==============================================================*/
/* Table: t_alipay_refund_jnl_ext                               */
/*==============================================================*/
create table t_alipay_refund_jnl_ext
(
   id                   bigint not null auto_increment,
   refund_id            varchar(64) not null comment '退款id',
   notify_info          text comment '支付宝退款结果信息',
   create_time          datetime not null,
   primary key (id)
);

alter table t_alipay_refund_jnl_ext comment '支付宝退款结果上下文表';

/*==============================================================*/
/* Index: index_alipay_refund_jnl_ext_refund_id                 */
/*==============================================================*/
create unique index index_alipay_refund_jnl_ext_refund_id on t_alipay_refund_jnl_ext
(
   refund_id
);

/*==============================================================*/
/* Table: t_homepage_image                                      */
/*==============================================================*/
create table t_homepage_image
(
   id                   bigint not null auto_increment comment '自增id',
   image_id             varchar(64) not null comment '图片id',
   content_name         varchar(128) comment '内容名称',
   content_desc         varchar(256) comment '内容备注',
   img_url              varchar(256) not null comment '轮播图片的地址',
   image_seqno          smallint default 0 comment '轮播图片排序,从小到大',
   to_url               varchar(256) comment '点击这张图片跳转到的url',
   type                 char(2) not null comment '01-轮播图',
   status               char(1) not null comment '0-启用，1-停用，只显示启用状态的图片，如果需要换轮播图，则需要停用之前的，显示最新启用排序的几条',
   operator             varchar(64) comment '记录最后对记录进行操作的人',
   create_time          datetime not null comment '创建时间',
   update_time          datetime comment '修改时间',
   primary key (id)
);

alter table t_homepage_image comment '首页上的展示内容，如：轮播图、广告位等';

/*==============================================================*/
/* Table: t_notice_info                                         */
/*==============================================================*/
create table t_notice_info
(
   id                   bigint not null auto_increment,
   notice_title         varchar(64),
   notice_content       varchar(1024) not null,
   notice_seqno         smallint not null default 0 comment '排序，从小到大，默认0',
   notice_type          char(2) comment '公告类型：01-url（点击公告跳转到一个url），02-弹窗(点击公告时弹框提示)，03-不跳转(公告只做文本展示)',
   notice_url           varchar(256) comment '当notice_type为01-url时，此字段不能为空',
   status               char(1) comment '状态：0-启用，1-停用，只展示启用状态的公告',
   create_time          datetime not null comment '创建时间',
   update_time          datetime comment '修改时间',
   primary key (id)
);

alter table t_notice_info comment '公告信息维护';

/*==============================================================*/
/* Table: t_order                                               */
/*==============================================================*/
create table t_order
(
   order_id             bigint not null auto_increment comment '订单id',
   order_no             varchar(64) not null comment '订单号，用户展示和外系统交互的订单号，比如和支付宝系统',
   user_id              varchar(64) not null comment '购买人id',
   order_amount         decimal(11,2) comment '订单金额',
   order_subject        varchar(256) comment '订单说明',
   address_id           varchar(32) comment '订单地址',
   order_status         char(2) comment '订单状态:00-初始状态、01-订单取消、02-订单超时、03-已完成、04-申请退款、05-退款中、06-已退款、07-部分退款',
   pay_type             char(1) comment '0-支付宝，1-微信',
   pay_status           char(1) comment '0-待付款
            1-已付款  
            2-支付超时：表示订单超过了最大支付时间',
   order_remark         varchar(128) comment '订单描述',
   version              bigint comment '如果需要的话用来做乐观锁',
   create_time          datetime comment '创建时间',
   update_time          timestamp comment '修改时间',
   primary key (order_id)
);

alter table t_order comment '订单表';

/*==============================================================*/
/* Index: t_order__index                                        */
/*==============================================================*/
create index t_order__index on t_order
(
   order_no
);

/*==============================================================*/
/* Table: t_order_evaluate                                      */
/*==============================================================*/
create table t_order_evaluate
(
   evaluate_id          bigint not null auto_increment comment '自增id',
   order_id             bigint not null comment '订单id',
   product_id           bigint not null comment '产品id',
   context              varchar(256) comment '评价内容',
   level                char(1) comment '等级分为：非常满意（5分）满意（3）一般（1分）不满意（一2）',
   mark                 int comment '评价分数',
   image                varchar(512) comment '用下划线分隔（aa.jpg|bbjipg）',
   create_time          datetime comment '创建时间',
   primary key (evaluate_id)
)
auto_increment = 1;

alter table t_order_evaluate comment '订单评价表';

/*==============================================================*/
/* Table: t_order_product                                       */
/*==============================================================*/
create table t_order_product
(
   order_product_id     bigint not null auto_increment comment '自增id',
   order_id             bigint not null comment '订单id',
   product_id           bigint not null comment '服务id',
   buy_num              int,
   product_price        decimal(11,2) comment '产品单价',
   actual_price         decimal(11,2) comment '购买时的实际单价，如果有折扣，实际单价比产品单价低',
   total_price          decimal(11,2) comment '实际总价',
   create_time          datetime comment '创建时间',
   primary key (order_product_id)
)
auto_increment = 1;

alter table t_order_product comment '订单详情表：记录订单下面的产品信息';

/*==============================================================*/
/* Table: t_pay_config                                          */
/*==============================================================*/
create table t_pay_config
(
   id                   bigint not null auto_increment,
   pay_key              varchar(10) not null comment '其他系统标识
            alipay
            weixin',
   pay_type             varchar(10) comment 'pcweb
            phoneweb
            android
            iso',
   pay_name             varchar(32) comment '名称',
   pay_desc             varchar(128) comment '支付备注',
   config_status        char(1) not null comment '0-启用 1-停用',
   send_url             varchar(256) comment '发送url',
   return_url           varchar(256) comment '返回url',
   notify_url           varchar(256) comment '通知url',
   app_id               varchar(128) not null comment '应用标识',
   app_private_key      varchar(4096) comment '应用私钥',
   app_public_key       varchar(4096) comment '应用公钥',
   exosys_public_key    varchar(4096) comment '对方系统公钥',
   sign_type            varchar(128) comment '签名类型',
   impl_class           varchar(256) comment '实现类',
   primary key (id)
)
auto_increment = 1;

alter table t_pay_config comment '支付配置';

/*==============================================================*/
/* Table: t_product_bigtype_info                                */
/*==============================================================*/
create table t_product_bigtype_info
(
   bigtype_id           bigint not null auto_increment comment '自增id',
   bigtype_name         varchar(64) comment '大类名称',
   bigtype_seqno        smallint comment '大类排序',
   status               char(1) not null comment '状态：0-启用，1-停用',
   create_time          datetime not null,
   update_time          datetime,
   operator             varchar(64),
   primary key (bigtype_id)
);

alter table t_product_bigtype_info comment '产品大类信息';

/*==============================================================*/
/* Table: t_product_info                                        */
/*==============================================================*/
CREATE TABLE t_product_info
(
   product_id           BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增id',
   product_name         VARCHAR(64) NOT NULL COMMENT '产品名称:如亚木沟生态草莓500g',
   smalltype_id         BIGINT,
   product_price        DECIMAL(11,2) COMMENT '产品价格，单位默认元，比如18.50，默认18.50元',
   product_url          VARCHAR(256),
   product_seqno        INT DEFAULT 0 COMMENT '产品在小类中的排序，升序',
   price_unit           VARCHAR(64) COMMENT '价格单位，直接根据价格带出来，默认XX/斤，可修改，如
            18.50/份，
            18.50/斤，
            5元/个，每个100g',
   product_num          INT COMMENT '当前产品总数量，数量为0时显示已售完',
   is_discount          CHAR(1) COMMENT '是否打折，0-是，1-否',
   product_discount     DECIMAL(4,2) COMMENT '产品折扣：99.99表示99.99%',
   product_content      VARCHAR(512) COMMENT '产品介绍',
   nutrient_content     VARCHAR(512) COMMENT '营养成分',
   people_suitble       VARCHAR(128) COMMENT '适宜人群',
   product_information  VARCHAR(512) COMMENT '产品知识',
   STATUS               CHAR(1),
   VERSION              BIGINT COMMENT '版本，更新产品信息时做乐观锁控制',
   product_desc         VARCHAR(128) COMMENT '产品备注',
   create_time          DATETIME,
   update_time          DATETIME,
   PRIMARY KEY (product_id)
);

ALTER TABLE t_product_info COMMENT '产品信息表:存放鲜到家各种产品';

/*==============================================================*/
/* Table: t_product_smalltype_info                              */
/*==============================================================*/
create table t_product_smalltype_info
(
   smalltype_id         bigint not null auto_increment comment '自增id',
   smalltype_name       varchar(64) not null comment '小类名称',
   bigtype_id           bigint not null comment '所属大类',
   small_seqno          smallint not null default 0 comment '排序：小类产品在大类里面的排序，从小到大，默认0',
   status               char(1) not null comment '状态：0-启用，1-停用',
   create_time          datetime not null,
   update_time          datetime,
   operator             varchar(64),
   primary key (smalltype_id)
);

alter table t_product_smalltype_info comment '产品小类信息';

/*==============================================================*/
/* Table: t_shopping_cart                                       */
/*==============================================================*/
create table t_shopping_cart
(
   id                   bigint not null auto_increment,
   user_id              bigint not null,
   product_id           bigint not null,
   create_time          datetime not null,
   primary key (id)
);

alter table t_shopping_cart comment '用户购物车信息存放';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   user_id              bigint not null auto_increment comment '用户id',
   user_account         varchar(64) not null comment '用户账号',
   user_password        varchar(64) not null comment '用户密码',
   user_phone           varchar(11) not null comment '手机号',
   user_member_name     varchar(32) not null comment '会员名',
   user_name            varchar(64) comment '身份证姓名',
   user_idcard          varchar(32) comment '身份证号码',
   user_image           varchar(256) comment '用户头像',
   user_age             int comment '用户年龄',
   user_sex             char(1) comment '用户性别  0-男，1-女',
   user_email           varchar(128) comment '用户邮箱',
   create_time          datetime comment '创建时间',
   update_time          timestamp comment '修改时间',
   last_login_time      date comment '最后登录时间',
   last_login_ip        varchar(18) comment '最后登录IP',
   primary key (user_id)
)
auto_increment = 1;

alter table t_user comment '用户表';

/*==============================================================*/
/* Table: t_user_address                                        */
/*==============================================================*/
create table t_user_address
(
   id                   bigint not null auto_increment comment '收货地址id',
   address_province     varchar(20) not null comment '省',
   address_city         varchar(20) not null comment '市',
   address_area         varchar(20) not null comment '区域',
   address_text         varchar(100) not null comment '详细地点',
   address_default      tinyint(1) unsigned not null default 0 comment '是否默认',
   user_id              varchar(64) not null default '0' comment '用户id',
   user_name            varchar(20) not null comment '收货人名称，默认用户名称',
   user_phone           char(11) not null comment '收货手机号，默认用户手机号，可修改',
   update_time          datetime comment '修改时间',
   create_time          datetime not null,
   primary key (id)
)
auto_increment = 1;

alter table t_user_address comment '用户收货地址表';

