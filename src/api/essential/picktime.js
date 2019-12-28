import axios from "../../libs/api.request";

export const getPickTimeList = data =>{
    return axios.request({
        url:"picktime/getPickTimeList",
        method:"post",
        data:data
    })
}

export const findTimeData = data =>{
    return axios.request({
        url:"picktime/findTimeData",
        method:"get",
        data:null
    })
}


export const save = data => {
    return axios.request({
        url:'picktime/save',
        method:'post',
        data:data
    });
};

export const update = data =>{
    return axios.request({
        url:'picktime/update',
        method:'put',
        data:data
    });
};


export const saveOrUpdate = data => {
    return data.id ? update(data) : save(data);
};

