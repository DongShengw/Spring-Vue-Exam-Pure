<template>
  <div  v-wechat-title="$route.meta.title" class="home" style="padding: 20px">
    <el-page-header  content="" @back="goBack" />
    <!--    功能-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="checkGrade()">查看成绩分析</el-button>
<!--      <el-button type="primary">导入</el-button>-->
<!--      <el-button type="primary">导出</el-button>-->
    </div>
    <!--    搜索-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="按试卷名称搜索" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">搜索</el-button>
    </div>
    <!--表格-->
    <el-table
        :data="tableData"
        stripe
        height="600"
        style="width: 100%; text-align: center"
        border>
      <el-table-column prop="studentId" label="学号" sortable/>
      <el-table-column prop="studentName" label="姓名" ></el-table-column>
      <el-table-column prop="subject" label="考试科目" ></el-table-column>
      <el-table-column prop="etScore" label="分数" ></el-table-column>
      <el-table-column prop="answerDate" label="考试日期" ></el-table-column>
      <!--状态标签-->
<!--      <el-table-column-->
<!--          prop="tag"-->
<!--          label="考试状态"-->
<!--          width="100"-->
<!--          :filters="[-->
<!--        { text: '未开始', value: 'begin' },-->
<!--        { text: '进行中', value: 'going' },-->
<!--        { text: '已结束', value: 'over' },-->
<!--      ]"-->
<!--          :filter-method="filterTag"-->
<!--          filter-placement="bottom-end"-->
<!--      >-->
<!--        <template #default="scope">-->
<!--          <el-tag-->
<!--              :type="scope.row.tag === 'Home' ? '' : 'success'"-->
<!--              disable-transitions-->
<!--          >{{ scope.row.tag }}</el-tag-->
<!--          >-->
<!--        </template>-->
<!--      </el-table-column>-->

<!--      <el-table-column label="操作">-->
<!--        <template #default="scope">-->
<!--          <el-button type="primary" color="#626aef" plain @click="checkAll(scope.row)">查看详细数据</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
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
import { compareTime } from '@/utils/compareTime.js'

export default {
  name: 'examManage',
  components: {},
  data() {
    return {
      search: '',
      currentPage4: 1,
      pageSize4: 10,
      // tag:'',
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
      // console.log(this.$cookies.get("data").teacherId)
      request.get("/score/exam", {
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
          id:this.$route.query.examCode
          // teacherId:this.$cookies.get("data").teacherId
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        // this.tableData.forEach((element,index) => {
        //   if(compareTime(element.examDate)){
        //     this.tableData[index]['tag'] = '未开始'
        //   } else if(!compareTime(element.examDate)){
        //     this.tableData[index]['tag'] = '已结束'
        //   }else{
        //     this.tableData[index]['tag'] = '进行中'
        //   }
        // })
        // console.log(this.tableData)
        this.total = res.data.total
      })
    },
    add() {
      this.dialogVisible = true
      this.form = {}
    },
    save(){
      console.log(this.form)
      if(!this.form){
        request.put("/exam",this.form).then(res => {
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
        request.post("/exam",this.form).then(res => {
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
    filterTag(value,row){
      return row.tag === value
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/exam/" + id).then(res => {
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
    checkGrade() {
      this.$router.push({ path: "/gradePart", query: { examCode: this.$route.query.examCode ,name:this.$route.query.source} });
    },
    goBack(){
      this.$router.push("/scorePart")
    }

  }
}
</script>
