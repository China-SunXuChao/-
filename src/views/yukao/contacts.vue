<template>
    <div>
        <el-card>
            <div align="right">
                <el-button size="mini" type="warning" plain @click="openWindowInser('addEditContacts')">  <i class="el-icon-circle-plus"></i> 新增 </el-button>
                <el-button size="mini" type="success" plain @click="openWindowUpdate('addEditContacts')">  <i class="el-icon-circle-close"></i> 修改</el-button>
                <el-button size="mini" type="success" plain @click="deleteContacts">  <i class="el-icon-circle-close"></i> 删除</el-button>

                <el-button size="mini" type="info" @click="flushs" plain>     <i class="el-icon-loading"></i> 刷新</el-button><!-- -->
            </div>
            <br>
            <self-table @rowClickOne="rowClickOne" :columns="contactsColumns" :data="contactsData" :params="contactsParams" :loading="contactsLoading"></self-table>
        </el-card>
        <add-edit-contacts ref="addEditContacts"></add-edit-contacts>
    </div>
</template>

<script>
    import {getListByUsers,deleteContacts} from "@/api/contacts/contacts";

    export default {
        name: "contacts",
        components: {
            AddEditContacts:()=>import("@/views/yukao/child/add-edit-contacts.vue")
        },
        data(){
          return{
              contactsLoading:true,
              contactsParams:{page:1,size:10,total:0},
              contactsData:[],
              contactsColumns:[
                  {
                      label:"联系人",
                      prop:"manName",
                      headerAlign:'center',
                      align:'center'
                  },
                  {
                      label:"联系人所在区域(省市)",
                      prop:"manStation",
                      headerAlign:'center',
                      align:'center'
                  },
                  {
                      label:"详细地址",
                      prop:"manAddress",
                      headerAlign:'center',
                      align:'center'
                  },
                  {
                      label:"联系人电话",
                      prop:"manTel",
                      headerAlign:'center',
                      align:'center'
                  },
                  {
                      label:"公司号",
                      prop:"cname",
                      headerAlign:'center',
                      align:'center'
                  },
                  {
                      label:"联系人类型",
                      prop:"typeName",
                      headerAlign:'center',
                      align:'center'
                  },
                  {
                      label:"操作人",
                      prop:"operateName",
                      headerAlign:'center',
                      align:'center'
                  },
                  {
                      label:"操作时间",
                      prop:"operateDateTime",
                      headerAlign:'center',
                      align:'center'
                  },
                  {
                      label:"常用程度",
                      prop:"hot",
                      headerAlign:'center',
                      align:'center'
                  }
              ],
              rowValue:{}
          }
        },
        methods:{
            rowClickOne(row){
                this.rowValue="";
                this.rowValue = row;
            },
            getListByUsers(){
                this.contactsParams.id = this.$store.state.user.access.id;
                getListByUsers(this.contactsParams).then(res=>{
                    console.log(res.data.data.rows)
                    this.contactsData = res.data.data.rows;
                    this.contactsLoading = false;
                    this.contactsParams.total = res.data.data.total;
                })
            },
            openWindowUpdate(name){
                if(this.rowValue.manName==null){
                    this.$notify.error({
                        title: '错误',
                        message: '你没有选中数据，无法修改!!'
                    });
                    return false;
                }else{
                    this.$refs[name].value=true;
                    this.$refs[name].data = this.rowValue;
                    this.rowValue={};
                }
            },
            openWindowInser(name){
                this.rowValue={};
                this.$refs[name].value=true;
                this.$refs[name].data = {};
            },
            flushs(){
                this.contactsLoading = true;
                this.contactsData=[];
                this.rowValue={};
                this.getListByUsers();
                this.$message.success("刷新成功！")
            },
            deleteContacts(){
                if(this.rowValue.manName==null){
                    this.$notify.error({
                        title: '错误',
                        message: '你没有选中数据，无法删除!!'
                    });
                    return false;
                }else{


                    this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        deleteContacts(this.rowValue).then(res=>{
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            this.getListByUsers();
                        })
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });



                }
            }
        },
        created() {
            this.getListByUsers();
        }
    }
</script>

<style scoped>

</style>