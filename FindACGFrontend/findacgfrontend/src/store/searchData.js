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

    const getUserList = async () => {
        // 定义请求参数
        const params = {
            userName: ""
        };

        try {
            // 发送 POST 请求
            const response = await axios.post('http://127.0.0.1:8101/api/user/list/page', params);
            const dataArray = response.data.data.records;
            dataItem.value = response.data.data.records;
            dataArray.forEach(item => {
                console.log('---');
            });
        } catch (error) {
            console.error('Error fetching user list:', error);
        }
    };


    return {
        count,
        dataItem,
        increment,
        getData,
        getUserList,
    };
});
