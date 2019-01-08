<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>Smatching Admin Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <style>
        .radio-inline+.radio-inline, .checkbox-inline+.checkbox-inline { margin-left: 0px; }
    </style>
</head>

<body>
<div class="container">         
    <div id="signupbox" style=" margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info" style="width:625px">
        
            <!-- 상단 폼 제목 -->
            <div class="panel-heading">
                <div class="panel-title" style="text-align: center">지원 사업 추가</div>
            </div>  
            
            <!-- 폼 내용 -->
            <div class="panel-body" >
                <form> <!-- 폼이 두번 들어가야 모양이 이쁨.. -->                        
                    <form class="form-horizontal" id="myform">
                    
                        <!-- 제목 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">지원사업 제목<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <input class="input-md textinput textInput form-control" name="title" placeholder="ex) 2019년 돈줘요줘 지원사업" style="margin-bottom: 10px" type="text" />
                            </div>
                        </div>

                        <!-- 기관 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">기관</label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <input class="input-md textinput textInput form-control" name="institution" placeholder="ex) 중소기업청" style="margin-bottom: 10px" type="text" />
                            </div>
                        </div>

                        <!-- 부서 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">부서</label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <input class="input-md textinput textInput form-control" name="part" placeholder="ex) 창업지원팀" style="margin-bottom: 10px" type="text" />
                            </div>
                        </div>

                        <!-- 전화번호 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">전화번호</label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <input class="input-md textinput textInput form-control" name="phone" placeholder="ex) 02-123-4567"  style="margin-bottom: 10px" type="tel" />
                            </div>
                        </div>

                        <!-- 공고일 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">공고일<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <input class="input-md textinput textInput form-control" name="reg_date" style="margin-bottom: 10px" type="date" max="9999-12-31" />
                            </div>
                        </div>

                        <!-- 모집시작일 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">모집시작일<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <input class="input-md textinput textInput form-control" name="start_date" style="margin-bottom: 10px" type="date" max="9999-12-31" />
                            </div>
                        </div>

                        <!-- 모집마감일 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">모집마감일<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <input class="input-md textinput textInput form-control" name="end_date" style="margin-bottom: 10px" type="date" max="9999-12-31" value="9999-12-31" />
                            </div>
                        </div>

                        <!-- 참고 URL -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">참고 URL</label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <input class="input-md textinput textInput form-control" name="refer_url" placeholder="ex) http://www.bizinfo.go.kr/see/seea/selectSEEA140Detail.do?pblancId=PBLN_000000000039743&menuId=80001001001"  style="margin-bottom: 10px" type="text" />
                            </div>
                        </div>

                        <!-- 원본 URL -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">원본 URL</label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <input class="input-md textinput textInput form-control" name="origin_url" placeholder="ex) http://www.at.or.kr/home/apko000000/index.action" style="margin-bottom: 10px" type="text" />
                            </div>
                        </div>



                        <!-- 기타 공고 여부 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">기타 공고 여부</label>
                            <div class="controls col-md-8" style="margin-bottom: 10px; padding-left: 35px;">
                                <label class="radio-inline" style="padding-right: 18px"> <input type="radio" name="notfit" id="notfitID" style="margin-bottom: 10px"> YES</label>
                                <label class="radio-inline"> <input type="radio" name="notfit" style="margin-bottom: 10px" checked="checked"> NO</label>
                            </div>
                        </div>

                        <!-- 지역 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">지역</span></label>
                            <div class="controls col-md-8" style="margin-bottom: 10px">
                                <label class="radio-inline" style="display: block; font-weight:bold; color: blue;"><input type="checkbox" name="locationselectall" style="margin-bottom: 10px"> 국내 전체</label>

                                <label class="radio-inline"><input type="checkbox" name="location" value="seoul" style="margin-bottom: 10px"> 서울</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="busan" style="margin-bottom: 10px"> 부산</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="daegu" style="margin-bottom: 10px"> 대구</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="incheon" style="margin-bottom: 10px"> 인천</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="gwangju" style="margin-bottom: 10px"> 광주</label>

                                <label class="radio-inline"><input type="checkbox" name="location" value="daejeon" style="margin-bottom: 10px"> 대전</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="ulsan" style="margin-bottom: 10px"> 울산</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="sejong" style="margin-bottom: 10px"> 세종</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="gangwon" style="margin-bottom: 10px"> 강원</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="kyunggi" style="margin-bottom: 10px"> 경기</label>

                                <label class="radio-inline"><input type="checkbox" name="location" value="kyungnam" style="margin-bottom: 10px"> 경남</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="kyungbuk" style="margin-bottom: 10px"> 경북</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="jeonnam" style="margin-bottom: 10px"> 전남</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="jeonbuk" style="margin-bottom: 10px"> 전북</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="chungnam" style="margin-bottom: 10px"> 충남</label>

                                <label class="radio-inline"><input type="checkbox" name="location" value="chungbuk" style="margin-bottom: 10px"> 충북</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="jeju" style="margin-bottom: 10px"> 제주</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="aborad" style="margin-bottom: 10px"> 국외</label>
                            </div>
                        </div>

                        <!-- 나이 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">나이</label>
                            <div class="controls col-md-8"  style="margin-bottom: 10px">
                                <label class="radio-inline" style="display: block; font-weight:bold;"><input type="checkbox" name="selectall" style="margin-bottom: 10px"> 전체</label>

                                <label class="radio-inline"> <input type="checkbox" name="age" value="twenty_less" style="margin-bottom: 10px"> 만 20세 미만</label>
                                <label class="radio-inline"> <input type="checkbox" name="age" value="twenty_forty" style="margin-bottom: 10px"> 만 20세 이상 ~ 39세 이하</label>
                                <label class="radio-inline"> <input type="checkbox" name="age" value="forty_more" style="margin-bottom: 10px"> 만 40세 이상</label>
                            </div>
                        </div>

                        <!-- 설립 경과 년수 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">설립 경과 년수</span></label>
                            <div class="controls col-md-8" style="margin-bottom: 10px">
                                <label class="radio-inline" style="display: block; font-weight:bold;"><input type="checkbox" name="selectall" style="margin-bottom: 10px"> 전체</label>

                                <label class="radio-inline"><input type="checkbox" name="period" value="zero_one" style="margin-bottom: 10px"> 0~1년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="one_two" style="margin-bottom: 10px"> 1~2년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="two_three" style="margin-bottom: 10px"> 2~3년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="three_four" style="margin-bottom: 10px"> 3~4년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="four_five" style="margin-bottom: 10px"> 4~5년</label>

                                <label class="radio-inline"><input type="checkbox" name="period" value="five_six" style="margin-bottom: 10px"> 5~6년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="six_seven" style="margin-bottom: 10px"> 6~7년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="seven_more" style="margin-bottom: 10px"> 7년 이상</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="yet" style="margin-bottom: 10px"> 예비창업자</label>
                            </div>
                        </div>

                        <!-- 기업 형태 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">기업 형태</span></label>
                            <div class="controls col-md-8" style="margin-bottom: 10px">
                                <label class="radio-inline" style="display: block; font-weight:bold;"><input type="checkbox" name="selectall" style="margin-bottom: 10px"> 전체</label>

                                <label class="radio-inline"><input type="checkbox" name="busiType" value="midsmall" style="margin-bottom: 10px"> 중소기업</label>
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="midbig" style="margin-bottom: 10px"> 중견기업</label>
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="big" style="margin-bottom: 10px"> 대기업</label>
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="sole" style="margin-bottom: 10px"> 1인창조기업</label>
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="small" style="margin-bottom: 10px"> 소상공인</label>

                                <label class="radio-inline"><input type="checkbox" name="busiType" value="tradi" style="margin-bottom: 10px"> 전통시장</label>
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="pre" style="margin-bottom: 10px"> 예비창업자</label>
                            </div>
                        </div>

                        <!-- 가능한 지원사업분야 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">가능한 지원사업분야</span></label>
                            <div class="controls col-md-8" style="margin-bottom: 10px">
                                <label class="radio-inline" style="display: block; font-weight:bold;"><input type="checkbox" name="selectall" style="margin-bottom: 10px"> 전체</label>

                                <label class="radio-inline"><input type="checkbox" name="category" value="loan" style="margin-bottom: 10px"> 무대출 자금 지원</label>
                                <label class="radio-inline"><input type="checkbox" name="category" value="edu" style="margin-bottom: 10px"> 창업교육,창업 멘토링</label>
                                <label class="radio-inline"><input type="checkbox" name="category" value="know" style="margin-bottom: 10px"> 지식재산권 (특허, 저작권 등)</label>
                                <label class="radio-inline"><input type="checkbox" name="category" value="global" style="margin-bottom: 10px"> 해외판로 확대</label>
                                <label class="radio-inline"><input type="checkbox" name="category" value="place" style="margin-bottom: 10px"> 시설,공간</label>

                                <label class="radio-inline"><input type="checkbox" name="category" value="make" style="margin-bottom: 10px"> 시제품 제작, 제조양산</label>
                                <label class="radio-inline"><input type="checkbox" name="category" value="local" style="margin-bottom: 10px"> 국내판로 확대</label>
                                <label class="radio-inline"><input type="checkbox" name="category" value="gov" style="margin-bottom: 10px"> 정부 대출 지원</label>
                            </div>
                        </div>


                        <!-- 업종 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">업종</span></label>
                            <div class="controls col-md-8" style="margin-bottom: 10px">
                                <label class="radio-inline" style="display: block; font-weight:bold;"><input type="checkbox" name="selectall" style="margin-bottom: 10px"> 전체</label>

                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="a" style="margin-bottom: 10px"> A 농업, 임업 및 어업 (01~03)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="b" style="margin-bottom: 10px"> B 광업 (05~08)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="c" style="margin-bottom: 10px"> C 제조업 (10~34)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="d" style="margin-bottom: 10px"> D 전기, 가스, 증기 및 공기 조절 공급업 (35)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="e" style="margin-bottom: 10px"> E 수도, 하수 및 폐기물 처리, 원료 재생업 (36~39)</label>

                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="f" style="margin-bottom: 10px"> F 건설업 (41~42)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="g" style="margin-bottom: 10px"> G 도매 및 소매업 (45~47)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="h" style="margin-bottom: 10px"> H 운수 및 창고업 (49~52)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="i" style="margin-bottom: 10px"> I 숙박 및 음식점업 (55~56)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="j" style="margin-bottom: 10px"> J 정보통신업 (58~63)</label>

                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="k" style="margin-bottom: 10px"> K 금융 및 보험업 (64~66)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="l" style="margin-bottom: 10px"> L 부동산업 (68)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="m" style="margin-bottom: 10px"> M 전문, 과학 및 기술 서비스업 (70~73)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="n" style="margin-bottom: 10px"> N 사업시설 관리, 사업지원 및 임대서비스업 (74~76)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="o" style="margin-bottom: 10px"> O 공공 행정, 국방 및 사회보장 행정 (84)</label>

                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="p" style="margin-bottom: 10px"> P 교육 서비스업 (85)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="q" style="margin-bottom: 10px"> Q 보건업 및 사회복지 서비스업 (86~87)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="r" style="margin-bottom: 10px"> R 예술, 스포츠 및 여가관련 서비스업 (90~91)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="s" style="margin-bottom: 10px"> S 협회 및 단체, 수리 및 기타 개인 서비스업 (94~96)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="t" style="margin-bottom: 10px"> T 가구 내 고용활동 및 달리 분류되지 않은 자가 소비 생산활동 (97~98)</label>

                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="u" style="margin-bottom: 10px"> U 국제 및 외국기관 (99)</label>
                                <label class="radio-inline" style="display: block;"><input type="checkbox" name="field" value="v" style="margin-bottom: 10px"> V 4차산업분야</label>
                            </div>
                        </div>

                        <!-- 우대사항 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">우대사항</span></label>
                            <div class="controls col-md-8" style="margin-bottom: 10px">
                                <label class="radio-inline" style="display: block; font-weight:bold;"><input type="checkbox" name="selectall" style="margin-bottom: 10px"> 전체</label>

                                <label class="radio-inline"><input type="checkbox" name="advantage" value="retry" style="margin-bottom: 10px"> 재도전기업</label>
                                <label class="radio-inline"><input type="checkbox" name="advantage" value="woman" style="margin-bottom: 10px"> 여성기업</label>
                                <label class="radio-inline"><input type="checkbox" name="advantage" value="disabled" style="margin-bottom: 10px"> 장애인기업</label>
                                <label class="radio-inline"><input type="checkbox" name="advantage" value="social" style="margin-bottom: 10px"> 사회적기업</label>
                                <label class="radio-inline"><input type="checkbox" name="advantage" value="sole" style="margin-bottom: 10px"> 1인창조기업</label>

                                <label class="radio-inline"><input type="checkbox" name="advantage" value="fourth" style="margin-bottom: 10px"> 4차산업관련기업</label>
                                <label class="radio-inline"><input type="checkbox" name="advantage" value="univ" style="margin-bottom: 10px"> 대학(원)생</label>
                                <label class="radio-inline"><input type="checkbox" name="advantage" value="togather" style="margin-bottom: 10px"> 공동창업</label>
                            </div>
                        </div>


                        <!-- 사업요약 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">사업요약</span></label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <textarea class="form-control" name="detail_one" rows="5" style="margin-bottom: 10px"></textarea>
                            </div>
                        </div>

                        <!-- 지원대상 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">지원대상</span></label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <textarea class="form-control" name="detail_two" rows="5" style="margin-bottom: 10px"></textarea>
                            </div>
                        </div>

                        <!-- 지원내용 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">지원내용</span></label>
                            <div class="controls col-md-8" style="padding-left: 35px;">
                                <textarea class="form-control" name="detail_three" rows="5" style="margin-bottom: 10px"></textarea>
                            </div>
                        </div>

                        <!-- 추가하기 버튼-->
                        <div class="form-group">
                            <div class="controls col-md-8" style="text-align: center;">
                                <input type="button" value="추가하기" class="btn btn btn-primary" style="width:200px; margin-top:30px;" onclick="sendNotice()"/>
                            </div>
                        </div> 
                            
                    </form>

                </form>
            </div>
        </div>
    </div> 
</div>
    

<script type="text/javascript">
    // $(document).ready(function() { });

    var multipleOptions = ["location", "age", "period", "field", "advantage", "busiType", "category"];
    var textOptions = ["title", "institution", "end_date", "reg_date", "start_date", "phone", "refer_url", "origin_url", "part"];
    var longTextOptions = ["detail_one", "detail_two", "detail_three"];


    // 전체 체크박스 작동 설정
    $("input[name=selectall]").click(function() {
        $(this).parent().parent().find("input").not("input[name=selectall]").prop('checked', this.checked);
    });


    // 전체가 아닌 나머지가 체크 해제되면 전체도 체크 뺌 (지역 제외)
    $("input:checkbox").not("input[name=selectall]").not("input[name=location]").not("input[name=locationselectall]").click(function() {
        if(!(this.checked)) {
            $(this).parent().parent().find("input[name=selectall]").prop('checked', false);
        }
    });

    // 국내 전체 체크박스 작동 설정 (지역)
    $("input[name=locationselectall]").click(function() {
        $(this).parent().parent().find("input").not("input[name=locationselectall]").not("input[value=aborad]").prop('checked', this.checked);
    });

    // 국내 전체가 아닌 나머지가 체크 해제되면 전체도 체크 뺌 (지역)
    $("input[name=location]").not("input[value=aborad]").click(function() {
        if(!(this.checked)) {
            $(this).parent().parent().find("input[name=locationselectall]").prop('checked', false);
        }
    });




    function makeMapFromMultipleOptions(option_name) {
        var result = {};
        $("input:checkbox[name="+ option_name +"]").each(function() {
            result[this.value] = this.checked;
        });
        return result;
    }

    function getValueFromTextOptions(option_name) {
        return $("input[name=" + option_name + "]").val();
    }

    function getValueFromLongTextOptions(option_name) {
        return $("textarea[name=" + option_name + "]").val();
    }


    function sendNotice() {
        if(confirm("입력한 정보로 지원사업 공고를 등록하시겠습니까?") == true) {
            var result = {};
            var flag = false;

            $(".asteriskField").each(function() {
                if($(this).parent().parent().find("input").val() == '') {
                    flag = true;
                }
            });
            if(flag) {
                alert("필수항목을 모두 입력해주세요.")
                return;
            }

            for(var i=0; i<multipleOptions.length; i++) {
                result[multipleOptions[i]] = makeMapFromMultipleOptions(multipleOptions[i]);
            }

            for(var i=0; i<textOptions.length; i++) {
                result[textOptions[i]] = getValueFromTextOptions(textOptions[i]);
            }

            for(var i=0; i<textOptions.length; i++) {
                result[longTextOptions[i]] = getValueFromLongTextOptions(longTextOptions[i]);
            }

            result["notfit"] = $("#notfitID").is(':checked');


            $.ajax({
                url : location.href, // 현재 페이지의 URL과 쿼리스트링까지 그대로. (location.search 하면 쿼리스트링만 나옴)
                type : "POST",
                data : JSON.stringify(result),
                contentType: 'application/json; charset=UTF-8',
                dataType: 'html',
                error : function() {
                    alert("추가 실패 - Ajax Error");
                },
                success : function(response) {
                    var response_code = (JSON.parse(response)["status"]);
                    if(response_code == 201) {
                        alert("성공적으로 추가하였습니다.");
                        location.reload();
                    }
                    else {
                        alert("추가 실패 - Code: " + response_code);
                    }

                }
            })
        }

        else {
            return;
        }
    }


</script>
</body>
</html>
