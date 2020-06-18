<template>
    <div>
        <h1 style="margin-left:3rem;">* 게시물 관리</h1>
        <CompetitionList></CompetitionList>
        <h1 style="margin-left:3rem; margin-top:3rem">* 회원 관리</h1>
        <UserList></UserList>
        <h1 style="margin-left:3rem; margin-top:3rem">* 공지사항 관리</h1>
        <AdminNotice></AdminNotice>
    </div>
</template>
<script>
import CompetitionList from './CompetitionList.vue'
import UserList from './UserList.vue'
import AdminNotice from './AdminNotice.vue'
export default {
    name: 'AdminPage',
    components: {
        CompetitionList,
        UserList,
        AdminNotice
    },
    data() {
        return {

        }
    },
    methods: {

    },
    watch: {

    }
}
</script>