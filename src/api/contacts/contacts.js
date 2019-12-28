import axios from "../../libs/api.request";

export const getListByUsers = data =>{
    return axios.request({
        url: "contacts/getListByUsers",
        method: "post",
        data:data
    })
}
export const getCopmanyData = data =>{
    return axios.request({
        url: "contacts/getCopmanyData",
        method: "get",
        data:null
    })
}

export const getTypeData = data =>{
    return axios.request({
        url: "contacts/getTypeData",
        method: "get",
        data:null
    })
}


export const updateContacts = data => {
    return axios.request({
        url:'contacts/updateContacts',
        method:'put',
        data:data
    });
};

export const saveContacts = data =>{
    return axios.request({
        url:'contacts/saveContacts',
        method:'post',
        data:data
    });
};

export const deleteContacts = data =>{
    return axios.request({
        url:'contacts/deleteContacts',
        method:'delete',
        data:data
    });
};



export const saveOrUpdate = data => {
    return data.serialID ? updateContacts(data) : saveContacts(data);
};
