<template>
  <div v-wechat-title="$route.meta.title" style="padding: 20px">
    <el-card style="width: 70%; margin: 0 auto">
      <!--表格-->
      <el-table
          :data="tableData"
          stripe
          height="200"
          style="width: 100%; text-align: center"
          border>
        <el-table-column prop="studentName" label="昵称"/>
        <el-table-column prop="grade" label="年级"/>
        <el-table-column prop="sex" label="性别"/>
        <el-table-column prop="major" label="专业"/>
        <el-table-column prop="tel" label="电话"/>
        <el-table-column prop="pwd" label="密码"/>
        <el-table-column prop="email" label="邮箱"/>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button type="primary" color="#626aef" plain @click="dialogVisible = true">修改密码</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-card>

    <el-dialog v-model="dialogVisible" title="提示" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="密码">
          <el-input v-model="form.pwd" style="width:80%"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="Update">确认</el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "studentInfo",
  data(){
    return{
      form:{
        studentId:this.$cookies.get("data").studentId,
      },
      dialogVisible:false,
      tableData:[],
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/student/"+this.$cookies.get("data").studentId).then(res => {
        if (res.code === '0') {
          console.log(res)
          this.tableData = res.data.records
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }

      })
    },
    Update() {
      console.log(this.form)
      // this.form.studentId = this.$cookies.get("data").studentId
      request.put("/student", this.form).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "更新成功"
          })
          // if(this.$cookies.get("data").pwd !== res.data.pwd){
          //   this.$router.push("/logout")
          // }else {
          //   this.$router.push("/studentIndex")//登录成功之后进行页面跳转，跳转到主页
          // }
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()//刷新表格的数据
        this.dialogVisible = false  //关闭弹窗
      })
    }
  }

}
</script>

<style scoped>

</style>
