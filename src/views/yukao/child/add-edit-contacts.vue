<template>
    <div>
        <el-dialog width="800px" title="操作页面" style="background: #F0F0F0;" :close-on-click-modal="false"   :visible.sync="value" :show-close="false" :center="true">
<!---->
            <el-form ref="form" :model="data" :rules="rules"  fullscreen inline label-position="right" label-width="120px" size="mini">
                <el-form-item prop="manName" label="联系人">
                    <el-input type="text" v-model="data.manName" placeholder="请输入联系人"></el-input>
                </el-form-item>
                <el-form-item prop="manStation" label="联系人所在区域(省市)">
                    <el-input type="text" v-model="data.manStation" placeholder="请输入联系人所在区域(省市)"></el-input>
                </el-form-item>
                <el-form-item prop="manAddress" label="详细地址">
                    <el-input type="text" v-model="data.manAddress" placeholder="请输入详细地址"></el-input>
                </el-form-item>
                <el-form-item prop="manTel" label="联系人电话">
                    <el-input type="text" v-model="data.manTel" placeholder="请输入联系人电话"></el-input>
                </el-form-item>
                <el-form-item prop="cname" label="公司号">
                    <el-select style="width: 180px" v-model="data.cid" clearable filterable placeholder="请选择公司名">
                        <el-option :key="item.cid" :value="item.cid" :label="item.cname" v-for="item in copmanyData"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="typeName" label="联系人类型">
                    <el-select style="width: 180px" v-model="data.tid" clearable filterable placeholder="请选择联系人类型">
                        <el-option :key="item.tid" :value="item.tid" :label="item.typeName" v-for="item in typeData"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="hot" label="常用程度">
                    <el-input type="text" v-model="data.hot" placeholder="请输入常用程度"></el-input>
                </el-form-item>
            </el-form>

            <template slot="footer">
                <el-button type="infor" @click="value = false" size="mini">取消</el-button>
                <el-button type="success" size="mini" @click="submit">提交</el-button>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import {getCopmanyData,getTypeData,saveOrUpdate} from "@/api/contacts/contacts";

    export default {
        name: "add-edit-contacts",
        data(){
            return{
                value:false,
                data:{},
                copmanyData:[],
                typeData:[]
            }
        },
        methods:{
            submit(){
                this.data.userId = this.$store.state.user.access.id;
                this.$refs['form'].validate(valid=>{
                    if(valid){
                        saveOrUpdate(this.data).then(res=>{
                            this.$message({
                                type:res.data.success?'success':'error',
                                message:res.data.message
                            });
                            this.value = false;
                            this.$parent.getListByUsers(); //根据当前用户的departmentId来刷新当前部门的用户列表
                        });
                    }else{
                        return false;
                    }
                });
            }
        },
        watch:{
            value(data){
                if (data){
                    getCopmanyData().then(res=>{
                        this.copmanyData = res.data.data;
                    });
                    getTypeData().then(res=>{
                        this.typeData = res.data.data;
                    })
                }
            }
        },
        props:{
            manNameRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            manStationRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            manAddressRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            manTelRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            cnameRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            typeNameRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            hotRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },

        },
        computed:{
            rules(){
                return{
                    manName:this.manNameRule,
                    manStation:this.manStationRule,
                    manAddress:this.manAddressRule,
                    manTel:this.manTelRule,
                    cname:this.cnameRule,
                    typeName:this.typeNameRule,
                    hot:this.hotRule,
                }
            }
        }

    }
</script>

<style scoped>

</style>