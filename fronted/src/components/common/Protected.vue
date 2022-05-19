<template>
    <div class="protected">
        <h1><b-badge variant="success">Welcome to Protected Page!</b-badge></h1>
        <h5>당신은 인증된 사용자 입니다.</h5>
        <p><router-link to="/">home</router-link></p>
    </div>
</template>

<script>
export default {
	name: 'protected',
     data () {
        return {
        }
    },
    methods: {
    }
}
</script>

<style scoped>
footer{border-top:1px solid #35495e; text-align:center; font-size:16px; color:#41b883; margin:100px 0 0 0;}
</style>