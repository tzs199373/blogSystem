<template>
  <div id="app">
    <el-container class="my-container">
      <el-header class="my-header">
        <span style="color: aliceblue;font-size: x-large">iBlog</span>
      </el-header>
      <el-container>
        <el-aside>
          <el-menu :default-active="defaultActive" style="height: 100%">
            <el-menu-item index="" @click="redirectUrl('/article')">
              <i class="el-icon-menu"></i>
              <span slot="title">我的文章</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main class="my-main">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>/* eslint-disable */
export default {
  name: 'app',
    data () {
        return {
        defaultActive: this.$route.name
      }
    },
    methods: {
      redirectUrl(val){
        this.$router.push(val);
      }
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    position: absolute;
    top: 0;
    left: 0;
    bottom: 0;
    width: 100%;
  }
  .my-container {
    margin: 0 auto;
    position: absolute;
    width: 100%;
    height: 100%;
  }
  .my-header {
    background-color: deepskyblue;
    height: 20px;
    font-size: large;
    display: flex;
    justify-content:center;
    align-items:center;
  }
  .my-main {
    margin: 1em 1em;
  }
</style>
