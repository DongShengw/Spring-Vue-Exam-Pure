//显示学生成绩
<template>
  <div v-wechat-title="$route.meta.title" class="table">
    <p class="title">我的分数</p>
    <!--表格-->
    <el-table
        :data="tableData"
        stripe
        height="600"
        style="width: 100%; text-align: center"
        border>
      <el-table-column prop="subject" label="考试科目" ></el-table-column>
      <el-table-column prop="answerDate" label="考试日期" sortable></el-table-column>
      <el-table-column prop="etScore" label="分数" ></el-table-column>
      <el-table-column label="是否及格" >
<!--        <el-tag :type="tableData.etScore>= 60 ? 'success' : 'danger'">{{tableData.etScore >= 60 ? "及格" : "不及格"}}</el-tag>-->
        <template #default="scope">
          <el-tag
              :type="scope.row.etScore >= 60 ? 'success' : 'danger'"
              disable-transitions
          >{{ scope.row.etScore >= 60 ? "及格" : "不及格"}}</el-tag
          >
        </template>
      </el-table-column>
      <!--      -->
      <!--      <el-table-column label="操作">-->
      <!--        <template #default="scope">-->
      <!--          <el-button @click="handleEdit(scope.row)">编辑</el-button>-->
      <!--          <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.id)">-->
      <!--            <template #reference>-->
      <!--              <el-button type="danger">删除</el-button>-->
      <!--            </template>-->
      <!--          </el-popconfirm>-->
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
import request from "@/utils/request";

export default {
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
    this.loading = true //数据加载则遮罩表格
  },
  methods: {
    load() {
      let studentId = this.$cookies.get("data").studentId
      request.get("/score/"+studentId,{
        params:{
          pageNum:this.currentPage4,
          pageSize:this.pageSize4,
        }
      }).then(res => {
        console.log(res)
        if(res.code == '0') {
          this.tableData = res.data.records
          this.total = res.data.total
          this.loading = false //数据加载完成去掉遮罩
          //
          // this.score = res.data.records[0].etScore
          // this.pagination = {...res.data.records[0]}
          // let mapVal = this.score.map((element,index) => { //通过map得到 filter:[{text,value}]形式的数组对象
          //   let newVal = {}
          //   newVal.text = element.answerDate
          //   newVal.value = element.answerDate
          //   return newVal
          // })
          // let hash = []
          // const newArr = mapVal.reduce((item, next) => { //对新对象进行去重操作
          //   hash[next.text] ? '' : hash[next.text] = true && item.push(next);
          //   return item
          // }, []);
          // this.filter = newArr
        }
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
    formatter(row, column) {
      return row.address;
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    }
  }
};
</script>

<style lang="scss" scoped>
.pagination {
  padding-top: 20px;
}
.table {
  width: 980px;
  margin: 0 auto;
  .title {
    margin: 20px;
  }
  .content-el {
    background-color: #fff;
    padding: 20px;
  }
}
</style>
