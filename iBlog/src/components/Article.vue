<template>
  <div class="cantainer">
    <el-row>
      <el-input
        placeholder="请输入内容"
        v-model="search_input" >
        <i slot="suffix" class="el-input__icon el-icon-search" @click="qryArticleList()"></i>
      </el-input>
    </el-row>
    <el-table style="width: 100%;"
              :data="dataList">
      <el-table-column type="index" width="50">
      </el-table-column>
      <el-table-column label="标题" prop="title" width="180">
      </el-table-column>
      <el-table-column label="作者" prop="author" width="180">
      </el-table-column>
      <el-table-column label="日期" prop="createTime" width="180">
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5, 10, 20, 40]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalCount">
    </el-pagination>
  </div>
</template>

<script>/* eslint-disable */
  export default {
    name: 'Article',
    data () {
      return {
        currentPage:1,
        pageSize:10,
        totalCount:0,
        dataList: [],
        search_input:"",
      }
    },
    created() {
      this.handleCurrentChange(this.currentPage);
    },
    methods: {
      handleSizeChange: function (pageSize) {
        this.pageSize = pageSize;
        this.currentPage = 1;
        this.handleCurrentChange(this.currentPage);
      },
      handleCurrentChange: function(currentPage){
        this.currentPage = currentPage;
        this.$axios({
          url: this.global.server_config.url+'/qryArticleList',
          method:'post',
          data:JSON.stringify({currentPage: currentPage,pageSize:this.pageSize}),
          headers:{'Content-Type':'application/json'}
        }).then(response => {
          this.dataList = response.data.data.items;
          this.totalCount = response.data.data.totalNum;
        })
      },
      qryArticleList:function () {
        alert(this.search_input)
      }
    }
  }
</script>
