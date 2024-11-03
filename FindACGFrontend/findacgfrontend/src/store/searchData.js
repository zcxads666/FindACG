import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from "axios";

export const useCounterStore = defineStore('counter', () => {
    const count = ref(0);
    const increment = () => {
        count.value++;
    };

    const dataItem =  ref([]);

    const getData = async () => {
        // 发送 GET 请求
        try {
            const response = await axios.get('https://jsonplaceholder.typicode.com/posts');
            const dataArray = response.data;
            dataItem.value = response.data;
            dataArray.forEach(item => {
                //console.log('User ID:', item.userId);
                //console.log('ID:', item.id);
                //console.log('Title:', item.title);
                //console.log('Body:', item.body);
                console.log('---');
            });
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };


    return {
        count,
        dataItem,
        increment,
        getData
    };
});
