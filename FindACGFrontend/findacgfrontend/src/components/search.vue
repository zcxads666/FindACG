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
      <a-tab-pane key="All" tab="All"><router-view /></a-tab-pane>
      <a-tab-pane key="novel" tab="Novel" force-render><router-view /></a-tab-pane>
      <a-tab-pane key="anime" tab="Wiki"><router-view /></a-tab-pane>
      <a-tab-pane key="user" tab="User"><router-view /></a-tab-pane>
    </a-tabs>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useCounterStore } from "../store/searchData.js";

const store = useCounterStore();
const router = useRouter();
const route = useRoute();

const value = ref('');
const activeKey = ref('');

// 初始页面加载时根据路由参数获取数据
onMounted(() => {
  if (route.query.search) {
    value.value = route.query.search;
    // const params = {
    //   searchType: "",
    //   searchText: value.value
    // };
    // store.getSearchList(params);
  }
});

// 搜索事件处理
const onSearch = async (searchValue) => {
  console.log('Search triggered with value:', searchValue);

  // 清空当前数据
  store.clearData();

  // 更新路由
  try {
    await router.push({
      query: { search: searchValue }
    });

    await fetchData();


    console.log('Navigation completed.');
  } catch (error) {
    console.error('Navigation failed:', error);
  }
};

// Tab 菜单切换事件处理
const handleMenuClick = (pane) => {
  console.log(pane);

  const key = pane;
  let path;

  if (key === 'All') {
    path = '/all';
  } else if (key === 'novel') {
    path = '/all';
  } else if (key === 'anime') {
    path = '/all';
  } else if (key === 'user') {
    path = '/user';
  }

  console.log(value.value);
  store.clearData();
  // 添加查询参数并更新路由
  router.push({
    path: `/home/search${path}`,
    query: { search: value.value }
  });
};


// 根据 activeKey 获取数据
const fetchData = async () => {


  if (activeKey.value === 'user') {
    const param = {
      userName: value.value
    };
    console.log('Fetching user list with params:', param);
     await store.getUserList(param);
  }
  else {
    const params = {
      searchType: activeKey.value !== 'All' ? activeKey.value : "",
      searchText: value.value
    };
    console.log('Fetching search list with params:', params);
    await store.getSearchList(params);
  }
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
