<template>
	<header>
		<h1><router-link to="/" class="logo"><img alt="Vue logo" src="../../assets/daegu.jpg" width="80"></router-link></h1>
		<div class="menuWrap">
			<ul class="menu" v-if="check">
				<li><router-link to="/NoticeBoard">통합 게시판</router-link></li>
				<li><router-link to="/">취업 정보</router-link></li>
				<li><router-link to="/">팀원 찾기</router-link></li>
				<li><router-link to="/">로그아웃</router-link></li>
				<li><router-link to="/">마이페이지</router-link></li>
			</ul>
			<ul class="menu" v-else>
				<li><router-link to="/NoticeBoard">통합 게시판</router-link></li>
				<li><router-link to="/">취업 정보</router-link></li>
				<li><router-link to="/">팀원 찾기</router-link></li>
				<li><router-link to="/Login">로그인</router-link></li>
				<li><router-link to="/SignUp">회원가입</router-link></li>
			</ul>
		</div>
	</header>
</template>

<script>
import store from '../../store'
export default {
	name: 'Header',
	data() {
		return {
			check: store.state.loginSuccess //vuex에서 로그인 상태를 받아와서 저장
		}
	}
}
</script>

<style scoped>
header{width:100%; text-align:center; position:relative; height:120px; border-bottom:1px solid #35495e}
header h1{position:absolute; top:0; left:100px;}
header ul.menu:after{display:block; clear:both; content:'';}
header ul.menu{position:absolute; top:20px; right:50px;}
header ul.menu li{float:left; padding:10px 20px; list-style:none;}

a{text-decoration:none; color:#333;}
</style>