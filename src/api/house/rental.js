import axios from "../../libs/api.request";

export const getRentalList = data =>{
    return axios.request({
        url: "rental/getRentalList",
        method: "post",
        data:data
    })
}
