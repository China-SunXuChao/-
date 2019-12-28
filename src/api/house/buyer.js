import axios from "../../libs/api.request";

export const getBuyerList = data =>{
    return axios.request({
        url: "buyer/getBuyerList",
        method: "post",
        data:data
    })
}
export const updateBuyer = data => {
    return axios.request({
        url:'buyer/updateBuyer',
        method:'put',
        data:data
    });
};

export const saveBuyer = data =>{
    return axios.request({
        url:'buyer/saveBuyer',
        method:'post',
        data:data
    });
};


export const saveOrUpdate = data => {
    return data.id ? updateBuyer(data) : saveBuyer(data);
};

