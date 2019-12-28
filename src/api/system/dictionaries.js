import axios from '@/libs/api.request.js'

export const list = data => {
	return axios.request({
		method:'post',
		data:data,
		url:'dictionaries/list'
	});
};

export const save = data => {
	return axios.request({
		method:'post',
		data:data,
		url:`dictionaries/save`
	});
};

export const update = data => {
	return axios.request({
		method:'put',
		data:data,
		url:`dictionaries/update`
	});
};

export const saveOrUpdate = data => {
	return data.id ? update(data) : save(data);
};

export const getDictionariesByCode = data =>{
	return axios.request({
		url:`dictionaries/getDictionariesByCode`,
		data:data,
		method:'post'
	});
};