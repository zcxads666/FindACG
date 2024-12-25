import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from "axios";

export const useCounterStore = defineStore('counter', () => {
    const count = ref(0);
    const increment = () => {
        count.value++;
    };

    const dataItem = ref([]);
    const searchText = ref('');  // 新增：存储当前的搜索文本

    const getData = async () => {
        // 发送 GET 请求
        try {
            const response = await axios.get('https://jsonplaceholder.typicode.com/posts');
            const dataArray = response.data;
            dataItem.value = response.data;
            dataArray.forEach(item => {
                console.log('---');
            });
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    const getUserList = async (params) => {
        // 定义请求参数

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

    const getSearchList = async (params) => {
        try {
            // 发送 POST 请求
            const response = await axios.post('http://127.0.0.1:8101/api/FingAny/doFindAny', params);

            // 检查 response.data.data 是否是 JSON 字符串
            let dataArray = response.data.data;
            if (typeof dataArray === 'string') {
                dataArray = JSON.parse(dataArray);
            }

            // 确保 dataArray 是数组
            if (!Array.isArray(dataArray)) {
                throw new Error('Data is not an array');
            }

            dataItem.value = dataArray; // 直接赋值解析后的数组
            dataArray.forEach(item => {
                const { Id, Title, Content, Tags } = item;
                console.log('---');
                console.log('Id:', Id);
                console.log('Title:', Title);
                console.log('Content:', Content);
                console.log('Tags:', Tags);
            });
        } catch (error) {
            console.error('Error fetching search list:', error);
        }
    };

    // 新增：清空数据
    const clearData = () => {
        dataItem.value = [];
    };

    // 新增：获取当前搜索文本
    const getSearchText = () => {
        return searchText.value;
    };

    // 新增：设置搜索文本
    const setSearchText = (text) => {
        searchText.value = text;
    };

    return {
        count,
        dataItem,
        increment,
        getData,
        getUserList,
        getSearchList,
        clearData,
        getSearchText,
        setSearchText
    };
});
