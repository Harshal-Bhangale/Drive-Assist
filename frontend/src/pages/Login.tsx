import { useState } from 'react'
import { api } from '../lib/api'

export default function Login() {
  const [identifier, setIdentifier] = useState('')
  const [password, setPassword] = useState('')
  const [message, setMessage] = useState('')

  async function onSubmit(e: React.FormEvent) {
    e.preventDefault()
    try {
      const data = await api.post<{ id: number; name: string }>(
        '/api/users/login',
        { identifier, password }
      )
      setMessage(`Welcome, ${data.name}`)
    } catch (err: any) {
      setMessage(err.message)
    }
  }

  return (
    <div className="card">
      <h2>Login</h2>
      <form onSubmit={onSubmit} className="form">
        <input placeholder="Email or Username" value={identifier} onChange={e => setIdentifier(e.target.value)} />
        <input placeholder="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} />
        <button type="submit">Login</button>
      </form>
      {message && <p className="muted">{message}</p>}
    </div>
  )
}




