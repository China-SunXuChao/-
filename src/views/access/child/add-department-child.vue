<template>
    <div>
        <el-dialog title="部门页面" style="background: #F0F0F0;" :close-on-click-modal="false" :close-on-press-escape="false"  :visible.sync="value" :show-close="false" :center="true">
            <el-form ref="form" :rules="rules" :model="department" label-position="right" label-width="80px">
                <el-form-item prop="name" label="部门名称">
                    <el-input type="text" v-model="department.name" placeholder="请输入部门名称"></el-input>
                </el-form-item>


            </el-form>




            <template slot="footer">
                <el-button type="infor" @click="value = false">取消</el-button>
                <el-button type="success" @click="handleSubmit(department)">提交</el-button>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import {saveOrUpdate} from "@/api/access/department";

    export default {
        name: "add-department-child",
        data(){
            return{
                value:false,
                department: {}
            }
        },
        props:{
            nameRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            }
        },
        computed:{
            rules(){
                return {
                    name : this.nameRule
                }
            }
        },
        methods:{
            handleSubmit(department){
                this.$refs['form'].validate(valid=>{
                    if(valid){
                        saveOrUpdate(this.department).then(res=>{
                            this.$message({
                                type:res.data.success ? 'success' : 'error',
                                message:res.data.message
                            });
                            this.value = false;
                            this.$parent.getDepartments();
                        });
                    }else{
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>