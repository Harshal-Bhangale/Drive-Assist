import { Link, useLocation } from 'react-router-dom'

export default function Navbar() {
  const { pathname } = useLocation()
  return (
    <nav className="navbar">
      <div className="brand">Drive Assist</div>
      <div className="links">
        <Link className={pathname === '/dashboard' ? 'active' : ''} to="/dashboard">Dashboard</Link>
        <Link className={pathname === '/fuel' ? 'active' : ''} to="/fuel">Fuel</Link>
        <Link className={pathname === '/garages' ? 'active' : ''} to="/garages">Garages</Link>
        <Link className={pathname === '/rentals' ? 'active' : ''} to="/rentals">Rentals</Link>
        <Link className={pathname === '/booking' ? 'active' : ''} to="/booking">Booking</Link>
        <Link className={pathname === '/login' ? 'active' : ''} to="/login">Login</Link>
        <Link className={pathname === '/register' ? 'active' : ''} to="/register">Register</Link>
      </div>
    </nav>
  )
}




