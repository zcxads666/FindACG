import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import axios from "axios";
import router from "../router/index.js";

export const userData = defineStore('login', () => {
    const loginUser = ref({});

    const getLoginUser = async () => {
        try {
            const response = await axios.get('http://localhost:8101/api/user/get/login', {
                withCredentials: true
            });
            if (response.data.message === "ok") {
                loginUser.value = response.data.data; // 直接更新 loginUser
                console.log('登录用户数据:', loginUser.value);
            } else {
                console.error('Failed to get login user:', response.data.message);
            }
        } catch (error) {
            console.error('Error fetching login user:', error);
        }
    };

    const userName = computed(() => {
        return loginUser.value.userName || '未获取';  // 使用直接访问
    });

    const userAvatar = computed(() => {
        return loginUser.value.userAvatar || '无名氏';
    });






    const LogoutUser = async () => {
        try {
            const response = await axios.post('http://localhost:8101/api/user/logout', {}, {
                withCredentials: true
            });

            if (response.data.message === "ok") {
                // 注销成功后清理本地用户信息
                console.log('注销成功');

                // 跳转到登录页面或首页
                await router.push('/login');
            } else {
                console.error('注销失败:', response.data.message);
                alert('注销失败，请稍后再试');
            }
        } catch (error) {
            console.error('请求失败:', error);
            alert('请求失败，请稍后再试');
        }
    };





    return {
        loginUser,
        getLoginUser,
        userName,
        userAvatar,
        LogoutUser
    };
});
