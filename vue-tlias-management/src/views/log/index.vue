<script setup>
import { onMounted, ref } from 'vue'
import { queryPageApi} from '@/api/log'

//列表展示数据
let tableData = ref([])

//钩子函数 - 页面加载时触发
onMounted(() => {
  queryPage()
})

//分页组件
const pagination = ref({currentPage: 1, pageSize: 15, total: 0})
//每页展示记录数发生变化时触发
const handleSizeChange = (pageSize) => {
  pagination.value.pageSize = pageSize
  queryPage()
}
//当前页码发生变化时触发
const handleCurrentChange = (page) => {
  pagination.value.currentPage = page
  queryPage()
}

//分页条件查询
const queryPage = async () => {
  const result = await queryPageApi(pagination.value.currentPage,pagination.value.pageSize);

  if(result.code) {
    tableData.value = result.data.rows
    pagination.value.total = result.data.total
  }
}
</script>

<template>
  <div class="page-container">
    <div class="page-header">
      <el-icon class="page-icon"><Notebook /></el-icon>
      <span class="page-title">日志管理</span>
    </div>

    <div class="table-card">
      <el-table :data="tableData" border style="width: 100%" fit size="small">
        <el-table-column prop="operateEmpName" label="操作人" align="center" width="80" />
        <el-table-column prop="operateTime" label="操作时间" align="center" width="160" />
        <el-table-column prop="className" label="类名" align="center" min-width="250" />
        <el-table-column prop="methodName" label="方法名" align="center" width="100" />
        <el-table-column prop="costTime" label="操作耗时(ms)" align="center" width="110" />
        <el-table-column prop="methodParams" label="请求参数" align="center" width="200">
          <template #default="scope">
            <el-popover effect="light" trigger="hover" placement="top" width="auto" popper-style="font-size:12px">
              <template #default>
                <div>参数: {{ scope.row.methodParams }}</div>
              </template>
              <template #reference>
                <el-tag v-if="scope.row.methodParams.length <= 30">{{ scope.row.methodParams }}</el-tag>
                <el-tag v-else>{{ scope.row.methodParams.substring(0, 30) + '...' }}</el-tag>
              </template>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column prop="returnValue" label="返回值" align="center" />
      </el-table>

      <div class="pagination-wrapper">
        <el-pagination
          v-model:current-page="pagination.currentPage"
          v-model:page-size="pagination.pageSize"
          :page-sizes="[10, 20, 50, 100]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </div>
  </div>
</template>


<style scoped>
</style>