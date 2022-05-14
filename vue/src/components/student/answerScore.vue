<template>
  <div v-wechat-title="$route.meta.title" class="score">
    <el-card class="title">
      <p style="font-size: 20px; font-weight: bold;color: #34ac53;margin-bottom: 20px">{{this.$cookies.get("exam").subjectName}}</p >
      <p style="margin-bottom: 5px">总分：{{ this.$cookies.get("exam").oldScore }}</p >
      <p style="margin-bottom: 5px">限时：100分钟</p >
      <p style="margin-bottom: 5px">学生姓名：{{this.$cookies.get("data").studentName}}</p >
    </el-card>
    <el-card class="box-card">
      <img class="img1" v-if="score < 60" :src="imgSrc.fail1">
      <img class="img1" v-if="score >= 60 && score < 80" :src="imgSrc.fail2">
      <img class="img1" v-if="score >= 80 && score < 90" :src="imgSrc.pass1">
      <img class="img1" v-if="score >= 90 && score < 100" :src="imgSrc.pass2">
      <div id="circle">
          分数
        <br>
        {{this.$cookies.get("exam").score}}
      </div>

      <el-button color="#626aef" plain @click="CheckScore()" style="position: relative; left: 438px;top: 60px; ">返回首页</el-button>

    </el-card>
<!--    <div class="total">-->
<!--      <div class="look">-->
<!--        本次考试成绩-->
<!--      </div>-->
<!--      <div class="show">-->
<!--        <div class="img1" :class="{'img1Transform': imgShow}">-->
<!--          <img :src="imgSrc.fail1" alt="哭了" v-if="score < 60">-->
<!--          <img :src="imgSrc.pass1" alt="过了" v-if="score >= 60">-->
<!--        </div>-->
<!--        <div class="number" :class="{'border': isTransition}">-->
<!--          <span>{{score}}</span>-->
<!--          <span>分数</span>-->
<!--        </div>-->
<!--        <div class="img2" :class="{'img2Transform': imgShow}">-->
<!--          <img :src="imgSrc.fail2" alt="哭了" v-if="score < 60">-->
<!--          <img :src="imgSrc.pass2" alt="过了" v-if="score >= 60">-->
<!--        </div>-->
<!--      </div>-->
<!--      <ul class="time">-->
<!--        <li class="start"><span>开始时间</span> <span>{{startTime}}</span></li>-->
<!--        <li class="end"><span>结束时间</span> <span>{{endTime}}</span></li>-->
<!--      </ul>-->
<!--    </div>-->
  </div>
</template>

<script>
export default {
  data() {
    return {
      isTransition: false, //是否渲染完成
      score: 0, //总分
      source:'',
      OllScore:0,
      imgShow: false, //不及格图片显示
      imgSrc: {
        fail1: require("@/assets/img/bad.png"),
        fail2: require('@/assets/img/nobad.png'),
        pass1: require('@/assets/img/good.png'),
        pass2: require('@/assets/img/great.png')
      },
      startTime: null, //考试开始时间
      endTime: null, //考试结束时间
    }
  },
  created() {
    this.transiton()
    this.getScore()
  },
  methods: {
    transiton() {  //一秒后过渡
      setTimeout(() => {
        this.isTransition = true
        this.imgShow = true
      },1000)
    },
    getScore() {
      let source = this.$route.query.source
      let OllScore = this.$route.query.OllScore
      let score = this.$cookies.get("exam").score
      let startTime = this.$route.query.startTime
      let endTime = this.$route.query.endTime
      this.source =  source
      this.OllScore = OllScore
      this.score = score
      this.startTime = startTime
      this.endTime = endTime
      console.log(source)
      console.log(score)

      console.log(OllScore)

    },
    CheckScore(){
      this.$router.push({path:'/scoreTable'})
    }
  }
}
</script>

<style lang="scss" scoped>
*{
  background-color: #ffffff;
}
.img1{
  background:#ffffff;
  width: 114px;
  position: relative;
  top: 70px;
  left: 220px;
  height: 100px;
  flex: 1;
}

.score{
  height: 790px;
}
.box-card {
  background-color: #ffffff;
  margin: 40px auto;
  width: 1000px;
  height: 500px;
}
.score .title{
  padding: 40px;
  text-align: center;
  font-size: 24px;
}
#circle{
  background:#ffffff;
  margin: 0 auto;
  border: 5px solid darkcyan ;
  font-size:30px;
  width:260px;
  height:260px;
  border-radius:50%;
  text-align: center;
  display: flex;
  flex: 1;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.img2{
  background-color: #ffffff;
  width: 114px;
  position: relative;
  top: -200px;
  left: 750px;
  height: 100px;
  flex: 1;
}
</style>

