<template>
    <div class="Login">
        <!-- 대구대 이미지로 변경 -->
        <router-link to="/"><img  class="loginIcon" src="../../assets/daegu.jpg" alt="daugu" width="60"></router-link>
        <div class="protected" v-if="loginSuccess">
            <h1>
                <b-badge variant="success">보안 사이트에 대한 액세스가 허용되었습니다</b-badge>
            </h1>
            <h5>로그인 성공!</h5>
        </div>
        <div class="unprotected" v-else-if="loginError">
            <div class="form-wrapper">
                <!-- <form @submit.prevent="login()">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="username" v-model="user">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="password" v-model="password">
                    </div>
                    <div class="login-button">
                        <button class="btn btn-primary" variant="success" type="submit">로그인</button>
                    </div>
                </form> -->
                <h5>실패</h5>
                <div class="signup">
                    회원가입
                </div>
            </div>
        </div>
        <div class="unprotected" v-else>
            <div class="form-wrapper">
                <form v-on:submit.prevent>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="username" v-model="user">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="password" v-model="password">
                    </div>
                    <div class="login-button">
                        <button class="btn btn-primary" variant="success" @click="login()">로그인</button>
                    </div>
                </form>
                <div class="signup">
                    <router-link to="/SignUp">회원가입</router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name: 'Login',
    data() {
        return {
            loginSuccess: false,
            loginError: false,
            user: '',
            password: '',
            error: false
        }
    },
    methods: {
        login: function() {
            try {
                this.$store.dispatch('login', {
                    user: this.user,
                    password: this.password
                }).then(() => {
                    if (this.$store.getters.isLoggedIn == true) {
                        console.log(this.$store.state.userName)
                        this.$router.push({name: "HelloWorld"})
                    } else {
                        this.$store.state.loginError = true
                    }
                })           
            } catch (err) {
                this.loginError = true
                this.error = true
                throw new Error(err)
            }
        }
    },
    mounted() {
        if (this.loginError) {
            alert("틀렸습니다.")
        }
    }
}
</script>

<style>
.Login {
    height: 100%;
    padding-top: 15%;
    margin: auto;
}
.loginIcon {
    margin: 0 auto;
}
form {
    padding: 4%;
}
.form-group {
    width: 350px;
    margin-left: auto;
    margin-right: auto;
    padding: 1%;
    size: 20px;
}
.form-control {
    width: 340px;
    height: 35px;
}
.btn.btn-primary{
    border:1px solid #03c75b;
    background-color: #03c75b;
    width:350px;
    height: 50px;
    margin-left:auto;
    margin-right:auto;
    color: white;
}
.login-button{
    text-align:center;
}
.form-wrapper{
    width: 350px;
    margin: 0 auto;
}
.signup {
    text-align: right;
}
a {
  text-decoration-line: none;
  color: #42b983;
  font-weight: bold;
}
</style>


