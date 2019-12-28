import axios from "../../libs/api.request";


export const findFixedArea = data =>{
    return axios.request({
        url:"fixedarea/findFixedArea",
        method:"get",
        data:null
    })
}

export const findFixedAreaAll = data =>{
    return axios.request({
        url:"fixedarea/findFixedAreaAll",
        method:"post",
        data:data
    })
}


export const save = data => {
    return axios.request({
        url:'fixedarea/save',
        method:'post',
        data:data
    });
};

export const update = data =>{
    return axios.request({
        url:'fixedarea/update',
        method:'put',
        data:data
    });
};


export const saveOrUpdate = data => {
    return data.id ? update(data) : save(data);
};


