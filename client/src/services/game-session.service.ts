import { Configuration, GameSessionControllerApi } from '@/api'
import type { GameSessionDto } from '../api/models'
import { showToast } from '@/composables/useToast.ts'

const config = new Configuration({
  basePath: import.meta.env.VITE_API_BASE_URL,
})
const api = new GameSessionControllerApi(config)
/**
 * Holt den Spielstatus per API.
 */
export async function fetchGameSession(id: number): Promise<GameSessionDto | null> {
  try {
    const response = await api.getGameStatus(id)
    return response.data
  } catch (error) {
    const msg = error?.response?.data?.message ?? error?.message ?? 'Unbekannter Fehler'
    await showToast(msg)
    return null
  }
}
