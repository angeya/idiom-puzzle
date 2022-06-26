module.exports = {
  publicPath: './',
  devServer: {
    // port: 8088,
    public: '0.0.0.0:8081',
    // 代理配置(表)，在这里可以配置特定的请求代理到对应的API接口
    // 例如将'localhost:8080/api/xxx'代理到'http://xxxxxx.com/api/xxx'，xxxxxx.com为target
    // 在请求接口的时候，vue自动拼接本地ip + axios baseurl + 接口路径
    proxy: {
      '/api': { // 规定请求地址api作为开头，如登录接口localhost/api/login，然后将localhost的地址转换为代理的地址
        target: 'http://127.0.0.1:8080',
        ws: true,
        changeOrigin: true, // 是否跨域
        pathRewrite: {
          '^/api': '' // 由于真实接口没有api字符串，最后在请求的时候，将api去掉，地址最后变为http://10.45.140.57:8080/login
        }
      }
    }
  },
  lintOnSave: false
}
