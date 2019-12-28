<template>
    <div>

        <div style="margin: 20px 5px 10px 5px;padding: 5px">
            <el-button size="mini" type="success" @click="openWindow('addRoleChild',null)"><span class="el-icon-plus">新增角色</span></el-button>
        </div>

        <div :style="styleValue">
            <h4>
                权限列表如下：
            </h4>
            <el-tag :key="item" v-for="item in showArray" style="margin: 10px">{{item}}</el-tag>
        </div>


        <div style="margin: 5px;padding: 5px">
            <el-table :data="data" @row-dblclick="dbClick" v-loading="loading"
                      element-loading-text="超超正在为您拼命加载中...">
                <el-table-column type="index" align="center"></el-table-column>
                <el-table-column prop="name" label="角色名称" align="center"></el-table-column>
                <el-table-column prop="description" label="角色描述" align="center"></el-table-column>
                <el-table-column label="操作" align="center">
                    <template slot-scope="scope">
                        <el-button type="success" size="mini" @click="openWindow('addRoleChild',scope.row)">
                            <span class="el-icon-edit"></span>修改
                        </el-button>
                        <el-button type="success" size="mini" @click="remove(scope.row)">
                            <span class="el-icon-delete"></span>删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <div style="margin: 10px;text-align: center;">
            <el-pagination
                    background
                    layout="prev,pager,next"
                    :total="params.total"
                    :page-size="params.size"
                    :current-page="params.page"
                    :hide-on-single-page="true"
                    @size-change="handleSizeChange"
                    @current-change="handlePageChange"
            ></el-pagination>
        </div>

        <add-role-child ref="addRoleChild"></add-role-child>
    </div>
</template>

<script>
    import {list} from "@/api/access/role";

    export default {
        name: "perm-role",
        components: {
            AddRoleChild:()=>import("@/views/access/child/add-role-child.vue")
        },
        data(){
            return{
                loading:true,
                data:[],
                params:{page:1,size:6,total:0},
                styleValue:{
                    marginTop:'20px',
                    height:'100px',
                    display:'none'
                },
                showArray:[]
            }
        },
        methods:{
            getList(data){
                list(data).then(res=>{
                    if (res.data.success){
                        this.data = res.data.data.rows;
                        this.params.total = res.data.data.total;
                        this.loading =false;
                    }
                })
            },
            handleSizeChange(val){
                this.params.size = val;
                this.getList(this.params);
            },
            handlePageChange(val){
                this.params.page = val;
                this.getList(this.params);
            },
            remove(data){
                this.$message.warning("抱歉,暂时还没有删除功能");
                return false;
            },
            dbClick(row,column,event){
                let arr = [];
                row.permissions.forEach(permission=>{
                    arr.push(permission.name);
                })
                this.showArray = arr;
                if (this.showArray.length > 0){
                    this.styleValue.display = 'block';
                }else{
                    this.$message.warning("你选中的角色没有任何权限！")
                    this.styleValue.display = 'none';
                }
            },
            openWindow(name,data){
                if(data == null){
                    this.$refs[name].data = {};
                }else{
                    this.$refs[name].data = data;
                }
                this.$refs[name].value = true;
            }
        },
        created() {
            this.styleValue.display = 'none';
            this.getList(this.params);
        }
    }
</script>

<style scoped>

</style>