<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">自增id</label>
        <el-input
          class="filter-item"
          v-model="query.id"
          clearable
          placeholder="自增id"
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
            label="心情贴纸"
          >
            <el-input v-model="form.mood" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="标签"
          >
            <el-input v-model="form.tag" style="width: 370px;" />
          </el-form-item>
          <el-form-item
            label="引导词1"
            prop="guide1"
          >
            <el-input
              v-model="form.guide1"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="引导词2"
            prop="guide2"
          >
            <el-input
              v-model="form.guide2"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="引导词3"
            prop="guide3"
          >
            <el-input
              v-model="form.guide3"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="引导词4"
            prop="guide4"
          >
            <el-input
              v-model="form.guide4"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="引导词5"
            prop="guide5"
          >
            <el-input
              v-model="form.guide5"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="引导词6"
            prop="guide6"
          >
            <el-input
              v-model="form.guide6"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="内容1"
          >
            <el-input
              v-model="form.content1"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="内容2"
          >
            <el-input
              v-model="form.content2"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="内容3"
          >
            <el-input
              v-model="form.content3"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="内容4"
          >
            <el-input
              v-model="form.content4"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="内容5"
          >
            <el-input
              v-model="form.content5"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="内容6"
          >
            <el-input
              v-model="form.content6"
              :rows="3"
              style="width: 370px;"
              type="textarea"
            />
          </el-form-item>
          <el-form-item
            label="创建时间"
            prop="createdDate"
          >
            <el-date-picker v-model="form.createdDate" style="width: 370px;" type="datetime" />
          </el-form-item>
          <el-form-item
            label="更新时间"
            prop="updatedDate"
          >
            <el-date-picker v-model="form.updatedDate" style="width: 370px;" type="datetime" />
          </el-form-item>
          <el-form-item
            label="时间类型"
            prop="period"
          >
            <el-input v-model="form.period" style="width: 370px;" />
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
          label="心情贴纸"
          prop="mood"
        />
        <el-table-column
          label="标签"
          prop="tag"
        />
        <el-table-column
          label="引导词1"
          prop="guide1"
        />
        <el-table-column
          label="引导词2"
          prop="guide2"
        />
        <el-table-column
          label="引导词3"
          prop="guide3"
        />
        <el-table-column
          label="引导词4"
          prop="guide4"
        />
        <el-table-column
          label="引导词5"
          prop="guide5"
        />
        <el-table-column
          label="引导词6"
          prop="guide6"
        />
        <el-table-column
          label="内容1"
          prop="content1"
        />
        <el-table-column
          label="内容2"
          prop="content2"
        />
        <el-table-column
          label="内容3"
          prop="content3"
        />
        <el-table-column
          label="内容4"
          prop="content4"
        />
        <el-table-column
          label="内容5"
          prop="content5"
        />
        <el-table-column
          label="内容6"
          prop="content6"
        />
        <el-table-column
          label="创建时间"
          prop="createdDate"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createdDate) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="更新时间"
          prop="updatedDate"
        >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.updatedDate) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="时间类型"
          prop="period"
        />
        <el-table-column
          v-permission="['admin','diary:edit','diary:del']"
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
import crudDiary from '@/api/blog/diary'
import CRUD, { crud, form, header, presenter } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = {
  id: null,
  mood: null,
  tag: null,
  guide1: null,
  guide2: null,
  guide3: null,
  guide4: null,
  guide5: null,
  guide6: null,
  content1: null,
  content2: null,
  content3: null,
  content4: null,
  content5: null,
  content6: null,
  createdDate: null,
  updatedDate: null,
  period: null
}
export default {
  name: 'Diary',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({
      title: 'diary',
      url: 'api/diary',
      idField: 'id',
      sort: 'id,desc',
      crudMethod: { ...crudDiary }
    })
  },
  data() {
    return {
      permission: {
        add: ['admin', 'diary:add'],
        edit: ['admin', 'diary:edit'],
        del: ['admin', 'diary:del']
      },
      rules: {
        guide1: [
          { required: true, message: '引导词1不能为空', trigger: 'blur' }
        ],
        guide2: [
          { required: true, message: '引导词2不能为空', trigger: 'blur' }
        ],
        guide3: [
          { required: true, message: '引导词3不能为空', trigger: 'blur' }
        ],
        guide4: [
          { required: true, message: '引导词4不能为空', trigger: 'blur' }
        ],
        guide5: [
          { required: true, message: '引导词5不能为空', trigger: 'blur' }
        ],
        guide6: [
          { required: true, message: '引导词6不能为空', trigger: 'blur' }
        ],
        createdDate: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ],
        updatedDate: [
          { required: true, message: '更新时间不能为空', trigger: 'blur' }
        ],
        period: [
          { required: true, message: '时间类型不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        {
          key: 'id',
          display_name: '自增id'
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
