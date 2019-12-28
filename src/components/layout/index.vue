<template>
	<div id="index" >
		<el-container>
			<el-header>
				<el-row>
					<el-col :span="13">
						<el-row>
							<el-col :span="4"><h3 class="close_margin" @click="ClickisCollapse">LOGO</h3></el-col>
							<el-col :span="20" align="right"><a href="/base/Homes"><h3 class="close_margin">管理系统</h3></a></el-col>
						</el-row>
					</el-col>
					<el-col :span="10">
						<el-row type="flex" justify="end">
							<!--<el-col :span="18"><h4 class="close_margin">{{this.$store.state.user.access.nickName}}，欢迎您！</h4></el-col>-->
							<el-col :span="20">
								<div class="close_margin">
									<div style="margin-top: 10px;position: absolute;right: 250px">
										<el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar><strong style="width: 160px;position: absolute;right: -165px;top: -8px">{{this.$store.state.user.access.nickName}}，欢迎您！</strong>
									</div>
								</div>
							</el-col>
							<el-col :span="3">
								<el-dropdown trigger="click" class="close_margin">
									<h4 class="el-dropdown-link close_margin">
										个人设置<i class="el-icon-arrow-down el-icon--right"></i>
										<el-dropdown-menu slot="dropdown">
											<el-dropdown-item>修改密码</el-dropdown-item>
											<el-dropdown-item><span @click="out">退出账号</span></el-dropdown-item>
										</el-dropdown-menu>
									</h4>
								</el-dropdown>
							</el-col>
						</el-row>
					</el-col>
				</el-row>
			</el-header>
			<el-container style="height: 660px;">
				<el-aside width="200px" style="height: 880px;">
					<el-menu
							:collapse-transition="true"
							unique-opened
							:default-active="defaultActive"
							@select="menuSelect"
							default-active="2"
							class="el-menu-vertical-demo" router
							editable
							:collapse="isCollapse">
						<el-submenu v-for="(item,index) in computed_totalRoutes" :index="index+''">
							<template slot="title">
								<i style="size: 2px" :class="item.meta.icon"></i>
								<span>{{item.meta.title}}</span>
							</template>
							<el-menu-item
									v-for="child in item.children"
									:route="item.path +'/'+ child.path"
									:index="child.name+''"
									@click="addTab(child)">

								{{child.meta.title}}
							</el-menu-item>
						</el-submenu>
					</el-menu>
				</el-aside>

				<el-main style="height: 880px">
					<!-- 标签页（选项卡） -->
					<div class="mycard">
						<el-tabs
								v-model="editableTabsValue"
								type="card" closable
								@tab-remove="removeTab"
								class="eltabs"
								@tab-click="onClickTab">

							<!--tab 被选中时触发,被选中的标签 tab 实例-->
							<el-tab-pane
									v-for="(item,index) in editableTabs"
									:key="item.name"
									:label="item.title"
									:name="item.name">
							</el-tab-pane>

						</el-tabs>
					</div>
					<router-view></router-view>
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>

<script>
	import {setToken} from "@/utils/tools";
	import { mapActions } from 'vuex';
	export default {
		name: "",
		data() {
			return{
				preMenus:[],
				totalRoutes:[],
				gcount:3,
				xcount:1,

				defaultActive: 'home', //当前激活菜单的 index
				mSelect: 'home', //记录当前选中的菜单title
				editableTabsValue: 'home', //当前选中的标签页的name
				tabarrs: ["home"], //保存标签页的每一个对象的名字
				editableTabs: [{
					title: '首页', //初始化的标签页--首页
					name: 'home',
					content: ''
				}],
				counttab: 0, //删除标签页时计数器
				confirmtab: 0, //当前删除标签页的记录数
				tabIndex: 2,
				isCollapse:false,
			}
		},
		methods:{
			...mapActions([
				'logout'
			]),
			//侧边栏的展开与收起
			ClickisCollapse() {
				this.isCollapse = this.isCollapse ? false :true;
			},

			setCookie: function (cname, cvalue, exdays) {
				var d = new Date();
				d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
				var expires = "expires=" + d.toUTCString();
				console.info(cname + "=" + cvalue + "; " + expires);
				document.cookie = cname + "=" + cvalue + "; " + expires;
			},
			clearCookie: function () {
				this.setCookie("token", "", -1);
			},
//获取cookie
			getCookie: function (cname) {
				var name = cname + "=";
				var ca = document.cookie.split(';');
				for (var i = 0; i < ca.length; i++) {
					var c = ca[i];
					while (c.charAt(0) == ' ') c = c.substring(1);
					if (c.indexOf(name) != -1){
						return c.substring(name.length, c.length);
					}
				}
				return "111";
			},
//退出账号
			out() {
				this.$confirm('确认要退出超超Bos物流系统吗？').then(_ => {
					var token = this.getCookie("token")
					if(null!=token){
						var i = this.clearCookie();
						this.$router.push('/login');
					}
				}).catch(_ => {});


				//Cookies.remove('name');
			},
			menuSelect(index, indexPath) { //菜单激活回调 index: 选中菜单项的 index, indexPath: 选中菜单项的 index path
				//给记录当前选中的菜单title赋值
				this.mSelect = index;
			},
			onClickTab(val) { //点击标签页触发
				//跳转页面
				this.$router.push({
					name: val.name
				})
				//选中左侧菜单
				this.defaultActive = val.name;
				//给记录当前选中的菜单title赋值
				this.mSelect = val.name;
			},
			he(val) { //左菜单的展示和隐藏
				if (!val) {
					this.zsideWidth = '56px';
				} else {
					this.zsideWidth = '220px';
				}
				this.isCollapse = !val;
			},
			addTab(child) { //添加标签页
				if (this.tabarrs.indexOf(child.name) == -1) { //不存在则添加
					this.editableTabs.push({
						title: child.meta.title,
						name: child.name,
						content: child.name
					});
					this.tabarrs.push(child.name);
				}
				this.editableTabsValue = child.name;
				this.$router.push({
					name: child.name
				})
			},

			removeTab(targetName) {
				let tabs = this.editableTabs;
				//把当前选中的菜单title和要关闭的标签页name作比较
				if (this.mSelect.trim() === targetName.trim()) {
					//进去说明点击删除时当前页面就在要删除的标签页上
					//拿到选项卡数组遍历
					this.editableTabs.forEach(item => {
						this.counttab++;
						if (item.name == targetName.trim()) {

							this.confirmtab = this.counttab;
						}
					});

					if (this.editableTabs.length == this.confirmtab) { //说明是最后一个标签页
						this.$router.push({
							name: this.editableTabs[this.confirmtab - 2].name
						})
						this.mSelect = this.editableTabs[this.confirmtab - 2].name
						this.editableTabsValue = this.editableTabs[this.confirmtab - 2].name;
						this.defaultActive = this.editableTabs[this.confirmtab - 2].name;
					} else {
						this.$router.push({
							name: this.editableTabs[this.confirmtab].name
						})
						this.mSelect = this.editableTabs[this.confirmtab].name
						this.editableTabsValue = this.editableTabs[this.confirmtab].name;
						this.defaultActive = this.editableTabs[this.confirmtab].name;
					}
				}
				//把要删除的标签页的name从自定义保存标签页name的数组中删除
				this.tabarrs = this.tabarrs.filter(item => item != targetName);
				//页面上的数组也要删除
				this.editableTabs = tabs.filter(tab => tab.name !== targetName);
				//清空两个值
				this.counttab = 0;
				this.confirmtab = 0
			},
		},
		computed:{
			computed_totalRoutes() {
				let arr = [],childArr =  [];
				let that=this;
				this.menu.forEach(item=>{
					if(that.preMenus.indexOf(item.name)!= -1){
						//遍历子级
						if(item.children){
							childArr = [];
							item.children.forEach(subItem=>{
								if(that.preMenus.indexOf(subItem.name) != -1){
									childArr.push(subItem);
								}
							})
						}
						item.children = childArr;
						arr.push(item);
					}

				})
				return arr;
			}
		},
		mounted() {
			this.totalRoutes = this.$router.options.routes;
			return this.totalRoutes;
		},
		created() {
			console.log(this.$store.state.user.access.roles.menus)
			this.preMenus = this.$store.state.user.access.roles.menus;//菜单
			this.menu = this.$router.options.routes;
			this.$router.push('/');
		},

	}
</script>

<style scoped>
	/* 首页选项卡没有关闭按钮 */
	.el-tabs__nav .el-tabs__item:nth-child(1) span {
		display: none;
	}
	/* 所有的选项卡的间距设置 */
	.el-tabs__nav .el-tabs__item:nth-child(n) {
		margin: 3px !important;
		border: 1px solid !important;
	}

	.close_margin{
        color: white;
		margin: 0px;
		padding: 0px;
	}
	el-menu-item {
		text-align: left;
	}
	.el-header, .el-footer {
		background-image:url(../../assets/img/login-bg.jpg);
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	.el-aside {
		background-color: aliceblue;
		color: #333;
		text-align: center;
		line-height: 200px;
		height: 660px;
	}

    .el-menu-vertical-demo{
        background-color: #F0F0F0;
    }

	.el-main {
		background-color: ghostwhite;
		color: #333;
		text-align: center;
		/*line-height: 160px;*/
	}

	body > .el-container {
		margin-bottom: 40px;
	}

	.el-container:nth-child(5) .el-aside,
	.el-container:nth-child(6) .el-aside {
		line-height: 260px;
	}

	.el-container:nth-child(7) .el-aside {
		line-height: 320px;
	}

</style>
<style>



	/* 首页选项卡没有关闭按钮 */
	.el-tabs__nav .el-tabs__item:nth-child(1) span {
		display: none;
	}




</style>