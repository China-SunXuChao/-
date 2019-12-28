<template>
    <div>
        <el-card>
            <div slot="header">
                <h4>日志管理</h4>
            </div>
            <self-table :data="data"  :params="params" :columns="columns" :loading="loading"></self-table>
        </el-card>
    </div>
</template>

<script>
    import {getLogMonitoring} from "@/api/log/monitoring";

    export default {
        name: "monitoring",
        data(){
            return{
                data:[],
                loading:true,
                columns:[
                    {
                        prop:"username",
                        label:"操作人",
                        headerAlign:"center",
                        align:"center"
                    },{
                        prop:"operation",
                        label:"操作描述",
                        headerAlign:"center",
                        align:"center"
                    },{
                        prop:"time",
                        label:"耗时(毫秒)",
                        headerAlign:"center",
                        align:"center"
                    },{
                        prop:"method",
                        label:"执行方法",
                        headerAlign:"center",
                        align:"center",
                    },{
                        prop:"params",
                        label:"方法参数",
                        headerAlign:"center",
                        align:"center"
                    },{
                        prop:"ip",
                        label:"IP地址",
                        headerAlign:"center",
                        align:"center"
                    },{
                        prop:"createTime",
                        label:"操作时间",
                        headerAlign:"center",
                        align:"center"
                    }
                ],
                params:{page:1,size:10,total:0},
            }
        },
        methods:{
            getLogMonitoring(data){
                getLogMonitoring(data).then(res=>{
                    this.data = res.data.data.rows;
                    this.loading = false;
                })
            }
        },
        created() {
            this.getLogMonitoring(this.params);
        }
    }
</script>

<style scoped>

</style>