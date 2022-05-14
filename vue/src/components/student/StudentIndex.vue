<template>
  <div v-wechat-title="$route.meta.title" style="background-color: #eeeeee ;height: calc(100vh - 50px);">
    <div style="width:1300px; background-color:#fff; height: 100%; border-left: 3px solid #e0d8d8;border-right: 3px solid #e0d8d8; text-align: center;padding:0 70px;margin: 0 auto;">
      <el-scrollbar height="700px">
        <div class="wrapper">
          <ul class="top">
            <li class="order">试卷列表</li>
            <!--    搜索-->
            <div style="margin: 19px 0">
              <el-input v-model="search" placeholder="按题目信息搜索" style="width: 70%" clearable></el-input>
              <el-button type="primary" style="margin-left: 5px" @click="load">搜索</el-button>
            </div>
          </ul>
        </div>
        <!--卡片      -->
        <div style="margin: 0 auto">
            <ul class="paper" >
              <li class="item" v-for="(item,index) in tableData" :key="index">
                <h4 @click="toExamMsg(item.examCode)">{{item.source}}</h4>
                <p class="name">{{item.source}}-{{item.description}}</p>
                <div class="info">
                  <i class="el-icon-loading"></i><span>开考时间 {{item.examDate}}</span>
                  <i class="iconfont icon-icon-time"></i><span v-if="item.totalTime != null">限时{{item.totalTime}}分钟</span>
                  <i class="iconfont icon-fenshu"></i><span>满分{{item.totalScore}}分</span>
                </div>
              </li>
            </ul>
        </div>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import router from "@/router";

export default {
  name: "studentIndex",
  data() {
    return {
      loading: false,
      form: {},
      currentPage4: 1,
      pageSize4: 10,
      total: 0,
      search:'',
      tableData: []
    }
  },
  created() {
    this.load()
    this.loading = true
  },
  methods: {
    load(){
      request.get("/exam/student", {
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
          search:this.search,
          major:this.$cookies.get("data").major
        }
      }).then(res => {
        console.log(res.data)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    start(){
      this.$router.push("/studentTest")
    },
    //跳转到试卷详情页
    toExamMsg(examCode) {
      this.$router.push({path: '/examMsg', query: {examCode: examCode}})
      console.log(examCode)
    }
  },

}
</script>

<style lang="scss" scoped>
.pagination {
  padding: 20px 0px 30px 0px;
  .el-pagination {
    display: flex;
    justify-content: center;
  }
}
.paper {
  h4 {
    cursor: pointer;
  }
}
.paper .item a {
  color: #000;
}
.wrapper .top .order {
  cursor: pointer;
}
.wrapper .top .order:hover {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.wrapper .top .order:visited {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.item .info i {
  margin-right: 5px;
  color: #0195ff;
}
.item .info span {
  margin-right: 14px;
}
.paper .item {
  width: 520px;
  float: left;
  list-style-type:none;
  margin: 26px;
  border-radius: 4px;
  padding: 20px 30px;
  border: 1px solid #eee;
  box-shadow: 0 0 4px 2px rgba(217,222,234,0.3);
  transition: all 0.6s ease;
}
.paper .item:hover {
  box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
  transform: scale(1.03);
}
.paper .item .info {
  font-size: 14px;
  color: #88949b;
}
.paper .item .name {
  font-size: 14px;
  color: #88949b;
}
.paper * {
  margin: 20px 0;
}
.wrapper .paper {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.top .el-icon-search {
  position: absolute;
  right: 10px;
  top: 10px;
}
.top .icon {
  position: relative;
}
.wrapper .top {
  border-bottom: 1px solid #eee;
  margin-bottom: 20px;
}
#myExam .search-li {
  margin-left: auto;
}
.top .search-li {
  margin-left: 200px;
}
.top li {
  display: flex;
  align-items: center;
}
.top .search {
  margin-left: auto;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #eee;
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
  transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.top .search:hover {
  color: #0195ff;
  border-color: #0195ff;
}
.wrapper .top {
  display: flex;
}
.wrapper .top li {
  margin: 20px;
}
#myExam {
  width: 980px;
  margin: 0 auto;
}
#myExam .title {
  margin: 20px;
}
#myExam .wrapper {
  background-color: #fff;
}
</style>
