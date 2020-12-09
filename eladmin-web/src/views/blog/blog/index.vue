<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">内容</label>
        <el-input
          v-model="query.content"
          class="filter-item"
          clearable
          placeholder="内容"
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <label class="el-form-item-label">视频URL</label>
        <el-input
          v-model="query.videoUrl"
          class="filter-item"
          clearable
          placeholder="视频URL"
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
        <label class="el-form-item-label">发布时间</label>
        <el-input
          v-model="query.publishTime"
          class="filter-item"
          clearable
          placeholder="发布时间"
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <label class="el-form-item-label">是否原创</label>
        <el-input
          v-model="query.isOriginal"
          class="filter-item"
          clearable
          placeholder="是否原创"
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
            label="用户ID"
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
            label="视频URL"
          >
            <el-input v-model="form.videoUrl" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="创建时间"
          >
            <el-date-picker v-model="form.createTime" style="width: 370px;" type="datetime" />
          </el-form-item>
          <el-form-item
            label="发布时间"
          >
            <el-date-picker v-model="form.publishTime" style="width: 370px;" type="datetime" />
          </el-form-item>
          <el-form-item
            label="是否原创"
          >
            <el-radio
              v-for="item in dict.is_original"
              :key="item.id"
              v-model="form.isOriginal"
              :label="item.value"
            >{{ item.label }}
            </el-radio>
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
          label="用户ID"
          prop="userId"
        />
        <el-table-column
          label="内容"
          prop="content"
        />
        <el-table-column
          label="视频URL"
          prop="videoUrl"
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
          label="发布时间"
          prop="publishTime"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.publishTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="是否原创"
          prop="isOriginal"
        >
          <template slot-scope="scope">
            {{ dict.label.is_original[scope.row.isOriginal] }}
          </template>
        </el-table-column>
        <el-table-column
          v-permission="['admin','blog:edit','blog:del']"
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
import crudBlog from '@/api/blog/blog'
import CRUD, { crud, form, header, presenter } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = {
  blogId: null,
  userId: null,
  content: null,
  videoUrl: null,
  createTime: null,
  publishTime: null,
  isOriginal: null
}
export default {
  name: 'Blog',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['is_original'],
  cruds() {
    return CRUD({
      title: 'blog',
      url: 'api/blog',
      idField: 'blogId',
      sort: 'blogId,desc',
      crudMethod: { ...crudBlog }
    })
  },
  data() {
    return {
      permission: {
        add: ['admin', 'blog:add'],
        edit: ['admin', 'blog:edit'],
        del: ['admin', 'blog:del']
      },
      rules: {
        content: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        {
          key: 'content',
          display_name: '内容'
        },
        {
          key: 'videoUrl',
          display_name: '视频URL'
        },
        {
          key: 'createTime',
          display_name: '创建时间'
        },
        {
          key: 'publishTime',
          display_name: '发布时间'
        },
        {
          key: 'isOriginal',
          display_name: '是否原创'
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
