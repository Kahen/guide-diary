<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">博客ID</label>
        <el-input
          v-model="query.blogId"
          class="filter-item"
          clearable
          placeholder="博客ID"
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <label class="el-form-item-label">转发用户ID</label>
        <el-input
          v-model="query.userId"
          class="filter-item"
          clearable
          placeholder="转发用户ID"
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <label class="el-form-item-label">内容</label>
        <el-input
          v-model="query.content"
          class="filter-item"
          clearable
          placeholder="内容"
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <label class="el-form-item-label">创建时间</label>
        <el-input
          v-model="query.createTime"
          class="filter-item"
          clearable
          placeholder="创建时间"
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog
        :before-close="crud.cancelCU"
        :close-on-click-modal="false"
        :title="crud.status.title"
        :visible.sync="crud.status.cu > 0"
        width="500px"
      >
        <el-form ref="form" :model="form" :rules="rules" label-width="80px" size="small">
          <el-form-item
            label="转发ID"
          >
            <el-input v-model="form.repostId" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="博客ID"
          >
            <el-input v-model="form.blogId" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="转发用户ID"
          >
            <el-input v-model="form.userId" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="内容"
            prop="content"
          >
            <el-input
              v-model="form.content"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="创建时间"
          >
            <el-date-picker v-model="form.createTime" style="width: 370px;" type="datetime" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table
        ref="table"
        v-loading="crud.loading"
        :data="crud.data"
        size="small"
        style="width: 100%;"
        @selection-change="crud.selectionChangeHandler"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column
          label="转发ID"
          prop="repostId"
        />
        <el-table-column
          label="博客ID"
          prop="blogId"
        />
        <el-table-column
          label="转发用户ID"
          prop="userId"
        />
        <el-table-column
          label="内容"
          prop="content"
        />
        <el-table-column
          label="创建时间"
          prop="createTime"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          v-permission="['admin','repost:edit','repost:del']"
          align="center"
          label="操作"
          width="150px"
        >
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import CRUD, { crud, form, header, presenter } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'
import crudRepost from '@/api/blog/repost'

const defaultForm = { repostId: null, blogId: null, userId: null, content: null, createTime: null }
export default {
  name: 'Repost',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({
      title: 'blog_repost',
      url: 'api/repost',
      idField: 'repostId',
      sort: 'repostId,desc',
      crudMethod: { ...crudRepost }
    })
  },
  data() {
    return {
      permission: {
        add: ['admin', 'repost:add'],
        edit: ['admin', 'repost:edit'],
        del: ['admin', 'repost:del']
      },
      rules: {
        content: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        {
          key: 'blogId',
          display_name: '博客ID'
        },
        {
          key: 'userId',
          display_name: '转发用户ID'
        },
        {
          key: 'content',
          display_name: '内容'
        },
        {
          key: 'createTime',
          display_name: '创建时间'
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
