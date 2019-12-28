import axios from '@/libs/api.request.js'


export const userList = data => {
    return axios.request({
        url:'user/list',
        data:data,
        method:'post'
    });
};

export const save = data => {
    return axios.request({
        url:`user/save`,
        data:data,
        method:'post'
    });
};

export const update = data => {
    return axios.request({
        url:`user/update`,
        data:data,
        method:'put'
    });
};

export const saveOrUpdate = data => {
    return data.id ? update(data) : save(data);
};


