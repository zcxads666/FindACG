import { createRouter, createWebHistory } from 'vue-router';
import Login from '../components/Login.vue';
import Register from '../components/Register.vue';
import Home from '../components/Home.vue';
import { message } from "ant-design-vue";
import axios from "axios";

const routes = [
    { path: '/', redirect: '/home/search' }, // 设置默认重定向到 Home
    {
        path: '/home',
        name: 'Home',
        component: Home,
        meta: { requiresAuth: true },
        children: [
            {
                path: 'search',
                name: 'Search',
                component: () => import('../components/search.vue'),
                meta: { requiresAuth: true },
                children: [
                    {
                        path: 'all',
                        name: 'All',
                        component: () => import('../components/All.vue'),
                        meta: { requiresAuth: true },
                    },
                    {
                        path: 'wiki',
                        name: 'Wiki',
                        component: () => import('../components/Wiki.vue'),
                        meta: { requiresAuth: true },
                    },
                    {
                        path: 'user',
                        name: 'UserSearch',
                        component: () => import('../components/userSearch.vue'),
                        meta: { requiresAuth: true },
                    },
                    {
                        path: 'novel',
                        name: 'Novel',
                        component: () => import('../components/Novel.vue'),
                        meta: { requiresAuth: true },
                    },
                ],
            },
            { path: 'discuss', component: () => import('/src/components/discuss.vue'), meta: { requiresAuth: true } },
            { path: 'user', component: () => import('/src/components/user.vue'), meta: { requiresAuth: true } },
        ],
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
    },
    {
        path: '/register',
        name: 'Register',
        component: Register,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

async function checkAuthStatus() {
    try {
        const response = await axios.get('http://localhost:8101/api/user/get/login',{
            withCredentials: true
        });

        console.log(response.data);
        return response.data.message === "ok"
    } catch (error) {
        console.error('Error checking auth status:', error);
        return false;
    }
}

router.beforeEach(async (to, from, next) => {
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
    const publicRoutes = ['/login', '/register'];

    if (publicRoutes.includes(to.path)) {

        next();
    } else if (requiresAuth) {
        const isAuthenticated = await checkAuthStatus();
        if (!isAuthenticated) {
            message.error('请先登录');
            next({ name: 'Login' });
        } else {
            next();
        }
    } else {

        next();
    }
});

export default router;
