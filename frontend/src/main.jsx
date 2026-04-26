import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { ContextProvider } from './contextApi/ContextApi.jsx';
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <ContextProvider>
      <App />
    </ContextProvider>
  </StrictMode>,
)
