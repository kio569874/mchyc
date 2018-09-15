import fetch from './config/xdjFetch'
import {baseUrl} from './config/xdjEnv'

export function getCheckCode(phoneNo,appKey,checkcodeType){
  return sendPost('/sms/getCheckCode',{phoneNo, appKey, checkcodeType})
}

export function sendSmsCheckCode(phoneNo,checkCode){
  return sendPost('/sms/sendSmsCheckCode',{phoneNo,checkCode})
}

export function verifyCheckCode(phoneNo,checkCode,appKey,smsToken){
  return sendPost('/sms/verifyCheckCode',{phoneNo,checkCode,appKey,smsToken})
}

export function userInsert(userPhone,userPassword){
  return sendPost('/user/user/insert',{userPhone,userPassword})
}

export function userLogin(userAccount,userPassword){
  return sendPost('/user/user/login',{userAccount,userPassword})
}

const sendPost = (url,data,page,pageSize)=> {
  const body = {
    data : data,
    page:page,
    pageSize:pageSize
  };
  return fetch(baseUrl + url,body,'POST');
};
