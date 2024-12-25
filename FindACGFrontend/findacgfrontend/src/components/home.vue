<template>
  <a-layout class="layout">
    <a-layout-header>
      <div class="logo" />
      <a-menu
          v-model:selectedKeys="selectedKeys"
          theme="dark"
          mode="horizontal"
          :style="{ lineHeight: '64px' }"
          @click="handleMenuClick"
      >
        <a-menu-item key="1">搜索</a-menu-item>
        <a-menu-item key="2">讨论</a-menu-item>
        <a-menu-item key="3">用户</a-menu-item>
      </a-menu>
    </a-layout-header>
    <a-layout-content  style="padding: 0 50px; height: 500%;">

      <div :style="{ background: '#fff', padding: '24px', minHeight: '280px' }">

        <router-view  style="height: 1000px;"/>

      </div>
    </a-layout-content>
    <a-layout-footer style="text-align: center">
      ©2024 Created by zcxads
    </a-layout-footer>
  </a-layout>
</template>



<script setup>
import {ref, onMounted} from 'vue';
import {useRouter} from "vue-router";
import { useCounterStore } from "../store/searchData.js";

const store = useCounterStore();
console.log(store.count);
store.increment();
console.log(store.count);

const selectedKeys = ref(['1']);

const handleMenuClick = (event) => {
  const key = event.key;
  if (key === '1') {
    router.push('/home/search');
  } else if (key === '2') {
    router.push('/home/discuss');
  } else if (key === '3') {
    router.push('/home/user');
  }
};

const router = useRouter();




</script>




<style scoped>
.site-layout-content {
  min-height: 280px;
  padding: 24px;
  background: #fff;
}

#components-layout-demo-top .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

[data-theme='dark'] .site-layout-content {
  background: #141414;
}
</style>