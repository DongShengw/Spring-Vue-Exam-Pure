<template>
  <div v-wechat-title="$route.meta.title" style="width: 100%; height: 100vh; overflow: hidden" class="background" >
    <div style="width: 300px; margin: 150px auto">
      <div style="color: grey; font-size: 30px; text-align: center; padding: 30px 0 ">欢迎注册</div>
      <el-form ref="form" :model="form" size="large" :rules="rules">
        <!--        输入用户名-->
        <el-form-item prop="studentName">
          用户名
          <el-input  v-model="form.studentName" >
            <template #prefix>
              <el-icon class="el-input__icon"><user-filled /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <!--        输入密码-->
        <el-form-item prop="pwd">
          密码
          <el-input v-model="form.pwd" show-password>
            <template #prefix>
              <el-icon class="el-input__icon"><lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="confirm">
          确认密码
          <el-input v-model="form.confirm" show-password>
            <template #prefix>
              <el-icon class="el-input__icon"><lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
<!--        <el-form-item >-->
<!--          <el-select v-model="value"  placeholder="用户类型" style="margin-left: 40px;">-->
<!--            <el-option-->
<!--                v-for="item in options"-->
<!--                :key="item.value"-->
<!--                :label="item.label"-->
<!--                :value="item.value"/>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <!--        登录按钮-->
        <el-form-item>
          <el-button style="width: 50%; color: dodgerblue; font-size:16px;margin: 0 auto" @click="register">注册</el-button>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 50%; color: dodgerblue;font-size:16px; margin: 0 auto" @click="back">返回登录</el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>
</template>

<script>
import {UserFilled,Lock} from "@element-plus/icons";
import request from "@/utils/request";
import ElNotification from "element-plus";
import {h} from "@vue/runtime-core";

export default {
  name: "Register",
  components: {
    UserFilled,
    Lock
  },
  data(){
    return{
      form:{},
      rules:{
        studentName: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        pwd:[
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        confirm:[
          { required: true, message: '请确认密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    tips(data){
      this.$notify({
        title: '提示',
        message: h(
            'i',
            { style: 'color: teal' },
            '给您自动分配地登录账号为 ：'+data.studentId
        ),
        duration: 0,
      })
    },
    back(){
      this.$router.push("/login")//登录成功之后进行页面跳转，跳转到主页
    },
    register(){
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if(this.form.pwd !== this.form.confirm){
            this.$message({
              type:"error",
              message:"两次输入的密码不一致！"
            })
            return
          }
          console.log(delete this.form.confirm)
          console.log(this.form)
          request.post("/student/register",this.form).then(res => {
            if(res.code === '0'){
              this.$message({
                type:"success",
                message:"注册成功"
              })
              console.log(res)
              this.tips(res.data)
              this.$router.push("/login")//登录成功之后进行页面跳转，跳转到主页
            }else{
              this.$message({
                type:"error",
                message:res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.background{
  width:100%;
  height:100%;  /**宽高100%是为了图片铺满屏幕 */
  background: url('../assets/img/flower.jpg') center center ;
  z-index:-1;
  position: absolute;
}
</style>
