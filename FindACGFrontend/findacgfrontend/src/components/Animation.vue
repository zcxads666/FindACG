<template>
  <a-list item-layout="horizontal" :data-source="data" :pagination="pagination">
    <template #renderItem="{ item }">
      <a-list-item>
        <a-list-item-meta
            :description="item.title"
        >

          <template #title>
            {{ item.id }}

          </template>
          <template #avatar>
            <a-avatar src="https://joeschmoe.io/api/v1/random" />
          </template>
        </a-list-item-meta>
      </a-list-item>
    </template>
    <template>
      <a-pagination v-model:current="current" :total="10" show-less-items />
    </template>
  </a-list>

</template>


<script setup>
import { onMounted, ref ,watchEffect} from 'vue';
import { useRoute } from 'vue-router';
import { useCounterStore } from "../store/searchData.js";

const store = useCounterStore();

console.log(JSON.stringify(store.dataItem));


const route = useRoute();
let search = ref('');
onMounted(() => {
  search = route.query.search;
  console.log("search", search);
  store.getData()
});
watchEffect(() => {
  if (route.query.search) {
    search = route.query.search;
    console.log("search:", search);
  }
});
const data = ref(store.dataItem);
// const data = [
//   {
//     title: 'Ant Design Title 1',
//   },
//   {
//     title: 'Ant Design Title 2',
//   },
//   {
//     title: 'Ant Design Title 3',
//   },
//   {
//     title: 'Ant Design Title 4',
//   },
// ];

const current = ref(1);

const pagination = {
  onChange: page => {
    console.log(page);
  },
  pageSize: 10,
};

</script>