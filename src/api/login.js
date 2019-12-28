import axios from '@/libs/api.request.js'


//登录方法
export const login = data =>{
    return axios.request({
        method:'post',
        url:'user/login',
        data:data
    });
}


//登录之后的鉴权方法
export const getUserInfo = () =>{
    return axios.request({
        url:'user/profile',
        data:null,
        method:'get'
    });
}

export const logout = () =>{
    return axios.request({
        url:`user/logout`,
        data:null,
        method:'get'
    });
};
