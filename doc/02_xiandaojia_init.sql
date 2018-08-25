
insert into `t_sms_config` (`id`, `sms_config_id`, `sms_key`, `sms_name`, `sms_type`, `sms_desc`, `status`, `send_url`, `rec_url`, `apikey`, `mould_context`, `impl_class`) values('1','001','yunpian','云片短信平台','1','云片短信平台验证码接入','0','https://sms.yunpian.com/v2/sms/single_send.json',NULL,'1772b574381c03ca729ead5316f5bca7','【鲜到家】您的验证码是#code。有效期为#HOUR，如非本人操作，请忽略！','com.xiandaojia.spi.sms.impl.YunPianSmsServiceImpl');


insert into `t_checkcode_config` (`id`, `app_key`, `valid_duration`, `update_time`, `create_time`, `app_name`) values('1','1001','180','2018-03-27 12:15:51','2018-03-27 12:15:30','手机端');

