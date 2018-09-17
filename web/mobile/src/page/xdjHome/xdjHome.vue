<template>
    <div clas="home_container">
    	<head-top signin-up='xdj_home'>
        <router-link to="/xdjHome" slot="msite-title" class="msite_title">
          <span class="title_text ellipsis">{{homeTitle}}</span>
        </router-link>
    	</head-top>
    	<div class="home_carousel">
    		<div class="swiper-container">
		        <div class="swiper-wrapper">
                <div class="swiper-slide">
                  <img src="../../images/1.jpg">
		            </div>
                <div class="swiper-slide">
                  <img src="../../images/2.jpg">
                </div>
		        </div>
		        <div class="swiper-pagination"></div>
		    </div>
    	</div>
      <div class="top-nav">
          <div class="food_types_container">
            <router-link :to="{path: '/xdjTypeList'}" class="link_to_food">
              <figure>
                <img src="../../images/1.jpg">
                <figcaption>果蔬到家</figcaption>
              </figure>
            </router-link>
            <router-link :to="{path: '/xdjFlowersList'}" class="link_to_food">
              <figure>
                <img src="../../images/flower.jpg">
                <figcaption>我爱鲜花</figcaption>
              </figure>
            </router-link>
            <router-link :to="{path: '/xdjTypeList'}" class="link_to_food">
              <figure>
                <img src="../../images/food.jpg">
                <figcaption>美食推荐</figcaption>
              </figure>
            </router-link>
            <router-link :to="{path: '/xdjTypeList'}" class="link_to_food">
              <figure>
                <img src="../../images/1.jpg">
                <figcaption>其他</figcaption>
              </figure>
            </router-link>
          </div>
      </div>
      <div class="nav_product_list">
          <div class="left_nav" ref="leftNav">
            <ul>
              <li class="menu_left_li" :class="{activity_menu: 0 == menuIndex}" @click="chooseMenu(0)">
                <span>热销</span>
              </li>
              <li class="menu_left_li" :class="{activity_menu: 1 == menuIndex}" @click="chooseMenu(1)">
                <span>折扣</span>
              </li>
              <li class="menu_left_li" :class="{activity_menu: 2 == menuIndex}" @click="chooseMenu(2)">
                <span>当季果蔬</span>
              </li>
              <li class="menu_left_li" :class="{activity_menu: 3 == menuIndex}" @click="chooseMenu(3)">
                <span>...</span>
              </li>
            </ul>
          </div>
          <div class="product_list" ref="productList">
            <ul>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;borderColor:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
              <li>
                <section class="menu_detail_list">
                  <router-link  :to="{path: 'xdj_home/xdjProductDetail'}" tag="div" class="menu_detail_link">
                    <section class="menu_food_img">
                      <img src="../../images/food.jpg">
                    </section>
                    <section class="menu_food_description">
                      <h3 class="food_description_head">
                        <strong class="description_foodname">产品名称</strong>
                        <ul class="attributes_ul">
                          <li>
                            <p>产品属性</p>
                          </li>
                        </ul>
                      </h3>
                      <p class="food_description_content">描述</p>
                      <p class="food_description_sale_rating">
                        <span>月售99份</span>
                        <span>好评率100%</span>
                      </p>
                      <p class="food_activity">
                        <span style="{color: red;border-color:red}">产品活动</span>
                      </p>
                    </section>
                  </router-link>
                  <footer class="menu_detail_footer">
                    <section class="food_price">
                      <span>¥</span>
                      <span>100</span>
                    </section>
                  </footer>
                </section>
              </li>
            </ul>
            <p class="empty_data">加载更多...</p>
          </div>
      </div>
    	<xdj-foot-guide></xdj-foot-guide>
    </div>
</template>

<script>
import {mapMutations} from 'vuex'
import headTop from 'src/components/header/xdjHead'
import xdjFootGuide from 'src/components/footer/xdjFootGuide'
import 'src/plugins/swiper.min.js'
import 'src/style/swiper.min.css'
import {showBack, animate} from 'src/config/mUtils'

export default {
	data(){
        return {
            homeTitle: '公告: 鲜到家系统V1.0正式上线啦!', // 页面头部标题
            menuIndex: 0, //已选菜单索引值，默认为0
        }
    },
    async beforeMount(){
	  //组件挂在前
    },
    mounted(){
	    this.init();
    },
    components: {
    	headTop,
    	xdjFootGuide,
    },
    computed: {

    },
    methods: {
    	...mapMutations([
    		'RECORD_ADDRESS', 'SAVE_GEOHASH'
    	]),
      async init(){
        new Swiper('.swiper-container', {
          pagination: '.swiper-pagination',
          loop: true,
          autoplay: 4000
        });
      },
    	// 解码url地址，求去restaurant_category_id值
    	getCategoryId(url){
    		let urlData = decodeURIComponent(url.split('=')[1].replace('&target_name',''));
    		if (/restaurant_category_id/gi.test(urlData)) {
    			return JSON.parse(urlData).restaurant_category_id.id
    		}else{
    			return ''
    		}
    	},
      chooseMenu(index){
        this.menuIndex = index;
      },
      backTop(){
        animate(document.body, {scrollTop: '0'}, 400,'ease-out');
      }
    },
    watch: {

    }
}

</script>

<style lang="scss" scoped>
    @import 'src/style/mixin';
  .home_container{
    display: flex;
    flex-direction: column;
    overflow-y: hidden;
    position: absolute;
    right: 0;
    left: 0;
    height: 100%;
  }
	.msite_title{
		@include center;
        width: 100%;
        color: #fff;
        text-align: center;
        margin-left: -0.5rem;
        .title_text{
            @include sc(0.8rem, #fff);
            text-align: center;
            display: block;
        }
	}
	.home_carousel{
		padding-top: 2.1rem;
		background-color: #fff;
		border-bottom: 0.025rem solid $bc;
		height: 8.0rem;
		.swiper-container{
			@include wh(100%, auto);
			padding-bottom: 0.6rem;
			.swiper-pagination{
			}
		}
		.fl_back{
			@include wh(100%, 100%);
		}
	}
  .top-nav{
    margin-top: .4rem;
		background-color: #fff;
		border-bottom: 0.025rem solid $bc;
		height: 3.8rem;
    padding-top: 0.2rem;
    .food_types_container{
      display:flex;
      flex-wrap: wrap;
      .link_to_food{
        width: 25%;
        padding: 0.3rem 0rem;
        @include fj(center);
        figure{
          img{
            margin-bottom: 0.2rem;
            @include wh(1.8rem, 1.5rem);
          }
          figcaption{
            text-align: center;
            @include sc(0.5rem, #666);
          }
        }
      }
    }
	}
  .nav_product_list{
    padding-bottom: 2.1rem;
    margin-top: .4rem;
    display: flex;
    /*height: 16rem;*/
    flex-direction: row;
		border-bottom: 0.025rem solid $bc;
    .left_nav{
      width: 25%;
      flex-direction: column;
      display: flex;
      .menu_left_li{
        padding: .7rem .3rem;
        border-bottom: 0.025rem solid #ededed;
        /*box-sizing: border-box;*/
        border-left: 0.15rem solid #f8f8f8;
        position: relative;
        span{
          @include sc(.6rem, #666);
        }
      }
      .activity_menu{
        border-left: 0.15rem solid #3190e8;
        background-color: #fff;
        span:nth-of-type(1){
          font-weight: bold;
        }
      }
    }
    .product_list{
      width: 75%;
      background-color: #fff;
      overflow-y: auto;
      .menu_detail_list{
        background-color: #fff;
        padding: .6rem .4rem;
        border-bottom: 1px solid #f8f8f8;
        position: relative;
        .menu_detail_link{
          display:flex;
          .menu_food_img{
            margin-right: .4rem;
            img{
              @include wh(2rem, 2rem);
              display: block;
            }
          }
          .menu_food_description{
            width: 100%;
            .food_description_head{
              @include fj;
              margin-bottom: .2rem;
              .description_foodname{
                @include sc(.7rem, #333);
              }
              .attributes_ul{
                display: flex;
                li{
                  font-size: .3rem;
                  height: .6rem;
                  line-height: .35rem;
                  padding: .1rem;
                  border: 1px solid #666;
                  border-radius: 0.3rem;
                  margin-right: .1rem;
                  transform: scale(.8);
                  p{
                    border-color: red;
                    white-space: nowrap;
                  }
                }
                .attribute_new{
                  position: absolute;
                  top: 0;
                  left: 0;
                  background-color: #4cd964;
                  @include wh(2rem, 2rem);
                  display: flex;
                  align-items: flex-end;
                  transform: rotate(-45deg) translate(-.1rem, -1.5rem);
                  border: none;
                  border-radius: 0;
                  p{
                    @include sc(.4rem, #fff);
                    text-align: center;
                    flex: 1;
                  }
                }
              }
            }
            .food_description_content{
              @include sc(.5rem, #999);
              line-height: .6rem;
            }
            .food_description_sale_rating{
              line-height: .8rem;
              span{
                @include sc(.5rem, #333);
              }
            }
            .food_activity{
              line-height: .4rem;
              span{
                font-size: .3rem;
                border: 1px solid currentColor;
                border-radius: 0.3rem;
                padding: .08rem;
                display: inline-block;
                transform: scale(.8);
                margin-left: -0.35rem;

              }
            }
          }
        }
        .menu_detail_footer{
          margin-left: 2.4rem;
          font-size: 0;
          margin-top: .3rem;
          @include fj;
          .food_price{
            span{
              font-family: 'Helvetica Neue',Tahoma,Arial;
            }
            span:nth-of-type(1){
              @include sc(.5rem, #f60);
              margin-right: .05rem;
            }
            span:nth-of-type(2){
              @include sc(.7rem, #f60);
              font-weight: bold;
              margin-right: .3rem;
            }
            span:nth-of-type(3){
              @include sc(.5rem, #666);
            }
          }
        }
      }
      .empty_data{
        @include sc(0.5rem, #666);
        text-align: center;
        line-height: 2rem;
      }
    }
	}
    .return_top{
      position: fixed;
      bottom: 3rem;
      right: 1rem;
      .back_top_svg{
        @include wh(2rem, 2rem);
      }
    }
</style>
