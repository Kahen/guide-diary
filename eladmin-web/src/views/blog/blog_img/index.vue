<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">图片ID</label>
        <el-input
          v-model="query.imgId"
          class="filter-item"
          clearable
          placeholder="图片ID"
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <label class="el-form-item-label">博客ID</label>
        <el-input
          v-model="query.blogId"
          class="filter-item"
          clearable
          placeholder="博客ID"
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <label class="el-form-item-label">图片URL</label>
        <el-input
          v-model="query.imgUrl"
          class="filter-item"
          clearable
          placeholder="图片URL"
          style="width: 185px;"
          @keyup.enter.native="crud.toQuery"
        />
        <rrOperation :crud="crud"/>
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission"/>
      <!--表单组件-->
      <el-dialog
        :before-close="crud.cancelCU"
        :close-on-click-modal="false"
        :title="crud.status.title"
        :visible.sync="crud.status.cu > 0"
        width="500px"
      >
        <el-form ref="form" :model="form" :rules="rules" label-width="80px" size="small">
          <el-form-item label="图片ID">
            <el-input v-model="form.imgId" style="width: 370px;"/>
          </el-form-item>
          <el-form-item label="博客ID">
            <el-input v-model="form.blogId" style="width: 370px;"/>
          </el-form-item>
          <el-form-item label="图片URL">
            <el-input v-model="form.imgUrl" style="width: 370px;"/>
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
        <el-table-column type="selection" width="55"/>
        <el-table-column label="图片ID" prop="imgId"/>
        <el-table-column label="博客ID" prop="blogId"/>
        <el-table-column label="图片URL" prop="imgUrl"/>
        <el-table-column v-permission="['admin','img:edit','img:del']" align="center" label="操作" width="150px">
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
import crudImg from '@/api/blog/img'
import CRUD, {crud, form, header, presenter} from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = {imgId: null, blogId: null, imgUrl: null}
export default {
  name: 'Img',
  components: {pagination, crudOperation, rrOperation, udOperation},
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({title: 'blog_img', url: 'api/img', idField: 'imgId', sort: 'imgId,desc', crudMethod: {...crudImg}})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'img:add'],
        edit: ['admin', 'img:edit'],
        del: ['admin', 'img:del']
      },
      rules: {},
      queryTypeOptions: [
        {key: 'imgId', display_name: '图片ID'},
        {key: 'blogId', display_name: '博客ID'},
        {key: 'imgUrl', display_name: '图片URL'}
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
