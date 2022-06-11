<template>
    <div class="mypage_wrapper">
                <!-- 대구대 이미지로 변경 -->
        <router-link to="/"><img  class="loginIcon" src="../../assets/daegu.jpg" alt="daugu" width="60"></router-link>
        <h2>마이페이지</h2>
        <div>아이디 : {{ username }}</div>
        <p>공지사항 목록</p>
        <div class="noticeList">
            <div>
                <div>
                    <p>선택된 공지사항</p>
                    <span v-for="notice, index in noticeList" v-bind:key="notice.id">
                        <button class="button-1"><span>{{ notice }}</span>
                        <i v-on:click="deleteItem(index)"><icon name="times-circle"></icon></i></button>
                    </span>
                </div>
                <div class="scroll-wrapper">
                    <label>대학</label>
                    <vue-custom-scrollbar class="scroll-area"  :settings="settings">
                        <ul>
                            <li  v-for="item, index in this.$store.state.noticeBoard" v-bind:key='item.id'>
                                <button class="button-2"><span v-on:click="select(index)">{{ item.name }}</span></button>
                            </li>
                        </ul>
                    </vue-custom-scrollbar>
                </div>
                <div class="scroll-wrapper">
                    <label>학사-학과</label>
                    <vue-custom-scrollbar class="scroll-area"  :settings="settings">
                        <ul>
                            <li v-for="notice in noticeBoardList" v-bind:key="notice.id">
                                <button class="button-2"><span v-on:click="addList(notice)">{{ notice }}</span></button>
                            </li>
                        </ul>
                    </vue-custom-scrollbar>
                </div>
            </div>
            <button class="button-3" v-on:click="onSubmit()">수정</button>  
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
.mypage_wrapper{
    padding-top: 10%;
    padding-bottom: 6%;   
}
.scroll-wrapper {
    display: inline-block;
}
.scroll-area {
    position: relative;
    margin: auto;
    width: 400px;
    height: 200px;
}
ul{
    list-style:none;
}
.button-1{
        padding: 0.2rem 1rem;
        text-align: center;
        font-size: 15px;
        text-transform: uppercase;
        background: #fff;
        border-radius: 53px;
        border: 1px solid #e8e8e8;
        color: #1e1b1b;
        font-weight: bold;
        letter-spacing: 1px;
        box-shadow: 0px 4px 16px rgba(154, 154, 154, 0.31);
        }
.button-2{  padding: 0.2rem 2.8rem;
            text-align: center;
            font-size: 15px;
            text-transform: uppercase;
            cursor: pointer;
            background: #efefef;
            border: 1px solid #e8e8e8;
            color: #1e1b1b;
            font-weight: bold;
            letter-spacing: 1px;
            border-radius: 5px;}
.times-circle{
        cursor: pointer;
        width: 17px;
        height: 17px;
}
.button-3{
        padding: 0.5rem 1.2rem;
        background: #909090;
        text-align: center;
        font-size: 12px;
        text-transform: uppercase;
        cursor: pointer;
        border: none;
        color: rgb(255, 255, 255);
        font-weight: bold;
        letter-spacing: 0.5px;
        border-radius: 5px;
        transition: 0.3s linear;
        position: relative;
        z-index: 1;
        }
        
        .button-3::after {
        content: "";
        position: absolute;
        width: 100%;
        height: 100%;
        top: 0;
        left: 0;
        background: rgb(32, 32, 32);
        transform: scaley(0);
        border-radius: 5px;
        transform-origin: bottom;
        transition: transform 200ms ease-in;
        z-index: -1;
        }

        .button-3:hover::after {
        transform: scaley(1);
        }

</style>