export const api = {
  async get<T>(path: string) {
    const res = await fetch(path)
    if (!res.ok) throw new Error(await res.text())
    return res.json() as Promise<T>
  },
  async post<T>(path: string, body: unknown) {
    const res = await fetch(path, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body)
    })
    if (!res.ok) throw new Error(await res.text())
    return res.json() as Promise<T>
  },
  async put(path: string, body: unknown) {
    const res = await fetch(path, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body)
    })
    if (!res.ok) throw new Error(await res.text())
  }
}




