/* eslint-disable import/no-extraneous-dependencies */
import { defineConfig } from 'vite'

import vue from '@vitejs/plugin-vue'
import transformerDirective from '@unocss/transformer-directives'

import path from 'path'

export default defineConfig({
  server: {
    host: true,
  },
  plugins: [vue()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
      assets: path.resolve(__dirname, './src/assets'),
      components: path.resolve(__dirname, './src/components'),
      core: path.resolve(__dirname, './src/core'),
      pages: path.resolve(__dirname, './src/pages'),
      stores: path.resolve(__dirname, './src/stores'),
      styles: path.resolve(__dirname, './src/styles'),
    },
  },
})
