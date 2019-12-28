<template>
    <div>
        <el-dialog title="角色信息" style="background: #F0F0F0;" :close-on-click-modal="false" :close-on-press-escape="false"  :visible.sync="value" :show-close="false" :center="true">
            <el-form ref="form" :rules="rules" :model="data" label-position="right" label-width="80px">
                <el-form-item prop="name" label="角色名称">
                    <el-input type="text" v-model="data.name" placeholder="请输入角色名称"></el-input>
                </el-form-item>

                <el-form-item prop="description" label="角色描述">
                    <el-input type="textarea" rows="2" v-model="data.description" placeholder="请输入角色描述"></el-input>
                </el-form-item>

                <el-form-item label="分配权限">
                    <el-tree
                        ref="tree"
                        show-checkbox
                        :data="tree.treeData"
                        highlight-current
                        icon-class="el-icon-s-flag"
                        :props="tree.defaultProps"
                        check-on-click-node
                        @check-change="treeChange"
                        node-key="id"
                        :default-expanded-keys="tree.expandedKeys"
                        :default-checked-keys="tree.checkedKeys">
                    </el-tree>
                </el-form-item>
            </el-form>
            <template slot="footer">
                <el-button type="success" @click="handleSubmit(data)">提交</el-button>
                <el-button type="info" @click="value=false">取消</el-button>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import {saveOrUpdate} from "../../../api/access/role";
    import {all} from "../../../api/access/permission";

    export default {
        name: "add-role-child",
        data(){
            return{
                value:false,
                data:{},
                tree:{
                    treeData:[], //tree数据
                    defaultProps: { //tree数据格式化
                        children: 'children',
                        label: 'name'
                    },
                    expandedKeys:[], //用于tree数据回显
                    checkedKeys:[] //用于tree数据回显
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
            descriptionRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填',trigger:'blur'}
                    ]
                }
            }
        },
        computed:{
            rules(){
                return {
                    name:this.nameRule,
                    description:this.descriptionRule
                }
            }
        },
        watch:{
          value(data){
              if (data){
                  all().then(res=>{
                      this.tree.treeData = res.data.data;
                      //用于修改:tree选中的数据回显
                      let treekeys = [];
                      if (this.data.permissions && this.data.permissions.length > 0){
                          this.data.permissions.forEach(permission=>{
                             treekeys.push(permission.id);
                          });
                      }
                      this.tree.expandedKeys = treekeys;
                      this.tree.checkedKeys = treekeys;
                  })
              }
          }
        },
        methods:{
            treeChange(){
                this.data.permissionIds = this.$refs.tree.getCheckedKeys();
            },
            handleSubmit(data){
                this.$refs['form'].validate(valid=>{
                    if (valid){
                        if (!this.data.permissionIds){
                            this.$message.error("请分配权限");
                            return  false;
                        }

                        if (this.data.permissionIds.length == 0){
                            this.$message.error("请分配权限");
                            return false;
                        }

                        this.data.permissionIds = this.data.permissionIds.join(","); //关键 1,2,3,4,5

                        saveOrUpdate(this.data).then(res=>{
                            this.$message({
                                type:res.data.success ? 'success' : 'error',
                                message:res.data.message
                            });
                            this.value = false;
                            this.$parent.getList(this.$parent.params);
                        })

                    }else{
                        return false;
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>