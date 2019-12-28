<template>
	<div>
		<!--        <el-table :data="data" :border="true" :v-loading="loading" element-loading-text="正在努力给你加载。。。">
                        <template v-for="(column, index) in columns">
                            <el-table-column :prop="column.prop"
                                             :key='column.label'
                                             :label="column.label"
                                             :align="column.align"
                                             :width="column.width"
                                             :header-align="item.headerAlign">
                            </el-table-column>
                        </template>

                </el-table>-->



		<!--<el-table
                stripe
                ref="table"
                v-loading="loading"
                element-loading-text="拼命加载中"
                element-loading-spinner="el-icon-loading"
                :data="data"
                border fit highlight-current-row
                tooltip-effect="dark"
                style="width:100%"
                @sort-change="handleSortChange"
                @selection-change="handleSelectionChange"
                @row-dblclick="rowClick"
                @row-click="rowClickOne">
            <el-table-column  v-for="(item,index) in columns"
                             :width="item.width ? item.width : ''"
                             :key="index"
                             :align="item.align"
                             :label="item.label"
                             :prop="item.prop"
                             :type="item.type"
                             :header-align="item.headerAlign"
                             >

            </el-table-column>
        </el-table>-->



		<el-table
				:stripe="stripe"
				ref="table"
				:data="data"
				v-loading="loading"
				element-loading-text="拼命加载中"
				element-loading-spinner="el-icon-loading"
				border fit highlight-current-row
				tooltip-effect="dark"
				style="width:100%"
				@sort-change="handleSortChange"
				@selection-change="handleSelectionChange"
				@row-dblclick="rowClick"
				@row-click="rowClickOne">
			<el-table-column
					v-for="(item,index) in xuhao"
					:key="index"
					:width="item.width ? item.width : ''"
					:label="item.label"
					:align="item.align"
					:type="item.type"
			>

			</el-table-column>
			<el-table-column
					:show-overflow-tooltip="true"
					v-for="(item,index) in columns"
					:width="item.width ? item.width : ''"
					:align="item.align"
					:label="item.label"
					:prop="item.prop"
					:header-align="item.headerAlign"
			>
				<template slot-scope="scope">
					<el-tag v-if="item.render" :type="scope.row.status === '0' ? 'success' : 'danger'">
						{{item.render(scope.row)}}
					</el-tag>
					<span v-else>{{scope.row[item.prop]}}</span>
				</template>
			</el-table-column>
		</el-table>

		<el-pagination
				@size-change="handleSizeChange"
				@current-change="handleCurrentChange"
				:current-page="params.page"
				:page-size="params.size"
				:layout="layout"
				:total="params.total" align="center">
		</el-pagination>

	</div>
</template>

<script>
	export default {
		name: "SelfTable",
		props:{
			stripe:{
				type:Boolean,
				default:true
			},
			loading:{
				type:Boolean,
				default:false
			},
			data:{
				type:Array,
				default: () => {
					return []
				}
			},
			columns:{
				type:Array,
				default: () => {
					return []
				}
			},
			xuhao:{
				type:Array,
				default: () => {
					return []
				}
			},
			params:{
				type:Object,
				default:()=>({page:1,size:10,total:0})
			},
			layout: {
				default() {
					return 'total, prev, pager, next';
				},
				type: String
			}
		},
		methods: {
			handleButton(methods,row,index){  // 按钮事件
				this.$emit('handleButton',{'methods':methods,'row':row,'index':index})
			},
			handleSortChange(val){  // 排序
				this.$emit('handleSortChange',val)
			},
			handleSelectionChange(val){
				this.$emit('handleSelectionChange',val);
			},
			rowClick(val){
				this.$emit('rowClick',val);
			},
			rowClickOne(val){
				this.$emit('rowClickOne',val);
			},
			handleSizeChange(val) {
				this.$emit('size-change', val);
			},
			handleCurrentChange(val) {
				this.$emit('current-change', val);
			}
		}
	}
</script>

<style scoped>

</style>