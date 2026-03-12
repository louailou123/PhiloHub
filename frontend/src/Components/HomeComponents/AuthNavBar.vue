<script setup>
defineProps({
  transparent: {
    type: Boolean,
    default: false
  }
})
</script>

<template>
  <nav class="auth-nav" :class="{ 'nav-transparent': transparent }" aria-label="Auth navigation">

    <div class="nav-actions">
      <router-link to="/" class="nav-back-btn">
        <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M19 12H5"/><path d="M12 19l-7-7 7-7"/></svg>
        <span>Back to Home</span>
      </router-link>
    </div>
  </nav>
</template>

<style scoped>
.auth-nav {
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

.nav-actions {
  display: flex;
  align-items: center;
}

.nav-back-btn {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.875rem;
  font-weight: 700;
  color: rgba(17, 45, 78, 0.5);
  text-decoration: none;
  padding: 0.5rem 0.875rem;
  border-radius: 0.625rem;
  transition: all 0.2s ease;
}

.nav-back-btn:hover {
  color: #112D4E;
  background: rgba(17, 45, 78, 0.06);
}

@media (max-width: 640px) {
  .auth-nav {
    padding: 0 1rem;
  }
  .nav-back-btn span {
    display: none;
  }
}
</style>
