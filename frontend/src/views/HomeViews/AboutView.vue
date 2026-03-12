<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import HomeNavBar from '../../Components/HomeComponents/HomeNavBar.vue'

const router = useRouter()

const stats = ref([
  { value: "12,400+", label: "Active Thinkers" },
  { value: "60+", label: "Countries Represented" },
  { value: "8,200+", label: "Discussions Started" },
  { value: "4", label: "Years of Dialogue" },
])

const values = ref([
  {
    title: "Intellectual Honesty",
    desc: "We champion truth over comfort, and rigour over rhetoric. Every argument is evaluated on its merits.",
    icon: 'pi pi-info-circle'
  },
  {
    title: "Radical Inclusivity",
    desc: "Wisdom has no gender, nationality, or creed. We welcome every tradition — from Confucian ethics to Western analytic philosophy.",
    icon: 'pi pi-users'
  },
  {
    title: "Continuous Growth",
    desc: "The Socratic method is not just a historical curiosity — it is our living methodology. We question to grow.",
    icon: 'pi pi-chart-line'
  },
  {
    title: "Civil Discourse",
    desc: "Disagreement sharpens ideas. We foster debate that is fierce in logic yet generous in spirit.",
    icon: 'pi pi-shield'
  },
])


const goToAuth = (mode) => {
  router.push({ path: '/auth', query: { mode } })
}
</script>

<template>
  <div class="about-root">
    <!-- Navbar -->
    <HomeNavBar @login="goToAuth('login')" @register="goToAuth('register')" />

    <main>
      <!-- Hero -->
      <section class="about-hero" aria-labelledby="about-heading">
        <div class="about-hero-mesh" aria-hidden="true">
          <div class="hero-blob blob-a" />
          <div class="hero-blob blob-b" />
        </div>
        <div class="about-hero-inner">
          <div class="about-eyebrow" aria-hidden="true">
            <span>Our Story</span>
          </div>
          <h1 id="about-heading" class="about-hero-title">
            Built for those who<br />refuse to stop questioning.
          </h1>
          <p class="about-hero-sub">
            PhiloHub was born from a simple frustration: the internet had no shortage of opinions,
            but precious little space for disciplined, honest philosophical inquiry.
          </p>
        </div>
      </section>

      <!-- Stats -->
      <section class="stats-band" aria-label="Platform statistics">
        <div class="stats-inner">
          <div v-for="s in stats" :key="s.label" class="stat-item">
            <p class="stat-value">{{ s.value }}</p>
            <p class="stat-label">{{ s.label }}</p>
          </div>
        </div>
      </section>

      <!-- Mission -->
      <section class="mission-section" aria-labelledby="mission-heading">
        <div class="mission-copy">
          <p class="section-eyebrow">Our Mission</p>
          <h2 id="mission-heading" class="section-title">Philosophy for the modern age</h2>
          <p class="section-body">
            We believe that philosophical thinking — careful reasoning, dialectic exchange, the
            willingness to have one's beliefs challenged — is not an academic luxury. It is a
            civic necessity.
          </p>
          <p class="section-body">
            PhiloHub provides the infrastructure for that thinking to happen at scale: structured
            debate threads, curated reading groups, and a community standard that prizes insight
            over clout.
          </p>
        </div>
      </section>

      <!-- Values -->
      <section class="values-section" aria-labelledby="values-heading">
        <div class="values-inner">
          <div class="values-header">
            <p class="section-eyebrow">What We Stand For</p>
            <h2 id="values-heading" class="section-title">Our core values</h2>
          </div>
          <div class="values-grid">
            <article v-for="v in values" :key="v.title" class="value-card">
              <div class="value-icon" aria-hidden="true">
                <i :class="v.icon" style="font-size: 1.25rem;"></i>
              </div>
              <h3 class="value-title">{{ v.title }}</h3>
              <p class="value-desc">{{ v.desc }}</p>
            </article>
          </div>
        </div>
      </section>


      <!-- CTA -->
      <section class="about-cta" aria-labelledby="cta-heading">
        <div class="about-cta-inner">
          <h2 id="cta-heading" class="cta-title">Join the conversation.</h2>
          <p class="cta-desc">
            Thousands of thinkers are already here. Your perspective belongs in the dialogue.
          </p>
          <div class="cta-actions">
            <button @click="goToAuth('register')" class="btn-primary">
              Start your journey
            </button>
          </div>
        </div>
      </section>
    </main>

    <footer class="about-footer">
      <p class="footer-text">© 2026 PhiloHub — Dedicated to the pursuit of wisdom.</p>
    </footer>
  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800;900&display=swap');

.about-root {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: #F9F7F7;
  font-family: 'Inter', system-ui, sans-serif;
  color: #112D4E;
}

/* ── Navbar ── */
/* Removed in favor of HomeNavBar component */

/* ── Hero ── */
.about-hero {
  position: relative;
  background: #112D4E;
  padding: 5rem 2rem 6rem;
  overflow: hidden;
  text-align: center;
}
.about-hero-mesh {
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
  opacity: 0.12;
  animation: blobDrift 20s infinite alternate;
}
.blob-a { width: 50%; height: 50%; background: #3F72AF; top: -20%; left: -10%; }
.blob-b { width: 40%; height: 40%; background: #DBE2EF; bottom: -15%; right: -5%; animation-delay: -10s; }
@keyframes blobDrift {
  0%   { transform: translate(0,0) scale(1); }
  100% { transform: translate(5%, 8%) scale(1.08); }
}
.about-hero-inner {
  position: relative;
  z-index: 1;
  max-width: 48rem;
  margin: 0 auto;
  animation: fadeUp 0.8s cubic-bezier(0.16, 1, 0.3, 1) both;
}
@keyframes fadeUp {
  from { opacity: 0; transform: translateY(28px); }
  to   { opacity: 1; transform: translateY(0); }
}
.about-eyebrow {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  background: rgba(219, 226, 239, 0.1);
  border: 1px solid rgba(219, 226, 239, 0.2);
  border-radius: 99px;
  padding: 0.375rem 0.875rem;
  margin-bottom: 1.5rem;
}
.about-eyebrow span {
  font-size: 0.75rem;
  font-weight: 800;
  color: #DBE2EF;
  text-transform: uppercase;
  letter-spacing: 0.08em;
}
.about-hero-title {
  font-size: clamp(2rem, 4vw, 3.25rem);
  font-weight: 900;
  color: #F9F7F7;
  letter-spacing: -0.04em;
  line-height: 1.1;
  margin: 0 0 1.25rem;
}
.about-hero-sub {
  font-size: 1.125rem;
  color: rgba(219, 226, 239, 0.75);
  font-weight: 500;
  line-height: 1.65;
  margin: 0;
}

/* ── Stats band ── */
.stats-band {
  background: #3F72AF;
  padding: 2.5rem 2rem;
}
.stats-inner {
  max-width: 72rem;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 1.5rem;
  text-align: center;
}
.stat-value {
  font-size: clamp(1.75rem, 3vw, 2.25rem);
  font-weight: 900;
  color: #F9F7F7;
  letter-spacing: -0.04em;
  line-height: 1;
  margin: 0 0 0.375rem;
}
.stat-label {
  font-size: 0.75rem;
  font-weight: 700;
  color: rgba(249, 247, 247, 0.65);
  text-transform: uppercase;
  letter-spacing: 0.08em;
  margin: 0;
}

/* ── Mission ── */
.mission-section {
  padding: 5rem 2rem;
  max-width: 72rem;
  margin: 0 auto;
  width: 100%;
  display: flex;
  gap: 5rem;
  align-items: center;
}
.mission-copy { flex: 1; }
.section-eyebrow {
  font-size: 0.75rem;
  font-weight: 800;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  color: #3F72AF;
  margin: 0 0 0.75rem;
}
.section-title {
  font-size: clamp(1.75rem, 3vw, 2.5rem);
  font-weight: 800;
  color: #112D4E;
  letter-spacing: -0.03em;
  margin: 0 0 1rem;
  line-height: 1.2;
}
.section-body {
  font-size: 1.0625rem;
  color: #3F72AF;
  font-weight: 500;
  line-height: 1.7;
  margin: 0;
}
.section-body + .section-body {
  margin-top: 1rem;
}
.mission-callout {
  flex-shrink: 0;
  width: 100%;
  max-width: 22rem;
  background: #112D4E;
  border-radius: 1.5rem;
  padding: 2.5rem 2rem;
  position: relative;
  overflow: hidden;
}
.mission-callout::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 3px;
  background: linear-gradient(90deg, #3F72AF, #DBE2EF);
}
.callout-quote {
  font-size: 3rem;
  line-height: 1;
  color: rgba(219, 226, 239, 0.15);
  font-family: Georgia, serif;
  display: block;
  margin-bottom: 0.5rem;
}
.callout-text {
  font-size: 1rem;
  font-weight: 600;
  color: #DBE2EF;
  line-height: 1.65;
  font-style: italic;
  margin: 0 0 1.25rem;
}
.callout-source {
  font-size: 0.8125rem;
  font-weight: 700;
  color: rgba(219, 226, 239, 0.5);
  text-transform: uppercase;
  letter-spacing: 0.06em;
  margin: 0;
}

/* ── Values ── */
.values-section {
  background: #DBE2EF;
  padding: 5rem 2rem;
}
.values-inner {
  max-width: 72rem;
  margin: 0 auto;
}
.values-header {
  margin-bottom: 3rem;
}
.values-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(17rem, 1fr));
  gap: 1.25rem;
}
.value-card {
  background: #F9F7F7;
  border-radius: 1.25rem;
  padding: 1.75rem;
  border: 1px solid rgba(17, 45, 78, 0.06);
  transition: transform 0.2s, box-shadow 0.2s;
}
.value-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 12px 40px rgba(17, 45, 78, 0.1);
}
.value-icon {
  width: 2.75rem;
  height: 2.75rem;
  border-radius: 0.75rem;
  background: rgba(63, 114, 175, 0.12);
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1rem;
  color: #3F72AF;
}
.value-title {
  font-size: 1rem;
  font-weight: 800;
  color: #112D4E;
  margin: 0 0 0.5rem;
  letter-spacing: -0.02em;
}
.value-desc {
  font-size: 0.875rem;
  color: rgba(63, 114, 175, 0.85);
  line-height: 1.6;
  margin: 0;
  font-weight: 500;
}


/* ── CTA ── */
.about-cta {
  background: #112D4E;
  padding: 5rem 2rem;
  text-align: center;
}
.about-cta-inner {
  max-width: 36rem;
  margin: 0 auto;
}
.cta-title {
  font-size: clamp(1.875rem, 3.5vw, 2.75rem);
  font-weight: 900;
  color: #F9F7F7;
  letter-spacing: -0.04em;
  line-height: 1.1;
  margin: 0 0 1rem;
}
.cta-desc {
  font-size: 1.0625rem;
  color: rgba(219, 226, 239, 0.7);
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
.btn-primary {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.9375rem;
  font-weight: 800;
  color: #112D4E;
  border: none;
  padding: 0.875rem 2rem;
  border-radius: 0.875rem;
  background: #F9F7F7;
  box-shadow: 0 4px 20px rgba(0,0,0,0.12);
  cursor: pointer;
  transition: all 0.22s cubic-bezier(0.34, 1.56, 0.64, 1);
  font-family: inherit;
}
.btn-primary:hover {
  background: #DBE2EF;
  transform: translateY(-2px) scale(1.02);
}
.btn-outline {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.9375rem;
  font-weight: 700;
  color: #DBE2EF;
  text-decoration: none;
  padding: 0.875rem 2rem;
  border-radius: 0.875rem;
  border: 1.5px solid rgba(219, 226, 239, 0.3);
  background: transparent;
  transition: all 0.2s ease;
}
.btn-outline:hover {
  border-color: #DBE2EF;
  background: rgba(219, 226, 239, 0.08);
}

/* ── Footer ── */
.about-footer {
  background: #112D4E;
  border-top: 1px solid rgba(219, 226, 239, 0.1);
  padding: 1.5rem 2rem;
  text-align: center;
}
.footer-text {
  font-size: 0.8125rem;
  font-weight: 700;
  color: rgba(219, 226, 239, 0.45);
  text-transform: uppercase;
  letter-spacing: 0.08em;
  margin: 0;
}

/* ── Responsive ── */
@media (max-width: 900px) {
  .mission-section { flex-direction: column; gap: 2.5rem; }
  .mission-callout { max-width: 100%; }
  .stats-inner { grid-template-columns: repeat(2, 1fr); }
}
@media (max-width: 640px) {
  .about-nav { padding: 0 1rem; }
  .nav-link { display: none; }
  .about-hero { padding: 3.5rem 1rem 4.5rem; }
  .stats-inner { grid-template-columns: repeat(2, 1fr); }
  .values-section, .about-cta { padding: 3.5rem 1rem; }
  .mission-section { padding: 3.5rem 1rem; }
}
</style>
