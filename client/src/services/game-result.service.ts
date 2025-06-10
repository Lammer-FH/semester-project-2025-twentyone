import {
  Configuration,
  type GameResultDto,
  type GameResultOverviewDto,
  GameResultsApi,
} from '@/api'
import { showToast } from '@/composables/useToast.ts'

const config = new Configuration({
  basePath: import.meta.env.VITE_API_BASE_URL,
})
const api = new GameResultsApi(config)

export async function fetchGameResultBySession(sessionId: number): Promise<GameResultDto | null> {
  try {
    const response = await api.getGameResultForSession(sessionId)
    return response.data
  } catch (error: any) {
    const msg =
      error?.response?.data?.message ?? error?.message ?? 'Fehler beim Laden des Session-Results'
    await showToast(msg)
    return null
  }
}

export async function fetchResultsForPlayer(playerId: number): Promise<GameResultOverviewDto[]> {
  try {
    const response = await api.getResultsForPlayer(playerId)
    return response.data
  } catch (error: any) {
    const msg =
      error?.response?.data?.message ?? error?.message ?? 'Fehler beim Laden der Spielergebnisse'
    await showToast(msg)
    return []
  }
}
