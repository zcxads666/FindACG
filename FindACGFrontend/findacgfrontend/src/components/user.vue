<template>
  <a-divider orientation="left">基础信息</a-divider>
  <a-row>
    <a-col :flex="2">
      <a-col :flex="2" class="center-content">
        用户名：{{ userName }}
      </a-col>
    </a-col>
    <a-col :flex="1">
      <a-avatar :size="{ xs: 24, sm: 32, md: 40, lg: 64, xl: 80, xxl: 100 }" :src="userAvatar" >
        <template #icon>
          <AntDesignOutlined v-if="!userAvatar" />
        </template>
      </a-avatar>
    </a-col>
  </a-row>

  <a-divider orientation="left">修改信息</a-divider>

  <!-- 表单部分 -->
  <a-form :model="formData" @submit.prevent="updateUserInfo">
    <a-form-item label="需更新用户名">
      <a-input v-model="formData.userName" placeholder="请输入用户名" />
    </a-form-item>

    <a-form-item label="头像网页地址">
      <a-input v-model="formData.userAvatar" placeholder="请输入头像 URL" />
    </a-form-item>

    <a-form-item label="我的个人简介">
      <a-textarea v-model="formData.userProfile" placeholder="请输入个人简介" rows="3" />
    </a-form-item>

    <a-form-item>
      <a-col :span="24" style="text-align: right;">
      <a-button type="primary" html-type="submit">提交修改</a-button>
      </a-col>
    </a-form-item>
  </a-form>

  <a-divider orientation="left" >注销账号</a-divider>
  <a-col :span="24" style="text-align: right;">
    <a-button danger type="primary" html-type="submit" @click="logoutUser">登出</a-button>
  </a-col>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { userData } from "../store/userData.js";
import axios from 'axios';
import router from "../router/index.js";

// 获取 store
const store = userData();
const { getLoginUser, loginUser } = store;

// 表单数据
const formData = ref({
  userName: '',
  userAvatar: '',
  userProfile: ''
});

// 使用计算属性来获取用户信息
const userName = computed(() => {
  return store.loginUser?.userName || '未获取';
});

const userAvatar = computed(() => {
  return store.loginUser?.userAvatar || '无头像';
});

// 页面加载时获取用户数据
onMounted(async () => {
  try {
    await getLoginUser();
    formData.value.userName = store.loginUser?.userName || '';
    formData.value.userAvatar = store.loginUser?.userAvatar || '';
    formData.value.userProfile = store.loginUser?.userProfile || '';
  } catch (error) {
    console.error('Error fetching user data:', error);
  }
});

// 更新用户信息的请求函数
const updateUserInfo = async () => {
  try {
    const response = await axios.post('http://localhost:8101/api/user/update', formData.value, {
      withCredentials: true
    });

    if (response.data.message === "ok") {
      // 更新成功后处理逻辑
      console.log('用户信息更新成功:', response.data);
      // 可以在这里更新本地 store 或者提示用户成功
      store.loginUser = { ...store.loginUser, ...formData.value };
      // 例如，更新页面上的信息
      alert('用户信息更新成功!');
    } else {
      console.error('更新失败:', response.data.message);
      alert('更新失败，请稍后再试');
    }
  } catch (error) {
    console.error('请求失败:', error);
    alert('请求失败，请稍后再试');
  }
};


const logoutUser = async () => {
  try {
    console.log("正在调用注销功能...");
    await store.LogoutUser();
    console.log("注销成功！");
  } catch (error) {
    console.error("注销失败:", error);
  }
};
</script>

<style scoped>
.center-content {
  font-size: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>
