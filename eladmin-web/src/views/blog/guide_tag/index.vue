<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">内容</label>
        <el-input v-model="query.text" class="filter-item"
                  clearable
                  placeholder="内容" style="width: 185px;" @keyup.enter.native="crud.toQuery"/>
        <rrOperation :crud="crud"/>
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission"/>
      <!--表单组件-->
      <el-dialog :before-close="crud.cancelCU" :close-on-click-modal="false" :title="crud.status.title"
                 :visible.sync="crud.status.cu > 0" width="500px">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px" size="small">
          <el-form-item
            label="内容" prop="text">
            <el-input v-model="form.text" style="width: 370px;"/>
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
        <el-table-column label="uuid"
                         prop="id"/>
        <el-table-column label="内容"
                         prop="text"/>
        <el-table-column v-permission="['admin','tag:edit','tag:del']" align="center"
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
import crudTag from '@/api/blog/tag'

import CRUD, {crud, form, header, presenter} from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = {id: null, text: null}
export default {
  name: 'Tag',
  components: {pagination, crudOperation, rrOperation, udOperation},
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({
      title: 'guide_tag',
      url: 'api/tag',
      idField: 'id',
      sort: 'id,desc',
      crudMethod: {...crudTag}
    })
  },
  data() {
    return {
      permission: {
        add: ['admin', 'tag:add'],
        edit: ['admin', 'tag:edit'],
        del: ['admin', 'tag:del']
      },
      rules: {
        text: [
          {required: true, message: '内容不能为空', trigger: 'blur'}
        ]
      },
      queryTypeOptions: [
        {
          key: 'text',
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
