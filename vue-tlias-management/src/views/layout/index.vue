<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'

const router = useRouter()
const loginName = ref('')

onMounted(() => {
  try {
    let loginUser = JSON.parse(localStorage.getItem('loginUser'))
    if (loginUser) {
      loginName.value = loginUser.name
    }
  } catch (e) {
    // ignore
  }
})

const logout = () => {
  ElMessageBox.confirm('确认退出登录吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    ElMessage.success('退出登录成功')
    localStorage.removeItem('loginUser')
    router.push('/login')
  })
}
</script>

<template>
  <div class="common-layout">
    <el-container class="layout-container">
      <el-header class="header">
        <div class="header-left">
          <el-icon class="logo-icon"><Monitor /></el-icon>
          <span class="title">Tlias 智能学习辅助系统</span>
        </div>
        <div class="header-right">
          <div class="user-info">
            <el-icon><User /></el-icon>
            <span class="username">{{ loginName }}</span>
          </div>
          <el-divider direction="vertical" />
          <a href="javascript:void(0)" class="header-link" @click="logout">
            <el-icon><SwitchButton /></el-icon>
            <span>退出登录</span>
          </a>
        </div>
      </el-header>

      <el-container class="body-container">
        <el-aside width="220px" class="aside">
          <el-menu router :default-active="$route.path" class="sidebar-menu">
            <el-menu-item index="/index">
              <el-icon><Odometer /></el-icon>
              <span>首页</span>
            </el-menu-item>

            <el-sub-menu index="/manage">
              <template #title>
                <el-icon><Document /></el-icon>
                <span>班级学员管理</span>
              </template>
              <el-menu-item index="/clazz">
                <el-icon><HomeFilled /></el-icon>
                <span>班级管理</span>
              </el-menu-item>
              <el-menu-item index="/stu">
                <el-icon><UserFilled /></el-icon>
                <span>学员管理</span>
              </el-menu-item>
            </el-sub-menu>

            <el-sub-menu index="/system">
              <template #title>
                <el-icon><Setting /></el-icon>
                <span>系统信息管理</span>
              </template>
              <el-menu-item index="/dept">
                <el-icon><OfficeBuilding /></el-icon>
                <span>部门管理</span>
              </el-menu-item>
              <el-menu-item index="/emp">
                <el-icon><Avatar /></el-icon>
                <span>员工管理</span>
              </el-menu-item>
            </el-sub-menu>

            <el-sub-menu index="/report">
              <template #title>
                <el-icon><DataAnalysis /></el-icon>
                <span>数据统计管理</span>
              </template>
              <el-menu-item index="/report/emp">
                <el-icon><InfoFilled /></el-icon>
                <span>员工信息统计</span>
              </el-menu-item>
              <el-menu-item index="/report/stu">
                <el-icon><TrendCharts /></el-icon>
                <span>学员信息统计</span>
              </el-menu-item>
              <el-menu-item index="/log">
                <el-icon><Notebook /></el-icon>
                <span>日志信息统计</span>
              </el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>

        <el-main class="main-content">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.common-layout {
  height: 100vh;
  overflow: hidden;
}

.layout-container {
  height: 100vh;
}

.header {
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%);
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 24px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
  z-index: 10;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 12px;
}

.logo-icon {
  font-size: 28px;
  color: #409eff;
}

.title {
  color: #fff;
  font-size: 22px;
  font-family: 'Microsoft YaHei', sans-serif;
  font-weight: 600;
  letter-spacing: 2px;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 16px;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #ccd6e0;
  font-size: 14px;
}

.user-info .el-icon {
  font-size: 18px;
  color: #409eff;
}

.username {
  font-weight: 500;
}

.header :deep(.el-divider--vertical) {
  border-left-color: rgba(255, 255, 255, 0.2);
  height: 20px;
}

.header-link {
  display: flex;
  align-items: center;
  gap: 6px;
  color: #ccd6e0;
  text-decoration: none;
  font-size: 14px;
  padding: 6px 12px;
  border-radius: 6px;
  transition: all 0.3s;
}

.header-link:hover {
  color: #fff;
  background: rgba(255, 255, 255, 0.1);
}

.body-container {
  height: calc(100vh - 60px);
}

.aside {
  background: #fff;
  border-right: 1px solid #e8ecf1;
  overflow-y: auto;
  box-shadow: 2px 0 8px rgba(0, 0, 0, 0.04);
}

.aside::-webkit-scrollbar {
  width: 0;
}

.sidebar-menu {
  border-right: none;
  padding-top: 8px;
}

.sidebar-menu .el-menu-item {
  height: 50px;
  line-height: 50px;
  margin: 4px 8px;
  border-radius: 8px;
  transition: all 0.3s;
}

.sidebar-menu .el-menu-item:hover,
.sidebar-menu .el-menu-item.is-active {
  background: linear-gradient(135deg, #ecf5ff 0%, #e0edff 100%);
  color: #409eff;
}

.sidebar-menu .el-menu-item.is-active {
  font-weight: 600;
  box-shadow: 0 2px 8px rgba(64, 158, 255, 0.15);
}

.sidebar-menu .el-sub-menu :deep(.el-sub-menu__title) {
  height: 50px;
  line-height: 50px;
  margin: 4px 8px;
  border-radius: 8px;
  transition: all 0.3s;
}

.sidebar-menu .el-sub-menu :deep(.el-sub-menu__title:hover) {
  background: #f5f7fa;
}

.main-content {
  background: #f0f2f5;
  padding: 20px;
  overflow-y: auto;
}

.main-content::-webkit-scrollbar {
  width: 6px;
}

.main-content::-webkit-scrollbar-thumb {
  background: #c0c4cc;
  border-radius: 3px;
}

.main-content::-webkit-scrollbar-track {
  background: transparent;
}
</style>