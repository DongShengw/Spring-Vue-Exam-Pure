<template>
  <div v-wechat-title="$route.meta.title" class="home" style="padding: 20px">
    <el-page-header  content="" @back="goBack" />
    <!--    功能-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="checkGrade()">查看成绩分析</el-button>
<!--      <el-button type="primary">导入</el-button>-->
<!--      <el-button type="primary">导出</el-button>-->
    </div>
    <!--    搜索-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">搜索</el-button>
    </div>
    <!--表格-->
    <el-table
        :data="tableData"
        stripe
        style="width: 100%; text-align: center"
        border>
      <el-table-column prop="studentId" label="学号" sortable/>
      <el-table-column prop="studentName" label="姓名" ></el-table-column>
      <el-table-column prop="subject" label="考试科目" ></el-table-column>
      <el-table-column prop="etScore" label="分数" ></el-table-column>
      <el-table-column prop="answerDate" label="考试日期" ></el-table-column>
<!--      <el-table-column fixed="right" label="查看成绩">-->
<!--        <template #default="scope">-->
<!--          <el-button @click="checkGrade(scope.row.studentId)" type="primary">查看成绩</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <!--      -->
            <el-table-column label="操作">
              <template #default="scope">
                <el-button @click="handleEdit(scope.row)">编辑</el-button>
<!--                <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.id)">-->
<!--                  <template #reference>-->
<!--                    <el-button type="danger">删除</el-button>-->
<!--                  </template>-->
<!--                </el-popconfirm>-->
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
  components: {},
  data() {
    return {
      search: '',
      currentPage4: 1,
      pageSize4: 10,
      total: 0,
      dialogVisible:false,
      form:{},
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      request.get("/score/"+ this.$route.query.studentId, {
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add() {
      this.dialogVisible = true
      this.form = {}

    },
    save(){
      if(this.form.id){
        request.put("/score",this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            this.$message({
              type:"success",
              message:"更新成功"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
          this.load()//刷新表格的数据
          this.dialogVisible = false  //关闭弹窗
        })
      }
      else{
        request.post("/score",this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            this.$message({
              type:"success",
              message:"新增成功"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
          this.load()//刷新表格的数据
          this.dialogVisible = false  //关闭弹窗
        })
      }

    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true

    },
    checkGrade() {
      this.$router.push({ path: "/grade", query: { studentId: this.$route.query.studentId } });
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/score/" + id).then(res => {
        console.log(res)
        if(res.code === '0'){
          this.$message({
            type:"success",
            message:"删除成功"
          })
        }else{
          this.$message({
            type:"error",
            message:res.msg
          })
        }
        this.load()//刷新表格的数剧
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
    goBack(){
      this.$router.push({path:'/score'})
    }
  }
}
</script>
