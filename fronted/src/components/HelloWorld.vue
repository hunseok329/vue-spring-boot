<template>
  <div class="hello">
    <div v-if="this.$store.state.loginSuccess">
      <p class="logout" v-on:click="logout()">로그아웃</p>
    </div>
    <div v-else>
      <p class="login" ><router-link to="/Login">로그인</router-link></p>
    </div>
    <div class="menubar_wrapper">
      <ul>

        <li class="block_white">
          <router-link to="/NoticeBoard">
          <sequential-entrance fromTop>
            <div class="menubar_item_wrapper">
              <img alt="notiveBoard" src="../assets/n.png" class="menu_img">
              <div>통합게시판</div>
            </div>
            </sequential-entrance>
          </router-link>
        </li>
        
        <li class="block_green">
          <router-link to="/NoticeBoard">
          <sequential-entrance fromTop>
            <div class="menubar_item_wrapper">
              <img alt="info" src="../assets/t.png" class="menu_img">
              <div>팀원 모집 및 찾기</div>
            </div>
            </sequential-entrance>
          </router-link>
        </li>
        <li class="block_white">
          <router-link to="/">
          <sequential-entrance fromTop>
            <div class="menubar_item_wrapper">
              <img alt="noticeTeam" src="../assets/j.png" class="menu_img">
              <div>취업정보</div>
            </div>
            </sequential-entrance>
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import Header from './common/Header.vue';

export default {
  name: 'HelloWorld',
  components: {
    Header
  },
  beforeCreate() {
    console.log("beforeCreate");
  },
  data () {
    console.log("data");

    return {
    };
  },
  created() {
    console.log("Created");
  },
  methods: {
    logout: function() {
      this.$store.state.loginSuccess = false
    }
  },
  beforeMount(){
    console.log("beforeMount");
  },
  mounted(){
    console.log("mounted");
  },
  beforeUpdate(){
    console.log("beforeUpdate");
  },
  updated(){
    console.log("updated");
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.hello {
  height: 100%;
}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
  text-align: center;
}
li {
  display: inline-block;
  margin: 0 10px;
  padding-top: 10%;
  width: 30%;
  height: 100%;
}
a {
  text-decoration-line: none;
  color: #42b983;
  font-weight: bold;
}
.menubar_wrapper{
  height: 100%;
}
.menubar_wrapper ul {
  height: 100%;
}
.menu_img {
  width: 60%;
}
.login {
  text-align: right;
  padding-right: 5%;
}
.logout {
  text-align: right;
  padding-right: 5%;
  color: #42b983;
}
.menubar_item_wrapper {
  height: 100%;
  vertical-align: middle;
}
</style>
