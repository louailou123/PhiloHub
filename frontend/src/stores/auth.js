import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useAuthStore = defineStore('auth', () => {
  const user = ref(null)
  const token = ref(localStorage.getItem('token') || null)
  const isAuthenticated = computed(() => !!token.value)

  function setAuth(userData, userToken) {
    user.value = userData
    token.value = userToken
    localStorage.setItem('token', userToken)
  }

  function logout() {
    user.value = null
    token.value = null
    localStorage.removeItem('token')
  }

  return { user, token, isAuthenticated, setAuth, logout }
})
