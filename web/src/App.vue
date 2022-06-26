<template>
  <div id="app">
    <div>
      <el-input class="idiom-content" placeholder="请输入成语" v-model="idiom.content" @keyup.enter.native="getNextIdiom"></el-input>
      <el-button class="start-btn" type="primary" @click="getNextIdiom">开始接龙</el-button>
      <br>
      <br>
      <el-switch
        v-model="idiom.isSameSimplePinYin"
        inactive-text="同音模式">
      </el-switch>
      <el-switch
        v-model="idiom.isSameTonePinYin"
        inactive-text="同音同调模式">
      </el-switch>
    </div>
    <div class="result-container">
      <div v-for="(idiom, key) in resultList" :key="key" class="result-idiom">
        <span>{{idiom.content}}</span>
        <span>（{{idiom.tonePinYin.join(" ")}}）</span>
        <el-button type="success" size="small" @click="continueNext(idiom.content)">接下一个</el-button>
        <p>释义: {{idiom.explanation}}</p>
        <hr>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  name: 'App',
  watch: {
    'idiom.isSameSimplePinYin'(nVal) {
      if (nVal && this.idiom.isSameTonePinYin) {
        this.idiom.isSameTonePinYin = false
      }
    },
    'idiom.isSameTonePinYin'(nVal) {
      if (nVal && this.idiom.isSameSimplePinYin) {
        this.idiom.isSameSimplePinYin = false
      }
    }
  },
  data() {
    return {
      idiom: {
        content: "",
        isSameSimplePinYin: false,
        isSameTonePinYin: false,
      },
      resultList: []
    }
  },
  methods: {
    getNextIdiom() {
      const that = this
      
      this.axios({
      method: 'post',
      url: 'getNextIdiom',
      data: this.idiom
      }).then(res => {
        if (res.data) {
          if (res.data.length > 0) {
            that.resultList = res.data
            // console.log(that.resultList)
          } else {
            alert("没有找到下一个啦！")
          }
        } else {
          console.log("some error happened!")
        }
      })
    },
    continueNext(idiomContent) {
      this.idiom.content = idiomContent
      this.getNextIdiom()
    }
  }
}
</script>

<style lang="stylus">
#app
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center
  margin-top: 60px
  .idiom-content
    width 20%
    border 1px solid #409eff
    border-radius 5px
  .result-container
    margin-top 10px
  .start-btn
    margin-left 10px
  .result-idiom
    text-align left
    text-indent 2em
    background-color antiquewhite
</style>
