import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/layout/index.vue'

Vue.use(Router)

export default [
	{
		path:'/login',
		name:'login',
		component:()=>import("@/components/login/Login.vue"),
		meta:{
			title:'登录页面',
			hideInMenu : true,
			isParent: false,
			icon:''
		}
	},
	{
		path: '/',
		name: 'home',
		component: Home,
		meta: {
			title: '首页',
			hideInMenu: true
		}
	},
	{
		path: '/systemmanage',
		name: 'systemmanage',
		component: Home,
		meta: {
			title: '系统管理',
			hideInMenu: false,
			isParent: true,
			icon:'el-icon-setting'
		},
		children:[
			{
				path: 'permpermission',
				name: 'permpermission',
				component:()=>import('@/views/access/perm-permission.vue'),
				meta: {
					title: '权限管理',
					hideInMenu: false,
					isParent: false,
					icon:'el-icon-location'
				}
			},
			{
				path: 'permrole',
				name: 'permrole',
				component:()=>import("@/views/access/perm-role.vue"),
				meta: {
					title: '角色管理',
					hideInMenu: false,
					isParent: false,
					icon:'el-icon-location'
				}
			},
			{
				path: 'permuser',
				name: 'permuser',
				component:()=>import("@/views/access/perm-user.vue"),
				meta: {
					title: '用户管理',
					hideInMenu: false,
					isParent: false,
					icon:'el-icon-location'
				}
			}
		]
	},
	{
		path: '/yukao',
		name: 'yukao',
		component: Home,
		meta: {
			title: '月考',
			hideInMenu: false,
			isParent: true,
			icon:'el-icon-share'
		},
		children:[
			{
				path: 'contacts',
				name: 'contacts',
				component:()=>import('@/views/yukao/contacts.vue'),
				meta: {
					title: '联系人',
					hideInMenu: false,
					isParent: false,
					icon:'el-icon-view'
				}
			}
		]
	}

]
