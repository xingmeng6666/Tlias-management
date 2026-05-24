<script setup>
import { ref, onMounted } from 'vue'

const loginUser = ref('')

onMounted(() => {
  try {
    let user = JSON.parse(localStorage.getItem('loginUser'))
    if (user) {
      loginUser.value = user.name
    }
  } catch (e) {
    // ignore
  }
})

const quickLinks = [
  { name: '班级管理', path: '/clazz', icon: 'HomeFilled', color: '#409eff' },
  { name: '学员管理', path: '/stu', icon: 'UserFilled', color: '#67c23a' },
  { name: '部门管理', path: '/dept', icon: 'OfficeBuilding', color: '#e6a23c' },
  { name: '员工管理', path: '/emp', icon: 'Avatar', color: '#f56c6c' }
]
</script>

<template>
  <div class="page-container">
    <div class="welcome-section">
      <div class="welcome-text">
        <h1>欢迎回来，{{ loginUser }}！</h1>
        <p>Tlias 智能学习辅助系统 - 让管理更高效</p>
      </div>
      <div class="welcome-decoration">
        <el-icon class="deco-icon"><Monitor /></el-icon>
      </div>
    </div>

    <div class="quick-nav">
      <h2 class="section-title">
        <el-icon><Grid /></el-icon>
        快捷导航
      </h2>
      <div class="nav-cards">
        <router-link
          v-for="link in quickLinks"
          :key="link.path"
          :to="link.path"
          class="nav-card"
        >
          <div class="nav-card-icon" :style="{ background: link.color + '15', color: link.color }">
            <el-icon :size="32"><component :is="link.icon" /></el-icon>
          </div>
          <span class="nav-card-name">{{ link.name }}</span>
          <el-icon class="nav-card-arrow"><ArrowRight /></el-icon>
        </router-link>
      </div>
    </div>

    <div class="info-section">
      <div class="info-card">
        <div class="info-card-header">
          <el-icon><InfoFilled /></el-icon>
          <span>系统说明</span>
        </div>
        <div class="info-card-body">
          <ul>
            <li>支持班级、学员、部门、员工的全方位管理</li>
            <li>提供员工和学员的多维度数据统计分析</li>
            <li>完整的操作日志记录，确保数据可追溯</li>
            <li>响应式设计，适配多种终端设备</li>
          </ul>
        </div>
      </div>
      <div class="info-card">
        <div class="info-card-header">
          <el-icon><Document /></el-icon>
          <span>快速入门</span>
        </div>
        <div class="info-card-body">
          <ul>
            <li>从左侧菜单选择需要管理的模块</li>
            <li>使用搜索功能快速筛选数据</li>
            <li>点击操作按钮进行新增、编辑、删除</li>
            <li>查看统计报表了解数据概览</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.welcome-section {
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%);
  border-radius: 12px;
  padding: 40px;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}

.welcome-text h1 {
  font-size: 28px;
  font-weight: 600;
  margin-bottom: 8px;
}

.welcome-text p {
  font-size: 16px;
  color: #a0b4d0;
}

.deco-icon {
  font-size: 80px;
  color: rgba(64, 158, 255, 0.3);
}

.section-title {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 18px;
  font-weight: 600;
  color: #1a1a2e;
  margin-bottom: 16px;
}

.section-title .el-icon {
  color: #409eff;
}

.nav-cards {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 16px;
  margin-bottom: 24px;
}

.nav-card {
  display: flex;
  align-items: center;
  gap: 16px;
  background: #fff;
  border-radius: 10px;
  padding: 20px;
  text-decoration: none;
  color: #303133;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.06);
  transition: all 0.3s;
  cursor: pointer;
}

.nav-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.nav-card-icon {
  width: 56px;
  height: 56px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.nav-card-name {
  flex: 1;
  font-size: 16px;
  font-weight: 500;
}

.nav-card-arrow {
  color: #c0c4cc;
  transition: transform 0.3s;
}

.nav-card:hover .nav-card-arrow {
  transform: translateX(4px);
  color: #409eff;
}

.info-section {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.info-card {
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.06);
  overflow: hidden;
}

.info-card-header {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 16px 20px;
  background: #f5f7fa;
  font-size: 15px;
  font-weight: 600;
  color: #303133;
  border-bottom: 1px solid #ebeef5;
}

.info-card-header .el-icon {
  color: #409eff;
}

.info-card-body {
  padding: 16px 20px;
}

.info-card-body ul {
  list-style: none;
  padding: 0;
}

.info-card-body li {
  padding: 8px 0;
  font-size: 14px;
  color: #606266;
  border-bottom: 1px dashed #ebeef5;
  position: relative;
  padding-left: 16px;
}

.info-card-body li:last-child {
  border-bottom: none;
}

.info-card-body li::before {
  content: '';
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 6px;
  height: 6px;
  border-radius: 50%;
  background: #409eff;
}
</style>