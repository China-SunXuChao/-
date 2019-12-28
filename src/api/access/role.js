import axios from "../../libs/api.request";

export const list = data => {
    return axios.request({
        method:'post',
        data:data,
        url:'role/list'
    });
};

export const getRoles = () => {
    return axios.request({
        method:'get',
        data:null,
        url:'role/getRoles'
    });
};

export const save = data => {
    return axios.request({
        method:'post',
        data:data,
        url:`role/save`
    });
};

export const update = data => {
    return axios.request({
        method:'put',
        data:data,
        url:`role/update`
    });
};



export const saveOrUpdate = data => {
    return data.id ? update(data) : save(data);
};
