import {getProductListApi,getProductBigTypeListApi} from '../xdjApi'

/**
 * 获取产品列表
 * @param bigtypeId 大类id
 * @returns {*}
 */
export function getProductList(bigtypeId=''){
  return getProductListApi(bigtypeId);
}

/**
 * 获取所有产品大类
 * @returns {*}
 */
export function getAllProductBigType(){
  return getProductBigTypeListApi(1,9999);
}
