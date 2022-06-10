package com.example.vuespringboot.service;

import com.example.vuespringboot.dao.NoticeRepository;
import com.example.vuespringboot.dao.UserRepository;
import com.example.vuespringboot.entity.NoticeList;
import com.example.vuespringboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SignupUserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;

    public SignupUserService(UserRepository userRepository, NoticeRepository noticeRepository) {
        this.userRepository = userRepository;
        this.noticeRepository = noticeRepository;
    }
    public void SignupUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String noticeList = request.getParameter("noticeList");

        ArrayList<String> noticeArrayList = new ArrayList<String>();
        password = passwordEncoder.encode(password);

        System.out.println("this is user ud : " + username);
        System.out.println("this, is user password : " + password);
        System.out.println("this is noticeLsit : " + noticeList);
        System.out.println(noticeList.getClass().getName());

        if (!noticeList.isEmpty()){
            long cnt = countChar(noticeList, ',');
            if(cnt == 0){
                //noticeList item is 1
                noticeArrayList.add(noticeList);

            } else {
                //noticeList items
                String[] arrList = noticeList.split(",");
                for (String arr : arrList) {
                    noticeArrayList.add(arr);
                }
                System.out.println("Array list ");
                System.out.println(noticeArrayList);
            }

        } else {
            //noticeList is null
            System.out.println(noticeArrayList);
        }

        //테이블에 데이터 입력
        //user table save
        User user = new User(username, password, "USER", "");
        userRepository.save(user);

        //notice_list table save
        for (String notice_item : noticeArrayList) {
            NoticeList notice = new NoticeList(username, notice_item, getURL(notice_item));
            noticeRepository.save(notice);
            System.out.println("getURL 호출");
        }
    }

    public static long countChar(String str, char ch) {
        return str.chars()
                .filter(c -> c == ch)
                .count();
    }


    //이후에 DB로 이동
    public static String getURL(String department) {
        HashMap<String, String> url_map = new HashMap<>();
        url_map.put("자유전공학부", "https://udm.daegu.ac.kr/hakgwa_home/udm/sub.php?menu=page&menu_id=20");
        url_map.put("한국어교육학부", "https://dukorean.daegu.ac.kr/hakgwa_home/dukorean/sub.php?menu=page&menu_id=15");
        url_map.put("중국어중국학전공", "https://chinese.daegu.ac.kr/hakgwa_home/chinese/sub.php?menu=page&menu_id=30");
        url_map.put("일본어일본학전공", "https://japanese.daegu.ac.kr/hakgwa_home/japanese/sub.php?menu=page&menu_id=30");
        url_map.put("영어영문학전공", "https://english.daegu.ac.kr/hakgwa_home/english/sub.php?menu=page&menu_id=30");
        url_map.put("문화예술학부", "https://doca.daegu.ac.kr/hakgwa_home/doca/sub.php?menu=page&menu_id=1009");
        url_map.put("체육학과", "https://physical-education.daegu.ac.kr/hakgwa_home/physical-education/sub.php?menu=page&menu_id=30");
        url_map.put("스포츠레저학과", "https://sportsleisure.daegu.ac.kr/hakgwa_home/sportsleisure/sub.php?menu=page&menu_id=30");
        url_map.put("법학부", "https://law.daegu.ac.kr/hakgwa_home/law/sub.php?menu=page&menu_id=15");
        url_map.put("행정학과", "https://padep.daegu.ac.kr/hakgwa_home/padep/sub.php?menu=page&menu_id=20");
        url_map.put("경찰학부", "https://police.daegu.ac.kr/hakgwa_home/police/sub.php?menu=page&menu_id=30");
        url_map.put("부동산·지적학과", "https://realty.daegu.ac.kr/hakgwa_home/realty/sub.php?menu=page&menu_id=17");
        url_map.put("군사학과", "https://millitary-science.daegu.ac.kr/hakgwa_home/millitary-science/sub.php?menu=page&menu_id=1039");
        url_map.put("경영학전공", "https://dubiz.daegu.ac.kr/hakgwa_home/dubiz/sub.php?menu=page&menu_id=1004");
        url_map.put("회계학전공", "https://acta.daegu.ac.kr/hakgwa_home/acta/sub.php?menu=page&menu_id=30");
        url_map.put("(구)무역학과전공", "https://trade.daegu.ac.kr/hakgwa_home/trade/sub.php?menu=page&menu_id=25");
        url_map.put("경제금융전공", "https://economics.daegu.ac.kr/hakgwa_home/economics/sub.php?menu=page&menu_id=30");
        url_map.put("금융보험전공", "https://bogum.daegu.ac.kr/hakgwa_home/bogum/sub.php?menu=page&menu_id=30");
        url_map.put("호텔관광전공", "https://hotel.daegu.ac.kr/hakgwa_home/hotel/sub.php?menu=page&menu_id=30");
        url_map.put("관광경영전공", "https://tourismmanage.daegu.ac.kr/hakgwa_home/tourismmanage/sub.php?menu=page&menu_id=30");
        url_map.put("사회학과", "https://sociology.daegu.ac.kr/hakgwa_home/sociology/sub.php?menu=page&menu_id=26");
        url_map.put("국제관계학과", "https://international.daegu.ac.kr/hakgwa_home/international/sub.php?menu=page&menu_id=26");
        url_map.put("미디어커뮤니케이션학과", "https://comm.daegu.ac.kr/hakgwa_home/comm/sub.php?menu=page&menu_id=26");
        url_map.put("심리학과", "https://psychology.daegu.ac.kr/hakgwa_home/psychology/sub.php?menu=page&menu_id=26");
        url_map.put("문헌정보학과", "https://lis.daegu.ac.kr/hakgwa_home/lis/sub.php?menu=page&menu_id=26");
        url_map.put("아동가정복지학과", "https://familywelfare.daegu.ac.kr/hakgwa_home/familywelfare/sub.php?menu=page&menu_id=26");
        url_map.put("청소년상담복지학과", "https://youth.daegu.ac.kr/hakgwa_home/youth/sub.php?menu=page&menu_id=30");
        url_map.put("사회복지학과", "https://dsw.daegu.ac.kr/hakgwa_home/dsw/sub.php?menu=page&menu_id=30");
        url_map.put("지역사회개발·복지학과", "https://cdwelfare.daegu.ac.kr/hakgwa_home/cdwelfare/sub.php?menu=page&menu_id=30");
        url_map.put("빅데이터학과", "https://stat.daegu.ac.kr/hakgwa_home/stat/sub.php?menu=page&menu_id=26");
        url_map.put("나노화학전공", "https://chemistry.daegu.ac.kr/hakgwa_home/chemistry/sub.php?menu=page&menu_id=1058");
        url_map.put("생명과학전공", "https://bio.daegu.ac.kr/hakgwa_home/bio/sub.php?menu=page&menu_id=26");
        url_map.put("원예학전공", "https://horticulture.daegu.ac.kr/hakgwa_home/horticulture/sub.php?menu=page&menu_id=15");
        url_map.put("바이오산업전공", "https://bioind.daegu.ac.kr/hakgwa_home/bioind/sub.php?menu=page&menu_id=30");
        url_map.put("동물자원학과", "https://animal.daegu.ac.kr/hakgwa_home/animal/sub.php?menu=page&menu_id=14");
        url_map.put("산림자원학과", "https://forest.daegu.ac.kr/hakgwa_home/forest/sub.php?menu=page&menu_id=15");
        url_map.put("반려동물산업학과", "https://pets.daegu.ac.kr/hakgwa_home/pets/sub.php?menu=page&menu_id=1011");
        url_map.put("건축공학과", "https://arch.daegu.ac.kr/hakgwa_home/arch/sub.php?menu=page&menu_id=30");
        url_map.put("건설시스템공학과", "https://civileng.daegu.ac.kr/hakgwa_home/civileng/sub.php?menu=page&menu_id=30");
        url_map.put("환경기술공학과", "https://enveng.daegu.ac.kr/hakgwa_home/enveng/sub.php?menu=page&menu_id=30");
        url_map.put("기계공학부", "https://mechanical.daegu.ac.kr/hakgwa_home/mechanical/sub.php?menu=page&menu_id=30");
        url_map.put("식품공학과", "https://foodengr.daegu.ac.kr/hakgwa_home/foodengr/sub.php?menu=page&menu_id=30");
        url_map.put("식품영양학과", "https://fn.daegu.ac.kr/hakgwa_home/fn/sub.php?menu=page&menu_id=30");
        url_map.put("생명공학과", "https://biotech.daegu.ac.kr/hakgwa_home/biotech/sub.php?menu=page&menu_id=30");
        url_map.put("화학공학과", "https://cheers.daegu.ac.kr/hakgwa_home/cheers/sub.php?menu=page&menu_id=30");
        url_map.put("조경학과", "https://landscape.daegu.ac.kr/hakgwa_home/landscape/sub.php?menu=page&menu_id=30");
        url_map.put("신소재에너지공학전공", "https://mese.daegu.ac.kr/hakgwa_home/mese/sub.php?menu=page&menu_id=30");
        url_map.put("에너지시스템공학전공", "https://energy.daegu.ac.kr/hakgwa_home/energy/sub.php?menu=page&menu_id=1025");
        url_map.put("기계융복합공학과", "https://ime.daegu.ac.kr/hakgwa_home/ime/sub.php?menu=page&menu_id=27");
        url_map.put("전자공학전공", "https://ee.daegu.ac.kr/hakgwa_home/ee/sub.php?menu=page&menu_id=30");
        url_map.put("전기공학전공", "https://econtrol.daegu.ac.kr/hakgwa_home/econtrol/sub.php?menu=page&menu_id=30");
        url_map.put("정보통신공학전공", "https://ict.daegu.ac.kr/hakgwa_home/ict/sub.php?menu=page&menu_id=30");
        url_map.put("컴퓨터공학전공", "https://ce.daegu.ac.kr/hakgwa_home/ce/sub.php?menu=page&menu_id=30");
        url_map.put("컴퓨터소프트웨어전공", "https://sw.daegu.ac.kr/hakgwa_home/sw/sub.php?menu=page&menu_id=30");
        url_map.put("정보보호전공", "https://infosec.daegu.ac.kr/hakgwa_home/infosec/sub.php?menu=page&menu_id=1009");
        url_map.put("IT융합학과", "https://itcon.daegu.ac.kr/hakgwa_home/itcon/sub.php?menu=page&menu_id=28");
        url_map.put("메카트로닉스공학과", "https://mecha.daegu.ac.kr/hakgwa_home/mecha/sub.php?menu=page&menu_id=35");
        url_map.put("아트앤디자인전공", "https://dufineart.cafe24.com/bbs/board.php?bo_table=m4s1");
        url_map.put("영상애니매이션디자인학전공", "http://dgani.co.kr/board");
        url_map.put("생활조형디자인학전공", "https://pad.daegu.ac.kr/hakgwa_home/pad/sub.php?menu=page&menu_id=27");
        url_map.put("시각디자인전공", "https://visual.daegu.ac.kr/hakgwa_home/visual/sub.php?menu=page&menu_id=15");
        url_map.put("서비스디자인전공", "https://servicedesign.daegu.ac.kr/hakgwa_home/servicedesign/sub.php?menu=page&menu_id=1009");
        url_map.put("산업디자인학과", "http://didshow.daegu.ac.kr/default/COMMUNITY/sub1.php");
        url_map.put("패션디자인학과", "https://dfd.daegu.ac.kr/hakgwa_home/dfd/sub.php?menu=page&menu_id=27");
        url_map.put("실내건축디자인학과", "https://dhid.daegu.ac.kr/hakgwa_home/dhid/sub.php?menu=page&menu_id=27");
        url_map.put("국어교육과", "https://koredu.daegu.ac.kr/hakgwa_home/koredu/sub.php?menu=page&menu_id=27");
        url_map.put("영어교육과", "https://engedu.daegu.ac.kr/hakgwa_home/engedu/sub.php?menu=page&menu_id=27");
        url_map.put("역사교육과", "https://hisedu.daegu.ac.kr/hakgwa_home/hisedu/sub.php?menu=page&menu_id=25");
        url_map.put("일반사회교육과", "https://socialstudies.daegu.ac.kr/hakgwa_home/socialstudies/sub.php?menu=page&menu_id=25");
        url_map.put("지리교육과", "https://geoedu.daegu.ac.kr/hakgwa_home/geoedu/sub.php?menu=page&menu_id=15");
        url_map.put("유아교육과", "https://kidedu.daegu.ac.kr/hakgwa_home/kidedu/sub.php?menu=page&menu_id=15");
        url_map.put("특수교육과", "https://se.daegu.ac.kr/hakgwa_home/se/sub.php?menu=page&menu_id=15");
        url_map.put("초등특수교육과", "https://specialcho.daegu.ac.kr/hakgwa_home/specialcho/sub.php?menu=page&menu_id=15");
        url_map.put("유아특수교육과", "https://ecse.daegu.ac.kr/hakgwa_home/ecse/sub.php?menu=page&menu_id=15");
        url_map.put("수학교육과", "https://mathedu.daegu.ac.kr/hakgwa_home/mathedu/sub.php?menu=page&menu_id=15");
        url_map.put("물리교육과", "https://physed.daegu.ac.kr/hakgwa_home/physed/sub.php?menu=page&menu_id=30");
        url_map.put("화학교육과", "https://h2o.daegu.ac.kr/hakgwa_home/h2o/sub.php?menu=page&menu_id=1026");
        url_map.put("생물교육과", "https://bioedu.daegu.ac.kr/hakgwa_home/bioedu/sub.php?menu=page&menu_id=30");
        url_map.put("지구과학교육과", "https://earth.daegu.ac.kr/hakgwa_home/earth/sub.php?menu=page&menu_id=18");
        url_map.put("교직부", "https://dtp.daegu.ac.kr/hakgwa_home/dtp/sub.php?menu=page&menu_id=30");
        url_map.put("직업재활학과", "https://jobrh.daegu.ac.kr/hakgwa_home/jobrh/sub.php?menu=page&menu_id=25");
        url_map.put("언어치료학과", "https://daeguslp.daegu.ac.kr/hakgwa_home/daeguslp/sub.php?menu=page&menu_id=26");
        url_map.put("재활심리학과", "https://jaesim.daegu.ac.kr/hakgwa_home/jaesim/sub.php?menu=page&menu_id=26");
        url_map.put("재활공학과", "https://rst.daegu.ac.kr/hakgwa_home/rst/sub.php?menu=page&menu_id=26");
        url_map.put("물리치료학과", "https://lovept.daegu.ac.kr/hakgwa_home/lovept/sub.php?menu=page&menu_id=26");
        url_map.put("작업치료학과", "https://duot.daegu.ac.kr/hakgwa_home/duot/sub.php?menu=page&menu_id=12");
        url_map.put("재활건강증진학과", "https://rehab-health.daegu.ac.kr/hakgwa_home/rehab-health/sub.php?menu=page&menu_id=30");
        url_map.put("간호학과", "https://nursing.daegu.ac.kr/hakgwa_home/nursing/sub.php?menu=page&menu_id=12");
        url_map.put("보건행정학과(야간)", "https://dha.daegu.ac.kr/hakgwa_home/dha/sub.php?menu=page&menu_id=21");
        url_map.put("AI학부", "https://ai.daegu.ac.kr/hakgwa_home/ai/sub.php?menu=page&menu_id=1011");
        url_map.put("미래융합학부", "https://mirae.daegu.ac.kr/hakgwa_home/mirae/sub.php?menu=page&menu_id=30");
        url_map.put("실버복지상담학전공", "https://mirae.daegu.ac.kr/hakgwa_home/mirae/sub.php?menu=page&menu_id=30");
        url_map.put("자산관리·6차산업학전공", "https://mirae.daegu.ac.kr/hakgwa_home/mirae/sub.php?menu=page&menu_id=30");
        url_map.put("평생교육·청소년학전공", "https://mirae.daegu.ac.kr/hakgwa_home/mirae/sub.php?menu=page&menu_id=30");

        System.out.println(url_map.get(department));
        return url_map.get(department);
    }
}
