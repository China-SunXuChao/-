<template>
    <div>
        <el-dialog title="用户页面" style="background: #F0F0F0;" :close-on-click-modal="false"   :visible.sync="value" :show-close="false" :center="true">

            <el-form ref="form" :model="user" fullscreen inline :rules="rules" label-position="right" label-width="120px" size="mini">
                <el-form-item prop="nickName" label="用户姓名">
                    <el-input type="text" v-model="user.nickName" placeholder="请输入用户姓名"></el-input>
                </el-form-item>
                <el-form-item prop="departmentId" label="选择部门">
                    <el-select v-model="user.departmentId" clearable filterable placeholder="请选择部门">
                        <el-option :key="item.id" :value="item.id" :label="item.name" v-for="item in departmentData"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item prop="telephone" label="电话号码">
                    <el-input type="text" v-model="user.telephone" placeholder="请输入电话号码"></el-input>
                </el-form-item>
                <el-form-item prop="email" label="用户邮箱">
                    <el-input type="text" v-model="user.email" placeholder="请输入E-mail"></el-input>
                </el-form-item>

                <el-form-item prop="sex" label="用户性别">
                    <el-radio-group v-model="user.sex">
                        <el-radio :label="'1'">男</el-radio>
                        <el-radio :label="'2'">女</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item prop="residenceAddress" label="户籍住址">
                    <el-input type="text" v-model="user.residenceAddress" placeholder="请输入户籍地址"></el-input>
                </el-form-item>
                <el-form-item prop="nowAddress" label="现居住址">
                    <el-input type="text" v-model="user.nowAddress" placeholder="请输入现居地址"></el-input>
                </el-form-item>

                <el-form-item prop="type" label="用户类型">
                    <el-radio-group v-model="user.type">
                        <el-radio :label="'1'">员工</el-radio>
                        <el-radio :label="'2'" disabled>老板</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item prop="idCard" label="身份证号">
                    <el-input type="text" v-model="user.idCard" placeholder="请输入身份证号"></el-input>
                </el-form-item>

                <el-form-item prop="birthday" label="用户生日">
                    <el-date-picker v-model="user.birthday" type="datetime" placeholder="选择日期" size="mini"></el-date-picker>
                </el-form-item>

                <el-form-item prop="education" label="用户学历">
                    <!-- <el-select v-model="user.education" clearable filterable placeholder="请选择学历">
                        <el-option :value="1" :label="初中"></el-option>
                        <el-option :value="2" :label="高中"></el-option>
                        <el-option :value="3" :label="中专"></el-option>
                        <el-option :value="4" :label="大专"></el-option>
                        <el-option :value="5" :label="本科及以上"></el-option>
                    </el-select> -->

                </el-form-item>

                <el-form-item prop="graduationSchool" label="毕业院校">
                    <el-input type="text" v-model="user.graduationSchool" placeholder="请输入毕业院校"></el-input>
                </el-form-item>

                <el-form-item prop="major" label="用户专业">
                    <el-radio-group v-model="user.major">
                        <el-radio :label="'1'">计算机专业</el-radio>
                        <el-radio :label="'2'">其他专业</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item prop="emergencyContact" label="紧急联系">
                    <el-input type="text" v-model="user.emergencyContact" placeholder="请输入紧急联系人"></el-input>
                </el-form-item>

                <el-form-item prop="relation" label="姓名电话">
                    <el-input type="text" v-model="user.relation" placeholder="请输入紧急联系人姓名和电话"></el-input>
                </el-form-item>

                <el-form-item prop="jobTitle" label="职位名称">
                    <el-select v-model="user.jobTitle" clearable filterable placeholder="请选择职位">
                        <el-option :key="item.id" :value="item.id" :label="item.name" v-for="item in jobData"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="entryTime" label="入职日期">
                    <el-date-picker v-model="user.entryTime" type="datetime" placeholder="选择日期" size="mini"></el-date-picker>
                </el-form-item>
                <el-form-item prop="hasMember" label="是否转正">
                    <el-radio-group v-model="user.hasMember">
                        <el-radio :label="'1'">转正</el-radio>
                        <el-radio :label="'2'">不转正</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="jobNumber" label="用户工龄">
                    <el-input-number v-model="user.jobNumber" :min="1" :max="30" label="请输入工龄"></el-input-number>
                </el-form-item>
                <el-form-item prop="laborContract" label="劳动合同">
                    <el-radio-group v-model="user.laborContract">
                        <el-radio :label="'1'">已办理</el-radio>
                        <el-radio :label="'2'">未办理</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="contractTime" label="签订日期">
                    <el-date-picker v-model="user.contractTime" type="datetime" placeholder="选择日期" size="mini"></el-date-picker>
                </el-form-item>

                <el-form-item prop="contractNumber" label="续签天数">
                    <el-input-number v-model="user.contractNumber" :min="1" :max="366" label="按天统计"></el-input-number>
                </el-form-item>


                <el-form-item prop="hasSocialSecurity" label="是否社保">
                    <el-radio-group v-model="user.hasSocialSecurity">
                        <el-radio :label="'1'">有</el-radio>
                        <el-radio :label="'2'">无</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="hasLeave" label="是否离职">
                    <el-radio-group v-model="user.hasLeave">
                        <el-radio :label="'1'">未离职</el-radio>
                        <el-radio :label="'2'">已离职</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item prop="roleIds" label="分配角色">
                    <el-checkbox-group v-model="checkList">
                        <el-checkbox :key="item.id" :label="item.id" v-for="item in roleData">{{item.name}}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>


            </el-form>
            <template slot="footer">
                <el-button type="infor" @click="value = false">取消</el-button>
                <el-button type="success" @click="handleSubmit">提交</el-button>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import {findAll} from "../../../api/access/department";
    import {getRoles} from "../../../api/access/role";
    import {getDictionariesByCode} from "../../../api/system/dictionaries";
    import {saveOrUpdate} from "../../../api/access/user";

    export default {
        name: "add-user-child",
        data(){
            return {
                value : false,
                user:{},
                departmentData:[],
                roleData:[],
                jobData:[],
                checkList:[]
            }
        },
        props:{
            nickNameRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            departmentIdRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            },
            telephoneRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            emailRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            sexRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            },
            residenceAddressRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            nowAddressRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            typeRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            },
            idCardRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            birthdayRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            },
            graduationSchoolRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            majorRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            },
            emergencyContactRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            relationRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            jobTitleRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            },
            entryTimeRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            },
            hasMemberRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            },
            jobNumberRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            laborContractRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            },
            contractTimeRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            },
            contractNumberRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必填项',trigger:'blur'}
                    ]
                }
            },
            hasSocialSecurityRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            },
            hasLeaveRule:{
                type:Array,
                default:()=>{
                    return [
                        {required:true,message:'必选项',trigger:'change'}
                    ]
                }
            }
        },
        computed:{
            rules(){
                return {
                    nickName:this.nickNameRule,
                    departmentId:this.departmentIdRule,
                    telephone: this.telephoneRule,
                    email: this.emailRule,
                    sex: this.sexRule,
                    residenceAddress: this.residenceAddressRule,
                    nowAddress: this.nowAddressRule,
                    type: this.typeRule,
                    idCard: this.idCardRule,
                    birthday:this.birthdayRule,
                    graduationSchool:this.graduationSchoolRule,
                    major:this.majorRule,
                    emergencyContact:this.emergencyContactRule,
                    relation:this.relationRule,
                    jobTitle:this.jobTitleRule,
                    entryTime:this.entryTimeRule,
                    hasMember:this.hasMemberRule,
                    jobNumber:this.jobNumberRule,
                    laborContract:this.laborContractRule,
                    contractTime:this.contractTimeRule,
                    contractNumber:this.contractNumberRule,
                    hasSocialSecurity:this.hasSocialSecurityRule,
                    hasLeave:this.hasLeaveRule
                }
            }
        },
        watch:{
            value(data){
                if(data){
                    findAll().then(res=>{
                        this.departmentData = res.data.data;
                    });
                    getRoles().then(res=>{
                        this.roleData = res.data.data;
                    });

                    getDictionariesByCode({code:'JOB-TITLE'}).then(res=>{
                        this.jobData = res.data.data;
                    });

                    //处理角色多选框的数据回显
                    this.checkList = [];
                    if(this.user.roles && this.user.roles.length > 0){
                        this.user.roles.forEach(role=>{
                            this.checkList.push(role.id);
                        });
                    }
                }
            }
        },
        methods:{
            handleSubmit(){
                this.$refs['form'].validate(valid=>{
                    if(valid){

                        if(this.checkList.length == 0){
                            this.$message.error("请为用户分配角色");
                            return false;
                        }

                        this.user.roleIds = this.checkList.join(",");
                        saveOrUpdate(this.user).then(res=>{
                            this.$message({
                                type:res.data.success?'success':'error',
                                message:res.data.message
                            });
                            this.value = false;
                            //this.$parent.getDepartments();
                            this.$parent.getUsers({id:this.user.departmentId}); //根据当前用户的departmentId来刷新当前部门的用户列表
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