<template>
  <div v-wechat-title="$route.meta.title" class="home" style="padding: 20px">
    <!--    功能-->
<!--    <div style="margin: 10px 0">-->
<!--      <el-button type="primary" @click="add">新增</el-button>-->
<!--      <el-button type="primary">导入</el-button>-->
<!--      <el-button type="primary">导出</el-button>-->
<!--    </div>-->
    <!--    搜索-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="按试卷名称搜索" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">搜索</el-button>
    </div>
    <!--表格-->
    <el-table
        :data="tableData"
        stripe
        height="550"
        style="width: 100%; text-align: center"
        border>
      <el-table-column prop="source" label="试卷名称" sortable/>
      <el-table-column prop="description" label="介绍"/>
      <el-table-column prop="institute" label="所属学院"/>
      <el-table-column prop="major" label="所属专业"/>
      <el-table-column prop="grade" label="年级"/>
      <el-table-column prop="examDate" label="开考日期"></el-table-column>
      <el-table-column prop="totalTime" label="持续时间"/>
      <el-table-column prop="totalScore" label="总分"/>
      <el-table-column prop="type" label="试卷类型"/>
      <el-table-column prop="tips" label="考生提示"/>
      <el-table-column  label="操作">
        <template #default="scope">
          <el-button @click="toPart(scope.row.examCode,scope.row.source)" size = "small" type="primary">查看详细数据</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <!--      分页-->
      <el-pagination
          v-model:currentPage="currentPage4"
          v-model:page-size="pageSize4"
          :page-sizes="[5, 10, 15, 20]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"/>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src


import request from "@/utils/request";

export default {
  name: 'scorePart',
  components: {},
  data() {
    return {
      search: '',
      currentPage4: 1,
      pageSize4: 10,
      total: 0,
      dialogVisible:false,
      form:{
        source: null,
        description: null,
        institute: null,
        major: null,
        grade: null,
        examDate: null,
        totalTime: null,
        totalScore: null,
        type: null,
        tips: null,
        paperId: null,
      },
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      request.get("/exam", {
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
          search:this.search,
          teacherId:this.$cookies.get("data").teacherId
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    handleSizeChange(pageSize) {//改变当前每页的个数
      this.pageSize4 = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {//改变当前页码
      this.currentPage4 = pageNum
      this.load()
    },
    toPart(examCode,source) { //跳转到分段charts页面
      this.$router.push({path: '/scorePartOne', query:{examCode: examCode, source: source}})
    }
  }
}
</script>
