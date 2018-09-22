import {getStore} from '../config/mUtils'
import {setStore} from '../config/mUtils'
import {removeStore} from '../config/mUtils'

const TOKEN_KEY = "access_token";

export const saveToken = (token)=>{
  setStore(TOKEN_KEY,token);
};

export const clearToken = ()=>{
  removeStore(TOKEN_KEY);
};

export const getToken = ()=>{
  return getStore(TOKEN_KEY);
};
