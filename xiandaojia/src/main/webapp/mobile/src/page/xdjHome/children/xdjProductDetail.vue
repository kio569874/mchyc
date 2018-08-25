<template>
  <div clas="home_container">
    <head-top signin-up='xdj_home' go-back='true'>
      <router-link to="/xdjHome" slot="msite-title" class="msite_title">
        <span class="title_text ellipsis">{{homeTitle}}</span>
      </router-link>
    </head-top>
    <div class="product_carousel">
      <div class="swiper-container">
        <div class="swiper-wrapper">
          <div class="swiper-slide">
            <img src="../../../images/1.jpg">
          </div>
          <div class="swiper-slide">
            <img src="../../../images/2.jpg">
          </div>
        </div>
        <div class="swiper-pagination"></div>
      </div>
    </div>
    <div class="product-buy">
      <div class="smalltype-list">
          <span class="smalltype_activity">
            <span style="{color: red;border-color:red}">当季</span>
          </span>
          <span class="smalltype_activity">
            <span style="{color: red;border-color:red}">新品上市</span>
          </span>
          <span class="smalltype_activity">
            <span style="{color: red;border-color:red}">农场直达</span>
          </span>
      </div>
      <div class="salenum-evaluate">
        <span class="salenum">销量:100</span>
        <span class="evaluate">评价:4.9</span>
      </div>
    </div>
    <div class="product-information">
      <strong>亚木沟生态草莓500g</strong>
      <div class="buy">
        <section class="food_price">
          <span>¥</span>
          <span>100/斤</span>
          <span class="buy-product">
            <svg @touchstart="">
              <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#cart-minus"></use>
            </svg>
            <span class="cart_num">0</span>
            <svg class="add_icon" @touchstart="">
              <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#cart-add"></use>
            </svg>
            <span class="show_chooselist" @click="">加入购物车</span>
          </span>
        </section>
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
  import buyCart from 'src/components/common/buyCart'
  import ratingStar from 'src/components/common/ratingStar'

  export default {
    data(){
      return {
        homeTitle: '订单详情', // 页面头部标题
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
      chooseMenu(index){
        this.menuIndex = index;
        /*this.foodScroll.scrollTo(0, -this.shopListTop[index], 400);
        this.foodScroll.on('scrollEnd', () => {
          this.menuIndexChange = true;
        })*/
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
  .product_carousel{
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
  .product-buy{
    margin-top: .4rem;
    background-color: #fff;
    border-bottom: 0.025rem solid $bc;
    height: 3.8rem;
    padding-top: 0.2rem;
    .smalltype-list{
      padding-left: 1rem;
      .smalltype_activity{
        line-height: .8rem;
        span{
          font-size: .8rem;
          border: 1px solid currentColor;
          border-radius: 0.3rem;
          padding: .08rem;
          display: inline-block;
          transform: scale(.8);
          margin-left: -0.35rem;

        }
      }
    }
    .salenum-evaluate{
      padding-left: 1rem;
      .salenum{
        font-size:.6rem;
      }
      .evaluate{
        font-size:.6rem;
        position:relative;
        left:9rem;
      }
    }

  }
  .product-information{
    padding-left: 1rem;
    padding-bottom: 2.1rem;
    background-color: #fff;
    margin-top: .4rem;
    display: flex;
    height: 16rem;
    flex-direction: column;
    border-bottom: 0.025rem solid $bc;
    .buy{
      svg{
        @include wh(.9rem, .9rem);
        fill: #3190e8;
        position: relative;
        top:.15rem;
      }
      .cart_num{
        @include sc(1rem, #666);
        min-width: 1rem;
        text-align: center;
        font-family: Helvetica Neue,Tahoma;
      }
      .buy-product{
        position:relative;
        left:3rem;
      }
      .show_chooselist{
        @include sc(.55rem, #fff);
        padding: .1rem .2rem;
        background-color: $blue;
        border-radius: 0.2rem;
        border: 1px solid $blue;
        position: relative;
        bottom: .1rem;
      }
    }
  }
</style>
