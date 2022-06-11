<template>
    <div class="container">
        <center><img src="@/assets/webproducer.png"></center>
        <h2>웹 프로듀서</h2>
        <h3 v-for="content in working" v-bind:key="content.id">
            {{ content }}
        </h3>
        <ul>
            <li>
                <h3 style="color: green"> 적성 및 흥미</h3>
                <h4 v-for="content in working2" v-bind:key="content.id">
                    {{ content }}
                </h4>

            </li>
            <li>
                <h3 style="color: green;"> 관련 학과 </h3>
                <h4>{{ department }}</h4>
            </li>
            <li>
                <h3 style="color: green;"> 유사직업명 </h3>
                <h4>{{ job }}</h4>
            </li>
            <li>
                <h3 style="color: green;"> 필요 자격증 </h3>
                <h4>인터넷 정보 관리사 : 한국정보통신진흥협회 주최 &nbsp; <a href="https://www.kait.or.kr/">보러가기</a></h4>
                <h4>OCP, SCJP(1) : 국제 자격증으로, 오라클사에서 주최&nbsp; <a href="https://home.pearsonvue.com/oracle">보러가기</a></h4><br>
                <h4>더 자세히 보려면? &nbsp;<a
                href="https://www.career.go.kr/cnet/front/base/job/jobView.do?SEQ=1096#tab1"
                target="_blank"
                >
                커리어넷에서 자세히 보기
                </a></h4>
                <h5>정보출처: 커리어넷</h5>
            </li>
        </ul>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'JobBoard',
    data () {
        return {
            job : "",
            department: "",
            working: [],
            working2: [],
        }
    },
    methods: {
        getJob: function() {
            axios.get("api/job", {
                params: {
                    job: "Test"
                }
            }).then((response) => {
                this.job = response.data.job
                this.department = response.data.department
                this.working = response.data.working.split(".")
                this.working2 = response.data.working2.split(".")
                console.log(response.data)
            })
        }
    },
    mounted() {
        this.getJob();
    }
}
</script>

<style>
    h2{
      font-weight: bolder;
      font-size: 35px;
      color: #371beb;
      text-align: center;
    }
    h3{
      font-weight: bold;
    }
    h4{
      font-weight: normal;
      font-size: 15px;
    }
    h5{
      text-align: right;
    }
    img{
      width: 500px;
      height: 350px;

    }
</style>