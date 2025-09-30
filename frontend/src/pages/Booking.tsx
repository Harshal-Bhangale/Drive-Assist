import { useState } from 'react'
import { api } from '../lib/api'

export default function Booking() {
  const [carId, setCarId] = useState<number | ''>('')
  const [userId, setUserId] = useState<number | ''>('')
  const [pickupTime, setPickupTime] = useState('')
  const [dropTime, setDropTime] = useState('')
  const [message, setMessage] = useState('')

  async function onSubmit(e: React.FormEvent) {
    e.preventDefault()
    try {
      const body = {
        carId: Number(carId),
        userId: Number(userId),
        pickupTime: new Date(pickupTime).toISOString(),
        dropTime: new Date(dropTime).toISOString()
      }
      const data = await api.post<{ bookingId: number; totalCost: number }>("/api/rentals/book", body)
      setMessage(`Booking #${data.bookingId} confirmed. Total ₹${data.totalCost}`)
    } catch (err: any) {
      setMessage(err.message)
    }
  }

  return (
    <div className="card">
      <h2>Book a Car</h2>
      <form onSubmit={onSubmit} className="form">
        <input type="number" placeholder="Car ID" value={carId} onChange={e => setCarId(e.target.value === '' ? '' : Number(e.target.value))} />
        <input type="number" placeholder="User ID" value={userId} onChange={e => setUserId(e.target.value === '' ? '' : Number(e.target.value))} />
        <input type="datetime-local" value={pickupTime} onChange={e => setPickupTime(e.target.value)} />
        <input type="datetime-local" value={dropTime} onChange={e => setDropTime(e.target.value)} />
        <button type="submit">Book</button>
      </form>
      {message && <p className="muted">{message}</p>}
    </div>
  )
}




