<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

defineProps({
  transparent: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['login', 'register'])
</script>

<template>
  <nav class="home-nav" :class="{ 'nav-transparent': transparent }" aria-label="Main navigation">
    <router-link to="/" class="nav-brand">
      <div class="nav-logo" aria-hidden="true">
        <span>PH</span>
      </div>
      <span class="nav-wordmark">PhiloHub</span>
    </router-link>

    <div class="nav-links" role="list">
      <router-link to="/" class="nav-link" role="listitem">Home</router-link>
      <router-link to="/about" class="nav-link" role="listitem">About</router-link>
    </div>

    <div class="nav-actions">
      <router-link to="/auth?mode=login" class="nav-btn-ghost">Sign In</router-link>
      <router-link to="/auth?mode=register" class="nav-btn-solid">Join Free</router-link>
    </div>
  </nav>
</template>

<style scoped>
.home-nav {
  position: sticky;
  top: 0;
  z-index: 50;
  background: rgba(249, 247, 247, 0.88);
  backdrop-filter: blur(16px) saturate(180%);
  -webkit-backdrop-filter: blur(16px) saturate(180%);
  border-bottom: 1px solid rgba(17, 45, 78, 0.08);
  padding: 0 2rem;
  height: 4.5rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
  transition: all 0.3s ease;
}

.nav-transparent {
  background: transparent;
  backdrop-filter: none;
  border-bottom-color: transparent;
}

.nav-brand {
  display: flex;
  align-items: center;
  gap: 0.625rem;
  text-decoration: none;
}

.nav-logo {
  width: 2.25rem;
  height: 2.25rem;
  border-radius: 0.625rem;
  background: #112D4E;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  box-shadow: 0 4px 12px rgba(17, 45, 78, 0.15);
}

.nav-logo span {
  color: #F9F7F7;
  font-size: 0.75rem;
  font-weight: 900;
  letter-spacing: -0.05em;
}

.nav-wordmark {
  font-size: 1.125rem;
  font-weight: 800;
  color: #112D4E;
  letter-spacing: -0.03em;
}

.nav-links {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.nav-link {
  font-size: 0.9375rem;
  font-weight: 600;
  color: rgba(17, 45, 78, 0.65);
  text-decoration: none;
  padding: 0.5rem 1rem;
  border-radius: 0.75rem;
  transition: all 0.2s ease;
}

.nav-link:hover {
  color: #112D4E;
  background: rgba(17, 45, 78, 0.05);
}

.nav-link.router-link-active {
  color: #112D4E;
  background: rgba(17, 45, 78, 0.08);
}

.nav-actions {
  display: flex;
  align-items: center;
  gap: 0.75rem;
}

.nav-btn-ghost {
  font-size: 0.9375rem;
  font-weight: 700;
  color: #3F72AF;
  text-decoration: none;
  padding: 0.625rem 1.25rem;
  border-radius: 0.875rem;
  border: 1.5px solid rgba(63, 114, 175, 0.25);
  background: transparent;
  transition: all 0.2s ease;
}

.nav-btn-ghost:hover {
  border-color: #3F72AF;
  background: rgba(63, 114, 175, 0.05);
  color: #112D4E;
}

.nav-btn-solid {
  font-size: 0.9375rem;
  font-weight: 700;
  color: #F9F7F7;
  text-decoration: none;
  padding: 0.625rem 1.25rem;
  border-radius: 0.875rem;
  background: #112D4E;
  transition: all 0.25s cubic-bezier(0.34, 1.56, 0.64, 1);
  box-shadow: 0 4px 14px rgba(17, 45, 78, 0.15);
}

.nav-btn-solid:hover {
  background: #3F72AF;
  transform: translateY(-1px);
  box-shadow: 0 8px 20px rgba(63, 114, 175, 0.22);
}

@media (max-width: 768px) {
  .nav-links {
    display: none;
  }
  .home-nav {
    padding: 0 1rem;
  }
}
</style>