<template>
  <div v-wechat-title="$route.meta.title" class="home" style="padding: 20px">
    <el-page-header  content="" @back="goBack" />

    <!--    功能-->
<!--    <div style="margin: 10px 0">-->

<!--      <el-button type="primary">导入</el-button>-->
<!--      <el-button type="primary">导出</el-button>-->
<!--      <el-button type="primary" @click="handAdd">手动组卷</el-button>-->
<!--      <el-button type="primary" @click="autoAdd">自动组卷</el-button>-->

<!--    </div>-->
    <!--    搜索-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">搜索</el-button>
    </div>
    <!--表格-->
    <el-table
        :data="tableData"
        stripe
        height="600"
        style="width: 100%; text-align: center"
        :row-class-name="tableRowClassName"
        show-summary
        border>
      <el-table-column prop="subject" label="试卷名称" sortable/>
      <el-table-column prop="question" label="题目信息" ></el-table-column>
      <el-table-column prop="section" label="所属章节" ></el-table-column>
      <el-table-column prop="type" label="题目类型" ></el-table-column>
      <el-table-column prop="score" label="试题分数" ></el-table-column>
      <el-table-column prop="level" label="难度等级"></el-table-column>

<!--            <el-table-column label="操作">-->
<!--              <template #default="scope">-->
<!--                <el-button @click="handleEdit(scope.row)">编辑</el-button>-->
<!--                <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.paperId)">-->
<!--                  <template #reference>-->
<!--                    <el-button type="danger">删除</el-button>-->
<!--                  </template>-->
<!--                </el-popconfirm>-->
<!--              </template>-->
<!--            </el-table-column>-->
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
  name: 'paperManage',
  components: {},

  data() {
    return {
      search: '',
      currentPage4: 1,
      pageSize4: 10,
      total: 0,
      dialogVisible:false,
      subject:'',
      paperId:0,
      form:{},
      tableData: [],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      this.subject = this.$route.query.subject //获取试卷名称
      this.paperId = this.$route.query.paperId //获取paperId
      console.log(this.paperId)
      request.get("/paperManage",{
        params:{
          // pageNum:this.currentPage4,
          // pageSize:this.pageSize4,
          // search:this.search,
          paperId:this.paperId
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data[1].concat(res.data[2].concat(res.data[3]))
        this.total = res.data.total
      })
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true

    },
    handleSizeChange(pageSize) {//改变当前每页的个数
      this.pageSize4 = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {//改变当前页码
      this.currentPage4 = pageNum
      this.load()
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 == 0) {
        return "warning-row";
      } else {
        return "success-row";
      }
    },
    // handAdd() { //手动增加题库
    //   this.$router.push({path:'/addQuestions',query: {paperId: this.paperId,subject:this.source,tabs:'first'}})
    // },
    // autoAdd() { //自动增加题库
    //   this.$router.push({path:'/addQuestions',query: {paperId: this.paperId,subject:this.source,tabs:'second'}})
    // },
    goBack(){
      this.$router.push({path:'/AdminSetPaper'})
    }
  }
}
</script>
<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-9);
}
.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>
