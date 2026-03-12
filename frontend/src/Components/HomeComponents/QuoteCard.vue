<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import Card from 'primevue/card'
import Button from 'primevue/button'

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
})

const quote = ref(null)
const loadingQuote = ref(false)
const quoteError = ref('')

const fetchRandomQuote = async () => {
  loadingQuote.value = true
  quoteError.value = ''

  try {
    const { data } = await api.get('/quotes/random')
    quote.value = data
  } catch (error) {
    quoteError.value = 'Unable to load a quote right now.'
    // Fallback for demo if API fails
    quote.value = {
      text: "The only thing I know is that I know nothing.",
      author: "Socrates"
    }
  } finally {
    loadingQuote.value = false
  }
}

onMounted(() => {
  fetchRandomQuote()
})
</script>

<template>
  <Card class="quote-card border-none shadow-8 overflow-hidden">
    <template #title>
      <div class="flex align-items-center gap-2 mb-2">
        <i class="pi pi-sparkles text-cyan-400"></i>
        <span class="text-xs uppercase font-bold tracking-widest text-cyan-400">Daily Reflection</span>
      </div>
    </template>
    <template #content>
      <div v-if="loadingQuote" class="flex flex-column gap-2">
        <div class="skeleton-text h-1rem w-full"></div>
        <div class="skeleton-text h-1rem w-8"></div>
      </div>
      <div v-else-if="quoteError && !quote" class="text-sm text-red-400">
        {{ quoteError }}
      </div>
      <div v-else-if="quote" class="quote-container">
        <p class="text-xl md:text-2xl font-medium line-height-3 text-gray-100 italic transition-all duration-300">
          “{{ quote.text || quote.content || quote.quote }}”
        </p>
        <div class="flex align-items-center gap-3 mt-4">
          <div class="w-2rem h-px bg-cyan-700"></div>
          <p class="text-sm font-semibold text-gray-400 uppercase tracking-wider">
            {{ quote.author || 'Unknown Thinker' }}
          </p>
        </div>
      </div>
    </template>
    <template #footer>
      <div class="flex justify-content-end">
        <Button
          icon="pi pi-refresh"
          class="p-button-rounded p-button-text p-button-sm text-gray-400 hover:text-cyan-400 transition-colors duration-200"
          :loading="loadingQuote"
          @click="fetchRandomQuote"
          v-tooltip="'Get another thought'"
        />
      </div>
    </template>
  </Card>
</template>

<style scoped>
.quote-card {
  background: #F9F7F7;
  border: 1px solid #DBE2EF;
  border-bottom: 4px solid #3F72AF;
  border-radius: 1.5rem;
  transition: all 0.3s cubic-bezier(0.16, 1, 0.3, 1);
  box-shadow: 0 10px 30px rgba(17, 45, 78, 0.05) !important;
}

.quote-card:hover {
  transform: translateY(-4px);
  border-color: #3F72AF;
  box-shadow: 0 20px 50px rgba(17, 45, 78, 0.1) !important;
}

.skeleton-text {
  background: linear-gradient(90deg, #F9F7F7 25%, #DBE2EF 50%, #F9F7F7 75%);
  background-size: 200% 100%;
  animation: loading 1.5s infinite;
  border-radius: 4px;
}

@keyframes loading {
  0% { background-position: 200% 0; }
  100% { background-position: -200% 0; }
}

.quote-container {
  position: relative;
}

.quote-container::before {
  content: '"';
  position: absolute;
  top: -2.5rem;
  left: -2rem;
  font-size: 8rem;
  color: rgba(17, 45, 78, 0.03);
  font-family: serif;
  font-style: italic;
  font-weight: 900;
}
</style>
