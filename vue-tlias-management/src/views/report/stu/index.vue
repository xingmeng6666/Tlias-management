<script setup>
import { onMounted, onUnmounted, nextTick } from 'vue'
import * as echarts from 'echarts'
import { queryStudentCountDataApi, queryEmpGenderDataApi, queryStudentDegreeDataApi } from '@/api/report'

let countChart = null
let degreeChart = null

onMounted(async () => {
  await nextTick()
  loadStudentCountChart()
  loadDegreeChart()
})

onUnmounted(() => {
  if (countChart) { countChart.dispose(); countChart = null }
  if (degreeChart) { degreeChart.dispose(); degreeChart = null }
})

//获取职位统计报表
const loadStudentCountChart = async () => {
  let result = await queryStudentCountDataApi();
  let clazzList = result.data.clazzList;
  let dataList = result.data.dataList;

  initStudentCountChart(clazzList, dataList)
}

//获取性别统计报表
const loadDegreeChart = async () => {
  let result = await queryStudentDegreeDataApi();
  initDegreeChart(result.data)
}

function initStudentCountChart(clazzList, dataList) {
  const container = document.getElementById('container1')
  if (!container) return
  if (countChart) countChart.dispose()
  countChart = echarts.init(container)
  countChart.setOption({
    title: {
      text: '班级人数统计',
      subText: '',
      textStyle: {
        fontSize: 20
      },
      left: 'center'
    },
    grid:{
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel:true
    },
    tooltip: {},
    xAxis: {
      data: clazzList
    },
    yAxis: {},
    series: [
      {
        name: '人数',
        type: 'bar',
        data: dataList,
        itemStyle:{
          // 设置柱状渐变色---
          color:new echarts.graphic.LinearGradient(0, 0, 1, 1, [
            {
              offset: 0,
              color: '#ffbf61'
            },
            {
              offset: 1,
              color: '#dd5f85'
            }
          ])
        }
      }
    ]
  });
}

function initDegreeChart(degreeDataList) {
  const container = document.getElementById('container2')
  if (!container) return
  if (degreeChart) degreeChart.dispose()
  degreeChart = echarts.init(container)
  let option = {
    title: {
      text: '学员学历统计',
      subText: '',
      textStyle: {
        fontSize: 20
      },
      left: 'center'
    },
    grid:{
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel:true
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      top: '10%',
      left: 'center'
    },
    series: [
      {
        name: '学历',
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: false,
        top: '5%',
        itemStyle: {
          borderRadius: 5,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: 20,
            fontWeight: 'bold'
          }
        },
        data: degreeDataList
      }
    ]
  };
  // 绘制图表
  degreeChart.setOption(option);
}

</script>

<template>
  <div class="page-container">
    <div class="page-header">
      <el-icon class="page-icon"><TrendCharts /></el-icon>
      <span class="page-title">学员信息统计</span>
    </div>
    <div class="report-wrapper">
      <div class="report-card">
        <div id="container1" class="chart-container"></div>
      </div>
      <div class="report-card">
        <div id="container2" class="chart-container"></div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page-container {
  animation: none;
}

.report-wrapper {
  display: flex;
  gap: 16px;
}

.report-card {
  flex: 1;
  background: #fff;
  border-radius: 10px;
  padding: 16px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.06);
}

.chart-container {
  width: 100%;
  height: 500px;
}
</style>