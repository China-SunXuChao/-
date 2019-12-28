import axios from "../../libs/api.request";

export const getLogMonitoring = data =>{
    return axios.request({
        url: "log/getLogMonitoring",
        method: "post",
        data:data
    })
}

