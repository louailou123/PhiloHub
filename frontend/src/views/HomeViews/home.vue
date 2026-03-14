<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import HomeNavBar from '../../Components/HomeComponents/HomeNavBar.vue'

const quotes = ref([])
const featuredQuote = ref({ text: '', philosopher: 'Unknown', era: '' })

const loadingQuote = ref(true)
const quoteError = ref("")

const router = useRouter()

const api = axios.create({
  baseURL: "http://localhost:8081"
})

let quoteTimeout = null

const fetchRandomQuote = async () => {
  loadingQuote.value = true
  quoteError.value = ""

  try {
    const { data } = await api.get("/quote/get")

    quotes.value = Array.isArray(data) ? data : [data]

    if (quotes.value.length) {
      featuredQuote.value = quotes.value[0]
    }

  } catch (error) {

    quoteError.value = "Unable to load a quote right now."

    quotes.value = [
      {
        text: "The only thing I know is that I know nothing.",
        philosopher: "Socrates",
        era: "Classical Greece"
      }
    ]

    featuredQuote.value = quotes.value[0]
  } finally {
    loadingQuote.value = false
  }
}

const rotateQuote = () => {

  if (!quotes.value.length || !featuredQuote.value?.text) return

  const currentIndex = quotes.value.findIndex(
    q => q.text === featuredQuote.value.text
  )

  const nextIndex = (currentIndex + 1) % quotes.value.length
  featuredQuote.value = quotes.value[nextIndex]

  const delay = Math.max(featuredQuote.value.text.length * 100, 3000)

  quoteTimeout = setTimeout(rotateQuote, delay)
}

onMounted(async () => {

  await fetchRandomQuote()

  if (quotes.value.length) {

    const randomIndex = Math.floor(Math.random() * quotes.value.length)

    featuredQuote.value = quotes.value[randomIndex]

    const initialDelay =
      Math.max(featuredQuote.value.text.length * 100, 3000)

    quoteTimeout = setTimeout(rotateQuote, initialDelay)
  }
})

onUnmounted(() => {
  if (quoteTimeout) clearTimeout(quoteTimeout)
})

const getInitials = (name = '') => {
  return name
    .trim()
    .split(/\s+/)
    .map(w => w[0]?.toUpperCase())
    .join("")
    .slice(0, 2)
}
</script>

<template>
  <div class="home-root">
    <HomeNavBar />

    <main class="home-main">
      <!-- Hero -->
      <section class="hero-section" aria-labelledby="hero-heading">
        <div class="hero-mesh" aria-hidden="true">
          <div class="hero-blob blob-a" />
          <div class="hero-blob blob-b" />
          <div class="hero-blob blob-c" />
        </div>

        <div class="hero-inner">
          <!-- Copy -->
          <div class="hero-copy">
            <div class="hero-badge" aria-label="Tagline">
              <div class="hero-badge-dot" aria-hidden="true" />
              <span class="hero-badge-text">Connect. Discuss. Evolve.</span>
            </div>

            <h1 id="hero-heading" class="hero-title">
              Where <span class="hero-title-accent">Intellectual</span><br />
              Dialogue Begins.
            </h1>

            <p class="hero-bio">
              PhiloHub is a professional sanctuary for modern thinkers.
              Engage with timeless questions in a community dedicated to
              dialectic, clarity, and growth.
            </p>

            <div class="hero-actions">
              <router-link to="/auth?mode=register" class="btn-primary">
                Join the Community
              </router-link>

              <router-link to="/about" class="btn-secondary">
                Learn More
              </router-link>
            </div>
          </div>

          <!-- Quote card -->
          <aside class="hero-card-col" aria-label="Featured philosophical quote">
            <transition name="quote-fade" mode="out-in">
              <div v-if="loadingQuote" key="skeleton" class="quote-card">
                <span class="quote-mark" aria-hidden="true">&ldquo;</span>

                <div class="quote-text skeleton-text"></div>

                <div class="quote-author-row">
                  <div class="quote-avatar skeleton-avatar"></div>

                  <div>
                    <p class="quote-author-name skeleton-text"></p>
                    <p class="quote-author-era skeleton-text"></p>
                  </div>
                </div>

                <div class="quote-footer">
                  <span class="quote-tag skeleton-text"></span>
                  <div class="quote-cta skeleton-text"></div>
                </div>
              </div>

              <div v-else :key="featuredQuote.text" class="quote-card">
                <span class="quote-mark" aria-hidden="true">&ldquo;</span>

                <p class="quote-text">
                  {{ featuredQuote.text }}
                </p>

                <div class="quote-author-row">
                  <div class="quote-avatar">
                    <span>{{ getInitials(featuredQuote.philosopher) }}</span>
                  </div>

                  <div>
                    <p class="quote-author-name">
                      {{ featuredQuote.philosopher }}
                    </p>

                    <p class="quote-author-era">
                      {{ featuredQuote.era }}
                    </p>
                  </div>
                </div>

                <div class="quote-footer">
                  <span class="quote-tag">Hot Quotes</span>

                  <router-link
                    to="/auth?mode=register"
                    class="quote-cta"
                  >
                    Discuss this →
                  </router-link>
                </div>
              </div>
            </transition>
          </aside>
        </div>
      </section>

      <!-- Features -->
      <section class="features-section">
        <div class="section-inner">
          <p class="section-eyebrow">Why PhiloHub</p>

          <h2 class="section-title">
            Built for serious thinkers
          </h2>

          <p class="section-desc">
            Every feature is designed to foster deep, rigorous discussion —
            not surface-level noise.
          </p>

          <div class="features-grid">
            <article class="feature-card">
              <div class="feature-icon"></div>

              <h3 class="feature-title">
                Curated Community
              </h3>

              <p class="feature-desc">
                Join a verified network of philosophers,
                academics, and curious minds committed
                to intellectual rigour.
              </p>
            </article>

            <article class="feature-card">
              <div class="feature-icon"></div>

              <h3 class="feature-title">
                Structured Debates
              </h3>

              <p class="feature-desc">
                Dialectic threads with argument-counter
                argument structure keep discussions
                focused and productive.
              </p>
            </article>

            <article class="feature-card">
              <div class="feature-icon"></div>

              <h3 class="feature-title">
                Reading Groups
              </h3>

              <p class="feature-desc">
                Sync your reading with others —
                from Plato to contemporary philosophy.
              </p>
            </article>

            <article class="feature-card">
              <div class="feature-icon"></div>

              <h3 class="feature-title">
                Global Reach
              </h3>

              <p class="feature-desc">
                Thinkers from over 60 countries contribute
                to a cross-cultural philosophical dialogue.
              </p>
            </article>
          </div>
        </div>
      </section>

      <!-- CTA -->
      <section class="cta-section">
        <div class="cta-inner">
          <h2 class="cta-title">
            Ready to begin<br />
            your philosophical journey?
          </h2>

          <p class="cta-desc">
            Join thousands of thinkers already shaping
            the conversations that matter most.
          </p>

          <div class="cta-actions">
            <router-link
              to="/auth?mode=register"
              class="btn-primary"
            >
              Get Started — It's Free
            </router-link>

            <router-link
              to="/auth?mode=login"
              class="btn-secondary"
            >
              Sign In
            </router-link>
          </div>
        </div>
      </section>
    </main>

    <footer class="home-footer">
      <p class="footer-text">
        © 2026 PhiloHub — Dedicated to the pursuit of wisdom.
      </p>
    </footer>
  </div>
</template>


<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap');

.home-root {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: #F9F7F7;
  font-family: 'Inter', system-ui, sans-serif;
  overflow-x: hidden;
}

/* ── Transitions ── */
.quote-fade-enter-active,
.quote-fade-leave-active {
  transition: all 0.5s cubic-bezier(0.25, 0.46, 0.45, 0.94);
}
.quote-fade-enter-from {
  opacity: 0;
  transform: translateY(8px) scale(0.96);
}
.quote-fade-leave-to {
  opacity: 0;
  transform: translateY(-8px) scale(0.96);
}

/* ── Main ── */
.home-main {
  flex: 1;
  display: flex;
  flex-direction: column;
}

/* ── Hero ── */
.hero-section {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: calc(100vh - 4.5rem);
  padding: 4rem 2rem;
  overflow: hidden;
}
.hero-mesh {
  position: absolute;
  inset: 0;
  filter: blur(80px);
  pointer-events: none;
  z-index: 0;
  overflow: hidden;
}
.hero-blob {
  position: absolute;
  border-radius: 50%;
  opacity: 0.14;
  animation: blobDrift 24s infinite alternate cubic-bezier(0.45, 0, 0.55, 1);
}
.blob-a { width: 60%; height: 60%; background: #3F72AF; top: -20%; left: -10%; }
.blob-b { width: 50%; height: 50%; background: #112D4E; bottom: -20%; right: -10%; animation-delay: -8s; }
.blob-c { width: 35%; height: 35%; background: #DBE2EF; top: 30%; left: 30%; animation-delay: -16s; }
@keyframes blobDrift {
  0%   { transform: translate(0,0) scale(1); }
  100% { transform: translate(6%, 10%) scale(1.1); }
}

.hero-inner {
  position: relative;
  z-index: 1;
  display: flex;
  align-items: center;
  gap: 5rem;
  max-width: 72rem;
  width: 100%;
  margin: 0 auto;
}

/* Left column */
.hero-copy {
  flex: 1;
  min-width: 0;
  animation: fadeUp 0.85s cubic-bezier(0.16, 1, 0.3, 1) both;
}
@keyframes fadeUp {
  from { opacity: 0; transform: translateY(32px); }
  to   { opacity: 1; transform: translateY(0); }
}
.hero-badge {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  background: #DBE2EF;
  border: 1px solid rgba(63, 114, 175, 0.3);
  border-radius: 99px;
  padding: 0.375rem 0.875rem;
  margin-bottom: 1.5rem;
}
.hero-badge-dot {
  width: 0.5rem;
  height: 0.5rem;
  border-radius: 50%;
  background: #3F72AF;
  animation: pulse 2s infinite;
}
@keyframes pulse {
  0%, 100% { opacity: 1; transform: scale(1); }
  50% { opacity: 0.6; transform: scale(0.85); }
}
.hero-badge-text {
  font-size: 0.75rem;
  font-weight: 800;
  color: #112D4E;
  text-transform: uppercase;
  letter-spacing: 0.07em;
}
.hero-title {
  font-size: clamp(2.5rem, 5vw, 4.25rem);
  font-weight: 900;
  color: #112D4E;
  letter-spacing: -0.04em;
  line-height: 1.05;
  margin: 0 0 1.5rem;
}
.hero-title-accent {
  color: #3F72AF;
  position: relative;
  display: inline-block;
}
.hero-bio {
  font-size: 1.125rem;
  line-height: 1.65;
  color: #3F72AF;
  font-weight: 500;
  max-width: 34rem;
  margin: 0 0 2.5rem;
}
.hero-actions {
  display: flex;
  flex-wrap: wrap;
  gap: 0.875rem;
  align-items: center;
  margin-bottom: 3rem;
}
.btn-primary {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.9375rem;
  font-weight: 800;
  color: #F9F7F7;
  text-decoration: none;
  padding: 0.875rem 2rem;
  border-radius: 0.875rem;
  background: #3F72AF;
  box-shadow: 0 4px 20px rgba(63, 114, 175, 0.25);
  transition: all 0.25s cubic-bezier(0.34, 1.56, 0.64, 1);
  letter-spacing: 0.01em;
}
.btn-primary:hover {
  background: #112D4E;
  transform: translateY(-2px) scale(1.02);
  box-shadow: 0 10px 36px rgba(17, 45, 78, 0.22);
}
.btn-secondary {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.9375rem;
  font-weight: 700;
  color: #3F72AF;
  text-decoration: none;
  padding: 0.875rem 2rem;
  border-radius: 0.875rem;
  border: 1.5px solid rgba(63, 114, 175, 0.35);
  background: transparent;
  transition: all 0.2s ease;
}
.btn-secondary:hover {
  background: #DBE2EF;
  border-color: #112D4E;
  color: #112D4E;
}
.hero-proof {
  display: flex;
  align-items: center;
  gap: 1.5rem;
  flex-wrap: wrap;
  padding-top: 2rem;
  border-top: 1px solid rgba(17, 45, 78, 0.1);
}
.proof-label {
  font-size: 0.6875rem;
  font-weight: 800;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  color: rgba(17, 45, 78, 0.45);
}
.proof-icons {
  display: flex;
  gap: 1.25rem;
  align-items: center;
}
.proof-icon {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.25rem;
}
.proof-icon svg {
  color: #3F72AF;
  opacity: 0.75;
}
.proof-icon span {
  font-size: 0.625rem;
  font-weight: 700;
  color: rgba(17, 45, 78, 0.4);
  text-transform: uppercase;
  letter-spacing: 0.06em;
}

/* Right column – Quote card */
.hero-card-col {
  flex-shrink: 0;
  width: 100%;
  max-width: 22rem;
  animation: fadeUpDelay 0.9s cubic-bezier(0.16, 1, 0.3, 1) 0.1s both;
}
@keyframes fadeUpDelay {
  from { opacity: 0; transform: translateY(40px); }
  to   { opacity: 1; transform: translateY(0); }
}
.quote-card {
  position: relative;
  background: rgba(255, 255, 255, 0.6);
  backdrop-filter: blur(24px) saturate(160%);
  -webkit-backdrop-filter: blur(24px) saturate(160%);
  border: 1px solid rgba(255, 255, 255, 0.85);
  border-radius: 1.5rem;
  padding: 2rem;
  box-shadow:
    0 4px 6px rgba(17, 45, 78, 0.04),
    0 20px 60px rgba(17, 45, 78, 0.10);
  overflow: hidden;
}
.quote-card::before {
  content: '';
  position: absolute;
  top: -1px;
  left: 0;
  right: 0;
  height: 3px;
  background: linear-gradient(90deg, #3F72AF, #112D4E);
  border-radius: 1.5rem 1.5rem 0 0;
}
.quote-mark {
  font-size: 4rem;
  line-height: 1;
  color: #3F72AF;
  opacity: 0.15;
  font-family: Georgia, serif;
  margin-bottom: 0.5rem;
  display: block;
}
.quote-text {
  font-size: 1.0625rem;
  font-weight: 600;
  color: #112D4E;
  line-height: 1.6;
  margin: 0 0 1.5rem;
  font-style: italic;
}
.quote-author-row {
  display: flex;
  align-items: center;
  gap: 0.875rem;
}
.quote-avatar {
  width: 2.5rem;
  height: 2.5rem;
  border-radius: 50%;
  background: #112D4E;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}
.quote-avatar span {
  color: #F9F7F7;
  font-size: 0.75rem;
  font-weight: 800;
}
.quote-author-name {
  font-size: 0.875rem;
  font-weight: 800;
  color: #112D4E;
  margin: 0;
}
.quote-author-era {
  font-size: 0.75rem;
  color: rgba(17, 45, 78, 0.45);
  font-weight: 500;
  margin: 0;
}
.quote-footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 1.5rem;
  padding-top: 1.25rem;
  border-top: 1px solid rgba(17, 45, 78, 0.08);
}
.quote-tag {
  font-size: 0.6875rem;
  font-weight: 800;
  text-transform: uppercase;
  letter-spacing: 0.07em;
  color: rgba(17, 45, 78, 0.35);
}
.quote-cta {
  font-size: 0.75rem;
  font-weight: 700;
  color: #3F72AF;
  text-decoration: none;
  transition: color 0.18s;
}
.quote-cta:hover { color: #112D4E; }

/* ── Features section ── */
.features-section {
  background: #112D4E;
  padding: 5rem 2rem;
}
.section-inner {
  max-width: 72rem;
  margin: 0 auto;
}
.section-eyebrow {
  font-size: 0.75rem;
  font-weight: 800;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  color: #DBE2EF;
  opacity: 0.6;
  margin: 0 0 0.75rem;
}
.section-title {
  font-size: clamp(1.75rem, 3vw, 2.5rem);
  font-weight: 800;
  color: #F9F7F7;
  letter-spacing: -0.03em;
  margin: 0 0 1rem;
  line-height: 1.2;
}
.section-desc {
  font-size: 1.0625rem;
  color: rgba(219, 226, 239, 0.7);
  font-weight: 500;
  line-height: 1.65;
  max-width: 36rem;
  margin: 0 0 3.5rem;
}
.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(17rem, 1fr));
  gap: 1.25rem;
}
.feature-card {
  background: rgba(249, 247, 247, 0.05);
  border: 1px solid rgba(219, 226, 239, 0.1);
  border-radius: 1.25rem;
  padding: 1.75rem;
  transition: border-color 0.2s, background 0.2s;
}
.feature-card:hover {
  background: rgba(249, 247, 247, 0.08);
  border-color: rgba(63, 114, 175, 0.35);
}
.feature-icon {
  width: 2.75rem;
  height: 2.75rem;
  border-radius: 0.75rem;
  background: rgba(63, 114, 175, 0.2);
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1.125rem;
}
.feature-icon svg { color: #DBE2EF; }
.feature-title {
  font-size: 1rem;
  font-weight: 800;
  color: #F9F7F7;
  margin: 0 0 0.5rem;
  letter-spacing: -0.02em;
}
.feature-desc {
  font-size: 0.875rem;
  color: rgba(219, 226, 239, 0.65);
  line-height: 1.6;
  margin: 0;
  font-weight: 500;
}

/* ── CTA section ── */
.cta-section {
  padding: 5rem 2rem;
  background: #F9F7F7;
  text-align: center;
}
.cta-inner {
  max-width: 36rem;
  margin: 0 auto;
}
.cta-title {
  font-size: clamp(1.875rem, 3.5vw, 2.75rem);
  font-weight: 900;
  color: #112D4E;
  letter-spacing: -0.04em;
  line-height: 1.1;
  margin: 0 0 1rem;
}
.cta-desc {
  font-size: 1.0625rem;
  color: #3F72AF;
  font-weight: 500;
  line-height: 1.6;
  margin: 0 0 2.5rem;
}
.cta-actions {
  display: flex;
  gap: 0.875rem;
  justify-content: center;
  flex-wrap: wrap;
}

/* ── Footer ── */
.home-footer {
  background: #112D4E;
  padding: 1.5rem 2rem;
  text-align: center;
}
.footer-text {
  font-size: 0.8125rem;
  font-weight: 700;
  color: rgba(219, 226, 239, 0.5);
  text-transform: uppercase;
  letter-spacing: 0.08em;
  margin: 0;
}

/* ── Responsive ── */
@media (max-width: 900px) {
  .hero-inner {
    flex-direction: column;
    gap: 3rem;
    text-align: center;
  }
  .hero-card-col { max-width: 100%; }
  .hero-actions { justify-content: center; }
  .hero-proof { justify-content: center; }
  .hero-bio { margin-left: auto; margin-right: auto; }
}
@media (max-width: 640px) {
  .hero-section { padding: 2.5rem 1rem; }
  .features-section, .cta-section { padding: 3.5rem 1rem; }
}

/* ── Skeleton Loading ── */
.skeleton-text {
  background: linear-gradient(90deg, #F9F7F7 25%, #DBE2EF 50%, #F9F7F7 75%);
  background-size: 200% 100%;
  animation: skeleton-loading 2s infinite ease-in-out;
  border-radius: 0.25rem;
}
.skeleton-text.quote-text {
  height: 4rem;
  margin: 0 0 1.5rem;
}
.skeleton-text.quote-author-name {
  height: 1rem;
  width: 6rem;
  margin: 0 0 0.25rem;
}
.skeleton-text.quote-author-era {
  height: 0.875rem;
  width: 4rem;
}
.skeleton-text.quote-tag {
  height: 0.75rem;
  width: 4rem;
}
.skeleton-text.quote-cta {
  height: 0.875rem;
  width: 5rem;
}
.skeleton-avatar {
  width: 2.5rem;
  height: 2.5rem;
  border-radius: 50%;
  background: linear-gradient(90deg, #DBE2EF 25%, #F9F7F7 50%, #DBE2EF 75%);
  background-size: 200% 100%;
  animation: skeleton-loading 2s infinite ease-in-out;
}
@keyframes skeleton-loading {
  0% { background-position: 200% 0; }
  100% { background-position: -200% 0; }
}
</style>