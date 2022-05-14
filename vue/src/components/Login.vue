<template>
  <div v-wechat-title="$route.meta.title"  style="width: 100%; height: 100vh; overflow: hidden" class="background">
    <div style="width: 300px; margin: 200px auto">
      <div style="color: black; font-size: 34px; text-align: center; padding: 20px 0">欢迎登录
        <br>
        Stars 在线考试系统
      </div>
      <el-form ref="form" :model="form" size="large" :rules="rules" >
        <!--        输入用户名-->
        <el-form-item prop="username" >
          账号
          <el-input  v-model="form.username" >
            <template #prefix>
              <el-icon class="el-input__icon"><user-filled /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <!--        输入密码-->
        <el-form-item prop="password">
          密码
          <el-input v-model="form.password" show-password>
            <template #prefix>
              <el-icon class="el-input__icon"><lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <!--        用户类型选择-->
        <el-form-item prop="userType">
          <el-select v-model="value"  placeholder="用户类型" style="margin-left: 40px; font-size: 16px">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"/>
          </el-select>
        </el-form-item>
        <!--        登录按钮-->
        <el-form-item>
          <el-button style="width: 50%; color: dodgerblue; margin: 0 auto; font-size: 16px" @click="login">登录</el-button>
        </el-form-item>
        <!--        注册按钮-->
        <el-form-item>
          <el-button style="width: 50%; color: dodgerblue; margin: 0 auto ;font-size: 16px " @click="register">注册</el-button>
        </el-form-item>

      </el-form>
    </div>

  </div>
</template>

<script>
import {UserFilled,Lock} from "@element-plus/icons";

import request from "@/utils/request";
import { ref } from 'vue'
export default {
  name: "Login",
  components: {
    UserFilled,
    Lock
  },
  data(){
    return{
      form:{},
      value:ref('') ,
      options:[
        {
          value: 'student',
          label: '学生',
        },
        {
          value: 'teacher',
          label: '教师',
        },
        {
          value: 'admin',
          label: '管理员',
        }
      ],
      rules:{
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password:[
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    login(){
      //只有输入才会发出登录请求
      this.$refs['form'].validate((valid) => {
        if (valid) {
          //管理员登录
          if(this.value === 'admin'){
            request.post("/admin/login", this.form).then(res => {
              if (res.code === '0') {
                this.$message({
                  type: "success",
                  message: "登录成功"
                })
                console.log(res.data)
                this.$cookies.set("data",res.data)
                this.$cookies.set("name","Stars")
                this.$router.push("/admin")//登录成功之后进行页面跳转，跳转到主页
              } else {
                this.$message({
                  type: "error",
                  message: res.msg
                })
              }
            })
          }
          //教师登录
          if(this.value === 'teacher'){
            request.post("/teacher/login", this.form).then(res => {
              if (res.code === '0') {
                this.$message({
                  type: "success",
                  message: "登录成功"
                })
                console.log(res.data)
                this.$cookies.set("data",res.data)
                this.$cookies.set("name","Stars")
                this.$router.push("/teacher")//登录成功之后进行页面跳转，跳转到主页
              } else {
                this.$message({
                  type: "error",
                  message: res.msg
                })
              }
            })
          }
          //学生登录
          if(this.value === 'student'){
            request.post("/student/login", this.form).then(res => {
              if (res.code === '0') {
                this.$message({
                  type: "success",
                  message: "登录成功"
                })
                this.$cookies.set("data",res.data)
                sessionStorage.setItem("user",JSON.stringify(res.data))
                this.$router.push("/studentIndex")//登录成功之后进行页面跳转，跳转到主页
              } else {
                this.$message({
                  type: "error",
                  message: res.msg
                })
              }
            })
          }

        }
      })
    },
    register(){
      this.$router.push("/register")//登录成功之后进行页面跳转，跳转到主页
    }
  }
}
</script>

<style scoped>
.background{
  width:100%;
  height:100%;  /**宽高100%是为了图片铺满屏幕 */
  background: url('../assets/img/yun.jpg') center center ;
  z-index:-1;
  position: absolute;
}
</style>
