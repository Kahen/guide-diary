<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
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
            label="收藏用户ID"
          >
            <el-input v-model="form.userId" style="width: 370px;" />
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
          label="博客ID"
          prop="blogId"
        />
        <el-table-column
          label="收藏用户ID"
          prop="userId"
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
          v-permission="['admin','collect:edit','collect:del']"
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
import crudCollect from '@/api/blog/collect'
import CRUD, { crud, form, header, presenter } from '@crud/crud'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { collectId: null, blogId: null, userId: null, createTime: null }
export default {
  name: 'Collect',
  components: { pagination, crudOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({
      title: 'blog_collect',
      url: 'api/collect',
      idField: 'collectId',
      sort: 'collectId,desc',
      crudMethod: { ...crudCollect }
    })
  },
  data() {
    return {
      permission: {
        add: ['admin', 'collect:add'],
        edit: ['admin', 'collect:edit'],
        del: ['admin', 'collect:del']
      },
      rules: {}
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
