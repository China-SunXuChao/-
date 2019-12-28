<template>
    <div>
        <div style="margin: 20px 5px 10px 5px;padding: 5px;">
            <el-button size="mini" @click="openWindow('addPermissionChild',null)" type="success"><span class="el-icon-plus"></span>新增菜单</el-button>
        </div>



        <div style="margin: 5px;padding: 5px;">
            <el-table :data="data" row-key="id" :tree-props="{children:'children'}" v-loading="loading"
                      element-loading-text="超超正在为您拼命加载中...">
                <el-table-column type="index"></el-table-column>
                <el-table-column label="权限名称" prop="name" align="center">
                    <template slot-scope="scope">
                        <span :style="{'color':(scope.row.type == 1 && scope.row.pid == 0) ? '#67C23A' : (scope.row.type==1&&scope.row.pid!=0)? '#E6A23C' :( scope.row.type == 2 ? '#F56C6C' : '#909399' )}">{{scope.row.name}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="权限编码" prop="code" align="center">
                    <template slot-scope="scope">
                        <span :style="{'color': (scope.row.type == 1 && scope.row.pid == 0) ? '#67C23A' :(scope.row.type == 1 && scope.row.pid != 0) ? '#E6A23C' : ( scope.row.type == 2 ? '#F56C6C' : '#909399' ) }">{{scope.row.code}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="权限类型" prop="type" align="center">
                    <template slot-scope="scope">
						<span :style="{'color': (scope.row.type == 1 && scope.row.pid == 0) ? '#67C23A' :(scope.row.type == 1 && scope.row.pid != 0) ? '#E6A23C' : ( scope.row.type == 2 ? '#F56C6C' : '#909399' ) }">
						{{(scope.row.type == '1' && scope.row.pid == 0 ) ? '顶级菜单' : ((scope.row.type == '1' && scope.row.pid != 0) ? "子级菜单" : ( scope.row.type == '2' ? "按钮" : "API"))}}
						</span>
                    </template>
                </el-table-column>
                <el-table-column label="是否可见" prop="enVisible" align="center" >
                    <template slot-scope="scope">
                        <span :style="{'color': (scope.row.type == 1 && scope.row.pid == 0) ? '#67C23A' :(scope.row.type == 1 && scope.row.pid != 0) ? '#E6A23C' : ( scope.row.type == 2 ? '#F56C6C' : '#909399' ) }">{{scope.row.enVisible == 1 ? '可见':'不可见'}}</span>
                    </template>
                </el-table-column>

                <el-table-column label="操作" align="center">
                    <template slot-scope="scope">
                        <el-button @click="openWindow('addPermissionChild',scope.row)" :type="(scope.row.type == 1 && scope.row.pid == 0) ? 'success' :(scope.row.type == 1 && scope.row.pid != 0) ? 'warning' : ( scope.row.type == 2 ? 'danger' : 'info' ) " size="mini">
                            <span class="el-icon-edit"></span>修改
                        </el-button>

                        <el-button @click="remove(scope.row)" :type="(scope.row.type == 1 && scope.row.pid == 0) ? 'success' :(scope.row.type == 1 && scope.row.pid != 0) ? 'warning' : ( scope.row.type == 2 ? 'danger' : 'info' ) " size="mini">
                            <span class="el-icon-delete"></span>删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <add-permission-child ref="addPermissionChild"></add-permission-child>
        </div>



       <!-- <div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="活动性质" prop="type">
                    <el-checkbox-group v-model="ruleForm.type">
                        <el-checkbox label="美食/餐厅线上活动">A</el-checkbox>
                        <el-checkbox label="地推活动">B</el-checkbox>
                        <el-checkbox label="线下主题活动">C</el-checkbox>
                        <el-checkbox label="单纯品牌曝光">D</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
            </el-form>
        </div>-->

    </div>
</template>

<script>
    import * as permissionApi from '@/api/access/permission.js'
    import {hasOneOf} from "../../router";
    export default {
        name: "perm-permission",
        components:{
            AddPermissionChild:()=>import("@/views/access/child/add-permission-child.vue")
        },
        data(){
            return{
                data:[],
                loading:true,
                /*ruleForm: {
                    type: []
                },
                rules: {
                    type: [
                        { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
                    ],
                }*/
            }
        },
        methods:{
            findAll(){
                permissionApi.all().then(res=>{
                    this.data = res.data.data;
                    this.loading=false;
                })
            },
            remove(data){
                //查看是否有子类，如果有不能删除
                if (data.children && data.children.length>0){
                    this.$message.error("有子类无法删除");
                    return false;
                }

                this.$confirm("确定要删除吗?","友情提示",{type:'warning'}).then(()=>{
                    permissionApi.remove({id:data.id}).then(res=>{
                        this.$message({
                            type:res.data.success?'success':'error',
                            message:res.data.message
                        });
                        this.findAll();
                    });
                });
            },
            openWindow(name,data){
                if (data == null){
                    this.$refs[name].data={};
                }else{
                    this.$refs[name].tree = {expandedKeys:[],currentNodeKey:''};
                    console.log(data);
                    this.$refs[name].data=data;
                }
                this.$refs[name].value=true;
            }
        },
        created() {
            this.findAll();
        }
    }
</script>

<style scoped>

</style>