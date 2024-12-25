<template>
  <a-list item-layout="horizontal" :data-source="data" :pagination="pagination">
    <template #renderItem="{ item }">
      <a-list-item>
        <a-list-item-meta
            :description="item.Description"
        >
          <template #title>
            {{ item.Title }}
          </template>
          <template #avatar>
            <a-avatar v-if="item.Tags && item.Tags.includes('qiDian')" src="https://imgservices-1252317822.image.myqcloud.com/coco/s11022022/43dddf20.il61if.svg" style="margin-left: 10px;" />
          </template>
        </a-list-item-meta>
        <a :href="item.Content" target="_blank">点击跳转</a>

      </a-list-item>
    </template>



  </a-list>

</template>

<script setup>
import { onMounted, ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import { useCounterStore } from "../store/searchData.js";

const store = useCounterStore();

const route = useRoute();
const search = ref('');
const data = ref([]);
const current = ref(1);
const total = ref(10);

onMounted(() => {
  search.value = route.query.search;
  console.log("search", search.value);
  if (search.value) {
    const params = {
      searchType: "anime",
      searchText: search.value
    };
    store.getSearchList(params);
  }
});

watch(
    () => store.dataItem,
    (newData) => {
      data.value = newData;
      console.log("dataItem updated:", newData);
    },
    { immediate: true }
);

const pagination = {
  onChange: page => {
    console.log(page);
  },
  pageSize: 10,
};
</script>
