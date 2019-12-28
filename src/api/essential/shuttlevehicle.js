import axios from "../../libs/api.request";

export const findVehicleAll = data =>{
    return axios.request({
        url:"vehicle/findVehicleAll",
        method:"post",
        data:data
    })
}

export const getVehicleData = data =>{
    return axios.request({
        url:"vehicle/getVehicleData",
        method:"get",
        data:null
    })
}


export const findVehicleAll1 = data =>{
    return axios.request({
        url:"vehicle/findVehicleAll1",
        method:"post",
        data:data
    })
}

export const save = data => {
    return axios.request({
        url:'vehicle/save',
        method:'post',
        data:data
    });
};

export const update = data =>{
    return axios.request({
        url:'vehicle/update',
        method:'put',
        data:data
    });
};


export const saveOrUpdate = data => {
    return data.id ? update(data) : save(data);
};






