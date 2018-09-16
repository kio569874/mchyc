<template>
  <div class="cartcontrol">
    <!--减少的按钮,只有大于0才会显示-->
    <transition name="move">
      <div class="cart-decrease" v-show="food.count > 0" @click.stop.prevent="decreaseCart">
        <span class="inner icon-remove_circle_outline"></span>
      </div>
    </transition>
    <!-- 总数-->
    <div class="cart-count" v-show="food.count > 0">{{ food.count }}</div>
    <div class="cart-add icon-add_circle" @click.stop.prevent="addCart"></div>
  </div>
</template>

<script>
  import Vue from 'vue';
  import Bus from '../../config/EventBus';

  export default {
    props: {
      food: {
        type: Object
      }
    },
    created() {

    },
    methods: {
      addCart(event) {
        console.log('触发')
        if (!this.food.count) {
          Vue.set(this.food, 'count', 1)
          // this.food.count = 1  vue 检测不到变化，所以要调用vue的观测接口，引入全局的vue
        } else {
          this.food.count++;
        }
        Bus.$emit('cart.add', event.target);
      },
      decreaseCart(event) {
        if (this.food.count) {
          this.food.count--;
        }
      }
    }
  }
</script>

<style lang="scss" scoped>
  @import "../../style/mixin";
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

  .icon-add_circle:before {
    content: "\e908";
  }
  .icon-remove_circle_outline:before {
    content: "\e907";
  }
  .cartcontrol {
    font-size: 0;
    .cart-decrease {
      display: inline-block;
      padding: 6px;
      .inner {
        display: inline-block;
        line-height: 24px;
        font-size: 24px;
        color: rgb(0, 160, 220);
      }
      &.move-enter-active, &.move-leave-active {
        transition: all .5s;
        transform: translate3d(0, 0, 0);
        .inner{
          transition: all .5s;
          transform: rotate(0deg);
        }
      }
      &.move-enter, &.move-leave-active {
        opacity: 0;
        transform: translate3d(24px, 0, 0);
        .inner{
          transform: rotate(180deg);
        }
      }
    }
    .cart-count {
      display: inline-block;
      vertical-align: top;
      width: 12px;
      padding-top: 6px;
      line-height: 24px;
      text-align: center;
      font-size: 10px;
      color: rgb(147, 153, 159);
    }
    .cart-add {
      display: inline-block;
      line-height: 24px;
      font-size: 24px;
      padding: 6px;
      color: rgb(0, 160, 220);
    }
  }

</style>
