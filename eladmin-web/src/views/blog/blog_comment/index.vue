<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">博客ID</label>
        <el-input
          v-model="query.blogId"
          placeholder="博客ID"
          class="filter-item"
          clearable
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <label class="el-form-item-label">评论用户ID</label>
        <el-input
          v-model="query.userId"
          placeholder="评论用户ID"
          class="filter-item"
          clearable
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <label class="el-form-item-label">内容</label>
        <el-input
          v-model="query.content"
          placeholder="内容"
          class="filter-item"
          clearable
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
            label="博客ID"
          >
            <el-input v-model="form.blogId" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="评论用户ID"
          >
            <el-input v-model="form.userId" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="该评论所在的父级ID"
          >
            <el-input v-model="form.pid" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="该评论回复的评论ID,没有则为0"
          >
            <el-input v-model="form.replyId" style="width: 370px;" />
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
            label="发布时间"
          >
            <el-date-picker v-model="form.publishTime" style="width: 370px;" type="datetime" />
          </el-form-item>
          <el-form-item
            label="评论ID"
          >
            <el-input v-model="form.commentId" style="width: 370px;" />
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
          label="博客ID"
          prop="blogId"
        />
        <el-table-column
          label="评论用户ID"
          prop="userId"
        />
        <el-table-column
          label="该评论所在的父级ID"
          prop="pid"
        />
        <el-table-column
          label="该评论回复的评论ID,没有则为0"
          prop="replyId"
        />
        <el-table-column
          label="内容"
          prop="content"
        />
        <el-table-column
          label="发布时间"
          prop="publishTime"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.publishTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="评论ID"
          prop="commentId"
        />
        <el-table-column
          v-permission="['admin','comment:edit','comment:del']"
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
import crudComment from '@/api/blog/comment'
import CRUD, { crud, form, header, presenter } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = {
  blogId: null,
  userId: null,
  pid: null,
  replyId: null,
  content: null,
  publishTime: null,
  commentId: null
}
export default {
  name: 'Comment',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({
      title: 'blog_comment',
      url: 'api/comment',
      idField: 'commentId',
      sort: 'commentId,desc',
      crudMethod: { ...crudComment }
    })
  },
  data() {
    return {
      permission: {
        add: ['admin', 'comment:add'],
        edit: ['admin', 'comment:edit'],
        del: ['admin', 'comment:del']
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
          display_name: '评论用户ID'
        },
        {
          key: 'content',
          display_name: '内容'
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
