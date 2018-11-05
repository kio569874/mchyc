import fetch from './config/xdjFetch'
import {baseUrl} from './config/xdjEnv'
import {getToken} from "./service/TokenService"

export function getCheckCodeApi(phoneNo,appKey,checkcodeType){
  return sendPost('/sms/getCheckCode',{phoneNo, appKey, checkcodeType})
}

export function sendSmsCheckCodeApi(phoneNo,checkCode){
  return sendPost('/sms/sendSmsCheckCode',{phoneNo,checkCode})
}

export function verifyCheckCodeApi(phoneNo,checkCode,appKey,smsToken){
  return sendPost('/sms/verifyCheckCode',{phoneNo,checkCode,appKey,smsToken})
}

export function userInsertApi(userPhone,userPassword){
  return sendPost('/user/user/insert',{userPhone,userPassword})
}

export function userLoginApi(userAccount,userPassword){
  return sendPost('/user/user/login',{userAccount,userPassword})
}

export function mobileLoginApi(phoneNo){
  return sendPost('/user/user/mobileLogin',{phoneNo})
}

export function forgetPasswordApi(phoneNo,userPassword){
  return sendPost('/user/user/forgetPassword',{phoneNo,userPassword})
}

export function updatePasswordApi(oldPassword,newPassword){
  return sendPost('/user/user/updatePassword',{oldPassword,newPassword})
}

export function getProductListApi(bigtypeId){
  return sendPost('/product/productList/query',{bigtypeId})
}

export function getProductBigTypeListApi(page,pageSize){
  return sendPost('/product/productBigTypeInfo/query',null,page,pageSize)
}

const sendPost = (url,data,page,pageSize)=> {
  const body = {
    data : data,
    page:page,
    pageSize:pageSize
  };
  const headers = {
    "Authorization" : getToken()
  };
  return fetch(baseUrl + url,body,headers,'POST');
};
