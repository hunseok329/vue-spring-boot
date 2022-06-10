<template>
    <div>
        <h2>마이페이지</h2>
        <div>{{ username }}</div>
        <p>공지사항 목록</p>
        <div class="noticeList">
            <div>
                <span>선택된 공지사항</span>
                <div v-for="notice, index in noticeList" v-bind:key="notice.id">
                    <span>{{ notice }}</span>
                    <i v-on:click="deleteItem(index)">X</i>
                </div>
                <div class="scroll-wrapper">
                    <label>대학</label>
                    <vue-custom-scrollbar class="scroll-area"  :settings="settings">
                        <ul>
                            <li  v-for="item, index in this.$store.state.noticeBoard" v-bind:key='item.id'>
                                <span v-on:click="select(index)">{{ item.name }}</span>
                            </li>
                        </ul>
                    </vue-custom-scrollbar>
                </div>
                <div class="scroll-wrapper">
                    <label>학사-학과</label>
                    <vue-custom-scrollbar class="scroll-area"  :settings="settings">
                        <ul>
                            <li v-for="notice in noticeBoardList" v-bind:key="notice.id">
                                <span v-on:click="addList(notice)">{{ notice }}</span>
                            </li>
                        </ul>
                    </vue-custom-scrollbar>
                </div>
            </div>
            <button v-on:click="onSubmit()">test button</button>  
        </div>
  </div>
</template>

<script>
import axios from 'axios'
import vueCustomScrollbar from 'vue-custom-scrollbar'
import "vue-custom-scrollbar/dist/vueScrollbar.css"

export default {
    name: 'MyPage',
    components: {
        vueCustomScrollbar
    },
    data() {
        return {
            username: this.$store.getters.getUserName,
            noticeList: [],
            noticeBoardList: [],
            settings: {
                suppressScrollY: false,
                suppressScrollX: false,
                wheelPropagation: true,
            },
            modifyCheck: true
        }
    },
    methods: {
        getData : function() {
            axios.get('/api/mypage', {
                params: {
                    username: this.$store.getters.getUserName
                }
            })
            .then((response) => {
                this.noticeList = response.data
                console.log(response);
            })
            console.log("MyPage test log")
        },
        select : function(index) {
            this.noticeBoardList = this.$store.state.noticeBoard[index].department
        },
        addList: function(notice) {
            if (this.noticeList.indexOf(notice) == -1) {
                this.noticeList.push(notice)
            }  
        },
        deleteItem: function(index) {
            this.noticeList.splice(index, 1)
        },
        onSubmit: function() {
            console.log("test onSubmit")
            const fd = new FormData()
            fd.append('username', this.username)
            fd.append('noticeList', this.noticeList)
            try {
                axios.post('/api/modify', fd).then((response) => {
                    console.log(response)
                })
            } catch (error) {
                throw new Error(error)
            }
        }
    },
    mounted() {
        this.getData();
    }
}
</script>

<style>
.scroll-wrapper {
    display: inline-block;
}
.scroll-area {
    position: relative;
    margin: auto;
    width: 400px;
    height: 200px;
}
</style>