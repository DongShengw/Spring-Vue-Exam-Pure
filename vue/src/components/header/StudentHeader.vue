<template>
  <div style="height: 60px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
    <img style="height: 50px;width: 50px;margin-top: 3px;margin-left: 50px" src="../../assets/img/stars.jpg">
    <div style="width: 200px; padding-left: 10px;margin-top: 8px; font-weight: bold; color: dodgerblue">{{ this.$cookies.get("name") }}</div>
    <div style="flex: 1;">
      <el-menu
          class="el-menu-demo"
          mode="horizontal"
          router
          style="height: 60px"
      >
        <el-menu-item index="studentIndex">我的考试</el-menu-item>
        <el-menu-item index="scoreTable">我的成绩</el-menu-item>
      </el-menu>
    </div>
    <div style="width: 100px;text-align: center; padding-top: 15px" >
      <el-dropdown>
      <span class="el-dropdown-link">
        {{ this.$cookies.get("data").studentName }}
        <el-icon class="el-icon--right">
          <arrow-down />
        </el-icon>
      </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="toPerson()">个人中心</el-dropdown-item>
            <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { ArrowDown } from '@element-plus/icons'
export default {
  name: "Header",
  components: {
    ArrowDown
  },
  methods:{
    logout(){
      this.$cookies.remove("data")
      this.$router.push("/login")
    },
    toPerson(){
      this.$router.push("/studentInfo")
    }
  }
}
</script>

