<template>
    <div clas="home_container">
    	<head-top signin-up='xdj_home' go-back='true'>
        <router-link to="/xdjHome" slot="msite-title" class="msite_title">
          <span class="title_text ellipsis">{{homeTitle}}</span>
        </router-link>
    	</head-top>
      <div class="goods">
        <!--布局分析，两栏布局，左侧是固定有的宽度，右侧是根据手机子适应-->
        <div class="menu-wrapper" ref="menuWrapper">
          <ul>
            <!-- 循环类型, 给左侧加一个类，标识滑动高亮 -->
            <li v-for="(item, index) in goods" class="menu-item" :class="{'current': currentIndex === index}"
                @click="selectMenu(index, $event)">
            <span class="text">
              <!-- 判断 type> 0 显示不同的类名-->
              <span v-show="item.type>0" class="icon"
                    :class="classMap[item]"></span>{{item.name}}
            </span>
            </li>
          </ul>
        </div>
        <!--安装better-scroll 完成滑动效果-->
        <div class="foods-wrapper" ref="foodWrapper">
          <ul>
            <!--因为要计算高度，给一个类food-list-hook,包含右侧的一个大类的高度,给li标签-->
            <li v-for="item in goods" class="food-list food-list-hook">
              <h1 class="title">{{item.name}}</h1>
              <!-- 还是一个列表，因为每个商品里可能有多个-->
              <ul>
                <li v-for="food in item.foods" class="food-item">
                  <div class="icon">
                    <img width="57"  height="57" :src="food.icon">
                  </div>
                  <div class="content">
                    <h2 class="name">{{food.name}}</h2>
                    <p class="desc">{{food.description}}</p>
                    <!-- 其他信息-->
                    <div class="extra">
                      <span class="count">月售{{ food.sellCount}}份</span>
                      <span>好评率{{ food.rating}}%</span>
                    </div>
                    <!--价格-->
                    <div class="price">
                      <span class="now">现价: {{food.price}}</span>
                      <!--优惠价格，判断，不是所有的都有-->
                      <span class="old" v-show="food.oldPrice">优惠价: {{food.oldPrice}}</span>
                    </div>
                    <!--传入添加组件，传入商品数-->
                    <div class="cartcontrol-wrapper">
                      <cartcontrol :food="food"></cartcontrol>
                    </div>
                  </div>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <!--购物车组件， 传递数据给购物车组件信息 delivery-price 传递配送费信息,
        传递起送费信息 :min-price="seller.minPrice"
        刚开始会报错，因为seller vue不知道，要在vue.app中 从router-view 传过去-->
        <shopcard ref="shopcart"
                  :select-foods="selectFoods"
                  :delivery-price="seller.deliveryPrice"
                  :min-price="seller.minPrice"
        ></shopcard>
      </div>
    </div>
</template>

<script>
import headTop from 'src/components/header/xdjHead'
import {showBack, animate} from 'src/config/mUtils'
import BScroll from 'better-scroll';
import Bus from '../../../config/EventBus';
import shopCart from '../../../components/common/shopcat'
import cartcontrol from '../../../components/common/cartcontrol'

export default {
	data(){
        return {
          homeTitle: '果蔬到家', // 页面头部标题
          goods: [],
          listHeight: [], // 我们需要知道每一块区间的高度，才能滑动对应高亮,因为有多个，所以是数组
          scrollY: 0, // 拿到右侧滚动的高度
          selectedFood: {},
          seller: []
        }
    },
    mounted() {

    },
    computed: { // vue的计算属性
        currentIndex() { // 写一个方法,根据滚动的距离获取到对应的索引
          for (let i = 0; i < this.listHeight.length; i++) {
            let height1 = this.listHeight[i];
            let height2 = this.listHeight[i + 1];
            if (!height2 || (this.scrollY >= height1 && this.scrollY < height2)) {
              return i;
            }
          }
          return 0;
        },
        selectFoods() {
          let foods = [];
          this.goods.forEach((good) => {
            good.foods.forEach((food) => {
              if (food.count) {
                foods.push(food);
              }
            });
          });
          return foods;
        }
    },
    created() {
    this.goods =  [
      {
        'name': '热销榜',
        'type': -1,
        'foods': [
          {
            'name': '皮蛋瘦肉粥',
            'price': 10,
            'oldPrice': "",
            'description': '咸粥',
            'sellCount': 229,
            'rating': 100,
            'info': '一碗皮蛋瘦肉粥，总是我到粥店时的不二之选。香浓软滑，饱腹暖心，皮蛋的Q弹与瘦肉的滑嫩伴着粥香溢于满口，让人喝这样的一碗粥也觉得心满意足',
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': '很喜欢的粥',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 1,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/c/cd/c12745ed8a5171e13b427dbc39401jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/c/cd/c12745ed8a5171e13b427dbc39401jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '扁豆焖面',
            'price': 14,
            'oldPrice': "",
            'description': "",
            'sellCount': 188,
            'rating': 96,
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 1,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'info': "",
            'icon': 'http://fuss10.elemecdn.com/c/6b/29e3d29b0db63d36f7c500bca31d8jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/c/6b/29e3d29b0db63d36f7c500bca31d8jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '葱花饼',
            'price': 10,
            'oldPrice': "",
            'description': "",
            'sellCount': 124,
            'rating': 85,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 1,
                'text': '没啥味道',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 1,
                'text': '很一般啊',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/f/28/a51e7b18751bcdf871648a23fd3b4jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/f/28/a51e7b18751bcdf871648a23fd3b4jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '牛肉馅饼',
            'price': 14,
            'oldPrice': "",
            'description': "",
            'sellCount': 114,
            'rating': 91,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 1,
                'text': '难吃不推荐',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/d/b9/bcab0e8ad97758e65ae5a62b2664ejpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/d/b9/bcab0e8ad97758e65ae5a62b2664ejpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '招牌猪肉白菜锅贴/10个',
            'price': 17,
            'oldPrice': "",
            'description': "",
            'sellCount': 101,
            'rating': 78,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 1,
                'text': '不脆,不好吃',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/7/72/9a580c1462ca1e4d3c07e112bc035jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/7/72/9a580c1462ca1e4d3c07e112bc035jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '南瓜粥',
            'price': 9,
            'oldPrice': "",
            'description': '甜粥',
            'sellCount': 91,
            'rating': 100,
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/8/a6/453f65f16b1391942af11511b7a90jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/8/a6/453f65f16b1391942af11511b7a90jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '红豆薏米美肤粥',
            'price': 12,
            'oldPrice': "",
            'description': '甜粥',
            'sellCount': 86,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/d/22/260bd78ee6ac6051136c5447fe307jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/d/22/260bd78ee6ac6051136c5447fe307jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '八宝酱菜',
            'price': 4,
            'oldPrice': "",
            'description': "",
            'sellCount': 84,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/9/b5/469d8854f9a3a03797933fd01398bjpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/9/b5/469d8854f9a3a03797933fd01398bjpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '红枣山药糙米粥',
            'price': 10,
            'oldPrice': "",
            'description': "",
            'sellCount': 81,
            'rating': 91,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/9/b5/469d8854f9a3a03797933fd01398bjpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/9/b5/469d8854f9a3a03797933fd01398bjpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '糊塌子',
            'price': 10,
            'oldPrice': "",
            'description': "",
            'sellCount': 80,
            'rating': 93,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/0/05/097a2a59fd2a2292d08067e16380cjpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/0/05/097a2a59fd2a2292d08067e16380cjpeg.jpeg?imageView2/1/w/750/h/750'
          }
        ]
      },
      {
        'name': '单人精彩套餐',
        'type': 2,
        'foods': [
          {
            'name': '红枣山药粥套餐',
            'price': 29,
            'oldPrice': 36,
            'description': '红枣山药糙米粥,素材包,爽口莴笋丝,四川泡菜或八宝酱菜,配菜可备注',
            'sellCount': 17,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/6/72/cb844f0bb60c502c6d5c05e0bddf5jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/6/72/cb844f0bb60c502c6d5c05e0bddf5jpeg.jpeg?imageView2/1/w/750/h/750'
          }
        ]
      },
      {
        'name': '冰爽饮品限时特惠',
        'type': 1,
        'foods': [
          {
            'name': 'VC无限橙果汁',
            'price': 8,
            'oldPrice': 10,
            'description': "",
            'sellCount': 15,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': '还可以',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/e/c6/f348e811772016ae24e968238bcbfjpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/e/c6/f348e811772016ae24e968238bcbfjpeg.jpeg?imageView2/1/w/750/h/750'
          }
        ]
      },
      {
        'name': '精选热菜',
        'type': -1,
        'foods': [
          {
            'name': '娃娃菜炖豆腐',
            'price': 17,
            'oldPrice': "",
            'description': "",
            'sellCount': 43,
            'rating': 92,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': '菜量还可以,味道还可以',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/d/2d/b1eb45b305635d9dd04ddf157165fjpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/d/2d/b1eb45b305635d9dd04ddf157165fjpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '手撕包菜',
            'price': 16,
            'oldPrice': "",
            'description': "",
            'sellCount': 29,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/9/c6/f3bc84468820121112e79583c24efjpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/9/c6/f3bc84468820121112e79583c24efjpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '香酥黄金鱼/3条',
            'price': 11,
            'oldPrice': "",
            'description': "",
            'sellCount': 15,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/4/e7/8277a6a2ea0a2e97710290499fc41jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/4/e7/8277a6a2ea0a2e97710290499fc41jpeg.jpeg?imageView2/1/w/750/h/750'
          }
        ]
      },
      {
        'name': '爽口凉菜',
        'type': -1,
        'foods': [
          {
            'name': '八宝酱菜',
            'price': 4,
            'oldPrice': "",
            'description': "",
            'sellCount': 84,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/9/b5/469d8854f9a3a03797933fd01398bjpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/9/b5/469d8854f9a3a03797933fd01398bjpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '拍黄瓜',
            'price': 9,
            'oldPrice': "",
            'description': "",
            'sellCount': 28,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/6/54/f654985b4e185f06eb07f8fa2b2e8jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/6/54/f654985b4e185f06eb07f8fa2b2e8jpeg.jpeg?imageView2/1/w/750/h/750'
          }
        ]
      },
      {
        'name': '精选套餐',
        'type': -1,
        'foods': [
          {
            'name': '红豆薏米粥套餐',
            'price': 37,
            'oldPrice': "",
            'description': '红豆薏米粥,三鲜干蒸烧卖,拍黄瓜',
            'sellCount': 3,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/f/49/27f26ed00c025b2200a9ccbb7e67ejpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/f/49/27f26ed00c025b2200a9ccbb7e67ejpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '皮蛋瘦肉粥套餐',
            'price': 31,
            'oldPrice': "",
            'description': "",
            'sellCount': 12,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/8/96/f444a8087f0e940ef264617f9d98ajpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/8/96/f444a8087f0e940ef264617f9d98ajpeg.jpeg?imageView2/1/w/750/h/750'
          }
        ]
      },
      {
        'name': '果拼果汁',
        'type': -1,
        'foods': [
          {
            'name': '蜜瓜圣女萝莉杯',
            'price': 6,
            'oldPrice': "",
            'description': "",
            'sellCount': 1,
            'rating': "",
            'info': "",
            'ratings': [],
            'icon': 'http://fuss10.elemecdn.com/b/5f/b3b04c259d5ec9fa52e1856ee50dajpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/b/5f/b3b04c259d5ec9fa52e1856ee50dajpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '加多宝',
            'price': 6,
            'oldPrice': "",
            'description': "",
            'sellCount': 7,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/b/9f/5e6c99c593cf65229225c5661bcdejpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/b/9f/5e6c99c593cf65229225c5661bcdejpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': 'VC无限橙果汁',
            'price': 8,
            'oldPrice': 10,
            'description': "",
            'sellCount': 15,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': '还可以',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/e/c6/f348e811772016ae24e968238bcbfjpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/e/c6/f348e811772016ae24e968238bcbfjpeg.jpeg?imageView2/1/w/750/h/750'
          }
        ]
      },
      {
        'name': '小吃主食',
        'type': -1,
        'foods': [
          {
            'name': '扁豆焖面',
            'price': 14,
            'oldPrice': "",
            'description': "",
            'sellCount': 188,
            'rating': 96,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 1,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/c/6b/29e3d29b0db63d36f7c500bca31d8jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/c/6b/29e3d29b0db63d36f7c500bca31d8jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '葱花饼',
            'price': 10,
            'oldPrice': "",
            'description': "",
            'sellCount': 124,
            'rating': 85,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 1,
                'text': '没啥味道',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 1,
                'text': '很一般啊',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/f/28/a51e7b18751bcdf871648a23fd3b4jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/f/28/a51e7b18751bcdf871648a23fd3b4jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '牛肉馅饼',
            'price': 14,
            'oldPrice': "",
            'description': "",
            'sellCount': 114,
            'rating': 91,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 1,
                'text': '难吃不推荐',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/d/b9/bcab0e8ad97758e65ae5a62b2664ejpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/d/b9/bcab0e8ad97758e65ae5a62b2664ejpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '招牌猪肉白菜锅贴/10个',
            'price': 17,
            'oldPrice': "",
            'description': "",
            'sellCount': 101,
            'rating': 78,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 1,
                'text': '不脆,不好吃',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/7/72/9a580c1462ca1e4d3c07e112bc035jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/7/72/9a580c1462ca1e4d3c07e112bc035jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '糊塌子',
            'price': 10,
            'oldPrice': "",
            'description': "",
            'sellCount': 80,
            'rating': 93,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/0/05/097a2a59fd2a2292d08067e16380cjpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/0/05/097a2a59fd2a2292d08067e16380cjpeg.jpeg?imageView2/1/w/750/h/750'
          }
        ]
      },
      {
        'name': '特色粥品',
        'type': -1,
        'foods': [
          {
            'name': '皮蛋瘦肉粥',
            'price': 10,
            'oldPrice': "",
            'description': '咸粥',
            'sellCount': 229,
            'rating': 100,
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': '很喜欢的粥',
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 1,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/c/cd/c12745ed8a5171e13b427dbc39401jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/c/cd/c12745ed8a5171e13b427dbc39401jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '南瓜粥',
            'price': 9,
            'oldPrice': "",
            'description': '甜粥',
            'sellCount': 91,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/8/a6/453f65f16b1391942af11511b7a90jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/8/a6/453f65f16b1391942af11511b7a90jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '红豆薏米美肤粥',
            'price': 12,
            'oldPrice': "",
            'description': '甜粥',
            'sellCount': 86,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/d/22/260bd78ee6ac6051136c5447fe307jpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/d/22/260bd78ee6ac6051136c5447fe307jpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '红枣山药糙米粥',
            'price': 10,
            'oldPrice': "",
            'description': "",
            'sellCount': 81,
            'rating': 91,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/9/b5/469d8854f9a3a03797933fd01398bjpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/9/b5/469d8854f9a3a03797933fd01398bjpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '鲜蔬菌菇粥',
            'price': 11,
            'oldPrice': "",
            'description': '咸粥',
            'sellCount': 56,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': ""
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/e/a3/5317c68dd618929b6ac05804e429ajpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/e/a3/5317c68dd618929b6ac05804e429ajpeg.jpeg?imageView2/1/w/750/h/750'
          },
          {
            'name': '田园蔬菜粥',
            'price': 10,
            'oldPrice': "",
            'description': '咸粥',
            'sellCount': 33,
            'rating': 100,
            'info': "",
            'ratings': [
              {
                'username': '3******c',
                'rateTime': 1469281964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '2******3',
                'rateTime': 1469271264000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              },
              {
                'username': '3******b',
                'rateTime': 1469261964000,
                'rateType': 0,
                'text': "",
                'avatar': 'http://static.galileo.xiaojukeji.com/static/tms/default_header.png'
              }
            ],
            'icon': 'http://fuss10.elemecdn.com/a/94/7371083792c19df00e546b29e344cjpeg.jpeg?imageView2/1/w/114/h/114',
            'image': 'http://fuss10.elemecdn.com/a/94/7371083792c19df00e546b29e344cjpeg.jpeg?imageView2/1/w/750/h/750'
          }
        ]
      }
    ];// 模拟赋值
    this.seller = this.goods
    // DOM 更新了 操作dom时一定要在$nextTick里, 这个有点问题，必须刷新才能滚动，回来搞一下
    this.$nextTick(() => {
      this.initScroll();
      // 要实现滑动右侧商品，左侧对应的菜单实现高亮，其实就是去计算距离，在这里写一个方法,同样写在dom更新的这里面
      this.calculateHeight()
    });

    this.classMap = ['decrease', 'discount', 'special', 'invoice',
      'guarantee']

    Bus.$on('cart.add', el => {
      this.$nextTick(() => {
        this.$refs.shopcart.drop(el);
      })
    });
  },
    methods: {
    // 初始化滑动方法
    initScroll() {
      //  左侧滑动初始化，接受两个参数，dom对象和json对象 如何获得dom对象？通过v-el
      // 因为需要点击的，所以调用组件的时候传一个方法
      this.menuScroll = new BScroll(this.$refs.menuWrapper, {
        click: true
      })
      this.foodScroll = new BScroll(this.$refs.foodWrapper, {
        click: true,
        probeType: 3
      })
      // 就可以使用这个方法了，获取实时滚动的位置
      this.foodScroll.on('scroll', (pos) => {
        // 拿到实时的y坐标
        this.scrollY = Math.abs(Math.round(pos.y));
      })
    },
    selectMenu(index, event) { // 点击左侧右侧也要有对应的滑动,所以要传索引,点击了第几个
      // better-scroll 可以监听到此事件，浏览器原生不能监听到，就是false 直接return 防止pc端出现两次点击
      // 获取食物的li Dom节点列表
      let foodList = this.$refs.foodWrapper.getElementsByClassName('food-list-hook');
      let el = foodList[index];
      // 调用better-scroll 方法滚动到响应位置
      this.foodScroll.scrollToElement(el, 300);
    },
    calculateHeight() {
      // 获取到这个类,然后获取高度，完成左侧菜单滑动右侧左侧实时高亮更新
      let foodList = this.$refs.foodWrapper.getElementsByClassName('food-list-hook');
      let height = 0;
      this.listHeight.push(height); // 先把第一个高度push进去
      for (let i = 0; i < foodList.length; i++) { // 循环
        let item = foodList[i]; // 获得每一个高度
        height += item.clientHeight; // 高度累加
        this.listHeight.push(height); // 知道每个区间的高度之后 ，在push到这个数组中
        // 其实就是一个递增的区间数组，标识每个区间对应的高度是多少
      }
    },
    selectFood(food, event) {
      if (!event._constructed) {
        return;
      }
      this.selectedFood = food;
      this.$refs.food.show();
    }

  },
    components: { // 引用子组件
      'shopcard': shopCart,
      'cartcontrol': cartcontrol,
      headTop,
    }
}

</script>


<style lang="scss" scoped>
  @import "../../../../src/style/mixin";

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

  .goods {
    display: flex;
    position: absolute;
    top: 1.95rem;
    bottom: 46px;
    width: 100%;
    overflow: hidden;
    .menu-wrapper {
      width: 80px;
      flex: 0 0 80px;
      background: #f3f5f7;
      .menu-item {
        display: table;
        height: 54px;
        /*width: 56px;*/
        line-height: 14px;
        padding: 0 12px;
        &.current {
          position: relative;
          margin-top: -1px;
          z-index: 10;
          background: #fff;
          font-weight: 700;
          .text {
            @include border-none();
          }
        }
        .icon {
          display: inline-block;
          vertical-align: top;
          width: 12px;
          height: 12px;
          margin-right: 2px;
          background-size: 12px 12px;
          background-repeat: no-repeat;
        }
        .text {
          display: table-cell;
          width: 56px;
          vertical-align: middle;
          font-size: 12px;
          @include border-1px(rgba(7, 17, 27, .1));
        }
      }
    }
    .foods-wrapper {
      flex: 1;
      .title {
        padding-left: 14px;
        height: 26px;
        line-height: 26px;
        border-left: 2px solid #d9dde1;
        font-size: 12px;
        color: rgb(147, 153, 159);
        background: #f3f5f7;
      }
      .food-item {
        display: flex;
        margin: 18px;
        padding-bottom: 18px;
        @include border-1px(rgba(7, 17, 27, .1));
        &:last-child {
          @include border-none();
          margin-bottom: 0;
        }
        .icon {
          flex: 0 0 57px;
          margin-right: 10px;
        }
        .content {
          flex: 1;
          text-align: left;
          .name {
            margin: 2px 0 8px;
            line-height: 14px;
            font-size: 14px;
            color: rgb(7, 17, 27);
          }
          .desc,
          .extra {
            font-size: 10px;
            color: rgb(147, 153, 159);
          }
          .desc {
            line-height: 12px;
            margin-bottom: 8px;
          }
          .extra {
            line-height: 10px;
            .count {
              margin-right: 12px;
            }
          }
          .price {
            font-weight: 700;
            line-height: 24px;
            .now {
              margin-right: 8px;
              font-size: 14px;
              color: rgb(240, 20, 20);
            }
            .old {
              text-decoration: line-through;
              font-size: 10px;
              color: rgb(147, 153, 159);
            }
          }
          .cartcontrol-wrapper {
            position: absolute;
            right: 0;
            bottom: 12px;
          }
        }
      }
    }
  }
</style>
