<template>
    <div class="notice">
        <Header />
        <table class="table">
            <thead>
                <tr class="table_tr">
                    <th>번호</th>
                    <th>제목</th>
                    <th>카테고리</th>
                    <th>글쓴이</th>
                    <th>날짜</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="notice_item in notice_list" v-bind:key="notice_item.id" class="table_tr">
                    <td>{{ notice_item.head }}</td>
                    <td>{{ notice_item.title }}</td>
                    <td>{{ notice_item.category }}</td>
                    <td>{{ notice_item.author }}</td>
                    <td>{{ notice_item.date }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from 'axios'
import Header from '../common/Header.vue'

export default {
    name: 'NoticeBoard',
    components: {
        Header
    },
    data() {
        return {
            notice_list: []
        }
    },
    created() {
        console.log("Created");
    },
    methods: {
        getData : function() {
        axios.get("/api/hello", {
            params: {
                username: "admin"
            }
        })
        .then((response) => {
            console.log(response.data);
            this.notice_list = response.data;
        })
        },
        dataReset : function() {
        this.notice_list = [];
        },
    },
    beforeMount(){
        console.log("beforeMount");
    },
    mounted(){
        console.log("mounted");
        this.getData(); // 크롤링 비동기 처리
    },
    beforeUpdate(){
        console.log("beforeUpdate");
    },
    updated(){
        console.log("updated");
    }
}
</script>

<style>
table {
    margin: auto;
    width: 75%;
    text-align : left;  

}
td {
    font-size: 130%;
    padding-bottom: 2%;  
}
table th{
    padding : 12px;
    border-bottom: 2px solid  darkgray;
}
table td{
    padding : 12px;
}
table tr:nth-of-type(even){
    background-color: rgba(17, 199, 0, 0.1);
}
</style>