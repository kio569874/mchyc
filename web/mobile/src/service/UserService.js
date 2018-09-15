import {userInsert,userLogin} from '../xdjApi'
import {getStore} from '../config/mUtils'

/**
 * 注册用户
 * @param phoneNo
 * @param password
 * @returns {*}
 */
export function regeist(phoneNo,password){
  return userInsert(phoneNo,password);
}
/**
 * 账户登陆
 * @param phoneNo
 * @param password
 * @returns {*}
 */
export function accountLogin(account,password){
  return userLogin(account,password);
}
