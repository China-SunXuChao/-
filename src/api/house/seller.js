import axios from "../../libs/api.request";

export const getSellerList = data =>{
    return axios.request({
        url: "seller/getSellerList",
        method: "post",
        data:data
    })
}


export const updateSeller = data => {
    return axios.request({
        url:'seller/updateSeller',
        method:'put',
        data:data
    });
};

export const saveSeller = data =>{
    return axios.request({
        url:'seller/saveSeller',
        method:'post',
        data:data
    });
};


export const saveOrUpdate = data => {
    return data.id ? updateSeller(data) : saveSeller(data);
};