<template>
  <div class="shopcart">
    <div class="content" @click="toggleList">
      <div class="content-left" :class="{'highlight': totalCount > 0}">
        <div class="logo-wrapper">
          <div class="logo">
            <i class="icon-shopping_cart"></i>
          </div>
          <div class="num" v-show="totalCount > 0">{{ totalCount }}</div>
        </div>
        <div class="price">￥{{ totalPrice }}</div>
        <div class="desc">另需配送费￥{{ deliveryPrice }}元</div>
      </div>
      <div class="content-right" @click.stop.prevent="pay">
        <div class="pay" :class="payClass">{{ payDesc }}</div>
      </div>
    </div>
    <!--若干个小球，完成抛物线的效果-->
    <div class="ball-container">
      <template v-for="ball in balls">
        <transition name="drop" @before-enter="beforeEnter" @enter="enter" @after-enter="afterEnter">
          <div class="ball" v-show="ball.show">
            <div class="inner inner-hook"></div>
          </div>
        </transition>
      </template>
    </div>
    <transition name="fold">
      <!--购物车详情-->
      <div class="shopcart-list" v-show="listShow">
        <div class="list-header">
          <h1 class="title">购物车</h1>
          <span class="empty" @click="empty">清空</span>
        </div>
        <div class="list-content" ref="listContent">
          <ul>
            <li class="food border-1px" v-for="food in selectFoods">
              <span class="name">{{ food.name }}</span>
              <div class="price">￥{{ food.price * food.count }}</div>
              <div class="cartcontrol-wrapper">
                <cartcontrol :food="food"
                             @drop="drop"></cartcontrol>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
  import BScroll from 'better-scroll';
  import cartcontrol from '../common/cartcontrol'
  export default{
    props: {
      // 购物车实际上是跟一个数组关联，加一个数组push一个，所以定义一个空数组
      selectFoods: {
        type: Array,
        default(){
          return [];
        }
      },
      deliveryPrice: {
        type: Number,
        default: 50
      },
      minPrice: {
        type: Number,
        default: 0
      }
    },
    data() {
      return {
        balls: [
          {
            show: false
          },
          {
            show: false
          },
          {
            show: false
          },
          {
            show: false
          },
          {
            show: false
          }
        ],
        dropBalls: [],
        fold: true
      };
    },
    computed: {
      // 用计算属性去计算所有商品的总价，去遍历selectFoods，总数去累加
      totalPrice() {
        let total = 0;
        this.selectFoods.forEach((food) => {
          total += food.price * food.count;
        });
        return total; // return出去
      },
      totalCount() {  // 计算总数的方法
        let count = 0;
        this.selectFoods.forEach((food) => {
          count += food.count;
        });
        return count;
      },
      payDesc() {
        // 描述的状态变化，根据总价是相关的，存在计算关系的
        if (this.totalPrice === 0) { // 如果是等于0的时候，那就直接return
          return `￥${this.minPrice}元起送`;
        } else if (this.totalPrice < this.minPrice) {
          let diff = this.minPrice - this.totalPrice;
          return `还差￥${diff}元起送`;
        } else {
          return '去结算'
        }
      },
      // 判断描述的条件
      payClass() {
        if (this.totalPrice <= this.minPrice) {
          return 'not-enough';
        } else {
          return 'enough';
        }
      },
      listShow() {
        if (!this.totalCount) { // 如果等于0， 代表没有购买东西，直接就没状态，返回false
          this.fold = true;
          return false;
        }
        let show = !this.fold;
        if (show) {
          this.$nextTick(() => {
            if (!this.scroll) {
              this.scroll = new BScroll(this.$refs.listContent, {
                click: true
              });
            } else {
              // 重新计算better-scroll高度
              this.scroll.refresh();
            }

          });
        }
        return show; // 显示
      }
    },
    methods: {
      drop(el) {
        console.log(el)
        for (let i = 0; i < this.balls.length; i++) {
          let ball = this.balls[i];
          if (!ball.show) {
            ball.show = true;
            // 保存el
            ball.el = el;
            this.dropBalls.push(ball);
            return;
          }
        }
      },
      beforeEnter(el) {
        let count = this.balls.length;
        while (count--) {
          let ball = this.balls[count];
          if (ball.show) {
            let rect = ball.el.getBoundingClientRect();
            let x = rect.left - 32;
            let y = -(window.innerHeight - rect.top - 22);

            el.style.display = '';

            el.style.webkitTransform = `translate3d(0, ${y}px, 0)`;
            el.style.transform = `translate3d(0, ${y}px, 0)`;

            let inner = el.getElementsByClassName('inner-hook')[0];
            inner.style.webkitTransform = `translate3d(${x}px, 0, 0)`;
            inner.style.transform = `translate3d(${x}px, 0, 0)`;
          }
        }
      },
      enter(el) {
        let rf = el.offsetHeight;

        this.$nextTick(() => {
          el.style.webkitTransform = 'translate3d(0, 0, 0)';
          el.style.transform = 'translate3d(0, 0, 0)';

          let inner = el.getElementsByClassName('inner-hook')[0];
          inner.style.webkitTransform = 'translate3d(0, 0, 0)';
          inner.style.transform = 'translate3d(0, 0, 0)';
        });
      },
      afterEnter(el) {
        let ball = this.dropBalls.shift();
        if (ball) {
          ball.show = false;
          el.style.display = 'none';
        }
      },
      toggleList() { // 取反折叠方法
        if (!this.totalCount) {
          return;
        }
        this.fold = !this.fold;
      },
      empty() {
        this.selectFoods.forEach((food) => {
          food.count = 0
        });
      },
      hideList() {
        this.fold = true;
      },
      pay() {
        if(this.totalPrice < this.minPrice){
          return;
        }
        window.alert(`支付${this.totalPrice}元`);
      }
    },
    components: { // 引用子组件
      cartcontrol
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss">


  .shopcart {
    position: fixed;
    left: 0;
    bottom: 0;
    z-index: 50;
    width: 100%;
    height: 48px;
    .content {
      display: flex;
      background: #141d27;
      font-size: 0;
      color: rgba(255, 255, 255, .4);
      .content-left {
        flex: 1;
        .logo-wrapper {
          display: inline-block;
          vertical-align: top;
          position: relative;
          top: -10px;
          margin: 0 18px;
          padding: 6px;
          width: 56px;
          height: 56px;
          box-sizing: border-box;
          border-radius: 50%;
          background: #141d27;
          .logo {
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background: #2b343c;
            text-align: center;
            .icon-shopping_cart {
              font-size: 24px;
              line-height: 44px;
              color: #80858a;
            }
          }
          .num {
            position: absolute;
            top: 0;
            right: 0;
            width: 24px;
            height: 16px;
            line-height: 16px;
            text-align: center;
            border-radius: 16px;
            font-size: 9px;
            font-weight: 700;
            color: #fff;
            background: rgb(240, 20, 20);
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, .4);
          }
        }
        .price {
          vertical-align: top;
          display: inline-block;
          line-height: 24px;
          margin-top: 12px;
          padding-right: 12px;
          box-sizing: border-box;
          border-right: 1px solid rgba(255, 255, 255, .1);
          font-size: 16px;
          font-weight: 700;
        }
        .desc {
          display: inline-block;
          vertical-align: top;
          line-height: 24px;
          margin: 12px 0 0 12px;
          font-size: 10px;
          color:white;
        }
        &.highlight {
          .logo-wrapper {
            .logo {
              background: rgb(0, 160, 220);
              .icon-shopping_cart {
                color: #fff;
              }
            }
          }
          .price {
            color: #fff;
          }
        }
      }
      .content-right {
        flex: 0 0 105px;
        width: 105px;
        .pay {
          height: 48px;
          line-height: 48px;
          text-align: center;
          font-size: 12px;
          font-weight: 700;
          &.not-enough {
            background: #2b333b;
          }
          &.enough {
            background: #00b43c;
            color: #fff;
          }
        }
      }
    }
    .ball-container {
      .ball {
        position: fixed;
        left: 32px;
        bottom: 22px;
        z-index: 200;
        .inner {
          width: 16px;
          height: 16px;
          border-radius: 50%;
          background: rgb(0, 160, 220);
        }
        &.drop-enter-active {
          transition: all .4s cubic-bezier(.49, -0.29, .75, .41);
          .inner {
            transition: all .4s linear;
          }
        }
      }
    }
    .shopcart-list {
      position: absolute;
      top: 0;
      left: 0;
      z-index: -1;
      width: 100%;
      transform: translate3d(0, -100%, 0);
      &.fold-enter-active, &.fold-leave-active {
        transition: all .5s;
      }
      &.fold-enter, &.fold-leave-active {
        transform: translate3d(0, 0, 0);
      }
      .list-header {
        height: 40px;
        line-height: 40px;
        padding: 0 18px;
        background: #f3f5f7;
        border-bottom: 1px solid rgba(7, 17, 27, .1);
        .title {
          float: left;
          font-size: 14px;
          color: rgb(7, 17, 27);
        }
        .empty {
          float: right;
          font-size: 12px;
          color: rgb(0, 160, 220);
        }
      }
      .list-content {
        padding: 0 18px;
        max-height: 217px;
        background: #fff;
        overflow: hidden;
        text-align: left;
        .food {
          position: relative;
          padding: 12px 0;
          box-sizing: border-box;
          border-bottom: 1px solid rgba(7, 17, 27, .1);
          .name {
            line-height: 24px;
            font-size: 14px;
            color: rgb(7, 17, 27);
          }
          .price {
            position: absolute;
            right: 90px;
            bottom: 12px;
            line-height: 24px;
            font-weight: 700;
            font-size: 14px;
            color: rgb(240, 20, 20);
          }
          .cartcontrol-wrapper {
            position: absolute;
            bottom: 6px;
            right: 0;
          }
        }
      }
    }
  }
  .list-mask {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 40;
    background: rgba(7, 17, 27, .6);
    backdrop-filter: blur(10px);
    &.fade-enter-active, .fade-leave-active {
      transition: all .5s;
    }
    .fade-enter, .fade-leave-active {
      opacity: 0
    }
  }

  @font-face {
    font-family: 'sell-icon';
    src:  url('../../fonts/sell-icon.eot?qvetpz');
    src:  url('../../fonts/sell-icon.eot?qvetpz#iefix') format('embedded-opentype'),
    url('../../fonts/sell-icon.ttf?qvetpz') format('truetype'),
    url('../../fonts/sell-icon.woff?qvetpz') format('woff'),
    url('../../fonts/sell-icon.svg?qvetpz#sell-icon') format('svg');
    font-weight: normal;
    font-style: normal;
  }

  [class^="icon-"], [class*=" icon-"] {
    /* use !important to prevent issues with browser extensions that change fonts */
    font-family: 'sell-icon' !important;
    speak: none;
    font-style: normal;
    font-weight: normal;
    font-variant: normal;
    text-transform: none;
    line-height: 1;

    /* Better Font Rendering =========== */
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  .icon-shopping_cart:before {
    content: "\e903";
  }

</style>
