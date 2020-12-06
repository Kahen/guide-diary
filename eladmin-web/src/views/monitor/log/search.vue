<template>
  <div v-if="crud.props.searchToggle">
    <el-input
      v-model="query.blurry"
      class="filter-item"
      clearable
      placeholder="请输入你要搜索的内容"
      size="small"
      style="width: 200px;"
    />
    <date-range-picker v-model="query.createTime" class="date-item"/>
    <rrOperation/>
  </div>
</template>

<script>
import {header} from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import DateRangePicker from '@/components/DateRangePicker'

export default {
  components: {rrOperation, DateRangePicker},
  mixins: [header()]
}
</script>
