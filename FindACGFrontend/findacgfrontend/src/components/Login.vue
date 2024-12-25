<template>
  <div class="login-container">
    <a-card  :bordered="false" style="width: 300px ; opacity: 0.9" class="card">
    <h1>登 录</h1>
    <a-form @submit="onSubmit">
      <a-form-item>
        <a-input v-model:value="username" placeholder="用户名" />
      </a-form-item>
      <a-form-item>
        <a-input-password v-model:value="password" placeholder="密码" />
      </a-form-item>
      <a-form-item>
        <a-space :size=110>

        <a-button type="primary" :loading="iconLoading" @click="enterIconLoading" html-type="submit">登录</a-button>

        <a-button type="primary" @click="goToRegister">去注册</a-button>
        </a-space>
      </a-form-item>
    </a-form>

    </a-card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from "axios";
import {message} from "ant-design-vue";

const router = useRouter();
const username = ref('');
const password = ref('');

async function onSubmit() {
  try {

    const response = await axios.post('http://localhost:8101/api/user/login', { userAccount: username.value, userPassword: password.value },
        {
      withCredentials: true // 确保POST请求携带Cookie
    });
    if(response.data.message === "ok") {
      await router.push('/'); // 跳转到首页
      message.success('登录成功')

    }
    else {throw new Error(response.data.message);}
  } catch (error) {
    message.error('登录失败，请检查用户名和密码');
    console.error('登录失败，请检查用户名和密码');
  }
}

const iconLoading = ref(false);
const enterIconLoading = () => {
  iconLoading.value = {
    delay: 100,
  };
  setTimeout(() => {
    iconLoading.value = false;
  }, 1000);
};


const goToRegister = () => {
  router.push('/register'); // 跳转到注册页面
};


</script>

<style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 900px;
  padding: 20px;
  background-image: url('../assets/bg1.png');
  background-size: cover; /* 使背景图片覆盖整个容器 */
  background-position: center; /* 使背景图片居中 */
  background-repeat: no-repeat; /* 防止背景图片平铺 */

}

.card {
  background-color: rgba(255, 255, 255, 0.5);
}

h1 {
  text-align: center; /* 确保 h1 文字水平居中 */
  margin-bottom: 20px; /* 添加一些底部外边距 */
}
</style>
