import {userInsertApi,userLoginApi,updatePasswordApi,mobileLoginApi,forgetPasswordApi} from '../xdjApi'
import {saveToken} from "../service/TokenService"
import {getStore} from '../config/mUtils'
import {setStore} from '../config/mUtils'
import {removeStore} from '../config/mUtils'
import md5 from 'js-md5'

const USER_KEY = "user_info";
export function saveUserToLocal(userInfo){
  setStore(USER_KEY,userInfo);
}
export function getUserFromLocal(){
  return JSON.parse(getStore(USER_KEY));
}
export function clearUserFromLocal(){
  removeStore(USER_KEY);
}

/**
 * 注册用户
 * @param phoneNo
 * @param password
 * @returns {*}
 */
export function regeist(phoneNo,password){
  password = md5(password);
  return userInsertApi(phoneNo,password);
}
/**
 * 忘记密码
 * @param phoneNo
 * @param password
 * @returns {*}
 */
export function forgetPassword(phoneNo,password){
  password = md5(password);
  return forgetPasswordApi(phoneNo,password);
}
/**
 * 修改密码
 * @param phoneNo
 * @param password
 * @returns {*}
 */
export function updatePassword(oldPassword,newPassword){
  oldPassword = md5(oldPassword);
  newPassword = md5(newPassword);
  return updatePasswordApi(oldPassword,newPassword);
}
/**
 * 账户登陆
 * @param phoneNo
 * @param password
 * @returns {*}
 */
export async function accountLogin(account,password){
  password = md5(password);
  let res =  await userLoginApi(account,password);
  saveToken(res.data.token);
  return res;
}
/**
 * 手机验证码登陆
 * @param phoneNo
 * @param password
 * @returns {*}
 */
export async function mobileLogin(mobile){
  let res =  await mobileLoginApi(mobile);
  saveToken(res.data.token);
  return res;
}
