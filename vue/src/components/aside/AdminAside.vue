<template>
  <div v-wechat-title="$route.meta.title">
    <el-menu
        style="width: 200px; height:calc(100vh - 50px)"
        router
        active-text-color="#ffd04b"
        background-color="#545c64"
        text-color="#fff"
        class="el-menu-vertical-demo">
      <el-sub-menu index="1">
        <template #title>
          <el-icon><notebook /></el-icon>
          <span>考试管理</span>
        </template>
        <el-menu-item index="adminExamFunction">考试说明</el-menu-item>
        <el-menu-item index="adminExamManage">考试管理</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="2">
        <template #title>
          <el-icon><postcard /></el-icon>
          <span>题库管理</span>
        </template>
        <el-menu-item index="adminJudgeQuestion">判断题库</el-menu-item>
        <el-menu-item index="adminMultiQuestion">选择题库</el-menu-item>
        <el-menu-item index="adminFillQuestion">填空题库</el-menu-item>
        <el-menu-item index="adminAddQuestions">题库增加</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="3">
        <template #title>
          <el-icon><location /></el-icon>
          <span>试卷管理</span>
        </template>
        <el-menu-item index="adminSetPaper">试卷管理</el-menu-item>
        <!--        <el-menu-item index="addQuestions">添加题目</el-menu-item>-->
      </el-sub-menu>
      <el-sub-menu index="4" >
        <template #title>
          <el-icon><tickets /></el-icon>
          <span>成绩查询</span>
        </template>
        <el-menu-item index="adminScore">学生成绩查询</el-menu-item>
        <el-menu-item index="adminScorePart">成绩分段查询</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="5">
        <template #title>
          <el-icon><user /></el-icon>
          <span>学生管理</span>
        </template>
        <el-menu-item index="adminStudentManage">学生管理</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="6">
        <template #title>
          <el-icon><user-filled /></el-icon>
          <span>教师管理</span>
        </template>
        <el-menu-item index="teacherManage">教师管理</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="7">
        <template #title>
          <el-icon><avatar /></el-icon>
          <span>管理员管理</span>
        </template>
        <el-menu-item index="adminManage">管理员信息</el-menu-item>
      </el-sub-menu>
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
  name: "adminAside"
}
</script>

<style scoped>

</style>
