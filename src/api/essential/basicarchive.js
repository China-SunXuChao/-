import axios from "../../libs/api.request";

export const findArchiveAll = data =>{
    return axios.request({
        url:"archive/findArchiveAll",
        method:"post",
        data:data
    })
}


export const findAll = data =>{
    return axios.request({
        url:"archive/findAll",
        method:"get",
        data:null
    })
}


export const findArchiveZi = data =>{
    return axios.request({
        url:'archive/findArchiveZi',
        method:"post",
        data:data
    })
}

export const save = data => {
    return axios.request({
        url:'archive/save',
        method:'post',
        data:data
    });
};

export const update = data =>{
    return axios.request({
        url:'archive/update',
        method:'put',
        data:data
    });
};


export const saveOrUpdate = data => {
    return data.id ? update(data) : save(data);
};

export const saveOrUpdateZi = data => {
    return data.subId ? updateZi(data) : saveZi(data);
};

export const saveZi = data => {
    return axios.request({
        url:'archive/saveZi',
        method:'post',
        data:data
    });
};

export const updateZi = data =>{
    return axios.request({
        url:'archive/updateZi',
        method:'put',
        data:data
    });
};




