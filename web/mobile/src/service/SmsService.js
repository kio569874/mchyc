import {smsAppKey} from '../config/xdjEnv'
import {getCheckCodeApi,sendSmsCheckCodeApi,verifyCheckCodeApi} from '../xdjApi'

const checkCodeTypeRegeist = 'USER_REGISTER';
const checkCodeTypeForget = 'USER_UPDATE_PWD';
const checkCodeTypeLogin = 'USER_LOGIN';
let smsToken = '';

/**
 * 发送注册短信
 * @param phoneNo
 * @returns {Promise<*>}
 */
export async function getRegeistVerifyCode(phoneNo){
  let res =  await getCheckCodeApi(phoneNo,smsAppKey,checkCodeTypeRegeist);
  smsToken = res.data.smsToken;
  let res2 =  await sendSmsCheckCodeApi(phoneNo,res.data.checkCode);
  return res2;
}

/**
 * 发送修改密码短信
 * @param phoneNo
 * @returns {Promise<*>}
 */
export async function getForgetVerifyCode(phoneNo){
  let res =  await getCheckCodeApi(phoneNo,smsAppKey,checkCodeTypeForget);
  smsToken = res.data.smsToken;
  let res2 =  await sendSmsCheckCodeApi(phoneNo,res.data.checkCode);
  return res2;
}

/**
 * 发送登陆短信
 * @param phoneNo
 * @returns {Promise<*>}
 */
export async function getLoginVerifyCode(phoneNo){
  let res =  await getCheckCodeApi(phoneNo,smsAppKey,checkCodeTypeLogin);
  smsToken = res.data.smsToken;
  let res2 =  await sendSmsCheckCodeApi(phoneNo,res.data.checkCode);
  return res2;
}

/**
 * 校验短信验证码
 * @param phoneNo
 * @param checkCode
 * @returns {*}
 */
export function verifyCheckCode(phoneNo,checkCode){
  return verifyCheckCodeApi(phoneNo,checkCode,smsAppKey,smsToken);
}
