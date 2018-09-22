<template>
  <div class="restContainer">
    <head-top head-title="密码修改" goBack="true"></head-top>
    <form class="restForm">
      <section class="input_container">
        <input type="password" placeholder="请输入密码" name="newPassWord" v-model="newPassWord">
      </section>
      <section class="input_container">
        <input type="password" placeholder="请确认密码" name="confirmPassWord" v-model="confirmPassWord">
      </section>
      <section class="input_container captcha_code_container">
        <input type="text" placeholder="验证码" name="mobileCode" maxlength="6" v-model="mobileCode">
        <div class="img_change_img">
          <img v-show="captchaCodeImg" :src="captchaCodeImg">
          <div class="change_img" @click="getCaptchaCode">
            <p>看不清</p>
            <p>换一张</p>
          </div>
        </div>
      </section>
    </form>
    <div class="login_container" @click="resetButton">确认修改</div>
    <alert-tip v-if="showAlert" :showHide="showAlert" @closeTip="closeTip" :alertText="alertText"></alert-tip>
  </div>
</template>

<script>
  import headTop from 'src/components/header/xdjHead'
  import alertTip from 'src/components/common/alertTip'
  import {mobileCode, checkExsis, sendMobile, getcaptchas, changePassword} from 'src/service/getData'
  import {forgetPassword} from'../../../service/UserService';

  export default {
    data(){
      return {
        phoneNumber: null, //电话号码
        newPassWord: null, //新密码
        confirmPassWord: null, //确认密码
        showAlert: false, //显示提示组件
        alertText: null, //提示的内容
        captchaCodeImg: null,
      }
    },
    components: {
      headTop,
      alertTip,
    },
    created(){
      this.getCaptchaCode();
      this.phoneNumber = this.$route.params.mobile;
    },
    methods: {
      async getCaptchaCode(){
        let res = await getcaptchas();
        this.captchaCodeImg = res.code;
      },
      //重置密码
      async resetButton(){
        if(!this.newPassWord){
          this.showAlert = true;
          this.alertText = '请输入新密码';
          return
        }else if(!this.confirmPassWord){
          this.showAlert = true;
          this.alertText = '请输确认密码';
          return
        }else if(this.newPassWord !== this.confirmPassWord){
          this.showAlert = true;
          this.alertText = '两次输入的密码不一致';
          return
        }else if(!this.mobileCode){
          this.showAlert = true;
          this.alertText = '请输验证码';
          return
        }
        // 确认修改
        try{
          let res = await forgetPassword(this.phoneNumber, this.newPassWord);
          this.showAlert = true;
          this.alertText = '修改成功';
          this.$router.push('/xdjLogin');
        }catch(e){
          this.showAlert = true;
          this.alertText = e.message;
        }
      },
      closeTip(){
        this.showAlert = false;
      }
    }
  }

</script>

<style lang="scss" scoped>
  @import 'src/style/mixin';

  .restContainer{
    padding-top: 1.95rem;
  }
  .restForm{
    background-color: #fff;
    margin-top: .6rem;
    .input_container{
      display: flex;
      justify-content: space-between;
      padding: .6rem .8rem;
      border-bottom: 1px solid #f1f1f1;
      input{
        @include sc(.7rem, #666);
      }
      button{
        @include sc(.65rem, #fff);
        font-family: Helvetica Neue,Tahoma,Arial;
        padding: .28rem .4rem;
        border: 1px;
        border-radius: 0.15rem;
      }
      .right_phone_number{
        background-color: $blue;
      }
    }
    .phone_number{
      padding: .3rem .8rem;
    }
    .captcha_code_container{
      height: 2.2rem;
      .img_change_img{
        display: flex;
        align-items: center;
        img{
          @include wh(3.5rem, 1.5rem);
          margin-right: .2rem;
        }
        .change_img{
          display: flex;
          flex-direction: 'column';
          flex-wrap: wrap;
          width: 2rem;
          justify-content: center;
          p{
            @include sc(.55rem, #666);
          }
          p:nth-of-type(2){
            color: #3b95e9;
            margin-top: .2rem;
          }
        }
      }
    }
  }
  .captcha_code_container{
    height: 2.2rem;
    .img_change_img{
      display: flex;
      align-items: center;
      img{
        @include wh(3.5rem, 1.5rem);
        margin-right: .2rem;
      }
      .change_img{
        display: flex;
        flex-direction: 'column';
        flex-wrap: wrap;
        width: 2rem;
        justify-content: center;
        p{
          @include sc(.55rem, #666);
        }
        p:nth-of-type(2){
          color: #3b95e9;
          margin-top: .2rem;
        }
      }
    }
  }
  .login_container{
    margin: 1rem .5rem;
    @include sc(.7rem, #fff);
    background-color: $blue;
    padding: .5rem 0;
    border: 1px;
    border-radius: 0.15rem;
    text-align: center;
  }
</style>
