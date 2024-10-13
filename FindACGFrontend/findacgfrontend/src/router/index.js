import { createRouter, createWebHistory } from 'vue-router';

const routes = [
    { path: '/', redirect: '/search' }, // 设置默认重定向
    {  path: '/search',
        name: 'Search',
        component: () => import('/src/components/search.vue'),
        children: [
            {
                path: 'animation:search?',
                name: 'animation',
                component: () => import('/src/components/Animation.vue')
            },
            {
                path: 'game',
                name: 'Game',
                component: () => import('/src/components/discuss.vue')
            },
            {
                path: 'comics',
                name: 'Comics',
                component: () => import('/src/components/discuss.vue')
            },
            {
                path: 'novel',
                name: 'novel',
                component: () => import('/src/components/discuss.vue')
            }
        ]},
    { path: '/discuss', component: () => import('/src/components/discuss.vue') },
    { path: '/user', component: () => import('/src/components/user.vue') },

];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
