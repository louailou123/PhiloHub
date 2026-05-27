import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import api from '../api/api'

export const useAuthStore = defineStore('auth', () => {
  const user = ref(JSON.parse(localStorage.getItem('user')) || null)
  const token = ref(localStorage.getItem('accessToken') || null)
  const refreshTokenValue = ref(localStorage.getItem('refreshToken') || null)
  
  const isAuthenticated = computed(() => !!token.value)

  function setAuth(userData, accessToken, refreshTokenStr) {
    user.value = userData
    token.value = accessToken
    refreshTokenValue.value = refreshTokenStr
    
    localStorage.setItem('user', JSON.stringify(userData))
    localStorage.setItem('accessToken', accessToken)
    localStorage.setItem('refreshToken', refreshTokenStr)
  }

  async function login(email, password) {
    const response = await api.post('/auth/login', { email, password })
    if (response.data.success) {
      const { user: userData, accessToken, refreshToken } = response.data.data
      setAuth(userData, accessToken, refreshToken)
      return response.data
    }
    throw new Error(response.data.message)
  }

  async function register(formData) {
    const response = await api.post('/auth/register', formData)
    if (response.data.success) {
      const { user: userData, accessToken, refreshToken } = response.data.data
      setAuth(userData, accessToken, refreshToken)
      return response.data
    }
    throw new Error(response.data.message)
  }

  async function refreshAccessToken() {
    if (!refreshTokenValue.value) throw new Error('No refresh token available')
    
    // Use raw axios to prevent interceptor loops
    const response = await api.post('/auth/refresh', {
      refreshToken: refreshTokenValue.value
    })
    
    if (response.data.success) {
      const { accessToken, refreshToken, user: userData } = response.data.data
      setAuth(userData, accessToken, refreshToken)
      return response.data
    }
    throw new Error('Could not refresh token')
  }

  async function logout() {
    try {
      if (refreshTokenValue.value) {
        await api.post('/auth/logout', { refreshToken: refreshTokenValue.value })
      }
    } catch (e) {
      // Ignore errors on logout
    } finally {
      user.value = null
      token.value = null
      refreshTokenValue.value = null
      localStorage.removeItem('user')
      localStorage.removeItem('accessToken')
      localStorage.removeItem('refreshToken')
    }
  }

  function handleOAuthCallback(accessToken, refreshTokenStr, userData) {
    setAuth(userData, accessToken, refreshTokenStr)
  }

  return { 
    user, 
    token, 
    refreshTokenValue, 
    isAuthenticated, 
    login,
    register,
    logout,
    refreshAccessToken,
    handleOAuthCallback
  }
})

