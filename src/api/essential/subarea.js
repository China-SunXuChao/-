import axios from "../../libs/api.request";

export const findSubArea = data =>{
    return axios.request({
        url:"/subarea/findSubArea",
        method:"post",
        data:data
    })
}


export const findFixeData = data =>{
    return axios.request({
        url:"/subarea/findFixeData",
        method:"get",
        data:null
    })
}

export const save = data => {
    return axios.request({
        url:'subarea/save',
        method:'post',
        data:data
    });
};

export const update = data =>{
    return axios.request({
        url:'subarea/update',
        method:'put',
        data:data
    });
};


export const saveOrUpdate = data => {
    return data.id ? update(data) : save(data);
};


