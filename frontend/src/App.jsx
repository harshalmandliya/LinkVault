import { BrowserRouter } from "react-router-dom"
import { Routes, Route } from "react-router-dom"
import LandingPage from "./components/LandingPage"
import AboutPage from "./components/AboutPage"

function App() {
  return (
     
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/about" element={<AboutPage />} />
    </Routes>
    </BrowserRouter>
    )
}

export default App
