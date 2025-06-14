import {
  Configuration,
  GameSessionApi,
  type GameSessionCreationRequestDto,
  type GameSessionDto,
  type GameSessionUpdateRequestDto,
} from '@/api'
import { showToast } from '@/composables/useToast.ts'

const config = new Configuration({
  basePath: import.meta.env.VITE_API_BASE_URL,
})
const api = new GameSessionApi(config)

export async function createGameSession(
  payload: GameSessionCreationRequestDto,
): Promise<GameSessionDto | null> {
  try {
    const response = await api.createGameSession(payload)
    return response.data
  } catch (error) {
    const msg = error?.response?.data?.message ?? error?.message ?? 'Erstellung fehlgeschlagen'
    await showToast(msg)
    return null
  }
}

export async function updateGameSession(
  id: number,
  payload: GameSessionUpdateRequestDto,
): Promise<GameSessionDto | null> {
  try {
    const response = await api.updateGameSession(id, payload)
    return response.data
  } catch (error) {
    const msg = error?.response?.data?.message ?? error?.message ?? 'Update fehlgeschlagen'
    await showToast(msg)
    return null
  }
}

export async function deleteGameSession(id: number): Promise<boolean> {
  try {
    await api.deleteGameSession(id)
    return true
  } catch (error) {
    const msg = error?.response?.data?.message ?? error?.message ?? 'Loeschen fehlgeschlagen'
    await showToast(msg)
    return false
  }
}
