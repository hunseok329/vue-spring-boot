<template>
    <div>
        <h4>회원가입</h4>
        <form>
            <label>USER ID : </label>
            <input type="text" placeholder="ID를 입력해주세요">
            <label>USER PW : </label>
            <input type="password" placeholder="비밀번호를 입력해주세요">
            <p>현재 등록된 공지사항 목록들</p>
            <label>원하는 공지사항 목록(개발중)</label>
        </form>
    </div>
</template>

<script>
export default {
    name: "Join",
    data() {
        return {
            username: "",
            password: "",
            noticeList: []
        }
    }
}
</script>

<style>

</style>