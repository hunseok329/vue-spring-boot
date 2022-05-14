<template>
  <div class="hello">
    <table>
      <tr v-for="test in tests" v-bind:key="test.id">
        <td>{{ test.head }}</td>
        <td>{{ test.title }}</td>
        <td>{{ test.author }}</td>
        <td>{{ test.date }}</td>
      </tr>
    </table>
    <button v-on:click="dataReset">get test</button>
      <div class="protected" v-if="loginSuccess">
          <h1>
              <b-badge variant="success">보안 사이트에 대한 액세스가 허용되었습니다</b-badge>
          </h1>
          <h5>로그인 성공!</h5>
      </div>
      <div class="unprotected" v-else-if="loginError">
          <h1>
              <b-badge variant="danger">이 페이지에 대한 접근 권한이 없습니다.</b-badge>
          </h1>
          <h5>로그인 실패!</h5>
      </div>
      <div class="unprotected" v-else>
          <h1>
              <b-badge variant="info">로그인해주세요</b-badge>
          </h1>
          <h5>로그인 하지 않았습니다. 로그인을 해주세요</h5>

          <form @submit.prevent="login()">
              <label>
                  <input type="text" placeholder="username" v-model="user">
              </label>
              <label>
                  <input type="password" placeholder="password" v-model="password">
              </label>
              <button variant="success" type="submit">Login</button>
              <p v-if="error" class="error">Bad login information</p>
          </form>
      </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'HelloWorld',
  beforeCreate() {
    console.log("beforeCreate");
  },
  data () {
    console.log("data");

    return {
      tests: [],
      loginSuccess: false,
      loginError: false,
      user: '',
      password: '',
      error: false
    };
  },
  created() {
    console.log("Created");
  },
  methods: {
    getData : function() {
      axios.get("/api/hello")
      .then((response) => {
        console.log(response.data);
        this.tests = response.data;
      })
    },
    dataReset : function() {
      this.tests = [mentos9615588];
    },
    async login() {
      try {
        const result = await axios.get('/api/login', {
          auth: {
            username: this.user,
            password: this.password
          }
        });
        if (result.status === 200) {
          this.loginSuccess = true
        }
      } catch (err) {
        this.loginError = true;
        throw new Error(err)
      }
    }
  },
  beforeMount(){
    console.log("beforeMount");
  },
  mounted(){
    console.log("mounted");
    this.getData();
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
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
