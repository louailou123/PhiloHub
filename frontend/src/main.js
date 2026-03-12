import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'

// PrimeVue
import PrimeVue from 'primevue/config'
import Button from 'primevue/button'
import Card from 'primevue/card'
import InputText from 'primevue/inputtext'
import Password from 'primevue/password'
import Toast from 'primevue/toast'
import ToastService from 'primevue/toastservice'
import Message from 'primevue/message'
import Tooltip from 'primevue/tooltip'

// Standard Lara Light theme for a professional academic feel
import 'primevue/resources/themes/lara-light-blue/theme.css'
import 'primevue/resources/primevue.min.css'
import 'primeicons/primeicons.css'
import 'primeflex/primeflex.css'

const app = createApp(App)
const pinia = createPinia()

app.use(router)
app.use(pinia)
app.use(PrimeVue, { ripple: true })
app.use(ToastService)

app.component('Button', Button)
app.component('Card', Card)
app.component('InputText', InputText)
app.component('Password', Password)
app.component('Toast', Toast)
app.component('Message', Message)
app.directive('tooltip', Tooltip)

app.mount('#app')