<template>
    <div>
        <el-dialog title="权限页面" style="background: #F0F0F0;" :close-on-click-modal="false" :close-on-press-escape="false" :visible.sync="value" :show-close="false" :center="true" >
            <el-form ref="form" :model="data" label-width="80px" label-position="right" :rules="rules">
                <el-form-item label="权限名称" prop="name">
                    <el-input type="text" v-model="data.name" placeholder="请输入权限名称"></el-input>
                </el-form-item>
                <el-form-item label="权限编码" prop="code">
                    <el-input type="text" v-model="data.code" placeholder="顶级菜单外，其他均需大写 格式：权限类型-业务代码-操作类型 如:POINT-ROLE-SAVE"></el-input>
                </el-form-item>
                <el-form-item label="是否可见" prop="enVisible">
                    <el-switch v-model="data.enVisible" active-text="可见" active-value="1" inactive-text="不可见" inactive-value="0"></el-switch>
                    <span style="margin-left:20px;color:gainsboro">*温馨提示: 除超级管理员外的其他管理员是否可用该权限</span>
                </el-form-item>
                <el-form-item label="权限描述" prop="description">
                    <el-input type="textarea" v-model="data.description" clearable rows="2" placeholder="请输入权限的描述信息" show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="父级菜单" prop="pid">
                    <el-alert title="如果不选择父级菜单，则表示该表单提交的将是父级菜单" style="height: 30px;margin-bottom: 10px;" type="warning" :closable="false" show-icon></el-alert>
                    <el-tree  :default-expanded-keys="tree.expandedKeys" :current-node-key="tree.currentNodeKey" :data="treeData" node-key="id" icon-class="el-icon-watermelon" :props="defaultProps" @node-click="handleNodeClick" :highlight-current="true"></el-tree>
                </el-form-item>
            </el-form>
            <template slot="footer" class="dialog-footer">
                <el-button type="primary" @click="handleSubmit">确 定</el-button>
                <el-button @click="cancel">取  消</el-button>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import * as permissionApi from '@/api/access/permission.js'
    export default {
        name: "add-permission-child",
        data(){
            return{
                value:false,
                data:{},
                treeData:[],
                tree:{
                    expandedKeys:[],
                    currentNodeKey:''
                },
                defaultProps:{
                    children:'children',
                    label:'name'
                }
            }
        },
        methods:{
            cancel(){
                this.tree.expandedKeys=[];
                this.tree.currentNodeKey="";
                this.value=false;
                this.$parent.findAll();
            },
            //树形菜单选择事件
            handleNodeClick(data,node,target){//选中的数据，node节点，组件实例
                this.data.pid = data.id;
                if (data.pid == "0"){
                    this.data.type = data.type; //说明是添加子级菜单，和父级同类型
                }else{
                    this.data.type = data.type + 1; //当前选中的下一层
                }
            },
            //提交表单事件
            handleSubmit(){
                if (!this.data.pid){
                    //说明用户没有选择
                    this.data.pid = "0";
                    this.data.type = 1;
                }

                if (this.data.type > 3){
                    this.$message.error("API权限为最下级，不能向下添加");
                    return false;
                }

                this.$refs['form'].validate(valid=>{
                    if (valid){
                        permissionApi.saveOrUpdate(this.data).then(res=>{
                            this.$message({
                                type:res.data.success ? 'success' : 'error',
                                message:res.data.message
                            });
                            this.value = false;
                            this.$parent.findAll();
                        })
                    }else{
                        return false;
                    }
                })



            }
        },
        computed:{
            rules(){
                return {
                    name:this.nameRule,
                    code:this.codeRule,
                    description:this.descriptionRule
                }
            }
        },
        props:{
            nameRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填',trigger:'blur'}
                    ]
                }
            },
            codeRule:{
                type:Array,
                default:()=> {
                    return [
                        {required:true,message:'必填',trigger:'blur'}
                    ]
                }
            },
            descriptionRule:{
                type:Array,
                default:()=> {
                    return [
                        {required:true,message:'必填',trigger:'blur'}
                    ]
                }
            }
        },
        watch:{
            //打开页面的时候加载所有树形数据
            value(data){
                if (data){
                    permissionApi.all().then(res=>{
                        this.treeData = res.data.data;
                    })
                }
            },
            'data.id'(data){//修改时, tree数据回显
                if(data){
                    this.tree.expandedKeys.push(this.data.id);
                    this.tree.currentNodeKey = this.data.id;
                }
            }
        }
    }
</script>

<style scoped>

</style>