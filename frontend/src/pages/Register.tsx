import { useState } from 'react'
import { api } from '../lib/api'

export default function Register() {
  const [name, setName] = useState('')
  const [username, setUsername] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [message, setMessage] = useState('')

  async function onSubmit(e: React.FormEvent) {
    e.preventDefault()
    try {
      const data = await api.post<{ id: number; name: string }>(
        '/api/users/register',
        { name, username, email, password }
      )
      setMessage(`Registered as ${data.name}`)
    } catch (err: any) {
      setMessage(err.message)
    }
  }

  return (
    <div className="card">
      <h2>Register</h2>
      <form onSubmit={onSubmit} className="form">
        <input placeholder="Name" value={name} onChange={e => setName(e.target.value)} />
        <input placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} />
        <input placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
        <input placeholder="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} />
        <button type="submit">Create account</button>
      </form>
      {message && <p className="muted">{message}</p>}
    </div>
  )
}




