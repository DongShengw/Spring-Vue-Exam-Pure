// 添加题库
<template>
  <div v-wechat-title="$route.meta.title" class="add">
    <el-tabs class="tabs" v-model="activeName">
      <el-tab-pane label="自动组卷" name="second">
        <span slot="label"><i class="iconfont icon-daoru-tianchong"></i>在线组卷</span>
        <div class="box">
          <ul>
            <li>
              <span>试题难度:</span>
              <el-select v-model="difficultyValue" placeholder="试题难度" class="w150">
                <el-option
                    v-for="item in difficulty"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li>
              <span>选择题数量：</span> <el-input type="text" v-model="changeNumber"></el-input>
            </li>
            <li>
              <span>填空题数量：</span> <el-input type="text" v-model="fillNumber"></el-input>
            </li>
            <li>
              <span>判断题数量：</span> <el-input type="text" v-model="judgeNumber"></el-input>
            </li>
            <li>
              <el-button type="primary" @click="create()">立即组卷</el-button>
            </li>
          </ul>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import request from "@/utils/request";
import { ref } from "vue";

export default {
  name: "addQuestions",
  data() {
    return {
      changeNumber: null, //选择题出题数量
      fillNumber: null, //填空题出题数量
      judgeNumber: null, //判断题出题数量
      activeName: '',  //活动选项卡
      options: [ //题库类型
        {
          value: '选择题',
          label: '选择题'
        },
        {
          value: '填空题',
          label: '填空题'
        },
        {
          value: '判断题',
          label: '判断题'
        },
      ],
      difficulty: [ //试题难度
        {
          value: '简单',
          label: '简单'
        },
        {
          value: '一般',
          label: '一般'
        },
        {
          value: '困难',
          label: '困难'
        }
      ],
      difficultyValue: '简单',
      levels: [ //难度等级
        {
          value: '1',
          label: '1'
        },
        {
          value: '2',
          label: '2'
        },
        {
          value: '3',
          label: '3'
        },
        {
          value: '4',
          label: '4'
        },
        {
          value: '5',
          label: '5'
        },
      ],
      rights: [ //正确答案
        {
          value: 'A',
          label: 'A'
        },
        {
          value: 'B',
          label: 'B'
        },
        {
          value: 'C',
          label: 'C'
        },
        {
          value: 'D',
          label: 'D'
        },
      ],
      paperId: null,
      optionValue: '选择题', //题型选中值
      subject: '', //试卷名称用来接收路由参数
      postChange: { //选择题提交内容
        subject: '', //试卷名称
        level: '', //难度等级选中值
        rightAnswer: '', //正确答案选中值
        section: '', //对应章节
        question: '', //题目
        analysis: '', //解析
        answerA: '',
        answerB: '',
        answerC: '',
        answerD: '',
      },
      postFill: { //填空题提交内容
        subject: '', //试卷名称
        level: '', //难度等级选中值
        answer: '', //正确答案
        section: '', //对应章节
        question: '', //题目
        analysis: '', //解析
      },
      postJudge: { //判断题提交内容
        subject: '', //试卷名称
        level: '', //难度等级选中值
        answer: '', //正确答案
        section: '', //对应章节
        question: '', //题目
        analysis: '', //解析
      },
      postPaper: { //考试管理表对应字段
        paperId: null,
        questionType: null, // 试卷类型 1--选择题  2--填空题   3--判断题
        questionId: null,
      }
    };
  },
  created() {
    this.getParams()
  },
  methods: {
    // handleClick(tab, event) {
    //   console.log(tab, event);
    // },
    create() {
      request.post("/item", {
        changeNumber: this.changeNumber,
        fillNumber: this.fillNumber,
        judgeNumber: this.judgeNumber,
        paperId: this.postPaper.paperId,
        subject: '计算机网络' //题目数量太少，指定为计算机网络出题
      }).then(res => {
        console.log(res)
        if(res.code=='0'){
          setTimeout(() => {
            this.$router.push({path:'/paperManage',query: {paperId: this.postPaper.paperId}})
            // this.$router.push({path: '/paperManage'})
          },1000)
          this.$message({
            type:"success",
            message:"组卷成功"
          })
        }else if(res.code=="0000"){
          this.$message({
            message: '组卷失败',
            type: 'error'
          })
        }

      })
    },
    getParams() {
      let subject = this.$route.query.subject //获取试卷名称
      let paperId = this.$route.query.paperId //获取paperId
      this.activeName = this.$route.query.tabs
      this.paperId = paperId
      this.subject = subject
      this.postPaper.paperId = paperId
    },
    changeSubmit() { //选择题题库提交
      this.postChange.subject = this.subject
      request.post("/multiQuestion",{ ...this.postPaper}).then(res => { //添加成功显示提示
        let status = res.code
        if(status == '0') {
          this.$message({
            message: '已添加到题库',
            type: 'success'
          })
          this.postChange = {}
        }
      }).then(() => {
        request.post("/multiQuestion/new").then(res => { //获取当前题目的questionId
          let questionId = res.data.data.questionId
          this.postPaper.questionId = questionId
          this.postPaper.questionType = 1
          request.post("/paperManage",{ ...this.postPaper})
        })
      })
    },
    fillSubmit() { //填空题提交
      this.postFill.subject = this.subject
      request.post("/fillQuestion",{ ...this.postPaper}).then(res => {
        let status = res.code
        if(status == '0') {
          this.$message({
            message: '已添加到题库',
            type: 'success'
          })
          this.postFill = {}
        }
      }).then(() => {
        request.get("/fillQuestion/new").then(res => { //获取当前题目的questionId
          let questionId = res.data.data.questionId
          this.postPaper.questionId = questionId
          this.postPaper.questionType = 2
          request.post("/paperManage",{ ...this.postPaper})
        })
      })
    },
    judgeSubmit() { //判断题提交
      this.postJudge.subject = this.subject
      request.post("/judgeQuestion",{ ...this.postPaper}).then(res => {
        let status = res.code
        if(status == '0') {
          this.$message({
            message: '已添加到题库',
            type: 'success'
          })
          this.postJudge = {}
        }
      }).then(() => {
        request.get("/judgeQuestion/new").then(res => { //获取当前题目的questionId
          let questionId = res.data.records.questionId
          this.postPaper.questionId = questionId
          this.postPaper.questionType = 3
          request.post("/paperManage",{ ...this.postPaper})
        })
      })
    }
  },
};
</script>

<style lang="scss" scoped>
.tabs{
  padding: 12px;
}
.add {
  margin: 5px 80px;
  .box {
    padding: 0px 20px;
    ul li {
      margin: 10px 0px;
      display: flex;
      align-items: center;
      .el-input {
        width: 6%;
      }
      .w150 {
        margin-left: 20px;
        width: 7%;
      }
    }
  }
  .el-icon-circle-plus {
    margin-right: 10px;
    vertical-align: middle;
  }
  .icon-daoru-tianchong {
    margin-right: 10px;
    vertical-align: middle;
  }
  .append {
    margin: 0px 20px;
    ul {
      display: flex;
      align-items: center;
      li {
        margin-right: 20px;
      }
    }
    .change {
      margin-top: 20px;
      padding: 20px 16px;
      background-color: #E7F6F6;
      border-radius: 4px;
      .title {
        padding-left: 6px;
        color: #2f4f4f;
        span:nth-child(1) {
          margin-right: 6px;
        }
        .answer {
          margin: 20px 0px 20px 8px;
        }
        .el-textarea {
          width: 98% !important;
        }
      }
      .options {
        ul {
          display: flex;
          flex-direction: column;
        }
        ul li {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 98%;
          margin: 10px 0px;
          span {
            margin-right: 20px;
          }
        }
      }
      .submit {
        display: flex;
        justify-content: center;
        align-items: center;
      }
    }
    .fill {
      .fillAnswer {
        display: flex;
        justify-content: center;
        align-items: center;
        span {
          margin-right: 6px;
        }
        .el-input {
          width: 91% !important;
        }
      }
      .analysis {
        margin-top: 20px;
        margin-left: 5px;
      }
    }
    .judge {
      .judgeAnswer {
        margin-left: 20px;
        margin-bottom: 20px;
      }
    }
    .w150 {
      width: 150px;
    }
    li:nth-child(2) {
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}
</style>


