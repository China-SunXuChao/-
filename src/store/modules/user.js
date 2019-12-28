//引入接口
import { login,getUserInfo  } from '@/api/login.js'

//校验方式  以token为主  需要引入操作token的工具类
import { getToken,setToken  } from '@/utils/tools.js'
import { stat } from 'fs';

// state的数据 由mutations来修改  mutations的方法由actions来调用



//存储数据的对象   初始化token值和access鉴权校验值
const state = {
    token: getToken(),
    access: '' //鉴权后的结果只
};


//vuex中的state数据,只能通过mutations来修改 其他方式均不可


//修改token的值 这里面是方法
const mutations = {
    SET_TOKEN(state,token){
        state.token = token;  //把传入的token的值赋值给state对象中的token
        setToken(token);    //把传入的token存入cookie
    },
    SET_ACCESS(state,access){
        state.access = access;
    },
    UN_SET_TOKEN(state,data){
        state.token = data;
        setToken(data);
        state.access = data;
    }
};



const actions = {
    handleLogin({commit},{username,password}){
        return new Promise((resolve,reject)=>{
            login({username,password}).then(res=>{
                if(res.data.code === 10000){
                    //登录成功之后我们需要把token存入到vuex中
                    commit('SET_TOKEN',res.data.data); //token = res.data.data;
                    resolve();
                }else{
                    alert(res.data.message);
                    return false;
                }
            });
        });
    },
    getUserInfo({state,commit}){
        return new Promise((resolve,reject)=>{
            try{
                getUserInfo().then(res=>{
                    commit('SET_ACCESS',res.data.data);
                    resolve(res.data.data);
                });
            }catch(e){
                reject(e);
            };
        });
    },

    logout({commit}){
        commit('UN_SET_TOKEN','');
    }
};

const getters = {};


export default {
    state,
    actions,
    getters,
    mutations
}