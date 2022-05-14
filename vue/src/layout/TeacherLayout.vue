<template>
  <div>
    <!--    头部-->
    <Header />
    <!--    主体-->
    <div style="display: flex">
      <!--      侧边栏-->
      <TeacherAside />
      <!--      内容区域home-->
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<script>
import Aside from "@/components/aside/TeacherAside";
import Header from "@/components/header/Header";
import TeacherAside from "@/components/aside/TeacherAside";

export default {
  name: "TeacherLayout",
  components:{
    TeacherAside,
    Header
  }
}
</script>

<style scoped>

</style>
