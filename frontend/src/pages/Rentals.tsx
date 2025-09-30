import { useEffect, useState } from 'react'
import { api } from '../lib/api'

type RentalItem = { id: number; carType: string; model: string; pricePerHour: number; rating: number; distanceKm: number }

export default function Rentals() {
  const [lat, setLat] = useState(18.5204)
  const [lon, setLon] = useState(73.8567)
  const [type, setType] = useState('')
  const [maxPrice, setMaxPrice] = useState<number | ''>('')
  const [list, setList] = useState<RentalItem[]>([])

  useEffect(() => {
    const params = new URLSearchParams()
    params.set('lat', String(lat))
    params.set('long', String(lon))
    if (type) params.set('type', type)
    if (maxPrice !== '') params.set('maxPrice', String(maxPrice))
    api.get<RentalItem[]>(`/api/rentals/available?${params.toString()}`).then(setList).catch(() => setList([]))
  }, [lat, lon, type, maxPrice])

  return (
    <div className="card">
      <h2>Car Rentals</h2>
      <div className="controls">
        <select value={type} onChange={e => setType(e.target.value)}>
          <option value="">All</option>
          <option>Hatchback</option>
          <option>Sedan</option>
          <option>SUV</option>
        </select>
        <input type="number" placeholder="Max price/hour" value={maxPrice} onChange={e => setMaxPrice(e.target.value === '' ? '' : parseFloat(e.target.value))} />
        <input type="number" value={lat} onChange={e => setLat(parseFloat(e.target.value))} />
        <input type="number" value={lon} onChange={e => setLon(parseFloat(e.target.value))} />
      </div>
      <ul className="list">
        {list.map(i => (
          <li key={i.id}><b>{i.model}</b> <span className="tag">{i.carType}</span> <span className="muted">₹{i.pricePerHour}/hr</span> <span className="muted">{i.distanceKm.toFixed(1)} km</span></li>
        ))}
      </ul>
    </div>
  )
}




