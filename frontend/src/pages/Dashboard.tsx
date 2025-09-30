import { useEffect, useState } from 'react'
import { api } from '../lib/api'

type Marker = { type: string; id: number; name: string; lat: number; lon: number; distanceKm: number }

export default function Dashboard() {
  const [lat, setLat] = useState(18.5204)
  const [lon, setLon] = useState(73.8567)
  const [markers, setMarkers] = useState<Marker[]>([])

  useEffect(() => {
    api.get<{ markers: Marker[] }>(`/api/map/nearby?lat=${lat}&long=${lon}&radiusKm=8`).then(d => setMarkers(d.markers)).catch(() => setMarkers([]))
  }, [lat, lon])

  return (
    <div className="grid-2">
      <div className="card">
        <h2>Map</h2>
        <div className="map-placeholder">
          <div className="pin" style={{ left: '50%', top: '50%' }}>You</div>
          {markers.slice(0, 12).map((m, i) => (
            <div key={m.type + m.id} className={`pin ${m.type.toLowerCase()}`} style={{ left: `${(i*13)%90+5}%`, top: `${(i*21)%70+15}%` }}>{m.type[0]}</div>
          ))}
        </div>
      </div>
      <div className="card">
        <h2>Nearby Services</h2>
        <div className="controls">
          <input type="number" value={lat} onChange={e => setLat(parseFloat(e.target.value))} />
          <input type="number" value={lon} onChange={e => setLon(parseFloat(e.target.value))} />
        </div>
        <ul className="list">
          {markers.map(m => (
            <li key={m.type + m.id}><span className="tag">{m.type}</span> {m.name} <span className="muted">{m.distanceKm.toFixed(1)} km</span></li>
          ))}
        </ul>
      </div>
    </div>
  )
}




