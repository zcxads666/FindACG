<template>
  <div class="register-container">
    <a-card :bordered="false" style="width: 300px; opacity: 0.9" class="card">
      <h1>注册</h1>
      <a-form @submit="onSubmit">
        <a-form-item>
          <a-input v-model:value="username" placeholder="用户名" />
        </a-form-item>
        <a-form-item>
          <a-input-password v-model:value="password" placeholder="密码" />
        </a-form-item>
        <a-form-item>
          <a-input-password v-model:value="confirmPassword" placeholder="确认密码" />
        </a-form-item>
        <a-form-item>
          <a-space :size=64>
          <a-button type="primary" html-type="submit">注册</a-button>
          <a-button type="link" @click="router.push('/login')">已有账号？去登录</a-button>
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
import { message } from "ant-design-vue";

const router = useRouter();
const username = ref('');
const password = ref('');
const confirmPassword = ref('');

async function onSubmit() {
  if (password.value !== confirmPassword.value) {
    message.error('密码和确认密码不一致');
    return;
  }

  try {
    // 这里假设你有一个注册 API
    const response = await axios.post('http://127.0.0.1:8101/api/user/register', { userAccount: username.value, userPassword: password.value ,checkPassword: confirmPassword.value});
    if(response.data.message === "ok") {
      message.success('注册成功');
      await router.push('/login'); // 跳转到登录页面
    }
    else {throw new Error(response.data.message);}
  } catch (error) {
    message.error('注册失败'+error);
    console.error('注册失败，请检查输入信息', error);
  }
}
</script>

<style scoped>
.register-container {
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
