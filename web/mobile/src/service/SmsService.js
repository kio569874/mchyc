import {smsAppKey} from '../config/xdjEnv'
import {getCheckCode,sendSmsCheckCode,verifyCheckCode} from '../xdjApi'

const checkCodeTypeRegeist = 'USER_REGISTER';
let smsToken = '';

/**
 * 发送注册短信
 * @param phoneNo
 * @returns {Promise<*>}
 */
export async function getRegeistVerifyCode(phoneNo){
  let res =  await getCheckCode(phoneNo,smsAppKey,checkCodeTypeRegeist);
  smsToken = res.data.smsToken;
  let res2 =  await sendSmsCheckCode(phoneNo,res.data.checkCode);
  return res2;
}

/**
 * 校验注册短信验证码
 * @param phoneNo
 * @param checkCode
 * @returns {*}
 */
export function verifyRegeistCheckCode(phoneNo,checkCode){
  return verifyCheckCode(phoneNo,checkCode,smsAppKey,smsToken);
}
