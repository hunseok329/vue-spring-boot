<template>
    <div class="Signup_wrapper">
                <!-- 대구대 이미지로 변경 -->
        <router-link to="/"><img  class="loginIcon" src="../../assets/daegu.jpg" alt="daugu" width="60"></router-link>
        <h4 class="wrapper_h4">회원가입</h4>
        <form v-on:submit.prevent>
            <p><label>USER ID : </label>
            <input class="signup_input" type="text" placeholder="ID를 입력해주세요" v-model="username">
            <button class="button" v-on:click="overlap()">중복확인</button></p>
            <div class="d"><label>USER PW : </label>
            <input class="signup_input" type="password" placeholder="비밀번호를 입력해주세요" v-model="password"></div>
            <p>현재 등록된 공지사항 목록들</p>

            <div class="noticeList">
                <div>
                    <p>선택한 공지사항</p>
                    <span v-for="notice, index in noticeList" v-bind:key="notice.id">
                        <button class="button-1"><span>{{ notice }}</span>
                        <i v-on:click="deleteItem(index)"><icon name="times-circle"></icon></i></button>
                    </span>
                </div>
                    <div class="scroll-wrapper">
                        <p><label>대학</label></p>
                        <vue-custom-scrollbar class="scroll-area"  :settings="settings">
                            <ul>
                                <li  v-for="item, index in this.$store.state.noticeBoard" v-bind:key='item.id'>
                                    <button class="button-2"><span v-on:click="select(index)">{{ item.name }}</span></button>
                                </li>
                            </ul>
                        </vue-custom-scrollbar>
                    </div>
                    <div class="scroll-wrapper">
                        <p><label>학사-학과</label></p>
                        <vue-custom-scrollbar class="scroll-area"  :settings="settings">
                            <ul>
                                <li v-for="notice in noticeBoardList" v-bind:key="notice.id">
                                    <button class="button-2"><span v-on:click="addList(notice)">{{ notice }}</span></button>
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
            <button class="button-3" v-on:click="onSubmit()">회원가입</button>
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
.Signup_wrapper {
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
.signup_input{
    padding: 0.1rem 1rem;
     background: #fff;
            border-radius: 53px;
            border: 2px solid #1e1b1b;
            color: #1e1b1b;
            font-weight: bold;
            letter-spacing: 1px;
            width: 200px;
            height: 20px;
    
}


.button{
          padding: 0.3rem 0.8rem;
          text-align: center;
          font-size: 13px;
          text-transform: uppercase;
          cursor: pointer;
          background: #0d0d0e;
          border-radius: 2px;
          border: none;
          color: #fff;
          font-weight: bold;
          letter-spacing: 0.4px;
          transition: 0.2s transform ease-out;
        }
        .button:hover {
        transform: scale(1.1);
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
.d{
    margin-right: 90px;
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