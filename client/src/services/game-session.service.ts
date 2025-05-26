import { Configuration, GameSessionControllerApi } from '@/api'
import type { GameSessionDto } from '../api/models'

const config = new Configuration({
  basePath: import.meta.env.VITE_API_BASE_URL,
})
const api = new GameSessionControllerApi(config)

/**
 * Holt den Spielstatus per API.
 */
export async function fetchGameSession(id: number): Promise<GameSessionDto> {
  const response = await api.getGameStatus(id)
  return response.data
}
