import { useEffect, useState } from 'react'
import { api } from '../lib/api'

type GarageItem = { id: number; name: string; servicesOffered: string; address: string; rating: number; distanceKm: number }

export default function Garages() {
  const [lat, setLat] = useState(18.5204)
  const [lon, setLon] = useState(73.8567)
  const [service, setService] = useState('')
  const [list, setList] = useState<GarageItem[]>([])

  useEffect(() => {
    const q = new URLSearchParams({ lat: String(lat), long: String(lon), serviceType: service, sortBy: 'distance' })
    api.get<GarageItem[]>(`/api/garages/nearby?${q.toString()}`).then(setList).catch(() => setList([]))
  }, [lat, lon, service])

  return (
    <div className="card">
      <h2>Garages</h2>
      <div className="controls">
        <input placeholder="Service e.g. tyre" value={service} onChange={e => setService(e.target.value)} />
        <input type="number" value={lat} onChange={e => setLat(parseFloat(e.target.value))} />
        <input type="number" value={lon} onChange={e => setLon(parseFloat(e.target.value))} />
      </div>
      <ul className="list">
        {list.map(i => (
          <li key={i.id}><b>{i.name}</b> <span className="muted">{i.distanceKm.toFixed(1)} km</span><div className="muted">{i.servicesOffered}</div><div className="muted">{i.address}</div></li>
        ))}
      </ul>
    </div>
  )
}




