import axios from "../../libs/api.request";

export const findAll = () => {
    return axios.request({
        method:'get',
        data:null,
        url:'department/findAll'
    });
};


export const removeDepartment = data => {
    return axios.request({
        method:'delete',
        url:`department/remove/${data.id}`,
        data:null
    });
}


export const save = data =>{
    return axios.request({
        method:'post',
        data:data,
        url:'department/save'
    });
};

export const update = data =>{
    return axios.request({
        method:'put',
        data:data,
        url:'department/update'
    });
}

export const saveOrUpdate = data =>{
    return data.id ? update(data) : save(data);
};


