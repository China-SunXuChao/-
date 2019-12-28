import axios from "../../libs/api.request";

export const all = () => {
    return axios.request({
        url:'permission/all',
        method:'get',
        data:null
    });
};

export const remove = data =>{
    return axios.request({
        url:`permission/remove/${data.id}`,
        method:'delete',
        data:null
    });
};

export const save = data => {
    return axios.request({
        url:'permission/save',
        method:'post',
        data:data
    });
};

export const update = data =>{
    return axios.request({
        url:'permission/update',
        method:'put',
        data:data
    });
};



export const saveOrUpdate = data => {
    return data.id ? update(data) : save(data);
};







