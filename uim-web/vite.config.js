import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'
import {resolve} from 'path'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig(({mode}) => {
    return {
        base: './',
        plugins: [
            vue(),
            AutoImport({
                resolvers: [ElementPlusResolver()],
            }),
            Components({
                resolvers: [ElementPlusResolver()],
            }),
        ],
        server: {
            proxy: {
                '/api': {
                    target: loadEnv(mode, process.cwd()).VITE_BASE_API,
                    changeOrigin: true,
                    rewrite: (path) => path.replace(/^\/api/, ''),
                },
            },
        },
        resolve: {
            alias: {
                '@': resolve(__dirname, 'src')
            }
        },
        build: {
            minify: "terser",
            terserOptions: {
                compress: {
                    // 生产环境移除console
                    drop_console: true,
                    drop_debugger: true
                }
            }
        }
    }
})
