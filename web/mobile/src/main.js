import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from './router/router'
import store from './store/'
import {routerMode} from './config/env'
import './config/rem'
import FastClick from 'fastclick'
// 引入vue resource第三方ajax
import VueResource from 'vue-resource'
if ('addEventListener' in document) {
    document.addEventListener('DOMContentLoaded', function() {
        FastClick.attach(document.body);
    }, false);
}
Vue.use(VueRouter)
Vue.use(VueResource)  // 注册一下,才能全局使用


const router = new VueRouter({
	routes,
	mode: routerMode,
	strict: process.env.NODE_ENV !== 'production',
	scrollBehavior (to, from, savedPosition) {
	    if (savedPosition) {
		    return savedPosition
		} else {
			if (from.meta.keepAlive) {
				from.meta.savedPosition = document.body.scrollTop;
			}
		    return { x: 0, y: to.meta.savedPosition || 0 }
		}
	}
})

new Vue({
	router,
	store,
}).$mount('#app')

