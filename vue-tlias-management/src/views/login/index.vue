<template>
  <div class="login-page">
    <!-- Left Content Section with Animated Characters -->
    <div class="left-section">
      <div class="logo-section">
        <a href="/" class="logo-link">
          <span>Tlias 智能学习辅助系统</span>
        </a>
      </div>

      <div class="characters-section">
        <AnimatedCharacters :isTyping="isTyping" :showPassword="showPassword" :passwordLength="loginForm.password.length"
          :loginFailed="loginFailed" :loginSuccess="loginSuccess" />
      </div>

      <div class="footer-links">
        <a href="/" class="footer-link">首页</a>
        <a href="/about" class="footer-link">关于我们</a>
      </div>

      <!-- Decorative elements -->
      <div class="grid-overlay"></div>
      <div class="blur-circle blur-circle-1"></div>
      <div class="blur-circle blur-circle-2"></div>
    </div>

    <!-- Right Login Section -->
    <div class="right-section">
      <div class="form-wrapper">
        <!-- Header -->
        <div class="form-header">
          <h1 class="form-title">欢迎回来!</h1>
          <p class="form-subtitle">请输入您的账号信息</p>
        </div>

        <!-- Login Form -->
        <form @submit.prevent="handleSubmit" class="login-form">
          <!-- Username Field -->
          <div class="form-group">
            <label for="username" class="form-label">用户名</label>
            <input id="username" v-model="loginForm.username" type="text" placeholder="请输入用户名" class="form-input"
              autocomplete="off" required @focus="isTyping = true" @blur="isTyping = false" />
          </div>

          <!-- Password Field -->
          <div class="form-group">
            <label for="password" class="form-label">密码</label>
            <div class="password-wrapper">
              <input id="password" v-model="loginForm.password" :type="showPassword ? 'text' : 'password'" placeholder="请输入密码"
                class="form-input" autocomplete="new-password" required @focus="isTyping = true" @blur="isTyping = false" />
              <button type="button" @click="showPassword = !showPassword" class="password-toggle">
                <svg v-if="showPassword" class="icon" xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                  viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                  stroke-linejoin="round">
                  <path d="M2 12s3-7 10-7 10 7 10 7-3 7-10 7-10-7-10-7Z" />
                  <circle cx="12" cy="12" r="3" />
                </svg>
                <svg v-else class="icon" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"
                  fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                  <path d="M9.88 9.88a3 3 0 1 0 4.24 4.24" />
                  <path d="M10.73 5.08A10.43 10.43 0 0 1 12 5c7 0 10 7 10 7a13.16 13.16 0 0 1-1.67 2.68" />
                  <path d="M6.61 6.61A13.526 13.526 0 0 0 2 12s3 7 10 7a9.74 9.74 0 0 0 5.39-1.61" />
                  <line x1="2" x2="22" y1="2" y2="22" />
                </svg>
              </button>
            </div>
          </div>

          <!-- Error Alert -->
          <div v-if="errorMessage" class="error-alert">
            {{ errorMessage }}
          </div>

          <!-- Buttons -->
          <div class="button-group">
            <button type="submit" class="submit-button" :disabled="isLoading">
              <span class="button-text">{{ isLoading ? '登录中...' : '登 录' }}</span>
              <svg class="button-icon" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"
                fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M5 12h14" />
                <path d="m12 5 7 7-7 7" />
              </svg>
            </button>
            <button type="button" class="reset-button" @click="resetForm">
              重 置
            </button>
          </div>
        </form>

        <!-- Footer -->
        <div class="form-footer">
          <p>Tlias智能学习辅助系统 &copy; {{ currentYear }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { loginApi } from '@/api/login'
import { ElMessage } from 'element-plus'
import AnimatedCharacters from '@/components/AnimatedCharacters.vue'

const router = useRouter()
const currentYear = computed(() => new Date().getFullYear())

const loginForm = ref({ username: '', password: '' })
const showPassword = ref(false)
const isTyping = ref(false)
const isLoading = ref(false)
const loginFailed = ref(false)
const loginSuccess = ref(false)
const errorMessage = ref('')

const handleSubmit = async () => {
  if (!loginForm.value.username || !loginForm.value.password) {
    errorMessage.value = '请输入用户名和密码'
    return
  }

  isLoading.value = true
  errorMessage.value = ''
  loginFailed.value = false
  loginSuccess.value = false

  try {
    const result = await loginApi(loginForm.value)
    if (result.code) {
      loginSuccess.value = true
      ElMessage.success('登录成功')
      localStorage.setItem('loginUser', JSON.stringify(result.data))
      setTimeout(() => {
        router.push('/')
      }, 2000)
    } else {
      throw new Error(result.msg || '登录失败')
    }
  } catch (error) {
    errorMessage.value = error.message || '用户名或密码错误，请重试'
    loginFailed.value = true
    setTimeout(() => {
      loginFailed.value = false
    }, 3000)
  } finally {
    isLoading.value = false
  }
}

const resetForm = () => {
  loginForm.value = { username: '', password: '' }
  errorMessage.value = ''
  loginFailed.value = false
  loginSuccess.value = false
}
</script>

<style scoped>
.login-page {
  display: grid;
  grid-template-columns: 1fr 1fr;
  min-height: 100vh;
  max-height: 100vh;
  overflow: hidden;
}

.left-section {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background: linear-gradient(to bottom right, #9ca3af, #6b7280, #4b5563);
  padding: 3rem;
  color: white;
}

.logo-section {
  position: relative;
  z-index: 20;
}

.logo-link {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 1.25rem;
  font-weight: 600;
  text-decoration: none;
  color: inherit;
}

.characters-section {
  position: relative;
  z-index: 20;
  display: flex;
  align-items: flex-end;
  justify-content: center;
  height: 500px;
}

.footer-links {
  position: relative;
  z-index: 20;
  display: flex;
  align-items: center;
  gap: 2rem;
  font-size: 0.875rem;
  color: #4b5563;
}

.footer-link {
  color: inherit;
  text-decoration: none;
  transition: color 0.2s;
}

.footer-link:hover {
  color: #1f2937;
}

.grid-overlay {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(255, 255, 255, 0.05) 1px, transparent 1px),
    linear-gradient(90deg, rgba(255, 255, 255, 0.05) 1px, transparent 1px);
  background-size: 20px 20px;
}

.blur-circle {
  position: absolute;
  border-radius: 50%;
  filter: blur(96px);
}

.blur-circle-1 {
  top: 25%;
  right: 25%;
  width: 16rem;
  height: 16rem;
  background: rgba(156, 163, 175, 0.2);
}

.blur-circle-2 {
  bottom: 25%;
  left: 25%;
  width: 24rem;
  height: 24rem;
  background: rgba(209, 213, 219, 0.2);
}

.right-section {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2rem;
  background: white;
}

.form-wrapper {
  width: 100%;
  max-width: 420px;
}

.form-header {
  text-align: center;
  margin-bottom: 2.5rem;
}

.form-title {
  font-size: 1.875rem;
  font-weight: 700;
  letter-spacing: -0.025em;
  margin-bottom: 0.5rem;
  color: #111827;
}

.form-subtitle {
  font-size: 0.875rem;
  color: #6b7280;
}

.login-form {
  display: flex;
  flex-direction: column;
  gap: 1.25rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.form-label {
  font-size: 0.875rem;
  font-weight: 500;
  color: #374151;
}

.form-input {
  width: 100%;
  height: 3rem;
  padding: 0 1rem;
  background: white;
  border: 1.5px solid rgba(229, 231, 235, 0.6);
  border-radius: 0.5rem;
  font-size: 1rem;
  transition: all 0.2s;
  outline: none;
}

.form-input::-ms-reveal,
.form-input::-ms-clear {
  display: none;
}

.form-input::-webkit-credentials-auto-fill-button {
  display: none;
}

.form-input::placeholder {
  opacity: 0.35;
}

.form-input:focus {
  border-color: #6366f1;
  box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.1);
}

.password-wrapper {
  position: relative;
}

.password-wrapper .form-input {
  padding-right: 2.5rem;
}

.password-toggle {
  position: absolute;
  right: 0.75rem;
  top: 50%;
  transform: translateY(-50%);
  background: none;
  border: none;
  color: #9ca3af;
  cursor: pointer;
  padding: 0;
  display: flex;
  align-items: center;
  transition: color 0.2s;
}

.password-toggle:hover {
  color: #111827;
}

.icon {
  width: 20px;
  height: 20px;
}

.error-alert {
  padding: 0.75rem;
  font-size: 0.875rem;
  color: #dc2626;
  background: rgba(220, 38, 38, 0.1);
  border: 1px solid rgba(220, 38, 38, 0.3);
  border-radius: 0.5rem;
}

.button-group {
  display: flex;
  gap: 1rem;
  margin-top: 0.5rem;
}

.submit-button {
  position: relative;
  flex: 1;
  height: 3rem;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  font-size: 1rem;
  font-weight: 500;
  border-radius: 0.5rem;
  cursor: pointer;
  overflow: hidden;
  transition: all 0.3s;
  background: #111827;
  color: white;
  border: none;
}

.submit-button:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
}

.submit-button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.button-text {
  transition: transform 0.3s;
}

.button-icon {
  width: 20px;
  height: 20px;
  transition: transform 0.3s;
}

.submit-button:hover:not(:disabled) .button-text {
  transform: translateX(-8px);
}

.submit-button:hover:not(:disabled) .button-icon {
  transform: translateX(8px);
}

.reset-button {
  height: 3rem;
  padding: 0 2rem;
  font-size: 1rem;
  font-weight: 500;
  border-radius: 0.5rem;
  cursor: pointer;
  transition: all 0.3s;
  background: white;
  color: #374151;
  border: 1.5px solid rgba(229, 231, 235, 0.6);
}

.reset-button:hover {
  background: #f9fafb;
  border-color: #d1d5db;
}

.form-footer {
  margin-top: 3rem;
  text-align: center;
  font-size: 0.75rem;
  color: #9ca3af;
}

@media (max-width: 1024px) {
  .login-page {
    grid-template-columns: 1fr;
  }

  .left-section {
    display: none;
  }
}
</style>
