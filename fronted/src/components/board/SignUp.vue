<template>
    <div>
        <h4>회원가입</h4>
        <form v-on:submit.prevent>
            <label>USER ID : </label>
            <input type="text" placeholder="ID를 입력해주세요" v-model="username">
            <button v-on:click="overlap()">중복확인</button>
            <label>USER PW : </label>
            <input type="password" placeholder="비밀번호를 입력해주세요" v-model="password">
            <p>현재 등록된 공지사항 목록들</p>

            <div class="noticeList">
                <div>
                    <span>선택한 공지사항</span>
                    <div v-for="notice, index in noticeList" v-bind:key="notice.id">
                        <span>{{ notice }}</span>
                        <i v-on:click="deleteItem(index)">X</i>
                    </div>
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
                    <!-- 학과 세부 사항 보류 -->
                    <!-- npm install vue-custom-scrollbar -->
                    <!-- <div class="scroll-wrapper">
                        <label>공지사항</label>
                        <vue-custom-scrollbar class="scroll-area"  :settings="settings" @ps-scroll-y="scrollHanle">
                            <ul v-for="noticeBoard in noticeBoardList" v-bind:key="noticeBoard.id">
                                <li>{{noticeBoard}}</li>
                            </ul>
                        </vue-custom-scrollbar>
                    </div> -->
            </div>
            <label>원하는 공지사항 목록(개발중)</label>
            <button v-on:click="onSubmit()">test button</button>
            
        </form>
    </div>
</template>

<script>
import axios from 'axios'
import vueCustomScrollbar from 'vue-custom-scrollbar'
import "vue-custom-scrollbar/dist/vueScrollbar.css"

export default {
    name: "SignUp",
    components: {
        vueCustomScrollbar
    },
    data() {
        return {
            username: "",
            password: "",
            noticeList: [],
            settings: {
                suppressScrollY: false,
                suppressScrollX: false,
                wheelPropagation: true,
            },
            noticeBoardList : [],
            usernameCheck: false,
        }
    },
    methods: {
        select: function(index) {
            console.log(index);
            this.noticeBoardList = this.$store.state.noticeBoard[index].department
            console.log(this.noticeBoardList);
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
            if(this.usernameCheck){
                const fd = new FormData()
                fd.append('username', this.username)
                fd.append('password', this.password)
                fd.append('noticeList', this.noticeList)
                try {
                    axios.post('/api/signup', fd).then((response) => {
                        console.log(response);
                        this.$router.push({name: 'Login'})
                    })
                } catch (error) {
                    throw new Error(error);
                }
            }
        },
        overlap: function() {
            try {
                axios.get('/api/overlap', {
                    params: {
                        username: this.username
                        }
                    }).then((res) => {
                    console.log(res.data);
                    if (res.data) {
                        alert("사용중인 아이디 입니다.")
                        this.usernameCheck = false
                    } else {
                        alert("사용 가능한 아이디 입니다.")
                        this.usernameCheck = true
                    }
                })
            } catch (error) {
                throw new Error(error);
            }
        }
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