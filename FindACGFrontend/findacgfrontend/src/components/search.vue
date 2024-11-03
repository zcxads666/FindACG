<template>
  <div class="container">
    <div class="search-container">
      <h1>Search</h1>
    </div>

    <div class="search-container">
      <a-input-search
          v-model:value="value"
          placeholder="Search here"
          enter-button
          @search="onSearch"
          style="width: 70%"
      />
    </div>

    <a-tabs v-model:activeKey="activeKey" @tab-click="handleMenuClick">
      <a-tab-pane key="1" tab="Animation"><router-view /></a-tab-pane>
      <a-tab-pane key="2" tab="Game" force-render><router-view /></a-tab-pane>
      <a-tab-pane key="3" tab="Comics"><router-view /></a-tab-pane>
      <a-tab-pane key="4" tab="Novel"><router-view /></a-tab-pane>
    </a-tabs>
  </div>
</template>

<script setup>
import { onMounted, ref, watchEffect } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';

const value = ref('');
const activeKey = ref('');

// 初始化输入框值
onMounted(() => {
  const route = useRoute();
  if (route.query.search) {
    value.value = route.query.search;
  }
});

// 监听路由变化
const router = useRouter();


watchEffect(() => {
  if (value.value) {
    // 无需此赋值
  }
});


const onSearch = async (searchValue) => {
  console.log('use value', searchValue);
  console.log('or use this.value', value.value);

  // 更新路由
  // router.push({
  //   query: { search: value.value }
  // });
  try {
    // 使用 await 确保导航完成后再进行下一步操作
    await router.push({
      //name: 'YourRouteName', // 替换为实际路由名称
      query: { search: value.value }// 根据需要传递参数
    });
    console.log('Navigation completed.');
  } catch (error) {
    console.error('Navigation failed:', error);
  }

};

const handleMenuClick = (pane) => {
  console.log(pane);

  const key = pane;
  let path;

  if (key === '1') {
    path = '/animation';
  } else if (key === '2') {
    path = '/game';
  } else if (key === '3') {
    path = '/comics';
  } else if (key === '4') {
    path = '/novel';
  }

  console.log(value.value);

  // 添加查询参数
  router.push({
    path: `/search${path}`,
    query: {search: value.value}
  });
};
</script>

<style scoped>
.search-container {
  display: flex;
  align-items: center;
  flex-direction: column;
}

.container {
  height: 1000px;
}
</style>
