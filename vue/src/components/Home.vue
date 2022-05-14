<template>
  <section class="index">
    <div class="hello">
      <i class="iconfont icon-xihuan"></i><span>很高兴遇见你,{{this.$cookies.get("data").teacherName}}老师。</span>
    </div>
    <div class="msg">
      <p class="title">教务公告：</p>
      <ul>
        <li @click="openMsg()"><i class="iconfont icon-flag"></i>五一放假通知</li>
        <li @click="openMsg()"><i class="iconfont icon-flag"></i>下周工作报告会</li>
        <li @click="openMsg()"><i class="iconfont icon-flag"></i>关于毕业生答辩具体流程</li>
      </ul>
    </div>
  </section>
<!--  <div class="block">-->
<!--    <el-carousel type="card"-->
<!--                 :interval=6000>-->
<!--      <el-carousel-item v-for="(item,index) in imgArr" :key="index">-->
<!--&lt;!&ndash;        <h3 class="small">{{ item }}</h3>&ndash;&gt;-->
<!--        <img src="https://w.wallhaven.cc/full/nm/wallhaven-nmvgyk.jpg" width="100%" height="100%">-->
<!--      </el-carousel-item>-->
<!--    </el-carousel>-->
<!--  </div>-->
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      imgArr: [
        { url: "https://w.wallhaven.cc/full/nm/wallhaven-nmvgyk.jpg" },
        {
          url: "https://w.wallhaven.cc/full/lq/wallhaven-lqovgr.jpg",
        },
        {
          url: "https://w.wallhaven.cc/full/76/wallhaven-76p9p9.jpg",
        },
        {
          url: "./img/3.jpg",
        },
      ],
      user: { //用户信息
        userName: null,
        userId: null
      }
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() { //获取用户信息
      let userName = this.$cookies.get("cname")
      let userId = this.$cookies.get("cid")
      this.user.userName = userName
      this.user.userId = userId
    },
    openMsg() {
      this.$alert('根据《国务院办公厅关于2022年部分节假日安排的通知》精神，越城区行政服务中心将于4月5日（星期五）至4月7日（星期天）进行清明节放假调休，共3天，放假期间不受理业务。4月8日（星期一）开始正常上班受理业务。望市民朋友相互转告，给您带来不便，敬请谅解。','清明节放假通知',{
        confirmButtonText: '确定'
      })
    }
  }
}
</script>


<style lang="scss" scoped>
.index {
  margin-left: 70px;
  .hello {
    font-size: 20px;
    color: #726f70;
    .icon-xihuan {
      font-size: 30px;
      color: #dd6572;
    }
  }
  .msg {
    .title {
      font-size: 30px;
      color: #000;
      margin-top: 40px;
      margin-left: 0px;
    }
    ul {
      display: flex;
      flex-direction: column;
      width: 600px;
      margin-left: 20px;
      overflow: hidden;
    }
    li {
      margin-top: 10px;
      font-size: 25px;
      color: lightcoral;
      cursor: pointer;
      display: inline-block;
    }
  }
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.block{
  width: 600px;
  height: 800px;
}
</style>

