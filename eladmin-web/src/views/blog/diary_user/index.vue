<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">自增id</label>
        <el-input v-model="query.id" class="filter-item"
                  clearable
                  placeholder="自增id" style="width: 185px;" @keyup.enter.native="crud.toQuery"/>
        <label class="el-form-item-label">uid</label>
        <el-input v-model="query.uid" class="filter-item"
                  clearable
                  placeholder="uid" style="width: 185px;" @keyup.enter.native="crud.toQuery"/>
        <label class="el-form-item-label">用户名</label>
        <el-input v-model="query.name" class="filter-item"
                  clearable
                  placeholder="用户名" style="width: 185px;" @keyup.enter.native="crud.toQuery"/>
        <label class="el-form-item-label">昵称</label>
        <el-input v-model="query.nickname" class="filter-item"
                  clearable
                  placeholder="昵称" style="width: 185px;" @keyup.enter.native="crud.toQuery"/>
        <rrOperation :crud="crud"/>
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission"/>
      <!--表单组件-->
      <el-dialog :before-close="crud.cancelCU" :close-on-click-modal="false" :title="crud.status.title"
                 :visible.sync="crud.status.cu > 0" width="500px">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px" size="small">
          <el-form-item
            label="自增id">
            <el-input v-model="form.id" style="width: 370px;"/>
          </el-form-item>
          <el-form-item
            label="uid" prop="uid">
            <el-input v-model="form.uid" style="width: 370px;"/>
          </el-form-item>
          <el-form-item
            label="用户名" prop="name">
            <el-input v-model="form.name" style="width: 370px;"/>
          </el-form-item>
          <el-form-item
            label="昵称" prop="nickname">
            <el-input v-model="form.nickname" style="width: 370px;"/>
          </el-form-item>
          <el-form-item
            label="用户介绍" prop="description">
            <el-input v-model="form.description" :rows="3" style="width: 370px;"
                      type="textarea"/>
          </el-form-item>
          <el-form-item
            label="粉丝数" prop="followersCount">
            <el-input v-model="form.followersCount" style="width: 370px;"/>
          </el-form-item>
          <el-form-item
            label="关注数" prop="friendsCount">
            <el-input v-model="form.friendsCount" style="width: 370px;"/>
          </el-form-item>
          <el-form-item
            label="博客数" prop="statusesCount">
            <el-input v-model="form.statusesCount" style="width: 370px;"/>
          </el-form-item>
          <el-form-item
            label="创建时间" prop="createTime">
            <el-date-picker v-model="form.createTime" style="width: 370px;" type="datetime"/>
          </el-form-item>
          <el-form-item
            label="头像链接" prop="avatarUrl">
            <el-input v-model="form.avatarUrl" style="width: 370px;"/>
          </el-form-item>
          <el-form-item
            label="密码哈希" prop="password">
            <el-input v-model="form.password" style="width: 370px;"/>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;"
                @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55"/>
        <el-table-column label="自增id"
                         prop="id"/>
        <el-table-column label="uid"
                         prop="uid"/>
        <el-table-column label="用户名"
                         prop="name"/>
        <el-table-column label="昵称"
                         prop="nickname"/>
        <el-table-column label="用户介绍"
                         prop="description"/>
        <el-table-column label="粉丝数"
                         prop="followersCount"/>
        <el-table-column label="关注数"
                         prop="friendsCount"/>
        <el-table-column label="博客数"
                         prop="statusesCount"/>
        <el-table-column label="创建时间"
                         prop="createTime">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="头像链接"
                         prop="avatarUrl"/>
        <el-table-column label="密码哈希"
                         prop="password"/>
        <el-table-column v-permission="['admin','diaryUser:edit','diaryUser:del']" align="center"
                         label="操作" width="150px">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination/>
    </div>
  </div>
</template>

<script>
import crudDiaryUser from '@/api/blog/diaryUser'
import CRUD, {crud, form, header, presenter} from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = {
  id: null,
  uid: null,
  name: null,
  nickname: null,
  description: null,
  followersCount: null,
  friendsCount: null,
  statusesCount: null,
  createTime: null,
  avatarUrl: null,
  password: null
}
export default {
  name: 'DiaryUser',
  components: {pagination, crudOperation, rrOperation, udOperation},
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({
      title: 'diary_user',
      url: 'api/diaryUser',
      idField: 'id',
      sort: 'id,desc',
      crudMethod: {...crudDiaryUser}
    })
  },
  data() {
    return {
      permission: {
        add: ['admin', 'diaryUser:add'],
        edit: ['admin', 'diaryUser:edit'],
        del: ['admin', 'diaryUser:del']
      },
      rules: {
        uid: [
          {required: true, message: 'uid不能为空', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '用户名不能为空', trigger: 'blur'}
        ],
        nickname: [
          {required: true, message: '昵称不能为空', trigger: 'blur'}
        ],
        description: [
          {required: true, message: '用户介绍不能为空', trigger: 'blur'}
        ],
        followersCount: [
          {required: true, message: '粉丝数不能为空', trigger: 'blur'}
        ],
        friendsCount: [
          {required: true, message: '关注数不能为空', trigger: 'blur'}
        ],
        statusesCount: [
          {required: true, message: '博客数不能为空', trigger: 'blur'}
        ],
        createTime: [
          {required: true, message: '创建时间不能为空', trigger: 'blur'}
        ],
        avatarUrl: [
          {required: true, message: '头像链接不能为空', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码哈希不能为空', trigger: 'blur'}
        ]
      },
      queryTypeOptions: [
        {
          key: 'id',
          display_name: '自增id'
        },
        {
          key: 'uid',
          display_name: 'uid'
        },
        {
          key: 'name',
          display_name: '用户名'
        },
        {
          key: 'nickname',
          display_name: '昵称'
        }
      ]
    }
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      return true
    }
  }
}
</script>

<style scoped>

</style>
