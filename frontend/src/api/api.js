import axios from 'axios'
import { useAuthStore } from '../stores/auth'

const api = axios.create({
  baseURL: 'http://localhost:8081/api',
  headers: {
    'Content-Type': 'application/json'
  }
})

// Request interceptor
api.interceptors.request.use(
  (config) => {
    const authStore = useAuthStore()
    if (authStore.token) {
      config.headers.Authorization = `Bearer ${authStore.token}`
    }
    return config
  },
  (error) => Promise.reject(error)
)

// Response interceptor
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config
    
    // If error is 401 and we haven't retried yet
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true
      
      const authStore = useAuthStore()
      if (authStore.refreshTokenValue) {
        try {
          await authStore.refreshAccessToken()
          // Update the header with new token
          originalRequest.headers.Authorization = `Bearer ${authStore.token}`
          return api(originalRequest)
        } catch (e) {
          // Refresh failed, logout
          authStore.logout()
          window.location.href = '/auth?mode=login'
          return Promise.reject(e)
        }
      }
    }
    return Promise.reject(error)
  }
)

export default api
