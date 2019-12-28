<template>
    <div>
        <div style="margin: 20px 5px 10px 5px;padding: 5px;">
            <el-row :gutter="20">

                <el-col :span="5">
                    <el-card shadow="never" style="border: 0rem">
                        <template slot="header">
                            <span>组织部门</span>
                            <span class="el-icon-plus" style="float: right" @click="openDepartmentWindow('addDepartmentChild',null)"></span>
                        </template>

                        <el-timeline>
                            <el-timeline-item v-for="department in department.data" :key="department.id">
                                <el-link @click="getUsers(department)" type="primary">{{department.name}}</el-link>
                                <el-tooltip class="item" effect="dark"  content="修改部门信息" placement="bottom">
                                    <span class="el-icon-edit" @click="openDepartmentWindow('addDepartmentChild',department)" style="float: right"></span>
                                </el-tooltip>
                                <el-tooltip class="item" effect="dark" content="删除部门信息" placement="bottom">
                                    <span class="el-icon-delete" style="float: right;margin-right: 20px;" @click="removeDepartment(department)"></span>
                                </el-tooltip>
                            </el-timeline-item>
                        </el-timeline>
                    </el-card>
                </el-col>


                <el-col :span="19">
                    <el-card shadow="never" style="border: 0rem">
                        <template slot="header">
                            <span>部门员工</span>
                            <el-tooltip class="item" effect="dark" content="添加员工" placement="bottom">
                                <span style="margin-left: 20px;float:right" class="el-icon-plus"  @click="openUserWindow('addUserChild',null)"></span>
                            </el-tooltip>
                        </template>

                        <el-table :data="user.data">
                            <el-table-column type="expand">
                                <template slot-scope="props">
                                    <el-form inline label-position="right" class="demo-table-expand">
                                        <el-form-item label="用户名"><span>{{props.row.username}}</span></el-form-item>
                                        <el-form-item label="密码"><span>{{props.row.password}}</span></el-form-item>
                                        <el-form-item label="等级"><span>{{props.row.level}}</span></el-form-item>
                                        <el-form-item label="角色 "><span>{{handleRole(props.row.roles)}}</span></el-form-item>
                                    </el-form>
                                </template>
                            </el-table-column>
                            <el-table-column align="center" prop="nickName" label="姓名"></el-table-column>
                            <el-table-column align="center" label="操作">
                                <template slot-scope="scope">
                                    <el-button size="mini" type="success" @click="openUserWindow('addUserChild',scope.row)"><span class="el-icon-edit"></span>修改</el-button>
                                    <el-button size="mini" type="success"> <span class="el-icon-delete"></span> 删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table>

                        <el-pagination background
                                       layout="prev,pager,next"
                                       :total="user.params.total"
                                       :page-size="user.params.size"
                                       :current-page="user.params.page"
                                       :hide-on-single-page="true"
                                       ></el-pagination>
<!--@size-change="handleSizeChange"
                                       @current-change="handlePageChange"-->

                    </el-card>
                </el-col>



            </el-row>
        </div>
        <add-department-child ref="addDepartmentChild"></add-department-child>
        <add-user-child ref="addUserChild"></add-user-child>
    </div>
</template>

<script>
    import {findAll,removeDepartment} from "@/api/access/department";
    import {userList} from "@/api/access/user";

    export default {
        name: "perm-user",
        components: {
            AddUserChild:()=>import("@/views/access/child/add-user-child.vue"),
            AddDepartmentChild:()=>import("@/views/access/child/add-department-child.vue")
        },
        data(){
            return{
                department:{
                    data:[]
                },
                user:{
                    data:[],
                    params:{page:1,size:10,total:0},
                    departmentId:''
                }
            }
        },
        methods:{
            //获得部门列表
            getDepartments(){
                findAll().then(res=>{
                    if (res.data.data){
                        this.department.data = res.data.data;
                    }
                })
            },
            //删除部门
            removeDepartment(data){
                this.$confirm("确认删除部门吗?","友情提示",{type:'warning'}).then(()=>{
                    removeDepartment(data).then(res=>{
                        this.$message({
                            type: res.data.success ? 'success' : 'error',
                            message: res.data.message
                        });
                        this.getDepartments();
                    });
                });
            },
            //修改和增加部门
            openDepartmentWindow(name,data){
                if(data == null){
                    this.$refs[name].department = {};
                }else{
                    this.$refs[name].department = data;
                }
                this.$refs[name].value = true;
            },
            //根据部门获取员工
            getUsers(department){
                this.departmentId = department.id;
                let obj = this.user.params;
                if (department){
                    obj = Object.assign({departmentId:department.id},this.user.params);
                }
                userList(obj).then(res=>{
                    if(res.data.success){
                        this.user.data = res.data.data.rows;
                        this.user.params.total = res.data.data.total;
                    }
                })
            },
            //角色查询
            handleRole(roles){
                if(roles.length > 0){
                    let roleNames = [];
                    roles.forEach(role=>{
                        roleNames.push(role.name);
                    });
                    return roleNames.join(",");
                }else{
                    return "";
                }
            },
            openUserWindow(name,data){ //打开添加,修改用户页面
                if(data == null){
                    this.$refs[name].user = {};
                }else {
                    this.$refs[name].user = data;
                }
                this.$refs[name].value = true;
            },

        },
        created() {
            this.departmentId = "";
            this.getDepartments();
        }
    }
</script>

<style scoped>
    .demo-table-expand {
        font-size: 0;
    }
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
</style>