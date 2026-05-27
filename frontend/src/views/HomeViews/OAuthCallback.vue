<template>
  <div class="flex items-center justify-center min-h-screen bg-gray-900 text-white">
    <div class="text-center">
      <i class="pi pi-spin pi-spinner text-primary" style="font-size: 3rem"></i>
      <p class="mt-4 text-xl">Authenticating with provider...</p>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useAuthStore } from '../../stores/auth'

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()

onMounted(() => {
  const { accessToken, refreshToken } = route.query
  if (accessToken && refreshToken) {
    try {
      const payload = JSON.parse(atob(accessToken.split('.')[1]))
      const userData = { email: payload.sub, username: payload.sub }
      
      authStore.handleOAuthCallback(accessToken, refreshToken, userData)
      router.push('/home')
    } catch (e) {
      console.error(e)
      router.push('/auth?mode=login')
    }
  } else {
    router.push('/auth?mode=login')
  }
})
</script>
