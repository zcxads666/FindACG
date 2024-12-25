<template>
  <a-list item-layout="horizontal" :data-source="data" :pagination="pagination">
    <template #renderItem="{ item }">
      <a-list-item>
        <a-list-item-meta :description="item.userProfile">
          <template #title>
            {{ item.userAccount }}
          </template>
          <template #avatar>
            <a-avatar src="https://joeschmoe.io/api/v1/random" />
          </template>
        </a-list-item-meta>
      </a-list-item>
    </template>
  </a-list>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useCounterStore } from "../store/searchData.js";

const store = useCounterStore();
const data = ref([]);

// 监听 store.dataItem 更新，更新搜索结果
watch(
    () => store.dataItem,
    (newData) => {
      data.value = newData;
      console.log("dataItem updated:", newData);
    },
    {immediate: true}
);

// 分页处理
// const pagination = {
//   onChange: (page) => {
//     console.log('Page changed to:', page);
//
//     // 获取分页数据
//     const params = {
//       searchType: "",
//       searchText: store.getSearchText(),  // 从 store 中获取当前的搜索值
//       page: page,
//       pageSize: pagination.pageSize
//     };
//     store.getSearchList(params);
//   },
//   pageSize: 10,
// };
</script>

