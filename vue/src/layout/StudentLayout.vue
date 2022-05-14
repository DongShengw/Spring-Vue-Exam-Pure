<template>
  <div>
    <!--    头部-->
    <StudentHeader />
    <!--    主体-->
    <div>
      <!--      内容区域home-->
      <router-view/>
    </div>
  </div>
</template>

<script>
import Aside from "@/components/aside/TeacherAside";
import Header from "@/components/header/Header";
import StudentHeader from "@/components/header/StudentHeader";

export default {
  name: "StudentLayout",
  components:{
    StudentHeader
  }
}
</script>

<style scoped>

</style>
