<template>
  <div v-wechat-title="$route.meta.title">
    <el-menu
        style="width: 200px; height:calc(100vh - 50px)"
        active-text-color="#ffd04b"
        background-color="#545c64"
        text-color="#fff"
        router
        class="el-menu-vertical-demo">
      <el-sub-menu index="1">
        <template #title>
          <el-icon><grid /></el-icon>
          <span>考试管理</span>
        </template>
        <el-menu-item index="examFunction">功能介绍</el-menu-item>
        <el-menu-item index="examManage">考试管理</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="2">
        <template #title>
          <el-icon><fold /></el-icon>
          <span>题库管理</span>
        </template>
        <el-menu-item index="judgeQuestion">判断题库</el-menu-item>
        <el-menu-item index="multiQuestion">选择题库</el-menu-item>
        <el-menu-item index="fillQuestion">填空题库</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="3">
        <template #title>
          <el-icon><location /></el-icon>
          <span>组卷管理</span>
        </template>
        <el-menu-item index="setPaper">设置试卷</el-menu-item>
<!--        <el-menu-item index="addQuestions">添加题目</el-menu-item>-->
      </el-sub-menu>
      <el-sub-menu index="4" >
        <template #title>
          <el-icon><school /></el-icon>
          <span>成绩查询</span>
        </template>
        <el-menu-item index="score">学生成绩查询</el-menu-item>
        <el-menu-item index="scorePart">成绩分段查询</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="5">
        <template #title>
          <el-icon><user /></el-icon>
          <span>学生管理</span>
        </template>
        <el-menu-item index="studentManage">学生管理</el-menu-item>
      </el-sub-menu>
<!--      <el-sub-menu index="6">-->
<!--        <template #title>-->
<!--          <el-icon><location /></el-icon>-->
<!--          <span>学生测试</span>-->
<!--        </template>-->
<!--        <el-menu-item index="index">学生</el-menu-item>-->
<!--      </el-sub-menu>-->
    </el-menu>
  </div>
</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>
