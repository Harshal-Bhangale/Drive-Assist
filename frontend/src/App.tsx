import { Route, Routes, Navigate } from 'react-router-dom'
import Navbar from './components/Navbar'
import Login from './pages/Login'
import Register from './pages/Register'
import Dashboard from './pages/Dashboard'
import Fuel from './pages/Fuel'
import Garages from './pages/Garages'
import Rentals from './pages/Rentals'
import Booking from './pages/Booking'

export default function App() {
  return (
    <div className="app">
      <Navbar />
      <div className="container">
        <Routes>
          <Route path="/" element={<Navigate to="/dashboard" replace />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/fuel" element={<Fuel />} />
          <Route path="/garages" element={<Garages />} />
          <Route path="/rentals" element={<Rentals />} />
          <Route path="/booking" element={<Booking />} />
        </Routes>
      </div>
    </div>
  )
}




