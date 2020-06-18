import Vue from 'vue'
import Router from 'vue-router'
import MainPage from './views/MainPage.vue'
import JoinPage from './views/JoinPage.vue'
import MyPage from './views/MyPage.vue'
import CreatePage from './views/CreatePage.vue'
import DetailPage from './views/DetailPage.vue'
import RegistHackaton from './components/yoon/RegistHacktonPage.vue';
import RegistMemberPage from './components/yoon/RegistMemberPage.vue';
import PageNotFound from  './components/yoon/PageNotFound.vue';
import ApplyHackatonPage from './components/yoon/ApplyHackatonPage.vue';
import AboutUsPage from './components/seo/AboutUsPage.vue';
import NoticePage from './components/seo/NoticePage.vue';
import MyInfoPage from  './components/yoon/MyInfoPage.vue';
Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes:[
        {
            path: '/',
            name: 'home',
            component: MainPage
        },
        {
            path:'/join',
            name: 'join',
            component: JoinPage
        },
        {
            path:'/my',
            name: 'Info',
            component: MyPage
        },
        {
            path:'/create',
            name:'Create',
            component: CreatePage
        },
        {
            path:'/detail/',
            name:'detailpage',
            component: DetailPage
        },
        {
            path:'/registhackaton',
            name:'registhackaton',
            component: RegistHackaton
        },
        {
            path:'/registmemberpage',
            name:'registmemberpage',
            component: RegistMemberPage
        },
        {
            path : '*',
            redirect: '/404'
        },
        {
            path:'/404',
            component : PageNotFound
        },
        {
            path:'/',
            component : PageNotFound
        },
        {
            path :'/applyhackatonpage',
            name : 'applyhackatonpage',
            component : ApplyHackatonPage
        },
        {
            path:'/aboutuspage',
            name:'aboutuspage',
            component: AboutUsPage
        },
        {
            path:'/noticepage',
            name: 'noticepage',
            component: NoticePage
        },
        {
            path : '/myinfopage',
            name : 'myinfopage',
            component : MyInfoPage 
        }

    ]
})