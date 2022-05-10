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
      tests: []
    };
  },
  methods: {
    onClick() {
      console.log(this.msg, "버튼");
    },
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
      this.tests = [];
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
