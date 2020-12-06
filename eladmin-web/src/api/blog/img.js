import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/img',
    method: 'post',
    data
  })
}

//test
export function del(ids) {
  return request({
    url: 'api/img/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/img',
    method: 'put',
    data
  })
}

export default {add, edit, del}
