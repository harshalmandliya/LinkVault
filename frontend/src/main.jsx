import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { ContextProvider } from './contextApi/ContextApi.jsx';
import App from './App.jsx'
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const queryClient = new QueryClient();

createRoot(document.getElementById('root')).render(
  <StrictMode>
     <QueryClientProvider client={queryClient}>
    <ContextProvider>
      <App />
    </ContextProvider>
    </QueryClientProvider>
  </StrictMode>,
)
