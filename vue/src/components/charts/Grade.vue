<template>
  <div id="grade">
    <el-page-header  content="" @back="goBack" />
    <div ref="box" class="box"></div>
    <div class="notFound" v-if="isNull">
      <i class="iconfont icon-LC_icon_tips_fill"></i><span>该考生未参加考试</span>
    </div>
  </div>
</template>
<script>
import request from "@/utils/request";
import * as echarts from 'echarts'
export default {
  name: "Grade",
  data() {
    return {
      isNull: false, //原始数据
      tableDataX: [], //x轴数据 保存次数
      tableDataY: [], //y轴数据 保存分数
      title: [],
      exam_subject: [],
      rootData:[],
      name: ''
    }
  },
  mounted() {
    this.score();
  },
  methods: {
    score() {
      let studentId = this.$route.query.studentId
      request.get("/score/"+studentId).then(res => { //根据学生Id查询成绩
        console.log(res)
        if(res.code === '0') {
          this.rootData = res.data.records
          this.name = this.rootData[0].studentName
          this.rootData.forEach((element,index) => {
            this.tableDataX.push(`第${index + 1}次`)
            this.tableDataY.push(element.etScore)
            this.exam_subject.push(element.subject)
          });
          let boxDom = this.$refs["box"];
          // console.log(boxDom)
          let scoreCharts = echarts.init(boxDom)
          // let scoreCharts = this.echarts.init(boxDom);
          let option = {
             title: {
               text: this.name
             },
            backgroundColor: '#f1ecec',
            tooltip: {
              type:"shadow",
              trigger: 'axis',
              // 自定义悬浮框展示的内容。此json对象的内容可自定义，我只是举个例子。{name: '内容标题'， value：'可以是任意字符，根据业务需求来吧'}
              formatter(params) {
                // console.log(params.value)
                return `考试科目：${this.rootData[params[0].axisIndex].subject} 时间：${this.rootData[params[0].axisIndex].answerDate}`
              }
            },
            xAxis: {
              type: "category",
              data: this.tableDataX
            },
            yAxis: {
              type: "value"
            },
            series: [
              {
                data: this.tableDataY,
                type: "line",
                itemStyle: { normal: { label: { show: true } } }
              }
            ]
          };
          scoreCharts.setOption(option);
          scoreCharts.on("mouseover", params => {
            console.log(params);
          });
        }else {
          this.isNull = true
        }
      })
    },
    goBack(){
      if(this.$cookies.get("data").role == 1)
        this.$router.push({path:'/scoreOne',query:{studentId:this.$route.query.studentId}})
      else {
        this.$router.push({path:'/adminScoreOne',query:{studentId:this.$route.query.studentId}})

      }
    }
  }
}
</script>

<style lang="scss" scoped>
#grade {
  padding: 20px;
  position: relative;
  .box{
    padding: 30px;
    width: 1500px;
    height: 700px;
  }
  .notFound {
    position: absolute;
    top: 20px;
    left: 20px;
  }
}
</style>
