import axios from "../../libs/api.request";



export const findCourierOk = data =>{
    return axios.request({
        url:"courier/findCourierOk",
        method:"post",
        data:data
    })
}


export const findCourier = data =>{
    return axios.request({
        url:"courier/findCourier",
        method:"get",
        data:null
    })
}

export const getCourierAll = data =>{
    return axios.request({
        url:"courier/getCourierAll",
        method:"post",
        data:data
    })
}


export const save = data => {
    return axios.request({
        url:'courier/save',
        method:'post',
        data:data
    });
};

export const update = data =>{
    return axios.request({
        url:'courier/update',
        method:'put',
        data:data
    });
};


export const saveOrUpdate = data => {
    return data.id ? update(data) : save(data);
};
