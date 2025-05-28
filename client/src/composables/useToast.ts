import { toastController } from '@ionic/vue'

export async function showToast(message: string, color: 'danger' | 'success' = 'danger') {
  const toast = await toastController.create({
    message,
    duration: 2000,
    color,
  })
  await toast.present()
}
