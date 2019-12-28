import axios from "../../libs/api.request";

export const getStandardList = data =>{
    return axios.request({
        url:"/standard/list",
        method:"post",
        data:data
    })
}

export const getSnameData = data =>{
    return axios.request({
        url:"/standard/getSnameData",
        method:"get",
        data:null
    })
}

export const findStandId = data =>{
    return axios.request({
        url:"/standard/findStandId",
        method:"post",
        data:data
    })
}

export const saveStandard = data =>{
    return axios.request({
        url:'/standard/saveStandard',
        data:data,
        method:'post'
    });
};

export const updateStandard = data=>{
    return axios.request({
        url:'/standard/updateStandard',
        data:data,
        method:'put'
    });
}


export const saveOrUpdate = data =>{
    return data.id ? updateStandard(data) : saveStandard(data);
}