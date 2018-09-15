/**
 * 配置编译环境和线上环境之间的切换
 *
 * baseUrl: 域名地址
 * smsAppKey: 短信appkey
 */

let baseUrl = '';
let smsAppKey = '1001';

if (process.env.NODE_ENV == 'development') {
  baseUrl = 'http://192.168.43.195:8999/xiandaojia';
}else if(process.env.NODE_ENV == 'production'){
	baseUrl = 'http://121.42.54.120:8999/xiandaojia';
}

export {
	baseUrl,
  smsAppKey
}
