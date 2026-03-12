<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import AuthNavBar from '../../Components/HomeComponents/AuthNavBar.vue'

const route = useRoute()
const router = useRouter()

// ── State ──────────────────────────────────────────────────────────────────
const authMode = ref('login')
const isSubmitting = ref(false)
const toast = ref(null)

const loginForm = ref({ email: '', password: '' })
const loginErrors = ref({ email: '', password: '' })

const registerForm = ref({
  name: '',
  email: '',
  password: '',
  confirmPassword: '',
})
const registerErrors = ref({
  name: '',
  email: '',
  password: '',
  confirmPassword: '',
})

const showLoginPw = ref(false)
const showRegPw = ref(false)
const showConfirmPw = ref(false)

// ── Sync Mode with URL ─────────────────────────────────────────────────────
const syncModeFromUrl = () => {
  const modeParam = route.query.mode
  if (modeParam === 'login' || modeParam === 'register') {
    authMode.value = modeParam
  }
}

onMounted(syncModeFromUrl)
watch(() => route.query.mode, syncModeFromUrl)

// ── Computed ───────────────────────────────────────────────────────────────
const isLoginMode = computed(() => authMode.value === 'login')

const validateEmail = (email) =>
  /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(String(email).toLowerCase())

const passwordStrength = computed(() => {
  const p = registerForm.value.password
  if (!p) return 0
  let score = 0
  if (p.length >= 8) score++
  if (/[A-Z]/.test(p)) score++
  if (/[0-9]/.test(p)) score++
  if (/[^A-Za-z0-9]/.test(p)) score++
  return score
})

const strengthMeta = computed(() => {
  const metas = [
    { label: '', color: '' },
    { label: 'Weak', color: '#ef4444' },
    { label: 'Fair', color: '#f97316' },
    { label: 'Good', color: '#3F72AF' },
    { label: 'Strong', color: '#22c55e' },
  ]
  return metas[passwordStrength.value]
})

// ── Actions ────────────────────────────────────────────────────────────────
const showToast = (type, title, message) => {
  toast.value = { type, title, message }
  setTimeout(() => {
    toast.value = null
  }, 3500)
}

const switchMode = (mode) => {
  authMode.value = mode
  router.push({ query: { mode } })
  loginErrors.value = { email: '', password: '' }
  registerErrors.value = { name: '', email: '', password: '', confirmPassword: '' }
}

const handleLogin = async () => {
  let valid = true
  loginErrors.value = { email: '', password: '' }

  if (!loginForm.value.email) {
    loginErrors.value.email = 'Email is required.'
    valid = false
  } else if (!validateEmail(loginForm.value.email)) {
    loginErrors.value.email = 'Enter a valid email address.'
    valid = false
  }
  if (!loginForm.value.password) {
    loginErrors.value.password = 'Password is required.'
    valid = false
  }

  if (!valid) return

  isSubmitting.value = true
  try {
    // Simulate API call
    await new Promise((r) => setTimeout(r, 1200))
    showToast('success', 'Welcome back!', 'You have been signed in.')
  } catch {
    showToast('error', 'Login Failed', 'Invalid credentials. Please try again.')
  } finally {
    isSubmitting.value = false
  }
}

const handleRegister = async () => {
  let valid = true
  registerErrors.value = { name: '', email: '', password: '', confirmPassword: '' }

  if (!registerForm.value.name.trim()) {
    registerErrors.value.name = 'Full name is required.'
    valid = false
  }
  if (!registerForm.value.email) {
    registerErrors.value.email = 'Email is required.'
    valid = false
  } else if (!validateEmail(registerForm.value.email)) {
    registerErrors.value.email = 'Enter a valid email address.'
    valid = false
  }
  if (!registerForm.value.password) {
    registerErrors.value.password = 'Password is required.'
    valid = false
  } else if (registerForm.value.password.length < 8) {
    registerErrors.value.password = 'Password must be at least 8 characters.'
    valid = false
  }
  if (!registerForm.value.confirmPassword) {
    registerErrors.value.confirmPassword = 'Please confirm your password.'
    valid = false
  } else if (registerForm.value.password !== registerForm.value.confirmPassword) {
    registerErrors.value.confirmPassword = 'Passwords do not match.'
    valid = false
  }

  if (!valid) return

  isSubmitting.value = true
  try {
    // Simulate API call
    await new Promise((r) => setTimeout(r, 1400))
    showToast('success', 'Account Created', 'Welcome to PhiloHub!')
    switchMode('login')
    loginForm.value.email = registerForm.value.email
    registerForm.value = { name: '', email: '', password: '', confirmPassword: '' }
  } catch {
    showToast('error', 'Registration Failed', 'That email may already be in use.')
  } finally {
    isSubmitting.value = false
  }
}

const handleSocialLogin = (provider) => {
  showToast('info', `Connecting to ${provider}...`, 'Redirecting to social login.')
  // Integration logic for OAuth would go here
}
</script>

<template>
  <div class="auth-root">
    <!-- Mesh background -->
    <div class="mesh-bg" aria-hidden="true">
      <div class="blob blob-1" />
      <div class="blob blob-2" />
      <div class="blob blob-3" />
    </div>

    <!-- Navbar -->
    <AuthNavBar />

    <main class="auth-main">
      <div class="card-wrapper">
        <div class="glass-card">
          <!-- Brand -->
          <header class="brand-header">
            <div class="logo-mark" aria-hidden="true">
              <span>PH</span>
            </div>
            <h1 class="heading">{{ isLoginMode ? "Welcome back" : "Join PhiloHub" }}</h1>
            <p class="subheading">
              {{ isLoginMode ? "The dialogue continues." : "Start your philosophical legacy." }}
            </p>
          </header>

          <!-- Tabs -->
          <div class="tab-switcher" role="tablist" aria-label="Authentication mode">
            <button
              role="tab"
              :aria-selected="isLoginMode"
              class="tab-btn"
              :class="{ active: isLoginMode }"
              @click="switchMode('login')"
            >
              Sign In
            </button>
            <button
              role="tab"
              :aria-selected="!isLoginMode"
              class="tab-btn"
              :class="{ active: !isLoginMode }"
              @click="switchMode('register')"
            >
              Register
            </button>
          </div>

          <!-- Forms -->
          <transition name="form-fade" mode="out-in">
            <!-- Login Form -->
            <form v-if="isLoginMode" key="login" class="auth-form" novalidate @submit.prevent="handleLogin">
              <div class="field-group">
                <label for="login-email" class="field-label">Email address</label>
                <input
                  id="login-email"
                  v-model="loginForm.email"
                  type="email"
                  class="p-input"
                  :class="{ invalid: !!loginErrors.email }"
                  placeholder="you@example.com"
                  autocomplete="email"
                  @input="loginErrors.email = ''"
                />
                <small v-if="loginErrors.email" class="field-error" role="alert">{{ loginErrors.email }}</small>
              </div>

              <div class="field-group">
                <div class="label-row">
                  <label for="login-password" class="field-label">Password</label>
                  <a href="#" class="forgot-link">Forgot password?</a>
                </div>
                <div class="password-wrap">
                  <input
                    id="login-password"
                    v-model="loginForm.password"
                    :type="showLoginPw ? 'text' : 'password'"
                    class="p-input"
                    :class="{ invalid: !!loginErrors.password }"
                    placeholder="••••••••"
                    autocomplete="current-password"
                    @input="loginErrors.password = ''"
                  />
                  <button type="button" class="pw-toggle" aria-label="Toggle password visibility" @click="showLoginPw = !showLoginPw">
                    <svg v-if="showLoginPw" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M17.94 17.94A10.07 10.07 0 0112 20c-7 0-11-8-11-8a18.45 18.45 0 015.06-5.94M9.9 4.24A9.12 9.12 0 0112 4c7 0 11 8 11 8a18.5 18.5 0 01-2.16 3.19m-6.72-1.07a3 3 0 11-4.24-4.24"/><line x1="1" y1="1" x2="23" y2="23"/></svg>
                    <svg v-else width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/></svg>
                  </button>
                </div>
                <small v-if="loginErrors.password" class="field-error" role="alert">{{ loginErrors.password }}</small>
              </div>

              <button type="submit" class="submit-btn" :disabled="isSubmitting">
                <span v-if="isSubmitting" class="spinner" aria-hidden="true" />
                {{ isSubmitting ? "Signing in…" : "Sign In" }}
              </button>

              <div class="social-divider">
                <span class="divider-text">or continue with</span>
              </div>

              <div class="social-grid">
                <button type="button" class="social-btn google" @click="handleSocialLogin('Google')">
                  <svg width="20" height="20" viewBox="0 0 24 24" fill="currentColor"><path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"/><path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"/><path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" fill="#FBBC05"/><path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"/></svg>
                  <span>Google</span>
                </button>
                <button type="button" class="social-btn facebook" @click="handleSocialLogin('Facebook')">
                  <svg width="20" height="20" viewBox="0 0 24 24" fill="currentColor"><path d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.47h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.47h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z"/></svg>
                  <span>Facebook</span>
                </button>
              </div>

              <p class="switch-text">
                New to PhiloHub?
                <button type="button" class="switch-link" @click="switchMode('register')">Create an account</button>
              </p>
            </form>

            <!-- Register Form -->
            <form v-else key="register" class="auth-form" novalidate @submit.prevent="handleRegister">
              <div class="field-group">
                <label for="reg-name" class="field-label">Full name</label>
                <input
                  id="reg-name"
                  v-model="registerForm.name"
                  type="text"
                  class="p-input"
                  :class="{ invalid: !!registerErrors.name }"
                  placeholder="Jane Doe"
                  autocomplete="name"
                  @input="registerErrors.name = ''"
                />
                <small v-if="registerErrors.name" class="field-error" role="alert">{{ registerErrors.name }}</small>
              </div>

              <div class="field-group">
                <label for="reg-email" class="field-label">Email address</label>
                <input
                  id="reg-email"
                  v-model="registerForm.email"
                  type="email"
                  class="p-input"
                  :class="{ invalid: !!registerErrors.email }"
                  placeholder="you@example.com"
                  autocomplete="email"
                  @input="registerErrors.email = ''"
                />
                <small v-if="registerErrors.email" class="field-error" role="alert">{{ registerErrors.email }}</small>
              </div>

              <div class="field-group">
                <label for="reg-password" class="field-label">Password</label>
                <div class="password-wrap">
                  <input
                    id="reg-password"
                    v-model="registerForm.password"
                    :type="showRegPw ? 'text' : 'password'"
                    class="p-input"
                    :class="{ invalid: !!registerErrors.password }"
                    placeholder="Min. 8 characters"
                    autocomplete="new-password"
                    @input="registerErrors.password = ''"
                  />
                  <button type="button" class="pw-toggle" aria-label="Toggle password visibility" @click="showRegPw = !showRegPw">
                    <svg v-if="showRegPw" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M17.94 17.94A10.07 10.07 0 0112 20c-7 0-11-8-11-8a18.45 18.45 0 015.06-5.94M9.9 4.24A9.12 9.12 0 0112 4c7 0 11 8 11 8a18.5 18.5 0 01-2.16 3.19m-6.72-1.07a3 3 0 11-4.24-4.24"/><line x1="1" y1="1" x2="23" y2="23"/></svg>
                    <svg v-else width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/></svg>
                  </button>
                </div>
                <div v-if="registerForm.password" class="strength-wrap" :aria-label="`Password strength: ${strengthMeta.label}`">
                  <div class="strength-track" aria-hidden="true">
                    <div
                      class="strength-fill"
                      :style="{ width: `${(passwordStrength / 4) * 100}%`, backgroundColor: strengthMeta.color }"
                    />
                  </div>
                  <span class="strength-text" :style="{ color: strengthMeta.color }">{{ strengthMeta.label }}</span>
                </div>
                <small v-if="registerErrors.password" class="field-error" role="alert">{{ registerErrors.password }}</small>
              </div>

              <div class="field-group">
                <label for="reg-confirm" class="field-label">Confirm password</label>
                <div class="password-wrap">
                  <input
                    id="reg-confirm"
                    v-model="registerForm.confirmPassword"
                    :type="showConfirmPw ? 'text' : 'password'"
                    class="p-input"
                    :class="{ invalid: !!registerErrors.confirmPassword }"
                    placeholder="Repeat your password"
                    autocomplete="new-password"
                    @input="registerErrors.confirmPassword = ''"
                  />
                  <button type="button" class="pw-toggle" aria-label="Toggle confirm password visibility" @click="showConfirmPw = !showConfirmPw">
                    <svg v-if="showConfirmPw" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M17.94 17.94A10.07 10.07 0 0112 20c-7 0-11-8-11-8a18.45 18.45 0 015.06-5.94M9.9 4.24A9.12 9.12 0 0112 4c7 0 11 8 11 8a18.5 18.5 0 01-2.16 3.19m-6.72-1.07a3 3 0 11-4.24-4.24"/><line x1="1" y1="1" x2="23" y2="23"/></svg>
                    <svg v-else width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/></svg>
                  </button>
                </div>
                <small v-if="registerErrors.confirmPassword" class="field-error" role="alert">{{ registerErrors.confirmPassword }}</small>
              </div>

              <button type="submit" class="submit-btn" :disabled="isSubmitting">
                <span v-if="isSubmitting" class="spinner" aria-hidden="true" />
                {{ isSubmitting ? "Creating account…" : "Create Account" }}
              </button>

              <div class="social-divider">
                <span class="divider-text">or join with</span>
              </div>

              <div class="social-grid">
                <button type="button" class="social-btn google" @click="handleSocialLogin('Google')">
                  <svg width="20" height="20" viewBox="0 0 24 24" fill="currentColor"><path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"/><path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"/><path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" fill="#FBBC05"/><path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"/></svg>
                  <span>Google</span>
                </button>
                <button type="button" class="social-btn facebook" @click="handleSocialLogin('Facebook')">
                  <svg width="20" height="20" viewBox="0 0 24 24" fill="currentColor"><path d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.47h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.47h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z"/></svg>
                  <span>Facebook</span>
                </button>
              </div>

              <p class="switch-text">
                Already a member?
                <button type="button" class="switch-link" @click="switchMode('login')">Sign in</button>
              </p>
            </form>
          </transition>
        </div>

        <p class="footer-note">
          By continuing, you agree to our
          <a href="#">Terms</a> and <a href="#">Privacy Policy</a>.
        </p>
      </div>
    </main>

    <!-- Toast UI -->
    <transition name="toast-fade">
      <div v-if="toast" class="toast-wrap" role="status" aria-live="polite">
        <div class="toast-card" :class="{ error: toast.type === 'error' }">
          <span class="toast-title">{{ toast.title }}</span>
          <span class="toast-msg">{{ toast.message }}</span>
        </div>
      </div>
    </transition>
  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap');

/* ── Reset / Root ────────────────────────────────────────── */
.auth-root {
  position: relative;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #F0F4FA;
  overflow: hidden;
  font-family: 'Inter', system-ui, sans-serif;
}

/* ── Auth Navbar ── */
.auth-nav {
  position: relative;
  z-index: 10;
  background: rgba(240, 244, 250, 0.85);
  backdrop-filter: blur(16px) saturate(180%);
  -webkit-backdrop-filter: blur(16px) saturate(180%);
  border-bottom: 1px solid rgba(17, 45, 78, 0.08);
  padding: 0 2rem;
  height: 4rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.auth-nav-brand {
  display: flex;
  align-items: center;
  gap: 0.625rem;
  text-decoration: none;
}

/* ── Mesh background ── */
.mesh-bg {
  position: fixed;
  inset: 0;
  filter: blur(90px);
  pointer-events: none;
  z-index: 0;
  overflow: hidden;
}
.blob {
  position: absolute;
  border-radius: 50%;
  opacity: 0.18;
  animation: drift 22s infinite alternate cubic-bezier(0.45,0,0.55,1);
}
.blob-1 { width:65%; height:65%; background:#3F72AF; top:-15%; left:-12%; }
.blob-2 { width:55%; height:55%; background:#112D4E; bottom:-15%; right:-12%; animation-delay:-7s; }
.blob-3 { width:40%; height:40%; background:#DBE2EF; top:35%; left:28%; animation-delay:-14s; }
@keyframes drift {
  0% { transform: translate(0,0) scale(1); }
  100% { transform: translate(8%,12%) scale(1.12); }
}

/* ── Layout ── */
.auth-main {
  position: relative;
  z-index: 1;
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2rem 1rem;
}
.card-wrapper {
  width: 100%;
  max-width: 26rem;
  animation: slideUp 0.7s cubic-bezier(0.2,1,0.3,1) both;
}
@keyframes slideUp {
  from { opacity:0; transform:translateY(36px); }
  to { opacity:1; transform:translateY(0); }
}
.glass-card {
  background: rgba(255,255,255,0.55);
  backdrop-filter: blur(28px) saturate(160%);
  -webkit-backdrop-filter: blur(28px) saturate(160%);
  border: 1px solid rgba(255,255,255,0.85);
  border-radius: 1.75rem;
  padding: 2.5rem 2.25rem;
  box-shadow: 0 4px 6px rgba(17,45,78,0.04), 0 20px 60px rgba(17,45,78,0.10);
}

/* ── Brand ── */
.brand-header { text-align:center; margin-bottom:1.75rem; }
.logo-mark {
  width:3.5rem; height:3.5rem; border-radius:1rem;
  background:#112D4E; display:inline-flex; align-items:center;
  justify-content:center; margin-bottom:1.25rem;
  box-shadow:0 8px 24px rgba(17,45,78,0.22);
}
.logo-mark span { color:#F9F7F7; font-size:1.125rem; font-weight:900; letter-spacing:-0.05em; }
.heading { font-size:1.875rem; font-weight:800; color:#112D4E; margin:0 0 0.375rem; letter-spacing:-0.03em; line-height:1.2; }
.subheading { font-size:0.875rem; color:#112D4E; opacity:0.55; font-weight:600; letter-spacing:0.04em; margin:0; }

/* ── Tabs ── */
.tab-switcher {
  display:flex; background:rgba(17,45,78,0.07);
  border-radius:0.875rem; padding:0.25rem; gap:0.25rem; margin-bottom:1.75rem;
}
.tab-btn {
  flex:1; border:none; background:transparent;
  padding:0.625rem 1rem; border-radius:0.625rem;
  font-size:0.875rem; font-weight:700; color:rgba(17,45,78,0.5);
  cursor:pointer; transition:all 0.22s ease; outline:none; font-family:inherit;
}
.tab-btn.active { background:#fff; color:#112D4E; box-shadow:0 2px 12px rgba(17,45,78,0.10); }
.tab-btn:not(.active):hover { color:#112D4E; background:rgba(255,255,255,0.4); }

/* ── Form ── */
.auth-form { display:flex; flex-direction:column; gap:1.25rem; }
.field-group { display:flex; flex-direction:column; gap:0.375rem; }
.field-label { font-size:0.75rem; font-weight:800; text-transform:uppercase; letter-spacing:0.07em; color:#112D4E; opacity:0.65; }
.label-row { display:flex; align-items:center; justify-content:space-between; }
.forgot-link { font-size:0.75rem; font-weight:700; color:#3F72AF; text-decoration:none; }
.forgot-link:hover { color:#112D4E; text-decoration:underline; }
.field-error { font-size:0.75rem; font-weight:600; color:#ef4444; padding-left:0.125rem; }

/* ── Inputs ── */
.p-input {
  background:rgba(255,255,255,0.65); border:1.5px solid rgba(17,45,78,0.12);
  border-radius:0.875rem; color:#112D4E; font-weight:600; font-size:0.9375rem;
  padding:0.75rem 1rem; width:100%; font-family:inherit; outline:none;
  transition:border-color 0.2s, box-shadow 0.2s, background 0.2s;
}
.p-input:focus { background:#fff; border-color:#3F72AF; box-shadow:0 0 0 4px rgba(63,114,175,0.12); }
.p-input.invalid { border-color:#ef4444; box-shadow:0 0 0 3px rgba(239,68,68,0.10); }
.p-input::placeholder { color:rgba(17,45,78,0.3); font-weight:500; }
.password-wrap { position:relative; }
.password-wrap .p-input { padding-right:3rem; }
.pw-toggle {
  position:absolute; right:0.875rem; top:50%; transform:translateY(-50%);
  background:none; border:none; cursor:pointer; padding:0;
  color:rgba(17,45,78,0.45); display:flex; align-items:center;
  transition:color 0.2s;
}
.pw-toggle:hover { color:#112D4E; }

/* ── Strength ── */
.strength-wrap { display:flex; align-items:center; gap:0.625rem; margin-top:0.25rem; }
.strength-track { flex:1; height:4px; background:rgba(17,45,78,0.1); border-radius:99px; overflow:hidden; }
.strength-fill { height:100%; border-radius:99px; transition:width 0.35s ease, background-color 0.35s ease; }
.strength-text { font-size:0.6875rem; font-weight:800; text-transform:uppercase; letter-spacing:0.05em; min-width:2.75rem; text-align:right; }

/* ── Submit ── */
.submit-btn {
  background:#112D4E; border:none; border-radius:0.875rem;
  color:#F9F7F7; font-weight:800; font-size:0.9375rem;
  letter-spacing:0.02em; padding:0.9rem 1.5rem; width:100%;
  font-family:inherit; cursor:pointer; margin-top:0.25rem;
  box-shadow:0 6px 24px rgba(17,45,78,0.18);
  transition:all 0.28s cubic-bezier(0.34,1.56,0.64,1);
  display:flex; align-items:center; justify-content:center; gap:0.5rem;
}
.submit-btn:hover:not(:disabled) { background:#3F72AF; transform:translateY(-2px) scale(1.015); box-shadow:0 10px 36px rgba(63,114,175,0.28); }
.submit-btn:active:not(:disabled) { transform:translateY(0) scale(0.99); }
.submit-btn:disabled { opacity:0.7; cursor:not-allowed; }

/* ── Spinner ── */
@keyframes spin { to { transform:rotate(360deg); } }
.spinner {
  width:1rem; height:1rem; border:2px solid rgba(249,247,247,0.35);
  border-top-color:#F9F7F7; border-radius:50%;
  animation:spin 0.75s linear infinite;
}

/* ── Switch text ── */
.switch-text { text-align:center; font-size:0.8125rem; font-weight:600; color:rgba(17,45,78,0.6); margin:0.25rem 0 0; }
.switch-link { background:none; border:none; padding:0; margin-left:0.25rem; font-size:inherit; font-weight:800; color:#3F72AF; cursor:pointer; font-family:inherit; }
.switch-link:hover { color:#112D4E; text-decoration:underline; }

/* ── Social Login ── */
.social-divider {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin: 0.5rem 0;
}
.social-divider::before,
.social-divider::after {
  content: '';
  flex: 1;
  height: 1px;
  background: rgba(17, 45, 78, 0.08);
}
.divider-text {
  font-size: 0.75rem;
  font-weight: 700;
  color: rgba(17, 45, 78, 0.35);
  text-transform: uppercase;
  letter-spacing: 0.04em;
}

.social-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 0.75rem;
}

.social-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.625rem;
  background: rgba(255, 255, 255, 0.65);
  border: 1.5px solid rgba(17, 45, 78, 0.1);
  border-radius: 0.875rem;
  padding: 0.625rem;
  cursor: pointer;
  transition: all 0.2s cubic-bezier(0.4, 0, 0.2, 1);
  font-family: inherit;
}
.social-btn:hover {
  background: #ffffff;
  border-color: rgba(17, 45, 78, 0.2);
  transform: translateY(-1px);
  box-shadow: 0 4px 12px rgba(17, 45, 78, 0.06);
}
.social-btn span {
  font-size: 0.8125rem;
  font-weight: 700;
  color: #112D4E;
}
.social-btn.facebook svg {
  color: #1877F2;
}

/* ── Footer note ── */
.footer-note { text-align:center; margin-top:1.25rem; font-size:0.75rem; color:rgba(17,45,78,0.4); font-weight:500; }
.footer-note a { color:rgba(17,45,78,0.6); font-weight:700; text-decoration:underline; text-underline-offset:2px; }

/* ── Toast ── */
.toast-wrap {
  position:fixed; bottom:1.5rem; right:1.5rem; z-index:9999;
  display:flex; flex-direction:column; gap:0.5rem;
}
.toast-card {
  background:rgba(255,255,255,0.9); backdrop-filter:blur(16px);
  border-radius:1rem; padding:1rem 1.25rem;
  box-shadow:0 8px 32px rgba(17,45,78,0.12);
  border-left:4px solid #22c55e;
  min-width:16rem; max-width:22rem;
  display:flex; flex-direction:column; gap:0.2rem;
}
.toast-card.error { border-left-color:#ef4444; }
.toast-title { font-size:0.875rem; font-weight:800; color:#112D4E; }
.toast-msg { font-size:0.8125rem; color:rgba(17,45,78,0.65); font-weight:500; }

/* ── Transitions ── */
.form-fade-enter-active, .form-fade-leave-active {
  transition: opacity 0.3s ease, transform 0.3s ease;
}
.form-fade-enter-from { opacity: 0; transform: translateY(10px); }
.form-fade-leave-to { opacity: 0; transform: translateY(-10px); }

.toast-fade-enter-active, .toast-fade-leave-active {
  transition: opacity 0.3s ease, transform 0.3s ease;
}
.toast-fade-enter-from { opacity: 0; transform: translateY(20px); }
.toast-fade-leave-to { opacity: 0; transform: translateY(20px); }

</style>
