<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">作者</label>
        <el-input
          class="filter-item"
          v-model="query.author"
          clearable
          placeholder="作者"
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <label class="el-form-item-label">内容</label>
        <el-input
          class="filter-item"
          v-model="query.content"
          clearable
          placeholder="内容"
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <label class="el-form-item-label">格言分类</label>
        <el-input
          class="filter-item"
          v-model="query.type"
          clearable
          placeholder="格言分类"
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
            label="作者"
            prop="author"
          >
            <el-input v-model="form.author" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="内容"
            prop="content"
          >
            <el-input v-model="form.content" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="格言分类"
            prop="type"
          >
            <el-input v-model="form.type" style="width: 370px;" />
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
        :data="crud.data"
        v-loading="crud.loading"
        size="small"
        style="width: 100%;"
        @selection-change="crud.selectionChangeHandler"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column
          label="作者"
          prop="author"
        />
        <el-table-column
          label="内容"
          prop="content"
        />
        <el-table-column
          label="格言分类"
          prop="type"
        />
        <el-table-column
          v-permission="['admin','motto:edit','motto:del']"
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
import crudMotto from '@/api/blog/motto'
import CRUD, { crud, form, header, presenter } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, author: null, content: null, type: null }
export default {
  name: 'Motto',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({
      title: 'guide_motto',
      url: 'api/motto',
      idField: 'id',
      sort: 'id,desc',
      crudMethod: { ...crudMotto }
    })
  },
  data() {
    return {
      permission: {
        add: ['admin', 'motto:add'],
        edit: ['admin', 'motto:edit'],
        del: ['admin', 'motto:del']
      },
      rules: {
        author: [
          { required: true, message: '作者不能为空', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '格言分类不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        {
          key: 'author',
          display_name: '作者'
        },
        {
          key: 'content',
          display_name: '内容'
        },
        {
          key: 'type',
          display_name: '格言分类'
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
