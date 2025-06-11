import { Configuration, type MoveCreationRequestDto, type MoveDto, MovesApi } from '@/api'
import { showToast } from '@/composables/useToast'

const config = new Configuration({
  basePath: import.meta.env.VITE_API_BASE_URL,
})
const api = new MovesApi(config)

/**
 * Holt alle Moves einer Session.
 * @param sessionId ID der Game-Session
 * @returns Array der MoveDto oder [] bei Fehler
 */
export async function fetchMovesForSession(sessionId: number): Promise<MoveDto[]> {
  try {
    const resp = await api.getMoves(sessionId)
    return resp.data
  } catch (error: any) {
    const msg = error?.response?.data?.message ?? error?.message ?? 'Fehler beim Laden der Moves'
    await showToast(msg)
    return []
  }
}

/**
 * Legt einen neuen Move in der Session an.
 * @param sessionId ID der Game-Session
 * @param moveType 'HIT' | 'STAND' | 'DOUBLE' | 'SPLIT'
 * @returns das angelegte MoveDto oder null bei Fehler
 */
export async function createMoveForSession(
  sessionId: number,
  moveType: 'HIT' | 'STAND' | 'DOUBLE' | 'SPLIT',
): Promise<MoveDto | null> {
  try {
    const payload: MoveCreationRequestDto = { moveType }
    const resp = await api.createMove(sessionId, payload)
    return resp.data
  } catch (error: any) {
    const msg = error?.response?.data?.message ?? error?.message ?? 'Fehler beim Anlegen des Moves'
    await showToast(msg)
    return null
  }
}
