/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/26 19:40:37                           */
/*==============================================================*/


/*==============================================================*/
/* Table: t_checkcode_config                                    */
/*==============================================================*/
create table t_checkcode_config
(
   id                   bigint not null auto_increment comment '����id',
   app_key              varchar(64) not null comment 'Ӧ�ñ�ʶ��������Է���Ӧ�ñ�ʶ��web�ˣ�ǰ�˻�ȡ��֤��ʱ��Ҫ��Ӧ�ñ�ʶ��������ͬӦ�á�',
   valid_duration       int not null comment 'ĳ��Ӧ������֤�����Ч��
            ��λ�룺����180S��Ϊ180',
   update_time          datetime comment '����ʱ�䣬ÿ����֤ʱ����Ҫ�������ʱ��',
   create_time          datetime not null comment '����ʱ��',
   app_name             varchar(64) not null comment 'Ӧ������',
   primary key (id)
)
auto_increment = 1;

alter table t_checkcode_config comment '��֤�����ñ���������֤�����Ч�ڵȣ���������չ����';

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
   id                   bigint not null auto_increment comment '����id',
   phone_no             varchar(32) not null comment '����������֤��ı�ʶ��Ŀǰʹ���ֻ����룬Ҳ�������û�id�ȣ�һ���ĵ���',
   check_code           varchar(8) not null comment '���ɵ���֤��',
   sms_token            varchar(64) not null comment '��֤��token��ͬһ���ֻ��Ŷ�ģ����ã����������𣬿�ֱ��ʹ��uuid',
   checkcode_type       char(2) comment '01-�û�ע����֤�� 02-�û���¼��֤�� 03-�û��޸�������֤ 00-����',
   app_key              varchar(64) not null comment 'Ӧ�ñ�ʶ',
   valid_duration       int comment 'ĳ��Ӧ������֤�����Ч��
            ��λ�룺����180S��Ϊ180',
   create_time          datetime not null comment '��֤������ʱ��',
   check_result         char(1) comment '��֤����֤���',
   check_error_num      int comment '��֤����֤�������',
   check_pass_time      datetime comment '��֤����֤ͨ��ʱ��',
   update_time          datetime comment '����ʱ�䣬ÿ����֤ʱ����Ҫ�������ʱ��',
   primary key (id)
)
auto_increment = 1;

/*==============================================================*/
/* Table: t_sms_config                                          */
/*==============================================================*/
create table t_sms_config
(
   id                   bigint not null auto_increment comment '����id',
   sms_config_id        varchar(64) not null comment '���Ž�������id',
   sms_key              varchar(10) not null comment '����ϵͳ��ʶ��������ʶ����ϵͳ��������Ƭ����ƽ̨��',
   sms_name             varchar(128) comment 'ϵͳ����',
   sms_type             varchar(20) not null comment '����',
   sms_desc             varchar(256) comment '��ע',
   status               char(1) not null comment '0-���� 1-ͣ��',
   send_url             varchar(256) comment '���͵�url',
   rec_url              varchar(256) comment '�첽���յ�url',
   apikey               varchar(128) not null comment '�����ʶ��Ψһ��ʶ',
   mould_context        varchar(512) not null comment 'ģ��',
   impl_class           varchar(512),
   primary key (id)
)
auto_increment = 1;

alter table t_sms_config comment '���ýӿڵĶ���ƽ̨��Ϣ������Ƭ����ƽ̨��';

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
   id                   bigint not null auto_increment comment '����id',
   sms_send_id          varchar(64) not null comment '���ŷ���Ψһid',
   send_phone           varchar(11) not null comment '���Ͷ��ŵ��ֻ�����',
   sms_context          varchar(512) not null comment '���͵�������������',
   send_sms_type        char(1) not null comment '���͵Ķ�������
            1-��֤��
            2-֪ͨ',
   send_count           int comment '��������������������ݹ��࣬���Ϊ�������ŷ���',
   send_fee             decimal comment '������ˮ�ķ��ã�����������ݹ��࣬���ж������ţ�����Ҳ����Ӧ����',
   fee_unit             varchar(32) comment '���õ�λ',
   send_return_code     varchar(10) comment '���ͽ�����룬������ʶ���ͽ��',
   send_return_message  varchar(512) comment '���ͽ����Ϣ',
   send_id              varchar(64) comment '�Է�ϵͳid,���緢����Ƭ��������һ���Է�ϵͳid',
   send_time            datetime not null comment '���ŷ���ʱ��',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   primary key (id)
)
auto_increment = 1;

alter table t_sms_send_jnl comment '���ŷ�����ˮ����¼���͵����ж�����ˮ��������֤����š�֪ͨ���ŵ�';

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
   id                   bigint not null auto_increment comment '����id',
   user_code            varchar(32) not null comment '�û�����',
   user_password        varchar(128) not null comment '�û�����',
   user_name            varchar(64) comment '�û�����',
   user_phone           varchar(20) comment '�ֻ�����',
   user_level           varchar(12) comment '�û�����',
   user_position        varchar(32) comment '�û�ְλ',
   user_status          char(1) not null comment '�û�״̬��0:���� 1:ͣ��',
   login_time           date not null comment '����¼ʱ��',
   login_ip             varchar(24) comment '��¼ip',
   create_time          date not null comment '����ʱ��',
   update_time          date comment '�޸�ʱ��',
   primary key (id)
)
auto_increment = 1;

alter table t_system_user comment 'ϵͳ�û���';




/*==============================================================*/
/* Table: t_alipay_order_jnl                                    */
/*==============================================================*/
create table t_alipay_order_jnl
(
   id                   bigint not null auto_increment comment '����id',
   order_pay_id         varchar(64) not null comment '����id',
   trade_no             varchar(64) comment '֧����������',
   order_no             varchar(64) not null comment '��ϵͳ������',
   total_amount         decimal(11,2) not null comment '�������',
   receipt_amount       decimal(11,2) comment 'ʵ�ս���λΪԪ����λС�����ý��Ϊ���ʽ��ף��̻��˻��ܹ�ʵ���յ��Ľ��',
   buyer_pay_amount     decimal(11,2) comment '���ʵ������λΪԪ����λС�����ý�����ñʽ������ʵ��֧���Ľ��������̻��ۿ۵Ƚ��',
   point_amount         decimal(11,2) comment '֧�������صĽ���״̬',
   invoice_amount       decimal(11,2) comment '�������û�֧���Ŀɿ��߷�Ʊ�Ľ���λΪԪ����λС�����ý�����ñʽ����п��Ը��û����߷�Ʊ�Ľ��',
   send_pay_date        datetime comment '���ν��״������ҵ�ʱ��',
   pay_time             datetime comment '֧��ʱ�� Ĭ�ϱ��ν��״������ҵ�ʱ��',
   order_status         char(1) comment '����״̬
            0:��ʼ
            1:֧����
            2:֧���ɹ�
            3:֧��ʧ��',
   buyer_logon_id       varchar(32) comment '֧�����û���¼id',
   timeout_express      datetime comment '���������������ʱ��',
   receive_notify_time  datetime comment '�յ�֧�����֪ͨʱ��',
   trade_status         varchar(32) comment '֧��������״̬',
   version              bigint comment '�汾����Ҫʱ�������ֹ���',
   create_time          datetime comment '����ʱ��',
   primary key (id)
)
auto_increment = 1;

alter table t_alipay_order_jnl comment '֧����������Ϣ';

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
   id                   bigint not null auto_increment comment '����id',
   order_pay_id         varchar(64) not null comment '����֧��id',
   notify_info          text comment '���֧�����Ľ����Ϣ���ֶ�',
   create_time          datetime not null comment '����ʱ��',
   primary key (id)
)
auto_increment = 1;

alter table t_alipay_order_jnl_ext comment '֧���������Ϣ�����ı�';

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
   refund_id            varchar(64) not null comment '�˿�id',
   order_pay_id         varchar(64) not null comment '����id',
   out_request_no       varchar(64) comment '�˿�������ˮ��',
   refund_amount        decimal(11,2) comment '�˿���',
   refund_time          datetime comment '�˿�ʱ��',
   refund_status        char(1) comment '�˿�״̬
            0:��ʼ
            1:�˿���
            2:�˿�ɹ�
            3:�˿�ʧ��',
   refund_reason        varchar(1024) comment '�˿�ԭ��',
   operator_id          varchar(16) comment '����Աid',
   gmt_refund_pay       varchar(32) comment '�˿�֧��ʱ��',
   version              bigint comment '�汾���������ֹ���',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '�޸�ʱ��',
   primary key (id)
);

alter table t_alipay_refund_jnl comment '�˿���ˮ��һ�ʶ������ܻ�ȫ���˿����˿����ֻ�˿�һ����';

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
   refund_id            varchar(64) not null comment '�˿�id',
   notify_info          text comment '֧�����˿�����Ϣ',
   create_time          datetime not null,
   primary key (id)
);

alter table t_alipay_refund_jnl_ext comment '֧�����˿��������ı�';

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
   id                   bigint not null auto_increment comment '����id',
   image_id             varchar(64) not null comment 'ͼƬid',
   content_name         varchar(128) comment '��������',
   content_desc         varchar(256) comment '���ݱ�ע',
   img_url              varchar(256) not null comment '�ֲ�ͼƬ�ĵ�ַ',
   image_seqno          smallint default 0 comment '�ֲ�ͼƬ����,��С����',
   to_url               varchar(256) comment '�������ͼƬ��ת����url',
   type                 char(2) not null comment '01-�ֲ�ͼ',
   status               char(1) not null comment '0-���ã�1-ͣ�ã�ֻ��ʾ����״̬��ͼƬ�������Ҫ���ֲ�ͼ������Ҫͣ��֮ǰ�ģ���ʾ������������ļ���',
   operator             varchar(64) comment '��¼���Լ�¼���в�������',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime comment '�޸�ʱ��',
   primary key (id)
);

alter table t_homepage_image comment '��ҳ�ϵ�չʾ���ݣ��磺�ֲ�ͼ�����λ��';

/*==============================================================*/
/* Table: t_notice_info                                         */
/*==============================================================*/
create table t_notice_info
(
   id                   bigint not null auto_increment,
   notice_title         varchar(64),
   notice_content       varchar(1024) not null,
   notice_seqno         smallint not null default 0 comment '���򣬴�С����Ĭ��0',
   notice_type          char(2) comment '�������ͣ�01-url�����������ת��һ��url����02-����(�������ʱ������ʾ)��03-����ת(����ֻ���ı�չʾ)',
   notice_url           varchar(256) comment '��notice_typeΪ01-urlʱ�����ֶβ���Ϊ��',
   status               char(1) comment '״̬��0-���ã�1-ͣ�ã�ֻչʾ����״̬�Ĺ���',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime comment '�޸�ʱ��',
   primary key (id)
);

alter table t_notice_info comment '������Ϣά��';

/*==============================================================*/
/* Table: t_order                                               */
/*==============================================================*/
create table t_order
(
   order_id             bigint not null auto_increment comment '����id',
   order_no             varchar(64) not null comment '�����ţ��û�չʾ����ϵͳ�����Ķ����ţ������֧����ϵͳ',
   user_id              varchar(64) not null comment '������id',
   order_amount         decimal(11,2) comment '�������',
   order_subject        varchar(256) comment '����˵��',
   address_id           varchar(32) comment '������ַ',
   order_status         char(2) comment '����״̬:00-��ʼ״̬��01-����ȡ����02-������ʱ��03-����ɡ�04-�����˿05-�˿��С�06-���˿07-�����˿�',
   pay_type             char(1) comment '0-֧������1-΢��',
   pay_status           char(1) comment '0-������
            1-�Ѹ���  
            2-֧����ʱ����ʾ�������������֧��ʱ��',
   order_remark         varchar(128) comment '��������',
   version              bigint comment '�����Ҫ�Ļ��������ֹ���',
   create_time          datetime comment '����ʱ��',
   update_time          timestamp comment '�޸�ʱ��',
   primary key (order_id)
);

alter table t_order comment '������';

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
   evaluate_id          bigint not null auto_increment comment '����id',
   order_id             bigint not null comment '����id',
   product_id           bigint not null comment '��Ʒid',
   context              varchar(256) comment '��������',
   level                char(1) comment '�ȼ���Ϊ���ǳ����⣨5�֣����⣨3��һ�㣨1�֣������⣨һ2��',
   mark                 int comment '���۷���',
   image                varchar(512) comment '���»��߷ָ���aa.jpg|bbjipg��',
   create_time          datetime comment '����ʱ��',
   primary key (evaluate_id)
)
auto_increment = 1;

alter table t_order_evaluate comment '�������۱�';

/*==============================================================*/
/* Table: t_order_product                                       */
/*==============================================================*/
create table t_order_product
(
   order_product_id     bigint not null auto_increment comment '����id',
   order_id             bigint not null comment '����id',
   product_id           bigint not null comment '����id',
   buy_num              int,
   product_price        decimal(11,2) comment '��Ʒ����',
   actual_price         decimal(11,2) comment '����ʱ��ʵ�ʵ��ۣ�������ۿۣ�ʵ�ʵ��۱Ȳ�Ʒ���۵�',
   total_price          decimal(11,2) comment 'ʵ���ܼ�',
   create_time          datetime comment '����ʱ��',
   primary key (order_product_id)
)
auto_increment = 1;

alter table t_order_product comment '�����������¼��������Ĳ�Ʒ��Ϣ';

/*==============================================================*/
/* Table: t_pay_config                                          */
/*==============================================================*/
create table t_pay_config
(
   id                   bigint not null auto_increment,
   pay_key              varchar(10) not null comment '����ϵͳ��ʶ
            alipay
            weixin',
   pay_type             varchar(10) comment 'pcweb
            phoneweb
            android
            iso',
   pay_name             varchar(32) comment '����',
   pay_desc             varchar(128) comment '֧����ע',
   config_status        char(1) not null comment '0-���� 1-ͣ��',
   send_url             varchar(256) comment '����url',
   return_url           varchar(256) comment '����url',
   notify_url           varchar(256) comment '֪ͨurl',
   app_id               varchar(128) not null comment 'Ӧ�ñ�ʶ',
   app_private_key      varchar(4096) comment 'Ӧ��˽Կ',
   app_public_key       varchar(4096) comment 'Ӧ�ù�Կ',
   exosys_public_key    varchar(4096) comment '�Է�ϵͳ��Կ',
   sign_type            varchar(128) comment 'ǩ������',
   impl_class           varchar(256) comment 'ʵ����',
   primary key (id)
)
auto_increment = 1;

alter table t_pay_config comment '֧������';

/*==============================================================*/
/* Table: t_product_bigtype_info                                */
/*==============================================================*/
create table t_product_bigtype_info
(
   bigtype_id           bigint not null auto_increment comment '����id',
   bigtype_name         varchar(64) comment '��������',
   bigtype_seqno        smallint comment '��������',
   status               char(1) not null comment '״̬��0-���ã�1-ͣ��',
   create_time          datetime not null,
   update_time          datetime,
   operator             varchar(64),
   primary key (bigtype_id)
);

alter table t_product_bigtype_info comment '��Ʒ������Ϣ';

/*==============================================================*/
/* Table: t_product_info                                        */
/*==============================================================*/
CREATE TABLE t_product_info
(
   product_id           BIGINT NOT NULL AUTO_INCREMENT COMMENT '����id',
   product_name         VARCHAR(64) NOT NULL COMMENT '��Ʒ����:����ľ����̬��ݮ500g',
   smalltype_id         BIGINT,
   product_price        DECIMAL(11,2) COMMENT '��Ʒ�۸񣬵�λĬ��Ԫ������18.50��Ĭ��18.50Ԫ',
   product_url          VARCHAR(256),
   product_seqno        INT DEFAULT 0 COMMENT '��Ʒ��С���е���������',
   price_unit           VARCHAR(64) COMMENT '�۸�λ��ֱ�Ӹ��ݼ۸��������Ĭ��XX/����޸ģ���
            18.50/�ݣ�
            18.50/�
            5Ԫ/����ÿ��100g',
   product_num          INT COMMENT '��ǰ��Ʒ������������Ϊ0ʱ��ʾ������',
   is_discount          CHAR(1) COMMENT '�Ƿ���ۣ�0-�ǣ�1-��',
   product_discount     DECIMAL(4,2) COMMENT '��Ʒ�ۿۣ�99.99��ʾ99.99%',
   product_content      VARCHAR(512) COMMENT '��Ʒ����',
   nutrient_content     VARCHAR(512) COMMENT 'Ӫ���ɷ�',
   people_suitble       VARCHAR(128) COMMENT '������Ⱥ',
   product_information  VARCHAR(512) COMMENT '��Ʒ֪ʶ',
   STATUS               CHAR(1),
   VERSION              BIGINT COMMENT '�汾�����²�Ʒ��Ϣʱ���ֹ�������',
   product_desc         VARCHAR(128) COMMENT '��Ʒ��ע',
   create_time          DATETIME,
   update_time          DATETIME,
   PRIMARY KEY (product_id)
);

ALTER TABLE t_product_info COMMENT '��Ʒ��Ϣ��:����ʵ��Ҹ��ֲ�Ʒ';

/*==============================================================*/
/* Table: t_product_smalltype_info                              */
/*==============================================================*/
create table t_product_smalltype_info
(
   smalltype_id         bigint not null auto_increment comment '����id',
   smalltype_name       varchar(64) not null comment 'С������',
   bigtype_id           bigint not null comment '��������',
   small_seqno          smallint not null default 0 comment '����С���Ʒ�ڴ�����������򣬴�С����Ĭ��0',
   status               char(1) not null comment '״̬��0-���ã�1-ͣ��',
   create_time          datetime not null,
   update_time          datetime,
   operator             varchar(64),
   primary key (smalltype_id)
);

alter table t_product_smalltype_info comment '��ƷС����Ϣ';

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

alter table t_shopping_cart comment '�û����ﳵ��Ϣ���';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   user_id              bigint not null auto_increment comment '�û�id',
   user_account         varchar(64) not null comment '�û��˺�',
   user_password        varchar(64) not null comment '�û�����',
   user_phone           varchar(11) not null comment '�ֻ���',
   user_member_name     varchar(32) not null comment '��Ա��',
   user_name            varchar(64) comment '���֤����',
   user_idcard          varchar(32) comment '���֤����',
   user_image           varchar(256) comment '�û�ͷ��',
   user_age             int comment '�û�����',
   user_sex             char(1) comment '�û��Ա�  0-�У�1-Ů',
   user_email           varchar(128) comment '�û�����',
   create_time          datetime comment '����ʱ��',
   update_time          timestamp comment '�޸�ʱ��',
   last_login_time      date comment '����¼ʱ��',
   last_login_ip        varchar(18) comment '����¼IP',
   primary key (user_id)
)
auto_increment = 1;

alter table t_user comment '�û���';

/*==============================================================*/
/* Table: t_user_address                                        */
/*==============================================================*/
create table t_user_address
(
   id                   bigint not null auto_increment comment '�ջ���ַid',
   address_province     varchar(20) not null comment 'ʡ',
   address_city         varchar(20) not null comment '��',
   address_area         varchar(20) not null comment '����',
   address_text         varchar(100) not null comment '��ϸ�ص�',
   address_default      tinyint(1) unsigned not null default 0 comment '�Ƿ�Ĭ��',
   user_id              varchar(64) not null default '0' comment '�û�id',
   user_name            varchar(20) not null comment '�ջ������ƣ�Ĭ���û�����',
   user_phone           char(11) not null comment '�ջ��ֻ��ţ�Ĭ���û��ֻ��ţ����޸�',
   update_time          datetime comment '�޸�ʱ��',
   create_time          datetime not null,
   primary key (id)
)
auto_increment = 1;

alter table t_user_address comment '�û��ջ���ַ��';

