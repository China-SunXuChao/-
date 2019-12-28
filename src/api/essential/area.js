import axios from "../../libs/api.request";

export const findAreaAll = data =>{
    return axios.request({
        url: "area/findAreaAll",
        method: "post",
        data:data
    })
}

export const findArea = data =>{
    return axios.request({
        url: "area/findArea",
        method: "get",
        data:null
    })
}

export const saveArea = data=>{
    return axios.request({
        url: "area/saveArea",
        method: "post",
        data:data
    })
}


export const exportData = data=>{
    return axios.request({
        url: "area/exportData",
        method: "post",
        data:data,
        responseType:"blob"
    })
}



