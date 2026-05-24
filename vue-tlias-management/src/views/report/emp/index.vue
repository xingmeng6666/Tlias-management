<script setup>
import { onMounted, onUnmounted, nextTick } from 'vue'
import * as echarts from 'echarts'
import { queryEmpJobDataApi, queryEmpGenderDataApi } from '@/api/report'

let jobChart = null
let genderChart = null

onMounted(async () => {
  await nextTick()
  loadJobChart()
  loadGenderChart()
})

onUnmounted(() => {
  if (jobChart) { jobChart.dispose(); jobChart = null }
  if (genderChart) { genderChart.dispose(); genderChart = null }
})

//获取职位统计报表
const loadJobChart = async () => {
  let result = await queryEmpJobDataApi();
  let jobList = result.data.jobList;
  let dataList = result.data.dataList;

  initJobChart(jobList, dataList)
}

//获取性别统计报表
const loadGenderChart = async () => {
  let result = await queryEmpGenderDataApi();
  initGenderChart(result.data)
}


function initJobChart(jobList, dataList) {
  const container = document.getElementById('container1')
  if (!container) return
  if (jobChart) jobChart.dispose()
  jobChart = echarts.init(container)
  jobChart.setOption({
    title: {
      text: '员工职位统计',
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
      data: jobList
    },
    yAxis: {},
    series: [
      {
        name: '人数',
        type: 'bar',
        data: dataList,
        itemStyle:{
          // 设置柱状渐变色
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

function initGenderChart(genderDataList) {
  const container = document.getElementById('container2')
  if (!container) return
  if (genderChart) genderChart.dispose()
  genderChart = echarts.init(container)
  let option = {
    title: {
      text: '员工性别统计',
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
        name: '性别',
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
        data: genderDataList
      }
    ]
  };
  // 绘制图表
  genderChart.setOption(option);
}

</script>

<template>
  <div class="page-container">
    <div class="page-header">
      <el-icon class="page-icon"><InfoFilled /></el-icon>
      <span class="page-title">员工信息统计</span>
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